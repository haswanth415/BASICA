package com.basica.basica.core.use;

import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.adobe.cq.sightly.WCMUsePojo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelNew extends WCMUsePojo{
	static String resourcePath;
	static JSONArray arr;

	public static String getResourcePath()
	{
		return resourcePath;
	}

	public static void setResourcePath(String resourcePath) {
		ExcelNew.resourcePath = resourcePath;
	}

	public static JSONArray getArr() {
		return arr;
	}

	public static void setArr(JSONArray arr) {
		ExcelNew.arr = arr;
	}



	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		Resource resource = getResource();
		resourcePath = resource.getPath();
		resourcePath = resourcePath.concat("/file/jcr:content");
		ResourceResolver resolver = getResourceResolver();
		Resource filePath = resolver.getResource(resourcePath);
		Node node = filePath.adaptTo(Node.class);
		Property property = node.getProperty("jcr:data");
		InputStream stream = property.getStream();
		Workbook workbook = Workbook.getWorkbook(stream);
		
		/* Sheet sheet = workbook.getSheet(0); Cell cell1 = sheet.getCell(0, 0);
		 Cell cell2 = sheet.getCell(0, 1); 
		 String content1 =cell1.getContents(); String content2 = cell2.getContents();*/
		 

		arr = new JSONArray();
		for (int j = 1; j < 6; j++) {
			Sheet sheet = workbook.getSheet(0);
			String[] values = new String[5];
			String[] keys = new String[5];
			JSONObject json = new JSONObject();
			for (int i = 0; i < 5; i++) {
				
				Cell cell1 = sheet.getCell(i, 0);
				Cell cell2 = sheet.getCell(i, j);
				keys[i] = cell1.getContents();// slno
				values[i] = cell2.getContents();// 1
				json.putOpt(keys[i], values[i]);
				
			
			}
			arr.put(json);
		}
	}
		
	}

