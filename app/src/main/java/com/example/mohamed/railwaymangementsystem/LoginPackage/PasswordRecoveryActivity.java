package com.example.mohamed.railwaymangementsystem.LoginPackage;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.controller.DataValidationLoader;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import org.json.JSONException;
import org.json.JSONObject;

public class PasswordRecoveryActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<String>{
    private Button recovrtyButton ;
    private EditText recoveryEditText;
    private ProgressBar progressBar;
    TextWatcher validation = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String email = recoveryEditText.getText().toString();
            String emailExpression = "\\b[A-Za-z0-9._%+-]+@[a-z]+\\.[a-z]{3,4}\\b";
            boolean emailMatching =email.matches(emailExpression);
            if(emailMatching)
            {
                recovrtyButton.setEnabled(true);
            }
            else
            {
                recovrtyButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        prepareViews();

    }
    void prepareViews()
    {
        recoveryEditText= (EditText)findViewById(R.id.password_recovry_et);
        recovrtyButton=(Button) findViewById(R.id.recover_password_btn);
        progressBar = (ProgressBar) findViewById(R.id.password_recovery_progress);
        recovrtyButton.setEnabled(false);
        recoveryEditText.addTextChangedListener(validation);
        recovrtyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = recoveryEditText.getText().toString();
                Bundle bundle  =new Bundle();
                bundle.putString("email",email);
                getLoaderManager().initLoader(1,bundle,PasswordRecoveryActivity.this).forceLoad();

            }
        });

    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email",bundle.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new DataValidationLoader(this,jsonObject,"www.google.com");// change the api
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String output) {
        progressBar.setVisibility(View.GONE);
        if (output == "done") {
            GlobalMethods.createToast(this,"check your mail box");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else
        {
            GlobalMethods.createToast(this,"email is wrong");

        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
