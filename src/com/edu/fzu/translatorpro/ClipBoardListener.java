package com.edu.fzu.translatorpro;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class ClipBoardListener {
    protected static String getClipBoardText() throws Exception
    {
    	//获取系统剪切板的内容
 	   Transferable clip=Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
 	   if(clip!=null)//不为空
 	   {
 		   if(clip.isDataFlavorSupported(DataFlavor.stringFlavor))//双重判断 内容是否支持字符串类型
 		   {
 			   return (String)clip.getTransferData(DataFlavor.stringFlavor);//返回该内容
 		   }
 	   }
 	   else{
 		   return "";
 	   }
 	   return "";//比起null感觉更好，因为null的话会有空指针异常抛出，而传回这个""则不会抛出空指针异常
    }
}