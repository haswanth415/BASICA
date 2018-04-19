package com.basica.basica.core.use;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class GlobalHeaderNavigation extends WCMUsePojo{
	private Map<String,String> linksMap=new HashMap<String,String>();
 
	  public Map<String, String> getLinksMap() {
		return linksMap;
	}

	public void setLinksMap(Map<String, String> linksMap) {
		this.linksMap = linksMap;
	}

	private static Logger log=LoggerFactory.getLogger(GlobalHeaderNavigation.class);
	
	@Override
	public void activate() throws Exception {
		   ResourceResolver resourceResolver = getResourceResolver();
		ValueMap properties = getProperties();
		if(properties.containsKey("navLink")){
			String navigationpath = properties.get("navLink",String.class);
			Resource navigationResource = resourceResolver.getResource(navigationpath);
			if(null!= navigationResource){
				        Iterator<Resource> listChildren = navigationResource.listChildren();
				        while(listChildren.hasNext()){
				        	Resource pageResource = listChildren.next();
				        	if(null!=pageResource.adaptTo(Page.class)){
				        		Page page = pageResource.adaptTo(Page.class);
				        		linksMap.put(page.getTitle(),page.getPath() +".html");
				        	}
				        }
			}
		}
		
		
		
	}

}
