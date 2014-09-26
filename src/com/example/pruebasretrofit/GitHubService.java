package com.example.pruebasretrofit;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService 
{
	  @GET("/users/{user}/repos")
	  List<Object> listRepos(@Path("user") String user);
}
