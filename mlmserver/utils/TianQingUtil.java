package com.mlm.mlmserver.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

public class TianQingUtil {


    //	params 需要按照 key 升序排序。 TreeMap 默认按照 key 升序排序。
    public String getParams(TreeMap<String, String> params) {
//		params.put("userId", userId);
//    	params.put("pwd", pwd);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", UUID.randomUUID().toString());
        params.put("dataFormat", "json");
        params.put("serviceNodeId", "NMIC_MUSIC_CMADAAS");
        String paramsString = "";
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            paramsString += key + "=" + val + "&";
        }
        paramsString = paramsString.substring(0, paramsString.length() - 1);
        String sign = getSign(paramsString);
        paramsString += "&sign=" + sign;
        return paramsString;
    }

    //	使用MD5加密并转大写
    public String getSign(String paramsString) {
        byte[] paramsByte = null;
        try {
            paramsByte = paramsString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return DigestUtils.md5DigestAsHex(paramsByte).toUpperCase();
    }

    // public JSONObject getData(TreeMap<String, String> params) {
    //     // String paramsString = getParams(params);
    //     // RestUtil rest = new RestUtil();
    //     // String data = rest.getRestData("http", "10.48.90.120", "/music-ws/api", paramsString, null);
    //     // return JSONObject.parseObject(data);
    // }
}
