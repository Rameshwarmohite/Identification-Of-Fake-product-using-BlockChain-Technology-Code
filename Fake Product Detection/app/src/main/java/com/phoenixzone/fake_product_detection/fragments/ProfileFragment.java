package com.phoenixzone.fake_product_detection.fragments;


import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.connectivity.JSONParser;
import com.phoenixzone.fake_product_detection.connectivity.ServerUtility;
import com.phoenixzone.fake_product_detection.utility.AsteriskPasswordTransformationMethod;
import com.phoenixzone.fake_product_detection.utility.PrefManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    EditText txtCurrentPass, txtNPass, txtCpass, txtUserName, txtEmail, txtMobile;
    Button btnUpdate;
    private PrefManager prefManager;
    private String username, email, mobile, currentPass, newPass, confirmPass;
    JSONParser jsonParser = new JSONParser();
    AlertDialog dialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        prefManager = new PrefManager(this.getContext());

        //get stored information

        username = prefManager.getUserName();
        email = prefManager.getUserEmail();
        mobile = prefManager.getUserMobile();
        currentPass = prefManager.getUserPassword();

        txtUserName = (EditText) view.findViewById(R.id.txtUsername);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtMobile = (EditText) view.findViewById(R.id.txtMobile);
        txtMobile.setEnabled(false);
        txtCurrentPass = (EditText) view.findViewById(R.id.txtCurrentPass);
        txtNPass = (EditText) view.findViewById(R.id.txtNewPass);
        txtCpass = (EditText) view.findViewById(R.id.txtConfirmPass);
        txtCurrentPass.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        txtNPass.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        txtCpass.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUserName.getText().toString().equals("")) {
                    txtUserName.setError("Please Enter Full Name");
                    return;
                }
                if (txtEmail.getText().toString().equals("")) {
                    txtEmail.setError("Please enter Email");
                    return;
                }
                if (!txtCurrentPass.getText().toString().equals(currentPass)) {
                    txtCurrentPass.setError("Current password does not matched");
                    return;
                }
                if (txtNPass.getText().toString().equals("")) {
                    txtNPass.setError("Enter new password");
                    return;
                }
                if (!txtNPass.getText().toString().equals(txtCpass.getText().toString())) {
                    txtCpass.setError("Password does not matched");
                    return;
                }
                new UpdateProfile().execute();

            }
        });
        setValues();
        return view;
    }

    public class UpdateProfile extends AsyncTask<String, String, String> {
        List<NameValuePair> params = new ArrayList<>();
        boolean isUpdated = false;
        String url = ServerUtility.url_update_profile();

        @Override
        protected void onPreExecute() {
            dialog = new SpotsDialog(ProfileFragment.this.getContext(), R.style.Custom);
            dialog.setCancelable(false);
            dialog.show();
            params.add(new BasicNameValuePair("name", txtUserName.getText().toString()));
            params.add(new BasicNameValuePair("email", txtEmail.getText().toString()));
            params.add(new BasicNameValuePair("phone_number", txtMobile.getText().toString()));
            params.add(new BasicNameValuePair("upassword", txtNPass.getText().toString()));
        }

        @Override
        protected String doInBackground(String... strings) {
            jsonParser = new JSONParser();
            JSONObject object = jsonParser.makeHttpRequest(url, "POST", params);
            try {
                isUpdated = object.has("success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if (isUpdated) {
                prefManager.setUserName(txtUserName.getText().toString());
                prefManager.setUserEmail(txtEmail.getText().toString());
                prefManager.setUserPassword(txtNPass.getText().toString());
                Toast.makeText(ProfileFragment.this.getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                prefManager.setIsUserLogin(false);
            } else {
                Toast.makeText(ProfileFragment.this.getContext(), "Profile Updation Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setValues() {
        txtUserName.setText(username);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        // txtCurrentPass.setText(currentPass);
    }

}
