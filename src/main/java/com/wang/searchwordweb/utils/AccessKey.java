package com.wang.searchwordweb.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class AccessKey {
    public static  String accessKey;
    private static  String path="https://aip.baidubce.com/oauth/2.0/token";
    public static  void  getKey(String Ak,String Sk){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse;
        try {
            URIBuilder uriBuilder=new URIBuilder(path);
            uriBuilder.setParameter("grant_type","client_credentials");
            uriBuilder.setParameter("client_id",Ak);
            uriBuilder.setParameter("client_secret",Sk);
            HttpPost post=new HttpPost(uriBuilder.build().toString());
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
             httpResponse = httpClient.execute(post);
            Map map = JSONObject.parseObject(EntityUtils.toString(httpResponse.getEntity()), Map.class);
            accessKey=(String) map.get("access_token");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setAccessKey(String accessKey) {
        AccessKey.accessKey = accessKey;
    }
}
