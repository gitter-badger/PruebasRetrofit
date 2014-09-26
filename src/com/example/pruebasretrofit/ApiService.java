package com.example.pruebasretrofit;

import java.util.List;

import org.json.JSONArray;

import com.google.gson.JsonArray;

import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService 
{
	@GET("/api/allusers")
	List<User> listRepos();

}
