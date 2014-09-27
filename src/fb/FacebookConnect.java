package fb;

import retrofit.RestAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.easy.facebook.android.apicall.GraphApi;
import com.easy.facebook.android.data.User;
import com.easy.facebook.android.error.EasyFacebookError;
import com.easy.facebook.android.facebook.FBLoginManager;
import com.easy.facebook.android.facebook.Facebook;
import com.easy.facebook.android.facebook.LoginListener;
import com.example.pruebasretrofit.ApiService;
import com.example.pruebasretrofit.MyUser;
import com.example.pruebasretrofit.Profile;
import com.example.pruebasretrofit.R;
import com.google.gson.GsonBuilder;

public class FacebookConnect extends Activity   implements LoginListener {

    private FBLoginManager fbManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            shareFacebook();
    }

    
    public void shareFacebook() {
    
    //change the permissions according to the function you want to use 
            String permissions[] = { "read_stream", "user_relationship_details",
                            "user_religion_politics", "user_work_history",
                            "user_relationships", "user_interests", "user_likes",
                            "user_location", "user_hometown", "user_education_history",
                            "user_activities", "offline_access","user_photos" };

            //change the parameters with those of your application
            fbManager = new FBLoginManager(this, R.layout.activity_main,
                            getString(R.string.facebook_app_id), permissions);

            if (fbManager.existsSavedFacebook()) {
                    fbManager.loadFacebook();
            } else {

                    fbManager.login();
            }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            fbManager.loginSuccess(data);
    }

    public void loginFail() {
            fbManager.displayToast("Login failed!");

    }

    public void logoutSuccess() {
            fbManager.displayToast("Logout success!");
    }
    public void loginSuccess(Facebook facebook) 
    {

            //library use example
            
            new AsyncBla().execute(facebook);
          
            
            finish();
            
            //new AsyncLogout().execute(facebook);

    }
    
    private class AsyncLogout extends AsyncTask<Facebook, Void, Void>
    {

		@Override
		protected Void doInBackground(Facebook... params) {
			// TODO Auto-generated method stub
			try {
				fbManager.logout(params[0]);
			} catch (EasyFacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
    	
    }
    
    private class AsyncBla extends AsyncTask<Facebook, Void, Void>
    {

		@Override
		protected Void doInBackground(Facebook... params) {
			final GraphApi graphApi = new GraphApi(params[0]);
            User user = new User();
            try {
                    user = graphApi.getMyAccountInfo();
                    //List<Photo> photos=graphApi.getPhotosMy(100);
                    new PostAsync().execute();
            } catch (EasyFacebookError e) {
            	 int a=3;
                 int b=a;
                    e.toString();
            }
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
    
    private class PostAsync extends AsyncTask<Void,Void,Void>
    {

		@Override
		protected Void doInBackground(Void... params) {
			try
			{

				RestAdapter restAdapter = new RestAdapter.Builder()
			    .setEndpoint("http://192.168.1.128:3000")
			    .build();
				ApiService service = restAdapter.create(ApiService.class);
				MyUser user=new MyUser();
				user.fb_id="jarlarj";
				user.profile=new Profile();
				
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
}
