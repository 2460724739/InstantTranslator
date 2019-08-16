package com.edu.fzu.translatorpro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHtmlContent {
	
	private static String FRONT_URL="http://www.baidu.com/s?wd=";
	private static String TRANSLATION_START="<span class=\"op_dict_text2\">";
	private static String TRANSLATION_END="</span>";
	
         public static String getTranslation(String EnglishString) throws Exception
         {
        	 URL url=new URL(FRONT_URL+EnglishString);
        	 HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        	 BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),
        			 "UTF-8"));//得到输入流，即网页的内容，在最后加上UTF-8标识，以便支持该形式
        	 String frontLine = "";
        	 String line;
        	 int flag=1;
        	 String content="";//读取输入流数据，获取翻译内容放进content
        	 while((line=reader.readLine())!=null){
        		 if(frontLine.indexOf(TRANSLATION_START)!=-1&&line.indexOf(TRANSLATION_END)==-1)
        		 {
        			 content+=line.replaceAll("　| ", "")+"\n";//去掉源代码上面的半角以及全角字符
                     flag=0;
        		 }
        		 if(line.indexOf(TRANSLATION_END)!=-1)
        		 {
        			 flag=1;
        		 }
        		 if(flag==1)
        		 {
        			 frontLine=line;
        		 }
        	 }
        	 return content;
         }
}














