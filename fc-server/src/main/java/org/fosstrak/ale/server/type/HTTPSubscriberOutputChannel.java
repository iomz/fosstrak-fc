/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Fosstrak (www.fosstrak.org).
 *
 * Fosstrak is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Fosstrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Fosstrak; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */
package org.fosstrak.ale.server.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.fosstrak.ale.exception.ImplementationException;
import org.fosstrak.ale.exception.InvalidURIException;
import org.fosstrak.ale.xsd.ale.epcglobal.ECReports;

import com.mysql.jdbc.StringUtils;

/**
 * send message as HTTP POST request using a standard socket.
 * @author swieland
 */
public class HTTPSubscriberOutputChannel extends AbstractSocketSubscriberOutputChannel {

	/** logger */
	private static final Logger LOG = Logger.getLogger(HTTPSubscriberOutputChannel.class);

	/** default port */
	private static final int DEFAULT_PORT = 80;
	
	private final URL url;
	private final String host;
	private final int port;	
	private final String path;
	
	public HTTPSubscriberOutputChannel(String notificationURI) throws InvalidURIException {
		super(notificationURI);
		try {
			url = new URL(notificationURI);
			host = url.getHost();
			port = (url.getPort() == -1) ? DEFAULT_PORT : url.getPort();
			path = StringUtils.startsWithIgnoreCase(url.getPath(), "/") ? url.getPath().substring(1) : url.getPath();
		} catch (Exception e) {
			LOG.error("malformed URL: ", e);
			throw new InvalidURIException("malformed URL: ", e);
		}
	}
	
	@Override
	public boolean notify(ECReports reports) throws ImplementationException {			
		LOG.debug("HTTP/1.1 POST request of reports '" + reports.getSpecName() + " to " + getHost() + ":" + getPort() + "'.");
		try {
			httpPostRequest(reports);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LOG.debug("Write reports '" + reports.getSpecName() + "' as post request to http socket '" + getHost() + ":" + getPort() + "'.");
		//writeToSocket(getPostRequest(reports));
		return true;
	}
	
	/**
	 * This method creates a post request from ec reports containing an xml representation of the reports.
	 * 
	 * @param reports to post as an xml
	 * @return response from the url
	 * @throws ImplementationException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */	
	private String httpPostRequest(ECReports reports) throws ImplementationException, ClientProtocolException, IOException {
		String xmlString = getXml(reports);
		String url = getURL().toString();
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		
		post.setHeader("User-Agent", "Fosstrak");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new BasicNameValuePair("xml", xmlString));
	    
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));

	    HttpResponse response = client.execute(post);
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + post.getEntity());
	    System.out.println("Response Code : " + 
	                                response.getStatusLine().getStatusCode());

	    BufferedReader rd = new BufferedReader(
	                    new InputStreamReader(response.getEntity().getContent()));

	    StringBuffer result = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) {
	        result.append(line);
	    }
	    	    
		return result.toString();
	}

	/**
	 * This method creates a post request from ec reports containing an xml representation of the reports.
	 * 
	 * @param reports to transform into a post request
	 * @return post request containing an xml representation of the reports
	 * @throws ImplementationException if an implementation exception occurs
	 */
	private String getPostRequest(ECReports reports) throws ImplementationException {
		
		LOG.debug("Create POST request with reports '" + reports.getSpecName() + "'.");
		
		// create body
		String body = getXml(reports);
		
		// create header
		StringBuffer header = new StringBuffer();
		
		// append request line
		header.append("POST ");
		// add the trimmed / again together with a white space
		String p = "/" + getPath() + " ";
		header.append(p);
			
		header.append("HTTP/1.1");
		header.append("\n");
		
		// append host
		header.append("Host: ");
		// append port 
		// patch by Gianrico D'Angelis <gianrico.dangelis@gmail.com>  
		header.append(getHost() + ":" + getPort());  
		header.append("\n");
		
    
      
		// append content type
		header.append("Content-Type: ");
		header.append("text/xml; charset=\"utf-8\"");
		header.append("\n");
		
		// append content length
		header.append("Content-Length: ");
		header.append(body.length());
		header.append("\n");
		
		// terminate body
		header.append("\n");

		return header + body;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}

	public URL getURL() {
		return url;
	}
	
	@Override
	public String toString() {
		return url.toString();
	}
	
	
}

