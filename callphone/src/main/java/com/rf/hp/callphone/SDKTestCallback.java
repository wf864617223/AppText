package com.rf.hp.callphone;

import java.util.HashMap;
import java.util.Set;
import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;



public class SDKTestCallback {

	private String MasterNum;
	private String SlaveNum;
	private String MasterShowNum;
	private String SlaveShowNum;

	public SDKTestCallback(String masterNum, String slaveNum) {
		MasterNum = masterNum;
		SlaveNum = slaveNum;
	}

	public SDKTestCallback(String masterNum, String slaveNum, String masterShowNum, String slaveShowNum) {
		MasterNum = masterNum;
		SlaveNum = slaveNum;
		MasterShowNum = masterShowNum;
		SlaveShowNum = slaveShowNum;
	}

	/**
	 *电话回呼
	 */
	public  void callFriend() {
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setSubAccount("3ff83cd974a611e6997a6c92bf2c165d", "a52332ab695fc9ad9922ef78c23fada7");// 初始化子帐号和子帐号TOKEN
		restAPI.setAppId("8a216da854e087830154e147418a0194");// 初始化应用ID
		result = restAPI.callback(MasterNum, SlaveNum, SlaveShowNum, MasterShowNum, "", "true", "","","","", "", "false", "", "");
		//result = restAPI.callback(MasterNum, SlaveNum, SlaveShowNum, MasterShowNum, "xx.wav(可选第三方自定义回拨提示音)", "是否一直播放提示音", "用于终止播放提示音的按键","第三方私有数据","最大通话时长","实时话单通知地址", "是否给主被叫发送话单", "是否录音", "通话倒计时", "到达倒计时时间播放的提示音");
		System.out.println("SDKTestCallback result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}

}
