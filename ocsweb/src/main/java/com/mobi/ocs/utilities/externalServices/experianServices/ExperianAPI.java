package com.mobi.ocs.utilities.externalServices.experianServices;

import java.io.IOException;

import com.mobi.ocs.utilities.Constants;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ExperianAPI
 */
public class ExperianAPI {

    public static final MediaType JSON = MediaType.get("application/x-www-form-urlencoded");

    OkHttpClient client = new OkHttpClient();

    public void getExperianCRIReport() {
        RequestBody requestBody = new FormBody.Builder()
                .addEncoded("ProductType", "ECI")
                .addEncoded("EntityName", "XXXXXX")
                .addEncoded("EntityId", "123123X")
                .addEncoded("EntityId2", "201901016790")
                .addEncoded("MobileNo", "60123456789")
                .addEncoded("EmailAddress", "experian@experian.com.my")
                .addEncoded("LastKnownAddress", "MID VALLEY")
                .addEncoded("ConsentGranted", "Y")
                .addEncoded("EnquiryPurpose", "NEW APPLICATION")
                .addEncoded("EastMalaysia", "")
                .addEncoded("Ref1", "NAME")
                .addEncoded("Ref2", "REF-NO: 1234")
                .addEncoded("Ref3", "STAFF-NO: 1234")
                .addEncoded("Ref4", "STAFF-DEPT: Business")
                .build();

        Request request = new Request.Builder()
                .url(Constants.Experian.getBaseUrl())
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("getExperianCRIReport >> " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}