package com.edu.fzu.translatorpro;

import javax.swing.UIManager;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainFrame mainFrame=new MainFrame();
        mainFrame.setVisible(true);
	}

}
