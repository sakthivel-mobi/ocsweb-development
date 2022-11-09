package com.mobi.ocs.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder {

	public static String getParamsString(Map<String, String> parameters) throws UnsupportedEncodingException{
	       StringBuilder result = new StringBuilder();

	        for (Map.Entry<String, String> entry : parameters.entrySet()) {
	            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	            result.append("=");
	            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	            result.append("&");
	        }

	        String resultString = result.toString();
	        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
