package com.edu.fzu.translatorpro;

import com.edu.fzu.baidu.TransApi;

import net.sf.json.JSONObject;

public class GetHtmlContentPro {
     private final static String APP_ID="Here is your APP_ID that you obtain";
     private final static String SECURITY_KEY="Here is your SWCURITY_KEY that you obtain";
     public static String getTranslation(String query) throws Exception
     {
    	 TransApi api=new TransApi(APP_ID, SECURITY_KEY);
    	 //query = ClipBoardListener.getClipBoardText();//������Ҫȥ������ֱ�Ӵ�������ͺ�
    	 String str=api.getTransResult(query, "auto", "zh");
    	 JSONObject json=JSONObject.fromObject(str.toString());
         String str1=json.get("trans_result").toString();
         if(str1==null){
        	 return "����Ϊ��";
         }
         str1=str1.replace("[", "");
         str1=str1.replace("]", "");
         JSONObject json1=JSONObject.fromObject(str1);
        return (String) json1.get("dst");
     }
     
     public static String getTranslation(String query,String language) throws Exception
     {   //static�����ﲻ����this.query=query;
    	 TransApi api=new TransApi(APP_ID, SECURITY_KEY);
    	 String str=api.getTransResult(query, "zh", language);
    	 JSONObject json=JSONObject.fromObject(str.toString());
         String str1=json.get("trans_result").toString();
         if(str1==null){
        	 return "����Ϊ��";
         }
         str1=str1.replace("[", "");
         str1=str1.replace("]", "");
         JSONObject json1=JSONObject.fromObject(str1);
        return (String) json1.get("dst");
     }
}
