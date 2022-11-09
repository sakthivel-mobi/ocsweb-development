package com.mobi.ocs.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DDEmailTemplate {
	
	public static String Template() {

		return " <html lang=\"en\">"
+"  <head>"
+"    <meta charset=\"utf-8\">"
+"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
+"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
+"    <style media=\"screen\">"
+"    html {"
+"	    height: 100%;"
+"	}"
+"    body {"
+"        background-image: url(asset/background.png);"
+"        background-repeat: no-repeat;"
+"        background-position: top center;"
+"        background-size: 450px 650px;"
+"        height: 100%;"
+"     }"
+"    .center-block {  "
+"        width:90%;  "
+"        padding:10px;" 
+"        height: 100%;"
+"        position: absolute;"
+"		top: 8px;"
+"		left: 16px;"
+"		font-size: 18px;"
+"    }  "
+"	img { "
+"    width: 100%;"
+"    height: 100%;"
+"	}"
+"    .container {"
+"	    max-height: 100%;"
+"	    padding-left: 0px;"
+"	    padding-right: 0px;"
+"	    width:40%;"
+"	    height: 50%;"
+"	    color: #0065B2;"
+"	    margin: auto;"
+"	     position: relative;"
+"	}"
+"	.row {"
+"	    margin-left: 0px;"
+"	    margin-right: 0px;"
+"	}"
+"	.row-first {"
+"	    margin-top:70px;"
+"	    margin-right: 0px;"
+"	    margin-left: 0px;"
+"	    overflow: hidden;"
+"	    font-size: 15px;"
+"	    padding: 0px;"
+"	    text-align: left;"
+"	}"
+"	.row-second {"
+"		margin-right: 0px;"
+"	    margin-left: 0px;"
+"	    font-size: 20px;"
+"	    padding: 0px;"
+"	    text-align: left;"
+"	}"
+"	.row-third {"
+"		margin-right: 0px;"
+"	    margin-left: 0px;"
+"	    font-size: 20px;"
+"	    padding: 0px;"
+"	    text-align: left;"
+"	}"
+"	.row-title {"
+"	    margin-top:7%;"
+"	    overflow: hidden;"
+"	    text-align: center;"
+"	}"
+"	.row-to {"
+"	    margin-top:30px;"
+"	    overflow: hidden;"
+"	    margin-left: 3%;"
+"	    margin-right: 50px;"
+"	        text-align: left;"
+"	}"
+"	.row-cop-header {"
+"	    margin-top:5px;"
+"	    margin-left: 3%;"
+"	    margin-right: 50px;"
+"	    margin-bottom: 20px;"
+"	    overflow: hidden;"
+"	    text-align: left;"
+"	}"
+"	.tab1"
+"	{"
+"	width:\"100%\" ;"
+"	 border:\"50%\";"
+"	 border-color:\"red\";"
+"	 text-align: left;"
+"	}"
+"	.row-content {"
+"	    overflow: hidden;"
+"	    margin-left: 0px;"
+"	    margin-right: 0px;"
+"	    font-size: 11px;"
+"		margin-bottom: 5px; "
+"	    text-align: left;"
+"	}"
+"	.col-title-right{"
+"		color: #F89797;"
+"		font-size: 15px;"
+"		position: relative;"
+"	    transform: translateY(30%);"
+"	}"
+"	.col-left{"
+"		padding: 0px;"
+"		width: 45%;"
+"		text-align: left;"
+"	}"
+"	.col-center{"
+"		padding: 0px;"
+"		width: 10%;"
+"		text-align: center;"
+"	}"
+"	.col-right{"
+"		padding: 0px;"
+"		width: 45%;"
+"		text-align: left;"
+"	}"
+"	.text-company-name {"
+"	    font-size: 12px;"
+"	    margin: 0px;"
+"	    text-align: left;"
+"	}"
+"	.text-company-detail {"
+"	    font-size: 10px;"
+"	    text-align: left;"
+"	}"
+"	.date1"
+"	{"
+"	margin-left:30%;"
+"	width:50%;"
+"	}"
+"	.datetable {"
+"		width: 100%;"
+"		margin-top:18%;"
+"		margin-right:45%;"
+"		margin-left:5%"
+"	}"
+".bgimg {"
+"    background-image: url('cid:mobi1'); "
//+"        background-repeat: no-repeat;"
//+"        background-position: top center;"
+"} "
+"    </style>"
+"  </head>"
+"  <body>"
+"Hi Finance Team,<BR><BR>"
//+"  <center>"
+"Kindly find the Attached DD File to upload for the "+new SimpleDateFormat("dd-MMM-yyyy").format(new Date())

//+"  </center>"
+"  </body>"
+"</html>";
	}
}
