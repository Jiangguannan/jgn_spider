package com.jgn;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderSohu {
	public static void main(String[] args) throws Exception {
		Connection connect = Jsoup.connect("http://news.sohu.com/");
		Document document = connect.get();
		Elements selects = document.select(".list16");
		for (Element list16 : selects) {
			Elements as = list16.select("a[href]");
			for (Element a : as) {
				String title = a.attr("title");
				String url = a.attr("href");
				if (url.startsWith("http")) {
					Connection doc = Jsoup.connect(url);
					Document docs = doc.get();
					Elements article = docs.select("article");
					for (Element art : article) {
						String text = art.text();
						System.out.println(text);
						
					}
				}else {
					String newurl="http:"+url;
					Connection doc = Jsoup.connect(newurl);
					Document docs = doc.get();
					Elements article = docs.select("article");
					for (Element art : article) {
						String text = art.text();
						System.out.println(text);
					}
				}
			}
			
		}
		
	}
}
