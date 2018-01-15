package com.erchuinet.service.app.service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.dao.RecruitMapper;
import com.github.stuxuhai.jpinyin.PinyinHelper;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class RecruitServiceImpl implements RecruitService {

	@Autowired
	private RecruitMapper recruitMapper;


	@Override
	public List<Map<String, Object>> queryRecruitList(Map map) {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Map<String, Object>> list = recruitMapper.queryRecruitList(map);
		List<Map<String, Object>> advise = recruitMapper.getAdvertising();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> array_element = list.get(i);
			array_element.put("type", "001");// 招聘信息
			result.add(array_element);
			if (i == 2) {
				if (advise != null && advise.size() > 0) {
					Map<String, Object> adMap = new HashMap<>();
					Random random = new Random();
					int num = random.nextInt(advise.size());
					Map<String, Object> map2 = advise.get(num);

					adMap.put("title", map2.get("image").toString());// 图片地址
					adMap.put("companyname", map2.get("url").toString());// 跳转地址
					adMap.put("workaddress", map2.get("linktype").toString());// 跳转类型

					adMap.put("ensure", "");
					adMap.put("wage", "");
					adMap.put("recruitmentid", "");
					adMap.put("workdate", "");
					adMap.put("companyid", "");
					adMap.put("skill", "");
					adMap.put("hobby", "");
					adMap.put("districtname", "");
					adMap.put("distance", "");
					adMap.put("type", "002");// 广告

					result.add(adMap);
				}
			}

		}
		if (result.size() < 5) {

			map.put("location", "1");
			List<Map<String, Object>> ls = recruitMapper.queryRecruitList(map);
			result.addAll(ls);
		}
		return result;
	}

	@Override
	public String getSelection() {
		JSONObject vo = new JSONObject();
    	
		JSONObject jsonObject = new JSONObject();
		
		try {

			List<Map<String, Object>> list = recruitMapper.getCity();
			List<Map<String, Object>> trade = recruitMapper.selectTrade();
			JSONArray cityArray = new JSONArray();
            for (Map<String, Object> map : list) {
            	
            	JSONObject cityJson = new JSONObject();
            	cityJson.put("cityid", map.get("cityid").toString());
            	cityJson.put("cityname", map.get("cityname").toString());
            	
//            	List<Map<String, Object>> districtList = new ArrayList<>();
				Object district = map.get("district");
//				Map<String, Object> map2;
				if(district!=null&&!"".equals(district.toString())) {
				 if(district.toString().contains(",")) {	
				 String arr[] = district.toString().split(",");
				 JSONArray districtArr = new JSONArray();
				 for (int i = 0; i < arr.length; i++) {
					 JSONObject districtJson = new JSONObject();
//					 map2 = new HashMap<>();
				     String ss[] = arr[i].split(";");
//				     map2.put("districtid",ss[0].toString());
//				     map2.put("districtname",ss[1].toString());
				     districtJson.put("districtid",ss[0].toString());
				     districtJson.put("districtname",ss[1].toString());
				     JSONArray tradetArr = new JSONArray();
//				     List<Map<String, Object>> tradeList = new ArrayList<>();
				     for (Map<String, Object> tt:trade) {
				    	 JSONObject tradeJson = new JSONObject();
				    	
				    	 if(ss[0].toString().equals(tt.get("districtid").toString())) {
//				    		 tt.remove("districtid");
//				    		 tradeList.add(tt);
				    		 
				    		 tradeJson.put("id", tt.get("id").toString());
				    		 tradeJson.put("tradingareaname", tt.get("tradingareaname").toString());
				    		 tradetArr.add(tradeJson);
				    	 }
				     }
				     districtJson.put("trading", tradetArr);
				     
//				     districtList.add(map2);
				     districtArr.add(districtJson);
				     
				  }
				 cityJson.put("district", districtArr);
				   
//				   map.put("district", JSONObject.toJSONString(districtList));
				 }
				 cityArray.add(cityJson);
			  }
            }
            jsonObject.put("city", cityArray);
            
            JSONArray sortArr = new JSONArray();
            String[] aa = { "推荐排序", "离我最近", "最新发布", "工资最高" };
    		for (int i = 0; i < aa.length; i++) {
    			JSONObject sortJson = new JSONObject();
    			sortJson.put("sortType", String.valueOf(i));
    			sortJson.put("sortName", aa[i]);
    			sortArr.add(sortJson);
    		}
    		jsonObject.put("sort", sortArr);
            JSONObject condition = new JSONObject();
    		String[] ee = { "不限", "日结", "周结", "半月结", "月结", "完工结" };
    		String[] bb = { "不限", "男生", "女生" };
    		String[] cc = { "不限", "学生", "非学生" };
    		JSONArray accountMethodArr = new JSONArray();
    		for (int i = 0; i < ee.length; i++) {
    			JSONObject accoutJson = new JSONObject();
    			accoutJson.put("methodId", String.valueOf(i));
    			accoutJson.put("reqName", ee[i]);
    			accountMethodArr.add(accoutJson);
    		}
    		JSONArray sexLimitArr = new JSONArray();
    		for (int i = 0; i < bb.length; i++) {
    			JSONObject sexJson = new JSONObject();
    			sexJson.put("methodId",String.valueOf(i));
    			sexJson.put("reqName", bb[i]);
    			sexLimitArr.add(sexJson);
    		}
    		JSONArray identityLimitArr = new JSONArray();
    		for (int i = 0; i < cc.length; i++) {
    			JSONObject identityJson = new JSONObject();
    			identityJson.put("methodId",String.valueOf(i));
    			identityJson.put("reqName", cc[i]);
    			identityLimitArr.add(identityJson);
    			
    		}
    		condition.put("accountMethod", accountMethodArr);
    		condition.put("sexLimit", sexLimitArr);
    		condition.put("identityLimit", identityLimitArr);
    		jsonObject.put("select", condition);

			// 岗位
			List<Map<String, Object>> poston = recruitMapper.getPostition();

//			List<Map<String, Object>> typeList = new ArrayList<>();
            
			JSONArray postArr = new JSONArray();
			if (poston != null && poston.size() > 0) {
				for (int i = 0; i < poston.size(); i++) {
					Map map = poston.get(i);
					Map<String, Object> type = new HashMap<>();
					JSONObject postJson = new JSONObject();
					if (map.get("parentid").toString().equals("0")) {
//						type.put("positionid", map.get("positionid"));
//						type.put("jobname", map.get("jobname").toString());
//						type.put("parentid", map.get("parentid").toString());

						postJson.put("positionid", map.get("positionid"));
						postJson.put("jobname", map.get("jobname").toString());
						postJson.put("parentid", map.get("parentid").toString());
						postArr.add(postJson);
						
//						typeList.add(type);
					}

				}
				for (int i=0;i<postArr.size();i++) {
//					List<Map<String, Object>> childrenList = new ArrayList<>();
					JSONArray childArr = new JSONArray();
					JSONObject t = postArr.getJSONObject(i);
					for (int j = 0; j < poston.size(); j++) {
						Map maps = poston.get(j);
						JSONObject childJson = new JSONObject();
						if (t.get("positionid").toString().equals(maps.get("parentid").toString())) {
//							childrenList.add(maps);
							childJson.put("positionid", maps.get("positionid"));
							childJson.put("jobname", maps.get("jobname").toString());
							childJson.put("parentid", maps.get("parentid").toString());
							childArr.add(childJson);
						}
					}
					t.put("children", childArr);

				}
			}
			// 广告
			List<Map<String, Object>> advertising = recruitMapper.getAdvertising();
			JSONArray advertArr = new JSONArray();
			for (Map<String, Object> m:advertising) {
				JSONObject adverJson = new JSONObject();
				Iterator it= m.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString();
					adverJson.put(key,m.get(key).toString());
				}
				advertArr.add(adverJson);
			}
			
//			result.put("city", list);
//			result.put("select", select);
			jsonObject.put("poston", postArr);
//			result.put("sort", sort);
			jsonObject.put("advertising", advertArr);
			vo.put("data", jsonObject);
    		vo.put("retcode","200");
    		vo.put("retmsg", "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			vo.put("retcode","500");
    		vo.put("retmsg", "出错了");
		}
		return vo.toString();
	}

	@Override
	public String favoriteSave(Map map) {
		ModelVo vo = new ModelVo();
		try {
			if(recruitMapper.checkFavorite(map)>0) {
				vo.setRetcode("400");
	    		vo.setRetmsg("该岗位已收藏过,不能重复收藏");
			}else {
			recruitMapper.favoriteSave(map);
    		vo.setRetcode("200");
    		vo.setRetmsg("收藏成功");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetcode("500");
    		vo.setRetmsg("出错了");
		}
		 return JSONObject.toJSON(vo).toString();
	}

	@Override
	public Map<String, Object> findRecruitDetail(Map map) {
		return recruitMapper.findRecruitDetail(map);
	}

	@Override
	public void saveComplaint(Map map) {
		recruitMapper.saveComplaint(map);
	}

	@Override
	public String apply(Map map) {
		ModelVo vo = new ModelVo();
		try {
			recruitMapper.apply(map);
			if (map.get("result").toString().equals("1")) {
				vo.setRetcode("200");
				vo.setRetmsg("提交成功");
			} else if (map.get("result").toString().equals("2")) {
				vo.setRetcode("300");
				vo.setRetmsg("您的账号异常无法报名");
			} else if (map.get("result").toString().equals("3")) {
				vo.setRetcode("300");
				vo.setRetmsg("你已经提交过报名");
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("提交失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		return JSONObject.toJSON(vo).toString();
	}

	@Override
	public boolean publishPartTimeJob(Map map) {
		try {
			recruitMapper.publishPartTimeJob(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getCityList() {
		JSONObject cityObj = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		try {
		List<Map<String, Object>> ls = recruitMapper.getCityList();
		Set<String> set = new HashSet<>();
		List<Map<String, Object>> cityData = new ArrayList<>();
//		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> hotCity = new ArrayList<>();
		JSONArray hotArr = new JSONArray();
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				Map<String, Object> m = ls.get(i);
				if (m.get("hot").toString().equals("1")) {// 热门城市
					if(m.containsKey("hot")) {
					  m.remove("hot");
					}
					JSONObject hotJson = new JSONObject();
					hotJson.put("cityid", m.get("cityid").toString());
					hotJson.put("cityname", m.get("cityname").toString());
//					hotCity.add(m);
					hotArr.add(hotJson);
				}
				String pinyin =  PinyinHelper.getShortPinyin(m.get("cityname").toString());//new PinyinConverter().getPinyin(m.get("cityname").toString());
				if (pinyin != null) {
					if (pinyin.length() >= 2) {
						pinyin = pinyin.substring(0, 1);
					}
					set.add(pinyin.toUpperCase());
				}
			}
			Comparator<Object> com = Collator.getInstance(java.util.Locale.US);
			Object[] objects = set.toArray();
			Arrays.sort(objects);
			List<Object> list = Arrays.asList(objects);
			Collections.sort(list, com);
			for (Object obj : list) {
				String initial = (String) obj;
				Map<String, Object> letter = new HashMap<>();
				List<Map<String, Object>> context = new ArrayList<>();
				for (int i = 0; i < ls.size(); i++) {
					Map<String, Object> m = ls.get(i);

					String pinyin = PinyinHelper.getShortPinyin(m.get("cityname").toString());//new PinyinConverter().getPinyin(m.get("cityname").toString());
					if (pinyin != null) {
						if (pinyin.length() >= 2) {
							pinyin = pinyin.substring(0, 1);
						}
						if (initial.equals(pinyin.toUpperCase())) {
							if(m.containsKey("hot")) {
							 m.remove("hot");
							}    
							context.add(m);
						}
					}
				}
				letter.put("letter", initial);
				letter.put("city", context);
				cityData.add(letter);
			}
//			data.put("city", cityData);
//			data.put("letter", list);
//			data.put("hot", hotCity);
			cityObj.put("city", cityData);
			cityObj.put("letter", list);
			cityObj.put("hot", hotArr);
		}
		jsonObject.put("data", cityObj);
		jsonObject.put("retcode","200");
		jsonObject.put("retmsg", "查询成功");
	} catch (Exception e) {
		e.printStackTrace();
		jsonObject.put("retcode","500");
		jsonObject.put("retmsg", "出错了");
	}
		return jsonObject.toString();
	}

	@Override
	public List<Map<String, Object>> recommendUserList(Map map) {
		return recruitMapper.recommendUserList(map);
	}

	@Override
	public void savePosition(Map map) {
		recruitMapper.saveComplaint(map);
	}

	@Override
	public List<Map<String, Object>> queryCompanyRecruitList(Map map) {
		return recruitMapper.queryCompanyRecruitList(map);
	}

	@Override
	public boolean deleteRecruit(Map map) {
		try {
			recruitMapper.deleteRecruit(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> queryJobProvedList(Map map) {
		return recruitMapper.queryJobProvedList(map);
	}



}
