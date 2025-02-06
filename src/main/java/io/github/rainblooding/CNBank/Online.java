package io.github.rainblooding.CNBank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Online {
    public static String getBankByCardOnline(String cardNo) {
        String charset = "utf-8";
        String cardBinCheck = "true";
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json";
        StringBuilder query = new StringBuilder();
        query.append("_input_charset=").append(URLEncoder.encode(charset, StandardCharsets.UTF_8));
        query.append("&cardBinCheck=").append(URLEncoder.encode(cardBinCheck, StandardCharsets.UTF_8));
        query.append("&cardNo=").append(URLEncoder.encode(cardNo, StandardCharsets.UTF_8));

        // 构建完整的 URI
        url = url + "?" + query;
        System.out.println(url);

        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;

        while (retryCount < maxRetries && !success) {
            try {
                // 创建 URL 对象
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                // 设置请求方法
                connection.setRequestMethod("GET");
                // 设置 User-Agent
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
                // 发起请求
                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);
                // 读取响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response: " + response.toString());
                success = true; // 请求成功，退出重试循环
                return response.toString();
            } catch (Exception e) {
                retryCount++;
                System.out.println("Attempt " + retryCount + " failed: " + e.getMessage());
                if (retryCount < maxRetries) {
                    // 使用指数退避增加等待时间
                    int waitTime = (int) Math.pow(2, retryCount) * 1000; // 每次失败后等待的时间是 2^n 秒
                    System.out.println("Retrying in " + waitTime + "ms...");
                    try {
                        Thread.sleep(waitTime); // 等待后重试
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                } else {
                    System.out.println("Max retries reached. Exiting.");
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        getBankByCardOnline("6230523150049717976");
    }
}
