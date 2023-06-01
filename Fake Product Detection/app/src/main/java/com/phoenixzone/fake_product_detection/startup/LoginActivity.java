package com.phoenixzone.fake_product_detection.startup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.connectivity.JSONParser;
import com.phoenixzone.fake_product_detection.connectivity.NetworkActivity;
import com.phoenixzone.fake_product_detection.connectivity.ServerUtility;
import com.phoenixzone.fake_product_detection.utility.PrefManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    private PrefManager prefManager;
    Button btnSignup, btnLogin;
    EditText txtUsername, txtPassword;
    AlertDialog dialog;

    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!isNetworkAvailable()) {
            startActivity(new Intent(LoginActivity.this, NetworkActivity.class));
            finish();
            return;
        }
        prefManager = new PrefManager(this);
//        if (prefManager.isUserLogin()) {
//            launchHomeScreen();
//            finish();
//        }


        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnLogin = (Button) findViewById(R.id.btn_signUp);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(LoginActivity.this, Activity_SignUp.class));
//            }
//        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new SpotsDialog(LoginActivity.this, R.style.Custom);
                dialog.setCancelable(false);
                dialog.show();
                if (isNetworkAvailable()) {
                    new UserLoginCheck().execute();
                } else {
                    startActivity(new Intent(LoginActivity.this, NetworkActivity.class));
                    finish();

                }


            }
        });
        if (getIntent().getBooleanExtra("EXIT", false)) {
            this.finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }


    }


    public class CheckNetwork extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new SpotsDialog(LoginActivity.this, R.style.Custom);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return isServerReachable(ServerUtility.url_get_user_info());
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {

                new UserLoginCheck().execute();


            } else {
                dialog.dismiss();

            }
        }
    }

    public boolean isServerReachable(String serverURL) {

        ConnectivityManager connMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL urlServer = new URL(serverURL);
                HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
                urlConn.setConnectTimeout(3000); //<- 3Seconds Timeout
                urlConn.connect();
                if (urlConn.getResponseCode() == 200) {
                    // Toast.makeText(this, "Server is Available", Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    //  Toast.makeText(this, "Server is not Available", Toast.LENGTH_LONG).show();
                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class UserLoginCheck extends AsyncTask<String, String, String> {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String url = ServerUtility.url_get_user_info();
        JSONObject object;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            SharedPreferences sharedPreferences = LoginActivity.this.getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
            String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
            params.add(new BasicNameValuePair("phone_number", txtUsername.getText().toString().trim()));
            params.add(new BasicNameValuePair("upassword", txtPassword.getText().toString().trim()));
            params.add(new BasicNameValuePair("fcm_token", token));
        }

        @Override
        protected String doInBackground(String... strings) {
            object = jsonParser.makeHttpRequest(url, "POST", params);
            try {
                prefManager.setUserMobile(txtUsername.getText().toString());
                prefManager.setUserEmail(object.getString("email"));
                prefManager.setUserName(object.getString("name"));
                prefManager.setUserPassword(txtPassword.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            try {
                if (object.getString("status").equals("Success")) {
                    launchHomeScreen();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void launchHomeScreen() {
        prefManager.setIsUserLogin(false);
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(LoginActivity.this, ScanQRActivity.class));
//        startActivity(new Intent(LoginActivity.this, ScanQRActivity.class));
        finish();
    }
}
