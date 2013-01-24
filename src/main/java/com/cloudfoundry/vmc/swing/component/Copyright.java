package com.cloudfoundry.vmc.swing.component;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Copyright{
	private static final long serialVersionUID = -8607149290281591829L;

	private static Copyright copyright;
	
	private JPanel cright;
	private JLabel name;
	private JLabel email;
	private JLabel web;
	
	private void initResource(){
		cright=new JPanel(new FlowLayout(FlowLayout.CENTER,20,15));
		name=new JLabel("Author: Leo");
		email=new JLabel("Email: dukai1008@gmail.com");
		web=new JLabel("© 2013");
	}
	
	public void initGUI(){
		this.initResource();
		this.buildCopyright();
	}
	
	public Copyright(){
		this.initGUI();
	}
	
	/**
	 * Build copyright
	 */
	public void buildCopyright(){
		cright.add(name);
		cright.add(email);
		cright.add(web);
	}
	
	public static Copyright getInstance(){
		if (null == copyright){
			copyright=new Copyright();
		}
		return copyright;
	}
	public JPanel toCopyright(){
		return cright;
	}
}
