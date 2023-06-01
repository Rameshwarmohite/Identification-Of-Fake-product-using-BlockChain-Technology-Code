package com.phoenixzone.fake_product_detection.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phoenixzone.fake_product_detection.R;

public class NoProductFoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_product_found);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}