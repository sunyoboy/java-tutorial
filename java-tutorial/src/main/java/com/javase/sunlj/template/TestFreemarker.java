package com.sunlj.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestFreemarker {
	
	private FreemarkerUtil freemarkerUtil;
	
	@Before
	public void init() {
		this.freemarkerUtil = new FreemarkerUtil();
	}
	
	
	@Test
	public void testTagModel() {
		Map root = new HashMap<String, Object>();
		
		root.put("indexOf", new IndexMethod());
		freemarkerUtil.fprint("ftl_01.ftl", root, "ftl.txt");
	}
	
	@Test
	public void testContentModel() {
		Map root = new HashMap<String, Object>();
		
		root.put("content", new ContentDirective());
		freemarkerUtil.fprint("static.html", root, "static.html");
	}	
	
}
