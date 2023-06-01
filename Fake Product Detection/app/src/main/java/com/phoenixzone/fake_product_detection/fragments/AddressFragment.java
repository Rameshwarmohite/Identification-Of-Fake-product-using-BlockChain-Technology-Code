package com.phoenixzone.fake_product_detection.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.utility.PrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    Button btnUpdate;
    EditText txtAddress1, txtCity, txtZip, txtCountry;
    private PrefManager prefManager;

    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        prefManager = new PrefManager(getContext());
        txtAddress1 = (EditText) view.findViewById(R.id.txtAddress1);
        txtCity = (EditText) view.findViewById(R.id.txtCity);
        txtZip = (EditText) view.findViewById(R.id.txtZip);
        txtCountry = (EditText) view.findViewById(R.id.txtCountry);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtAddress1.getText().toString().equals("")) {
                    txtAddress1.setError("Enter Address Line 1");
                    return;
                }
                if (txtZip.getText().toString().equals("")) {
                    txtZip.setError("Enter zip code");
                    return;
                }
                //set address for delivery
                prefManager.setUserAddress(txtAddress1.getText().toString());
                prefManager.setZipCode(txtZip.getText().toString());
                Toast.makeText(getContext(), "Address updated successfully..", Toast.LENGTH_SHORT).show();

            }
        });

        setAddress();
        return view;
    }

    private void setAddress() {
        txtAddress1.setText(prefManager.getUserAddress());
        txtZip.setText(prefManager.getZipCode());
    }

}
