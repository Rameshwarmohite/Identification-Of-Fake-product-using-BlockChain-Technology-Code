package com.phoenixzone.fake_product_detection.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.fragments.ImageListFragment;
import com.phoenixzone.fake_product_detection.fragments.ViewPagerActivity;
import com.phoenixzone.fake_product_detection.utility.CartItem;
import com.phoenixzone.fake_product_detection.utility.ImageUrlUtils;
import com.phoenixzone.fake_product_detection.utility.ProductInfo;


import org.json.JSONObject;

public class ItemDetailsActivity extends AppCompatActivity {
    int imagePosition;
    String stringImageUri, stringProductName, stringProductPrice, stringProductDescription, stringRatings, stringReviews;
    TextView txtPrice, txtDescription, txtName, txtRatings, txtReviews;
    public static ProductInfo selectedProduct;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        SimpleDraweeView mImageView = (SimpleDraweeView) findViewById(R.id.image1);

        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtName = (TextView) findViewById(R.id.txtName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtRatings = (TextView) findViewById(R.id.text_ratings);
        txtReviews = (TextView) findViewById(R.id.text_ratings_reviews);
        //Getting image uri from previous screen
        if (getIntent() != null) {
            stringImageUri = getIntent().getStringExtra(ImageListFragment.STRING_IMAGE_URI);
            stringProductName = getIntent().getStringExtra(ImageListFragment.STRING_PRODUCT_NAME);
            stringProductPrice = getIntent().getStringExtra(ImageListFragment.STRING_PRODUCT_PRICE);
            imagePosition = getIntent().getIntExtra(ImageListFragment.STRING_IMAGE_POSITION, 0);
            txtPrice.setText(stringProductPrice);
            txtDescription.setText(selectedProduct.getProduct_desc());
            txtName.setText(stringProductName);
        }
        Uri uri = Uri.parse(stringImageUri);
        mImageView.setImageURI(uri);
       


        requestQueue = Volley.newRequestQueue(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private void updateDetails(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(JSONObject response) {
                Log.d("resp", response.toString());
                try {


                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley error", "Error: " + error.getMessage());

                // hide the progress dialog
            }
        });
        // Adding request to request queue
        // AppController.getInstance().addToRequestQueue(jsonObjReq);
        // AppController.getInstance().addToRequestQueue(jsonObjReq);
        requestQueue.add(jsonObjReq);
    }


}
