package com.noteshare.ipProxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName	: TestProxy 
 * @Description	: dos下命令运行：java -cp .:jsoup-1.10.3.jar  -Dfile.encoding=UTF-8 TestProxy > success.log 2>error.log &
 *  ip代理地址：
 *  http://www.xicidaili.com/wt
    http://www.kuaidaili.com/free/intr/2/
    http://blog.csdn.net/zzq1992126/article/details/51434085
    http://blog.csdn.net/llqqxf/article/details/51881281
 * @author 		: NoteShare
 * @date 		: 2017年11月1日 下午6:05:16
 */
public class TestProxy {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 1904; i++) {
            try {
                HttpURLConnection url = (HttpURLConnection) new URL("http://www.kuaidaili.com/free/intr/" + i + "/").openConnection();
                url.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                url.connect();
                Document document = Jsoup.parse(url.getInputStream(), "utf-8", "http://www.kuaidaili.com/free/inha");
                url.disconnect();
                Elements select = document.select("table tbody tr");
                for (Element element : select) {
                    Elements td = element.select("td");
                    String ip = ((Element) td.get(0)).text();
                    int port = Integer.parseInt(((Element) td.get(1)).text());
                    String type = ((Element) td.get(3)).text();
                    try {
                        URL u = new URL("url链接");
                        HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection(new Proxy("HTTP".equals(type) ? Proxy.Type.HTTP : Proxy.Type.SOCKS, new InetSocketAddress(ip, port)));
                        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                        urlConnection.setConnectTimeout(3000);
                        urlConnection.setReadTimeout(10000);
                        urlConnection.connect();
                        String title = Jsoup.parse(urlConnection.getInputStream(), "utf-8", "http://itnoteshare.com").title();
                        urlConnection.disconnect();
                        System.out.println("成功访问：" + title + "," + ip + ":" + port + "=>" + type);
                    } catch (Exception e) {
                        System.err.println("代理访问失败：" + ip + ":" + port + "=>" + type + ",Error=>" + e.getMessage());
                    }
                }
            } catch (Exception e) {
                if (e.getMessage().contains("503 for")) {
                    i--;
                }
                System.err.println(e.getMessage());
            }
        }
    }
}