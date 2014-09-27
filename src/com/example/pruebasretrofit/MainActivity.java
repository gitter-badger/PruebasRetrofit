package com.example.pruebasretrofit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.easy.facebook.android.data.Photo;
import com.squareup.picasso.Picasso;

import fb.FacebookConnect;

public class MainActivity extends Activity 
{

	@InjectView(R.id.llPhotos) LinearLayout llPhotos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
		//new BlaAsync().execute();
		Intent inte=new Intent();
		inte.setClass(MainActivity.this,FacebookConnect.class);
		this.startActivity(inte);
		
	}
	
	@Override
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
		
		List<Photo>photos=((Appli)getApplication()).lista;
		if(photos==null)return;
		for(Photo photo:photos)
		{
			ImageView iv=new ImageView(MainActivity.this);
			Picasso.with(MainActivity.this).load(photo.getSource()).into(iv);
			llPhotos.addView(iv);
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
