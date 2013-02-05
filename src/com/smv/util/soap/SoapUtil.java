package com.smv.util.soap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceContext;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.smv.util.http.HttpUtil;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class SoapUtil {
	private static final Logger log = Logger.getLogger(SoapUtil.class);
	
	
	public static Map<String, String> getHttpHeaders(WebServiceContext ctx) {
		Map<String, String> result = new HashMap<String, String>();
		List<Header> headerList = (List<Header>) ctx.getMessageContext().get(Header.HEADER_LIST);
		
		for(Header header : headerList) {
			if (HttpUtil.getRegisteredHttpHeaders().get(header.getName().getLocalPart()) != null) {
				Node node = (Node) header.getObject();
				if (node.getFirstChild() != null && node.getFirstChild().getNodeValue() != null) {
					result.put(header.getName().getLocalPart(), node.getFirstChild().getNodeValue());
					log.debug("key: " + header.getName().getLocalPart() + ", value: " + node.getFirstChild().getNodeValue());
				}
			}			
		}
		
		return result; 
	}
}
