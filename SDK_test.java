package linux;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

public class SDK_test {

	public static void main(String[] args) throws Exception {
		 Long timestamp = System.currentTimeMillis();
		 	String secret = "SEC470cc01620c0d7efed33facfebb09fe7e0e05522902ec2e9834060e80bc10cfd";
	        String stringToSign = timestamp + "\n" + secret;
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
	        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
	        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
	        // System.out.println(timestamp);
	        // System.out.println(sign); 
	        
	        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?"
	        		+ "access_token=0d505e6fd3e8bf6501c93927442e42aa8e363b902e7970a6f7b925d4535c4a20"
	        		+"&timestamp="+timestamp+"&sign="+sign);
	        OapiRobotSendRequest request = new OapiRobotSendRequest();
	        request.setMsgtype("text");
	        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
	        text.setContent("hello world");
	        request.setText(text);
	        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();        
	        at.setIsAtAll(true);	        
	        request.setAt(at);
	        OapiRobotSendResponse response = client.execute(request);
	}

}
