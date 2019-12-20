package com.zhiguan.carownerhomecrm.common.migu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
	public static String MiguHttpSendJson(String url,String jsonStr) {
		try {
			// 创建url资源
			URL newUrl = new URL(url);
			// 建立http连接
			HttpURLConnection conn = (HttpURLConnection) newUrl.openConnection();
			// 设置允许输出
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 设置不用缓存
			conn.setUseCaches(false);
			// 设置传递方式
			conn.setRequestMethod("POST");
			// 设置维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置文件字符集:
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置文件类型:
			conn.setRequestProperty("ContentType", "application/json");
			// 开始连接请求
			conn.connect();
			OutputStream out = conn.getOutputStream();
			// 写入请求的字符串
			out.write(jsonStr.getBytes());
			out.flush();
			out.close();
			// 请求返回的状态
			if (conn.getResponseCode() == 200) {
				// 请求返回的数据
				InputStream in = conn.getInputStream();
				String a = null;
				try {
					byte[] data1 = new byte[in.available()];
					in.read(data1);
					// 转成字符串
					a = new String(data1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return a;
			} else {
				System.out.println("[state ====>失败<====]");
				return "no";
			}

		} catch (Exception e) {
			System.out.println("[state ====>Exception<====]");
			return "Exception";
		}
	}
	public static String MiguHttpSend(String url,String param) {
		String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            String line;
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
             in = new BufferedReader(inputStreamReader);
            while ((line = in.readLine()) != null) {
                result += line;
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
	}
	public static void main(String[] args) {
		String i=MiguHttpSend("http://www.migucloud.com/vod2/t2/template/trans/query","uid=1054&token=6eee1502b6acad52c3a24b37569608d4160fd5d7f0914229d2353ae9cff98168d4f20d87cd");
		System.out.println(i);
	}
	

}
