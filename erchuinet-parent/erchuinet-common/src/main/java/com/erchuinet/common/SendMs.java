package com.erchuinet.common;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
/**
 */

public class SendMs {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";//短信提交的接口

    public static boolean sendMesage(String phoneNum,int verificationCode) {
        HttpClient client = new HttpClient(); //创建连接
        PostMethod method = new PostMethod(Url);//使用post方法发送数据并指定连接
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
//        int mobile_code = (int)((Math.random()*9+1)*1000);//随机产生10到100000的int型数
        String content = new String("您的验证码是：" + verificationCode + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {
                new NameValuePair("account", "C36275776"), //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
                new NameValuePair("password", "baadf02faf622d18a50cfb92917c90da"),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phoneNum),//填写短信被发者的电话号码
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");//返回值为2时，表示提交成功
           /* String msg = root.elementText("msg");//提交结果描述
            String smsid = root.elementText("smsid");//当提交成功后，此字段为流水号，否则为0
*/            if("2".equals(code)){
                System.out.println("短信提交成功");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return  false;
    }

}