package com.edu.fzu.translatorpro;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class ClipBoardListener {
    protected static String getClipBoardText() throws Exception
    {
    	//��ȡϵͳ���а������
 	   Transferable clip=Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
 	   if(clip!=null)//��Ϊ��
 	   {
 		   if(clip.isDataFlavorSupported(DataFlavor.stringFlavor))//˫���ж� �����Ƿ�֧���ַ�������
 		   {
 			   return (String)clip.getTransferData(DataFlavor.stringFlavor);//���ظ�����
 		   }
 	   }
 	   else{
 		   return "";
 	   }
 	   return "";//����null�о����ã���Ϊnull�Ļ����п�ָ���쳣�׳������������""�򲻻��׳���ָ���쳣
    }
}