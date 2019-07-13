package com.example.mohamed.railwaymangementsystem.LoginPackage;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mohamed.railwaymangementsystem.APILayer.APIConnection;
import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.controller.DataValidationLoader;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements Callback<String>{
  private   EditText    emailEditText,passwordEditText,phoneEditText;
  private Button regisrationButton ;
  private ProgressBar progressBar ;
  TextWatcher isValid = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          String email =  emailEditText.getText().toString().trim();
          String password = passwordEditText.getText().toString().trim();
          String phoneNumber = phoneEditText.getText().toString().trim();

          String emailExpression = "\\b[A-Za-z0-9._%+-]+@[a-z]+\\.[a-z]{3,4}\\b";
          boolean emailMatching =email.matches(emailExpression);

          String passwordExpression = "\\b[a-zA-z0-9 ]{6,15}";
          boolean passwordMatching =password.matches(passwordExpression);

          String phoneNumberExpression = "\\b01.[0-9]{8}";
          boolean phoneMatching = phoneNumber.matches(phoneNumberExpression);




              if(emailMatching)
              {

                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.GREEN);
                  emailEditText.setBackground(gradientDrawable);


              }
              else {
                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.RED);
                  emailEditText.setBackground(gradientDrawable);

              }
              if(passwordMatching)
              {
                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.GREEN);
                  passwordEditText.setBackground(gradientDrawable);
              }
              else
              {
                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.RED);
                  passwordEditText.setBackground(gradientDrawable);
              }
              if(phoneMatching)
              {
                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.GREEN);
                  phoneEditText.setBackground(gradientDrawable);
              }
              else {
                  Drawable drawable = getDrawable(R.drawable.login_et);
                  GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                  gradientDrawable.setStroke(5, Color.RED);
                  phoneEditText.setBackground(gradientDrawable);
              }
          if(!TextUtils.isEmpty(email) &&
                  !TextUtils.isEmpty(password) &&
                  !TextUtils.isEmpty(phoneNumber)
                  &&emailMatching
                  &&passwordMatching
                  &&phoneMatching)
          {
              regisrationButton.setEnabled(true);
          }
          else
          {
              regisrationButton.setEnabled(false);
          }




      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
  };

   APIConnection  apiConnection ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiConnection = APIConnection.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
       prepareViews();
        register();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void prepareViews ()
    {
        passwordEditText =(EditText)findViewById(R.id.password_registration);
        emailEditText =(EditText)findViewById(R.id.email_registration);
        phoneEditText=(EditText) findViewById(R.id.phone_registration);
        regisrationButton=(Button)findViewById(R.id.registration_btn);
        progressBar= (ProgressBar)findViewById(R.id.regsitation_progress);
        regisrationButton.setEnabled(false);
        passwordEditText.addTextChangedListener(isValid);
        emailEditText.addTextChangedListener(isValid);
        phoneEditText.addTextChangedListener(isValid);


    }
    void register()
    {


        regisrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String email = emailEditText.getText().toString().trim();
                    String phoneNumber = phoneEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                  Call<String>  call =  apiConnection.register(email,password,phoneNumber);
                  call.enqueue(Registration.this);


            }
        });
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.v("error",response.message());
        if(response.isSuccessful())
        {
            if(response.body().contains("register done"))
            {

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                GlobalMethods.createToast(this,"registration Done");


            }
            else {

                GlobalMethods.createToast(this,"error with registration ");
            }
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        GlobalMethods.createToast(this,"connection error");

    }
}
