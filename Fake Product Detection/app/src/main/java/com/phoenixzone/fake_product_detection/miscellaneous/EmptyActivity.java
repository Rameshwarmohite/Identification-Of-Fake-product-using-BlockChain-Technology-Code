package com.phoenixzone.fake_product_detection.miscellaneous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phoenixzone.fake_product_detection.R;

public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
