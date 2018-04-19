package com.basica.basica.core.impl.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(paths={"/bin/products"},generateComponent=true, extensions="html")

public class JsonFile  extends SlingSafeMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log=LoggerFactory.getLogger(JsonFile.class);
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			
		JSONObject obj;
		JSONArray arr=new JSONArray();
		String[] products={"product1","product2","product3","product4"};
		String[] returnData=new String[products.length];
		
		for(int i=0;i<products.length;i++){
			obj=new JSONObject();
			returnData[i]=products[i];
				obj.put("text", returnData[i]);
				obj.put("value", returnData[i]);
				arr.put(obj);	
		}
		JSONObject jsonResponse=new JSONObject();
		jsonResponse.put("res", arr);
		response.getWriter().println(arr.toString());		
		} catch (Exception e) {
			log.info("JSON exception occured whie adding data to json obj",e);
	}
}
}

