package com.erchuinet.service.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.AbstractJedisTemplate;
import com.erchuinet.common.Config;
import com.erchuinet.common.MD5;
import com.erchuinet.common.ModelVo;
import com.erchuinet.common.SendMs;
import com.erchuinet.rong.RongCloud;
import com.erchuinet.rong.TokenResult;
import com.erchuinet.service.app.dao.UserMapper;
import com.erchuinet.service.app.model.User;


@SuppressWarnings("rawtypes")
@Service
public class UserServiceImpl extends AbstractJedisTemplate<String, String>implements UserService {
	@Autowired
	private UserMapper userMapper;

	// 存储数据
	public boolean add(final User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(String.valueOf(user.getId()));
				byte[] name = serializer.serialize(user.getUsername());

				return connection.setNX(key, name);
			}
		});
		return result;
	}

	/**
	 * 批量新增
	 * 
	 * @param list
	 * @return
	 */
	public boolean add(final List<User> list) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				for (User user : list) {
					byte[] key = serializer.serialize(String.valueOf(user.getId()));
					byte[] name = serializer.serialize(user.getUsername());
					connection.setNX(key, name);
				}
				return true;
			}
		}, false, true);
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 */
	public void delete(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);
	}

	/**
	 * 删除多个
	 * 
	 * @param keys
	 */
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(final User user) {
		String key = String.valueOf(user.getId());
		if (get(key) == null) {
			throw new NullPointerException("数据行不存在, key = " + key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(String.valueOf(user.getId()));
				byte[] name = serializer.serialize(user.getUsername());
				connection.set(key, name);
				return true;
			}
		});
		return result;
	}

	/**
	 * 通过key获取
	 * 
	 * @param keyId
	 * @return
	 */
	public User get(final String keyId) {
		User result = redisTemplate.execute(new RedisCallback<User>() {
			public User doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String name = serializer.deserialize(value);
				return new User(Integer.parseInt(String.valueOf(key)), name);
			}
		});
		return result;
	}

	@Override
	public String saveUserRegister(Map map) {
		userMapper.userRegister(map);
		ModelVo vo = new ModelVo();
		String result=map.get("result").toString();
		try {
			 if (result.equals("2")) {
				vo.setRetcode("400");
				vo.setRetmsg("手机号已注册");
			} else if(result.equals("0")) {
				vo.setRetmsg("注册失败");
				vo.setRetcode("300");
				
			}else{
				RongCloud rongCloud = RongCloud.getInstance(Config.appKey, Config.appSecret);
				TokenResult tokenResult = rongCloud.user.getToken(result, "10000"+result, "http://www.rongcloud.cn/images/logo.png");
				if(tokenResult.getCode()==200) {
					userMapper.saveToken(tokenResult.getToken(), Integer.valueOf(result));
				}
				map = userMapper.queryUserInfo(map);
				vo.setData(map);
				vo.setRetmsg("注册成功");
				vo.setRetcode("200");
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}

		return JSONObject.toJSON(vo).toString();
	}

	@Override
	public void completeUserInfo(Map map) {
		userMapper.completeUserInfo(map);
	}

	@Override
	public Map<String, Object> userLogin(Map map) {
		Map<String, Object> res = new HashMap<>();
		// 密码登录逻辑 1是密码登录  2是验证码登录
		if (map.get("loginStyle").toString().equals("1")) {
			String  pwd= MD5.GetMD5Code( map.get("pwd").toString());
			map.put("pwd",pwd);
			 res = userMapper.loginByPwd(map);
			return res;
		} else if (map.get("loginStyle").toString().equals("2")) {
			System.out.println("style1111111+:"+map.get("loginStyle") +"  phone : "+ map.get("phone").toString());
			 res = userMapper.loginByPwd(map);
			System.out.println("style+:"+map.get("loginStyle") +"  phone : "+ map.get("phone").toString() +"    res:" +res);
			return res;
		}
		return res;
	}

	@Override
	public String sendModifyMsg(Map map) {
		ModelVo vo = new ModelVo();
		Map<String, Object> result = new HashMap<>();
		try {

			String phone = map.get("phone").toString();
			int verificationCode = (int)((Math.random()*9+1)*1000);
			// 判断账号是否存在 如果存在返回验证码和用户id 如果不存在,返回其他
			int result1 = userMapper.findUserIsExist(map);
			if (map.get("flag").toString().equals("zhuce")) {
				if (result1 > 0) {
					vo.setRetcode("300");
					vo.setRetmsg("手机号已注册");
					return JSONObject.toJSON(vo).toString();
				} else {
					if(SendMs.sendMesage(phone,verificationCode)) {
						result.put("msgCode", String.valueOf(verificationCode));
						vo.setRetcode("200");
						vo.setRetmsg("验证码发送成功");
						vo.setData(result);
					}else {
						result.put("msgCode","");
						vo.setRetcode("400");
						vo.setRetmsg("验证码发送失败");
						vo.setData(result);
					}
				}

			} else if (map.get("flag").toString().equals("zhaohui") || map.get("flag").toString().equals("denglu")) {
				if (result1 > 0) {
					if(SendMs.sendMesage(phone,verificationCode)) {
						result.put("msgCode", String.valueOf(verificationCode));
						vo.setRetcode("200");
						vo.setRetmsg("验证码发送成功");
						vo.setData(result);
					}else {
						result.put("msgCode","");
						vo.setRetcode("400");
						vo.setRetmsg("验证码发送失败");
						vo.setData(result);
					}
				} else {
					vo.setRetcode("300");
					vo.setRetmsg("手机号已注册");
					return JSONObject.toJSON(vo).toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		return JSONObject.toJSON(vo).toString();
	}

	@Override
	public Boolean saveUserPwd(Map map) {
		return userMapper.saveUserPwd(map);
	}

	@Override
	public Map<String, Object> recruitSetting(Map map) {
		userMapper.recruitSetting(map);
		return map;
	}

	@Override
	public List<Map<String, Object>> myRecruitList(Map map) {
		return userMapper.myRecruitList(map);
	}

	@Override
	public List<Map<String, Object>> myCollectList(Map map) {
		return userMapper.myCollectList(map);
	}

	@Override
	public boolean updateUserPwd(Map map) {
		try {
			userMapper.updateUserPhone(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserPhone(Map map) {
		try {
			userMapper.updateUserPhone(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean forgetPwd(Map map) {
		try {
			userMapper.forgetPwd(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPhoneExists(Map map) {

		return userMapper.checkPhoneExists(map) > 0;
	}

	@Override
	public List<Map<String, Object>> myCertificateList(Map map) {

		return userMapper.myCertificateList(map);
	}

	@Override
	public Map<String, Object> myCertificateDetail(Map map) {

		return userMapper.myCertificateDetail(map);
	}

	@Override
	public Map<String, Object> viewUserInfo(Map map) {

		return userMapper.viewUserInfo(map);
	}

	@Override
	public List<Map<String, Object>> hostQueryWordList() {

		return userMapper.hostQueryWordList();
	}

	@Override
	public Map<String, Object> queryUserInfo(Map map) {
		return userMapper.queryUserInfo(map);
	}

	@Override
	public Boolean saveToken(String tokenNum,int userid) {
		 return userMapper.saveToken(tokenNum,userid);
		}
	@SuppressWarnings("unchecked")
	@Override
	public String saveThreeUserLogin(Map map) {
		Map user=null;
		ModelVo vo = new ModelVo();
		Map res = new HashMap<>();
		try {
			Map map2  = userMapper.checkUser(map);
			if(map2!=null && map2.get("userid")!=null) {//是否注册
				if(map2.get("phone")!=null&&!"".equals(map2.get("phone").toString())) {//注册后是否绑定手机号
					
					map.put("phone", map2.get("phone").toString());
					user = userMapper.queryUserInfo(map);
					res.put("registration", "1");
					res.put("user", user);
					vo.setData(res);
					vo.setRetcode("200");
					return JSONObject.toJSON(vo).toString();
				}else {
					if(map.get("phone")!=null&&!"".equals(map.get("phone").toString())) {
						userMapper.bindingPhone(map);
						user = userMapper.queryUserInfo(map);
						res.put("registration", "1");
						res.put("user", user);
						vo.setData(res);
						vo.setRetcode("200");
						return JSONObject.toJSON(vo).toString();
					}else {
						res.put("registration", "0");
						res.put("user", user);
						vo.setData(res);
						vo.setRetcode("200");
					    return JSONObject.toJSON(vo).toString();
					}
				}
			}else {
				userMapper.threeUserLogin(map);
				if(!map.get("result").toString().equals("0")) {
					String result= map.get("result").toString();
					RongCloud rongCloud = RongCloud.getInstance(Config.appKey, Config.appSecret);
					TokenResult tokenResult = rongCloud.user.getToken(result, "10000"+result, "http://www.rongcloud.cn/images/logo.png");
					if(tokenResult.getCode()==200) {
						userMapper.saveToken(tokenResult.getToken(), Integer.valueOf(result));
					}
					map2  = userMapper.checkUser(map);
					if(map2!=null&&map2.get("phone")!=null&&!"".equals(map2.get("phone").toString())) {//注册后是否绑定手机号
						map.put("phone", map2.get("phone").toString());
						user = userMapper.queryUserInfo(map);
						res.put("registration", "1");
						res.put("user", user);
						vo.setData(res);
						vo.setRetcode("200");
						return JSONObject.toJSON(vo).toString();
					}else {
						res.put("registration", "0");
						res.put("user", user);
						vo.setData(res);
						vo.setRetcode("200");
						return JSONObject.toJSON(vo).toString();
					}
					
				}
			}
			
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}
		return JSONObject.toJSON(vo).toString();
	}

	@Override
	public Map<String, Object> queryUserInfoById(Map map) {
		return userMapper.queryUserInfoById(map);
	}


}
