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
        			 "UTF-8"));//�õ�������������ҳ�����ݣ���������UTF-8��ʶ���Ա�֧�ָ���ʽ
        	 String frontLine = "";
        	 String line;
        	 int flag=1;
        	 String content="";//��ȡ���������ݣ���ȡ�������ݷŽ�content
        	 while((line=reader.readLine())!=null){
        		 if(frontLine.indexOf(TRANSLATION_START)!=-1&&line.indexOf(TRANSLATION_END)==-1)
        		 {
        			 content+=line.replaceAll("��| ", "")+"\n";//ȥ��Դ��������İ���Լ�ȫ���ַ�
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














