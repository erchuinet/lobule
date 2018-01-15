package com.erchuinet;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class Test {
	static double DEF_PI = 3.14159265359; // PI
	static double DEF_2PI = 6.28318530712; // 2*PI
	static double DEF_PI180 = 0.01745329252; // PI/180.0
	static double DEF_R = 6370693.5; // radius of earth

	public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > DEF_PI)
			dew = DEF_2PI - dew;
		else if (dew < -DEF_PI)
			dew = DEF_2PI + dew;
		dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	public double GetLongDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}

	/*
	 * public static void main(String[] args) { ArrayList<String> city = new
	 * ArrayList<String>(); city.add("Jonesboro"); city.add("Chaohu");
	 * city.add("LittleRock"); city.add("bridgeport"); city.add("Rome");
	 * city.add("chicago"); System.out.println("排序前"+city); //排序. city.sort(new
	 * Comparator<String>() { //重点是这个函数 public int compare(String o1, String o2) {
	 * //忽略掉大小写后,进行字符串的比较 String s1 = o1.toLowerCase(); String s2 =
	 * o2.toLowerCase(); return s1.compareTo(s2);//从a-z的排 //return
	 * s2.compareTo(s1);//从z-a的排 } }); System.out.println("排序后"+city);
	 * 
	 * Comparator<Object> com=Collator.getInstance(java.util.Locale.US); String[]
	 * newArray={"中山","汕头","广州","安庆","阳江","南京","武汉","北京","安阳","北方"}; List<String>
	 * list = Arrays.asList(newArray); Collections.sort(list, com); for(String
	 * i:list){ System.out.print(i+"  "); } String[]
	 * pinyinArray=PinyinHelper.toHanyuPinyinStringArray('按'); for(int i = 0; i <
	 * pinyinArray.length; ++i)
	 * 
	 * {
	 * 
	 * System.out.println(pinyinArray[i]);
	 * 
	 * }
	 * 
	 * }
	 */
	public static void main(String[] args) throws Exception {

		String url = "http://192.168.1.6:8080/erchuinet/recruit/getSelection.do";

		// String url = "http://192.168.1.6:9090/jeesite/a/service/testData/queryList";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("page", "1");
		jsonObject.put("device", "11.0-x86_64");
		jsonObject.put("userid", "61");
		// jsonObject.put("nickname", "有朝一日我成仙");
		// jsonObject.put("weixinid", "");
		jsonObject.put("UUID", "412EB216-01DE-4A36-B577-288AEEAAEA6E");
		// jsonObject.put("qqid", "83BD7126C1700DD8DFA87C8053CD45E1");
		// jsonObject.put("figureurl",
		// "https:\\/\\/q.qlogo.cn\\/qqapp\\/1106452718\\/83BD7126C1700DD8DFA87C8053CD45ED\\/100");

		jsonObject.put("version", "1.0");
		jsonObject.put("personORcompany", "person");
		jsonObject.put("UUID", "123132123132132");
//		String reString = HttpUtil.sendExternalPost(url, jsonObject.toString());
//		System.out.println(reString);
		 URI uri = new URI(url);
		 test(uri, jsonObject.toJSONString());
	  
	          

	}

	public static void test(URI uri, String args) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();

		httpclient.start();
		HttpPost httpPost = new HttpPost();
		httpPost.setURI(uri);
		StringEntity stringEntity = new StringEntity(args.toString(), "UTF-8");// 解决中文乱码问题
		stringEntity.setContentEncoding("UTF-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		
		httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {

			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}

			@Override
			public void completed(HttpResponse response) {
				System.out.println(httpPost.getRequestLine() + "->" + response.getStatusLine());
				String httpStr = null;
				try {
					httpStr = EntityUtils.toString(response.getEntity(), "UTF-8");
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(httpStr);
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Exception arg0) {
				System.out.println("failed");
			}

		});
		/*
		 * final HttpGet[] requests = new HttpGet[] { new
		 * HttpGet("http://www.apache.org/"), new HttpGet("https://www.verisign.com/"),
		 * new HttpGet("http://www.google.com/"), new HttpGet("http://www.baidu.com/")
		 * }; final CountDownLatch latch = new CountDownLatch(requests.length); for
		 * (final HttpGet request : requests) { httpclient.execute(request, new
		 * FutureCallback<HttpResponse>() { //无论完成还是失败都调用countDown()
		 * 
		 * @Override public void completed(final HttpResponse response) {
		 * latch.countDown(); System.out.println(request.getRequestLine() + "->" +
		 * response.getStatusLine()); }
		 * 
		 * @Override public void failed(final Exception ex) { latch.countDown();
		 * System.out.println(request.getRequestLine() + "->" + ex); }
		 * 
		 * @Override public void cancelled() { latch.countDown();
		 * System.out.println(request.getRequestLine() + " cancelled"); } }); }
		 * latch.await(); System.out.println("Shutting down"); } finally {
		 * httpclient.close(); }
		 */
		System.out.println("Done");

		
	          
	}
}
