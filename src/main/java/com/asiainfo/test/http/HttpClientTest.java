package com.asiainfo.test.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月29日  下午3:37:56
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class HttpClientTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        byte[] response = post("http://localhost:8080/springmock-test/service/post", 
                "{\"name\":\"chenzq\", \"sex\":\"male\"}".getBytes());
        System.out.println("response=" + new String(response, "utf-8"));
    }
    
    /**
     * body流的post请求
     * 
     * @param request
     * @param data
     * @return
     */
    public static byte[] post(String request, byte[] data) {
        
        try {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Accept", "application/json");        // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json");  // 设置发送数据的格式
            connection.connect();
            BufferedOutputStream bout = new BufferedOutputStream(connection.getOutputStream());
            bout.write(data);
            bout.flush();
            bout.close();
            
            // 读取响应
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("网络错误异常！!!!");
                return new byte[0];
            }
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream response = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int count = -1;
            while ((count = bis.read(buff)) > 0) {
                response.write(buff, 0, count);
            }
            bis.close();
            connection.disconnect();
            return response.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new byte[0];
    }
}