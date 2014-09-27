package com.example.pruebasretrofit;

import java.util.List;

import fb.FacebookConnect;

import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Action1;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity 
{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//new BlaAsync().execute();
		Intent inte=new Intent();
		inte.setClass(MainActivity.this,FacebookConnect.class);
		this.startActivity(inte);
		
	}
	
	private class BlaAsync extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) 
		{

			try
			{

				RestAdapter restAdapter = new RestAdapter.Builder()
			    .setEndpoint("http://192.168.1.128:3000")
			    .build();
				ApiService service = restAdapter.create(ApiService.class);
				List<MyUser> repos = service.listRepos();

				int a=3;
				int b=a;
			}
			catch(Exception e)
			{
				int a=3;
				int b=a;
			}
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
