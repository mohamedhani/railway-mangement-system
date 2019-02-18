package com.example.mohamed.railwaymangementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
     final private static  int GOOGLE_SINGIN_REQUEST = 2;
    private SignInButton googleSignInButton ;
    private GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        // start sign configration by google email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        googleSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 1);
            }});
        // end sign in configration by google email
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account !=null)
        {
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
    private  static  void handleGoogleSigninTask(Task<GoogleSignInAccount> task)
    {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.v("error", "handleGoogleSigninTask: " +e.getMessage());
        }
    }
    private  static void updateUI(GoogleSignInAccount account)
    {
                if(account !=null) {
                    String name = account.getDisplayName();
                    String email = account.getEmail();
                }


    }
}
