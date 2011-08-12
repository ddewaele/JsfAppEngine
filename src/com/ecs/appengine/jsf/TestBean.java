package com.ecs.appengine.jsf;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="testBean")
public class TestBean {

	private static final Logger log = Logger.getLogger(TestBean.class.getName());
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName2() {
		return "name2";
	}
	
	
	public String doSomething() {
		log.info("doSomething from JSF with name : " + name);
		System.out.println("doSomething from JSF with name : " + name);
		return "welcome.xhtml";
	}
}
