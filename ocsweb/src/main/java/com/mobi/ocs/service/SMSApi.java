package com.mobi.ocs.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SMSApi {

	@FormUrlEncoded
	@POST("/send.php")
	Call<String> sendOTPSMSToUser(@Field("body") String body, @Field("destination") String destination,
			@Field("password") String password, @Field("username") String username, @Field("sender") String sender,
			@Field("validity") String validity, @Field("reference") String reference);
}
