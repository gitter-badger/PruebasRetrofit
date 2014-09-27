package fb;

import retrofit.RestAdapter;
import android.app.Activity;
import android.app.Dialog;
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
import com.example.pruebasretrofit.Appli;
import com.example.pruebasretrofit.MyUser;
import com.example.pruebasretrofit.Profile;
import com.example.pruebasretrofit.R;

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
          
            
            //new AsyncLogout().execute(facebook);

    }
    
    
    private class AsyncBla extends AsyncTask<Facebook, Void, Void>
    {
    	Dialog dial;
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dial=new Dialog(FacebookConnect.this);
    		dial.show();
    	}

		@Override
		protected Void doInBackground(Facebook... params) {
			final GraphApi graphApi = new GraphApi(params[0]);
            User user = new User();
            try {
                    user = graphApi.getMyAccountInfo();
                    ((Appli)getApplication()).lista=graphApi.getPhotosMy(100);
                    //List<Photo> photos=graphApi.getPhotosMy(100);
                    //new PostAsync().execute();
            } catch (EasyFacebookError e) {
            	 int a=3;
                 int b=a;
                    e.toString();
            }
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(dial!=null && dial.isShowing())
			{
				dial.dismiss();
			}
            finish();
		}
    	
    }
}
