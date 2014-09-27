package com.example.pruebasretrofit;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiService 
{
	@GET("/api/allusers")
	List<MyUser> listRepos();
	
	
	@FormUrlEncoded
	@POST("/api/createuser")
	Object createUser(@Field("user") MyUser user);

}
