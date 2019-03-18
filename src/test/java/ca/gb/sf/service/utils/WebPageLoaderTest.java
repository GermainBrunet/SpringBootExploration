package ca.gb.sf.service.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import ca.gb.sf.util.WebPageLoader;

public class WebPageLoaderTest {

	@Test
	public void webPageLoaderTest() throws Exception {
		
		String url = "https://www.staples.ca/en/dell-refurbished-e6520-latitude-15-6-inch-notebook-2-3-ghz-intel-core-i5-2410m-128-gb-ssd-8-gb-ddr3-windows-10-professional/product_2929496_1-CA_1_20001?akamai-feo=off";
		// String url = "http://www.livescore.com/";
		// String url = "https://www.laptopcloseout.ca/dell-latitude-e6520";
		
		String output = WebPageLoader.getPageContent(url);
		System.out.println(output);
		
		InputStream targetStream = IOUtils.toInputStream(output, "UTF-8");
		
		/**
		final Tidy tidy = new Tidy();
		tidy.setQuiet(false);
		tidy.setShowWarnings(true);
		tidy.setShowErrors(0);
		tidy.setMakeClean(true);
		Document document = tidy.parseDOM(targetStream, null);
		**/
		
		 Tidy tidy = new Tidy();
		    tidy.setInputEncoding("UTF-8");
		    tidy.setOutputEncoding("UTF-8");
		    tidy.setWraplen(Integer.MAX_VALUE);
		    tidy.setPrintBodyOnly(true);
		    tidy.setXmlOut(true);
		    tidy.setSmartIndent(true);
		    ByteArrayInputStream inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
		    // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    Document document = tidy.parseDOM(inputStream, null);
		    // String output2 = outputStream.toString("UTF-8");
		
		    doSomething(document.getDocumentElement());
		    
		    // System.out.println(output2);
		    
	}
	
	public static void doSomething(Node node) {
	    // do something with the current node instead of System.out
	    // System.out.println(node.getNodeName());

	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	        	
	        	// System.out.println(currentNode.getTextContent());
	        	
	        	String content = currentNode.getTextContent();
	        	if (content != null && content.indexOf("349.99") != -1) {
	        		System.out.println("Found IT!!!");
	        	}
	        	
	        	
	            doSomething(currentNode);
	        }
	    }
	}
	
}
