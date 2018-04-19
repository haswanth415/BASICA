package com.basica.basica.core.use;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;


public class GlobalFooterNavigation extends WCMUsePojo{
	private Map<String,String> productLinks=new HashMap<String,String>();
	private Map<String,String> developmentLinks=new HashMap<String, String>();
	private Map<String,String> supportLinks=new HashMap<String, String>();
	private Map<String,String> partnersnavLinks=new HashMap<String, String>();


	@Override
	public void activate() throws Exception {
		ValueMap properties = getProperties();
		  ResourceResolver resourceResolver = getResourceResolver();
		  productLinks=getLinksMap(properties,resourceResolver,"productLink");
			setSupportLinks(getLinksMap(properties, resourceResolver, "supportLink"));
			developmentLinks=getLinksMap(properties, resourceResolver, "developementLink");
			setPartnersnavLinks(getLinksMap(properties, resourceResolver, "partnetLink"));
		   
	}


	private Map<String, String> getLinksMap(ValueMap properties, ResourceResolver resourceResolver, String property) {
		
		Map<String,String> navLinks=new HashMap<String, String>();
		if(properties.containsKey(property)){
			String navigationpath = properties.get(property,String.class);
			Resource navResource = resourceResolver.getResource(navigationpath);
			if(null!=navResource){
				Iterator<Resource> listChildren = navResource.listChildren();
				  while(listChildren.hasNext()){
					  Resource nextChild = listChildren.next();
					   if(null!=nextChild.adaptTo(Page.class));
					     Page page = nextChild.adaptTo(Page.class);
					     navLinks.put(page.getTitle(),page.getPath() +".html");
				  }
				
			}
		}
		return navLinks;
	}


	public Map<String,String> getProductLinks() {
		return productLinks;
	}


	public void setProductLinks(Map<String,String> productLinks) {
		this.productLinks = productLinks;
	}


	public Map<String,String> getDevelopmentLinks() {
		return developmentLinks;
	}


	public void setDevelopmentLinks(Map<String,String> developmentLinks) {
		this.developmentLinks = developmentLinks;
	}


	public Map<String,String> getSupportLinks() {
		return supportLinks;
	}


	public void setSupportLinks(Map<String,String> supportLinks) {
		this.supportLinks = supportLinks;
	}


	public Map<String,String> getPartnersnavLinks() {
		return partnersnavLinks;
	}


	public void setPartnersnavLinks(Map<String,String> partnersnavLinks) {
		this.partnersnavLinks = partnersnavLinks;
	}


	
}
