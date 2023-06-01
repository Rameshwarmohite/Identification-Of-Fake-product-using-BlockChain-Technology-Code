/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.phoenixzone.fake_product_detection.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.connectivity.JSONParser;
import com.phoenixzone.fake_product_detection.connectivity.ServerUtility;
import com.phoenixzone.fake_product_detection.product.ItemDetailsActivity;
import com.phoenixzone.fake_product_detection.startup.MainActivity;
import com.phoenixzone.fake_product_detection.utility.PrefManager;
import com.phoenixzone.fake_product_detection.utility.ProductInfo;


import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ImageListFragment extends Fragment {
    JSONParser jsonParser = new JSONParser();
    public static final String STRING_PRODUCT_NAME = "ProductName";
    public static final String STRING_PRODUCT_PRICE = "ProductPrice";
    public static final String STRING_IMAGE_URI = "ImageUri";
    public static final String STRING_IMAGE_POSITION = "ImagePosition";
    private static MainActivity mActivity;
    public static List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
    private PrefManager prefManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        prefManager = new PrefManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.layout_recylerview_list, container, false);
        //  setupRecyclerView(rv);

        new LoadProductInfo(rv).execute();
        return rv;
    }


    //load product info from server


    public class LoadProductInfo extends AsyncTask<String, String, String> {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        boolean isLoaded = false;
        RecyclerView recyclerView;
        String url = ServerUtility.url_get_product_info();


        private String product_id;
        private String category_fk;
        private String product_name;
        private String product_desc;
        private String product_price;
        private String product_photo;
        private double delivery_fee = 15.0;

        public LoadProductInfo(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            JSONObject jsonObject = jsonParser.makeHttpRequest(url, "GET", params);
            productInfos = new ArrayList<>();
            try {

                JSONArray jsonArray = jsonObject.getJSONArray("ProductInfo");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    product_id = object.getString("product_pk_id");
                    category_fk = object.getString("product_category_fk");
                    product_name = object.getString("product_name");
                    product_desc = object.getString("product_desc");
                    product_price = object.getString("product_price");
                    delivery_fee = object.getDouble("d_charge");
                    product_photo = ServerUtility.Server_URL + object.getString("product_photo");
                    ProductInfo productInfo = new ProductInfo(product_id, category_fk, product_name, product_desc, product_price, product_photo);
                    productInfos.add(productInfo);
                }
                prefManager.setDeliveryFee("" + delivery_fee);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            this.recyclerView.setLayoutManager(layoutManager);
            this.recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(this.recyclerView, productInfos));
        }
    }


    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {


        private List<ProductInfo> productInfos;

        // private String[] mValues;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final LinearLayout mLayoutItem;
            //public final ImageView mImageViewWishlist;
            public final TextView txtName, txtPrice;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image1);
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item);
                //   mImageViewWishlist = (ImageView) view.findViewById(R.id.ic_wishlist);
                txtName = (TextView) view.findViewById(R.id.txtName);
                txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, List<ProductInfo> items) {
            //mValues = items;
            productInfos = items;
            mRecyclerView = recyclerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            final ProductInfo productInfo = productInfos.get(position);
            final Uri uri = Uri.parse(productInfo.getProduct_photo());
            holder.mImageView.setImageURI(uri);
            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemDetailsActivity.selectedProduct = productInfo;
                    Intent intent = new Intent(mActivity, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI, productInfo.getProduct_photo());
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    intent.putExtra(STRING_PRODUCT_NAME, productInfo.getProduct_name());
                    intent.putExtra(STRING_PRODUCT_PRICE, productInfo.getProduct_price());
                    mActivity.startActivity(intent);

                }
            });
            holder.txtName.setText(productInfo.getProduct_name());
            holder.txtPrice.setText(productInfo.getProduct_price());

            //Set click action for wishlist


        }

        @Override
        public int getItemCount() {
            return productInfos.size();
        }
    }
}
