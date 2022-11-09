package com.mobi.ocs.utilities;

import com.mobi.ocs.dto.EmailNotes;

public class EmailTemplateNotes {

	public static String Template(EmailNotes params) {

		String result = "<!DOCTYPE html>" + "<html>"

				+ "<head>" + "<title></title>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8 \" />"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1 \">"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />" + "</head>"

				+ "<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">"
				/* <!-- HIDDEN PREHEADER TEXT --> */
				+ "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\"> You have a Message from OCS.</div>"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
				/* <!-- LOGO --> */
				+ "<tr>" + "<td bgcolor=\"#195ba8\" align=\"center\">"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">"
				+ "<tr>" + "<td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\"></td>" + "</tr>"
				+ "</table>" + "</td>" + "</tr>"

				+ "<tr>" + "<td bgcolor=\"#195ba8\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">"
				+ "<tr>"
				+ "<td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">"
				/*
				 * +
				 * "<img style=\"display: block; border: 0px; width:100%\" src=\"cid:moto_logo\" />"
				 */
				+ "</td>" + "</tr>"
				+ "</table>" + "</td>" + "</tr>"

				+ "<tr>" + "<td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">"
				+ "<tr>"
				+ "<td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 55px 0px 55px; color: #195ba8; \">"
				+ "<h1 >Hello!</h1>" + "<p>There is a Message for You from " + params.getSender() + "</br><q>"
				+ params.getRegarding() + "</q></br>" + params.getNotes() + "</q></p></br>" + "<p>Order ID : "
				+ params.getOrderId() + "</br>" + "Business Name : <q>" + params.getCompanyName() + "</q></p></br>"
				+ "</td>" + "</tr>"

				/*
				 * +"<tr style=\"margin-top:10px;\">"
				 * +"<td bgcolor=\"#ffffff\" style=\"text-align:center;margin-bottom:10px;\"><h1 style=\"color: #195ba8; \">"
				 * + params.getCompanyName() + "<h1 ></td>" +"<td></td>" +"<table>" +"<tr >"
				 * +"<td > </td>" +"</tr>" +"</table>" +"</tr>"
				 * 
				 * +"<tr>"
				 * +"<td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 55px 0px 55px; color: #195ba8;\">"
				 */
				/*
				 * +"<table style=\"width:100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >"
				 * +"<tr >" +"<td style=\"font-size:12px;color:#494e57; \">Company Name: </td>"
				 * +"<td style=\"font-size:12px;color:#494e57; \">Regarding: </td>" +"</tr>"
				 * +"<tr >"
				 * +"<td style=\"font-size:18px;color:#005baa;font-weight: bold; \">"+"</td>"
				 * +"<td style=\"font-size:18px;color:#005baa;font-weight: bold; \">"+"</td>"
				 * +"</tr>" +"<tr style=\"margin-top:10px;\">"
				 * +"<td style=\"font-size:12px;color:#494e57; \">Transaction Date: </td>"
				 * +"<td style=\"font-size:12px;color:#494e57; \">Fraud Status: </td>" +"</tr>"
				 * +"<tr >"
				 * +"<td style=\"font-size:18px;color:#005baa;font-weight: bold; \">"+"</td>"
				 * +"<td style=\"font-size:18px;color:#005baa;font-weight: bold; \">"+"</td>"
				 * +"</tr>" +"</table>"
				 */
				+ "<hr/>" + "<table style=\"width:100%; \" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >"
				+ "<tr style=\"margin-top:10px;\">"
				+ "<td style=\"font-size:11px;text-align:center;\">This is an auto-generated email.</td>" + "</tr>"
				+ "<tr>" + "<td style=\"font-size:11px;text-align:center;\">Do not Reply</td>" + "</tr>" + "</table>"
				+ "</td>" + "</tr>" + "</td>" + "</tr>" + "</table>" + "</body>" + "</html>";
		return result;
	}

}
