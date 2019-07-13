package com.example.mohamed.railwaymangementsystem.LoginPackage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mohamed.railwaymangementsystem.APILayer.APIConnection;
import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.UserAccount;
import com.example.mohamed.railwaymangementsystem.modules.APIModules.LoginResponse;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity  implements Callback<LoginResponse>/*implements  LoaderManager.LoaderCallbacks<String> */ {


    private Button btnLogin, forgetPasswordButton;
    private EditText emailEditText, passwordEditText;
    private Button registration;
    private ProgressBar progressBar;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        getSupportActionBar().hide();
        final APIConnection loginConnection = APIConnection.getInstance();


        btnLogin = (Button) findViewById(R.id.btnLogin);
        emailEditText = (EditText) findViewById(R.id.email_login);
        passwordEditText = (EditText) findViewById(R.id.password_login);
        registration = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        forgetPasswordButton = (Button) findViewById(R.id.forget_password_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            email = emailEditText.getText().toString();
                                            password = passwordEditText.getText().toString();
                                            if (GlobalMethods.isEmpty(email)) {
                                                GlobalMethods.createToast(LoginActivity.this, "enter the email please");
                                            } else if (GlobalMethods.isEmpty(password)) {
                                                GlobalMethods.createToast(LoginActivity.this, "enter the password please");

                                            } else {

                                              Call<LoginResponse> call= loginConnection.login(email,password);
                                              call.enqueue(LoginActivity.this);

                                            }
                                        }
                                    });

                forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);

                        startActivity(intent);
                    }
                });
        }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
        String JWT = preferences.getString("jwt", "");
        Log.v("token",JWT+"ok");

     if (JWT != "") {

            Intent intent = new Intent(this, UserAccount.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        progressBar.setVisibility(View.GONE);
        if(response.isSuccessful())
        {
            if(response.body().getMessage().contains("login successfully"))
            {
                SharedPreferences.Editor preferences = getSharedPreferences("userData",MODE_PRIVATE).edit();
                preferences.putString("jwt",response.body().getToken());
                preferences.putString("email",email);
                preferences.apply();
                Intent intent = new Intent(this,UserAccount.class);
                startActivity(intent);
            }
            else
            {
                GlobalMethods.createToast(this,response.body().getMessage());
            }

        }


    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        GlobalMethods.createToast(this,"there is an connection error");


    }

}

