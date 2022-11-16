package com.mobi.ocs.utilities.smtp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SmtpEmailClient {

	public JSONObject sendMessage(SmtpEmail message) {
		String inputLine = null;
		String output = null;
		JSONObject paramss = null;
		URL url;
		System.out.println("attachment is " + message.getAttachment());
		try {
			url = new URL("http://localhost:8081/SmtpWebProject/EmailApi/attachment");

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");

			JSONObject params = new JSONObject();

			params.put("subject", message.getSubject());
			params.put("msgto", message.getMsgto());
			params.put("cc", message.getCc());
			params.put("bcc", message.getBcc());
			params.put("attachment", message.getAttachment());
			params.put("textbody", message.getTextbody());
			params.put("bodyHtml", message.getBodyHtml());
			paramss = params;
			OutputStream os = con.getOutputStream();
			os.write(paramss.toString().getBytes());
			System.out.println("The params That passed" + paramss);
			os.flush();
			StringBuffer response = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			output = response.toString();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paramss;

	}
}
