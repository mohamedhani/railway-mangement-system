package com.example.mohamed.railwaymangementsystem;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import com.example.mohamed.railwaymangementsystem.controller.LoginLoader;


public class MainActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<String> {

    final private static  int GOOGLE_SINGIN_REQUEST = 2;
    final private static  String API = "http://192.168.1.8/php_rest_myblog-master/api/post/create.php";
            //"https://earthquake.usgs.gov/fdsnws/event/1/query";
    private SignInButton googleSignInButton ;
    private Toast toast =null;
    private GoogleSignInClient googleSignInClient;
    private Button btnLogin ;
    private EditText emailEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        googleSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        btnLogin =(Button) findViewById(R.id.btnLogin);
        emailEditText =(EditText)findViewById(R.id.email_login);
        passwordEditText=(EditText)findViewById(R.id.password_login) ;

        // start sign configration by google email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 1);
            }});
        // end sign in configration by google email
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email =  emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(GlobalMethods.isEmpty(email))
                {
                    GlobalMethods.createToast(MainActivity.this,"enter the email please");
                }
                else if(GlobalMethods.isEmpty(password))
                {
                    GlobalMethods.createToast(MainActivity.this,"enter the password please");

                }
                else
                {
                    GlobalMethods.createToast(MainActivity.this,"success login ");

                }

            }
        });
        getLoaderManager().initLoader(1,null,  this).forceLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account !=null)
        {   Log.v("gfg","login befor");
            updateUI(account);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                if(requestCode==GOOGLE_SINGIN_REQUEST ) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleGoogleSigninTask(task);
                }

    }
    private    void handleGoogleSigninTask(Task<GoogleSignInAccount> task)
    {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.v("error", "handleGoogleSigninTask: " +e.getMessage());
        }
    }
    private   void updateUI(GoogleSignInAccount account)
    {             Intent intent =new Intent(this,UserAccount.class);
                    intent.putExtra("name",account.getDisplayName());
                    intent.putExtra("email",account.getEmail());

                    startActivity(intent);

                if(account !=null) {
                    String name = account.getDisplayName();
                    String email = account.getEmail();

                }
    }

        @Override
        public Loader<String> onCreateLoader(int i, Bundle bundle) {
           /* Uri uri =  Uri.parse(API);
            Uri.Builder builder  =uri.buildUpon();
            builder.appendQueryParameter("format", "geojson");
            builder.appendQueryParameter("limit", "10");
*/
            return new LoginLoader(MainActivity.this, API);

        }

        @Override
        public void onLoadFinished(Loader<String> loader, String s) {
            emailEditText.setText(s);

        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }

}

