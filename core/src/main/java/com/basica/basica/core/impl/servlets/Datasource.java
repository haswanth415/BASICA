package com.basica.basica.core.impl.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.jcr.api.SlingRepository;


import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.wcm.api.Page;

@SlingServlet(resourceTypes={"myproject/data/datasource"},methods={"GET"},generateComponent=true)
public class Datasource extends SlingSafeMethodsServlet{
	@Reference
	SlingRepository repository;
	
	@Reference
	ResourceResolverFactory resolvserFactory;
	
	
	private static final long serialVersionUID = 1L;
	
    
    
    @SuppressWarnings("null")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
    	 
    	  //	Map<String, Object> titles = new HashMap<String, Object>();
			   ResourceResolver resourceResolver=null;
			   //resolvserFactory.getServiceResourceResolver(titles);

			   Resource pageResource = resourceResolver.getResource("/content/basica");
			   
					Iterator<Resource> listChildren = pageResource.listChildren();
					while (listChildren.hasNext()) {
						Resource childResource = listChildren.next();
						if (null != childResource.adaptTo(Page.class)) {
							Page page = childResource.adaptTo(Page.class);
						//titles.put(page.getTitle(), page.getPath());
						
							List<Resource> resourceList = new ArrayList<Resource>();
							 
							ValueMap vm = null;
					 
							for (int i = 1; i <= (page.getTitle().length()); i++) {
								vm = new ValueMapDecorator(new HashMap<String, Object>());
								String Value = page.getPath();
								String Text = page.getTitle();
								vm.put(page.getPath(), Value);
								vm.put(page.getTitle(), Text);
					 
								resourceList.add(new ValueMapResource(request.getResourceResolver(),
										new ResourceMetadata(), "nt:unstructured", vm));
							}
					 
							// Create a DataSource that is used to populate the drop-down control
							DataSource ds = new SimpleDataSource(resourceList.iterator());
							request.setAttribute(DataSource.class.getName(), ds);


			   
		}
    	   
		
		super.doGet(request, response);
}}
}