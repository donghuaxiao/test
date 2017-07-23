package com.ericsson.epay.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by xdhua on 2017/7/23.
 */
public class HttpUtils {
    public static String post( String url, String data) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postMethod = new HttpPost(url);
        HttpEntity requestBody = new ByteArrayEntity(data.getBytes("UTF-8"));
        postMethod.setEntity(requestBody);

        HttpResponse resp = httpClient.execute(postMethod);
        System.out.println( resp.getStatusLine());
        return EntityUtils.toString(resp.getEntity());
    }

    public static <T> T post( String url, Object obj, Class<T> clazz) throws Exception {
        String data = JaxbUtil.beanToXml(obj);
        String resp = HttpUtils.post(url,data);
        return JaxbUtil.xmlToBean(resp, clazz);
    }
}
