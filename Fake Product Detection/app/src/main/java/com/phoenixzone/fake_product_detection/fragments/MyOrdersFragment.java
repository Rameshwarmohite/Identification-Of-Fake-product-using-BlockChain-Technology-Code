package com.phoenixzone.fake_product_detection.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.adapter.OrderAdapter;
import com.phoenixzone.fake_product_detection.connectivity.JSONParser;
import com.phoenixzone.fake_product_detection.connectivity.ServerUtility;
import com.phoenixzone.fake_product_detection.utility.CartItem;
import com.phoenixzone.fake_product_detection.utility.OrderDetails;
import com.phoenixzone.fake_product_detection.utility.PrefManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {
    JSONParser jsonParser = new JSONParser();
    OrderAdapter orderAdapter;
    AlertDialog dialog;
    ListView orderlist;
    private PrefManager prefManager;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        prefManager = new PrefManager(this.getContext());
        orderlist = (ListView) view.findViewById(R.id.orderlist);
        new GetOrderHistory().execute();
        IntentFilter filter = new IntentFilter(String.valueOf(1001));
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(
                handlePushNewMessage, filter);
        return view;
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(handlePushNewMessage);
        super.onDestroy();
    }




    private final BroadcastReceiver handlePushNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            // Update list here and refresh listview using adapter.notifyDataSetChanged();
            new GetOrderHistory().execute();
        }
    };

    public class GetOrderHistory extends AsyncTask<String, String, String> {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        List<NameValuePair> params = new ArrayList<>();
        boolean isOrder = false;
        String url = ServerUtility.url_get_order_history();
        String orderid, product_name, product_photo, order_date, product_description;
        int quantity, status;
        double amount, total;
        double delivery_fee;

        @Override
        protected void onPreExecute() {
            dialog = new SpotsDialog(getContext(), R.style.Custom);
            dialog.setCancelable(false);
            dialog.show();
            params.add(new BasicNameValuePair("phone_number", prefManager.getUserMobile()));

        }

        @Override
        protected String doInBackground(String... strings) {
            JSONObject object = jsonParser.makeHttpRequest(url, "GET", params);
            try {
                JSONArray jsonArray = object.getJSONArray("OrderInfo");
                ArrayList<String> orders = new ArrayList<>();
                orderAdapter = new OrderAdapter(MyOrdersFragment.this.getContext(), R.layout.single_order);
                CartItem cartItem = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    orderid = jsonObject.getString("order_number").trim();
                    product_name = jsonObject.getString("product_name");
                    quantity = jsonObject.getInt("quantity");
                    product_photo = ServerUtility.Server_URL + "" + jsonObject.getString("product_photo");
                    order_date = jsonObject.getString("order_date");
                    amount = jsonObject.getDouble("amount");
                    delivery_fee = jsonObject.getDouble("delivery_charge");
                    total = jsonObject.getDouble("total") + delivery_fee;
                    status = jsonObject.getInt("order_status");
                    product_description = jsonObject.getString("desc");

                    double price = amount / quantity;
                    cartItem = new CartItem(product_name, price, quantity, product_photo, amount, product_description);
                    OrderDetails orderDetails = new OrderDetails(orderid, order_date, status, total, cartItem, delivery_fee);
                    if (!orders.contains(orderid)) {
                        orders.add(orderid);
                        orderAdapter.add(orderDetails);
                        orderAdapter.orderList.add(orderDetails);
                    } else {
                        orderAdapter.orderList.add(orderDetails);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            orderlist.setAdapter(orderAdapter);
        }
    }


}
