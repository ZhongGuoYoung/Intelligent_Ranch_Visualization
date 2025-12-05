package com.njcx.wzjzwindserver.utils;

import com.alibaba.fastjson2.JSONObject;
import com.njcx.wzjzwindserver.config.YmlConfig;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TianQingUtils {
    private String api = "";
    private String username = "";
    private String password = "";

    {
        YmlConfig ymlConfig = new YmlConfig();
        api = ymlConfig.getProperty("custom.tianQing.api");
        username = ymlConfig.getProperty("custom.tianQing.username");
        password = DigestUtils.md5DigestAsHex(ymlConfig.getProperty("custom.tianQing.password").getBytes(StandardCharsets.UTF_8)).toUpperCase().substring(0, 11);
    }

    public String getParams(TreeMap<String, String> params) {
        // params 需要按照 key 升序排序。TreeMap 默认按照 key 升序排序。
        params.put("userid", username);
        params.put("pwd", password);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("dataFormat", "json");
        // params.put("nonce", UUID.randomUUID().toString());
        // params.put("serviceNodeId", "NMIC_MUSIC_CMADAAS");
        String paramsString = "";
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            paramsString += key + "=" + val + "&";
        }
        paramsString = paramsString.substring(0, paramsString.length() - 1);
        System.out.println(paramsString);
        // paramsString，使用MD5加密并转大写
        String sign = DigestUtils.md5DigestAsHex(paramsString.getBytes(StandardCharsets.UTF_8)).toUpperCase();
        paramsString += "&sign=" + sign;
        // 删除 pwd
        paramsString = paramsString.replace("&pwd=" + password, "");
        return paramsString;
    }

    public JSONObject getData(TreeMap<String, String> params) {
        String paramsString = getParams(params);
        String url = api + "?" + paramsString;
        OkHttp3Utils okHttp3Utils = new OkHttp3Utils();
        String data = okHttp3Utils.get(url);
        return JSONObject.parseObject(data);
    }

    public static void main(String[] args) {
    }
}
