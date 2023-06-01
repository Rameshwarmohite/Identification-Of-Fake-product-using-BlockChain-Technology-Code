package com.phoenixzone.fake_product_detection.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.utility.CartItem;
import com.phoenixzone.fake_product_detection.utility.OrderDetails;
import com.phoenixzone.fake_product_detection.utility.PrefManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh on 10/23/2018.
 */

public class OrderAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public List<OrderDetails> orderList = new ArrayList<>();
    private static Context context;
    private PrefManager prefManager;

    public OrderAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        prefManager = new PrefManager(context);
    }


    public void add(@Nullable OrderDetails object) {
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        OrderHolder orderHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.single_order, parent, false);
            orderHolder = new OrderHolder();
            orderHolder.txtDate = (TextView) row.findViewById(R.id.txtDate);
            orderHolder.txtOrderid = (TextView) row.findViewById(R.id.txtOrderid);
            orderHolder.txtOrderamount = (TextView) row.findViewById(R.id.txtOrderamount);
            orderHolder.txtStatus = (TextView) row.findViewById(R.id.txtStatus);
            orderHolder.btnViewOrder = (Button) row.findViewById(R.id.btnViewOrder);
            row.setTag(orderHolder);
        } else {
            orderHolder = (OrderHolder) row.getTag();

        }

        try {
            final OrderDetails orderDetails = (OrderDetails) getItem(position);
            orderHolder.txtDate.setText(orderDetails.getOrder_date());
            orderHolder.txtOrderid.setText(orderDetails.getOrderid());
            if (orderDetails.getOrder_status() == 0) {
                orderHolder.txtStatus.setText("Pending");
                orderHolder.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.pending));
            } else if (orderDetails.getOrder_status() == 1) {
                orderHolder.txtStatus.setText("Accepted");
                orderHolder.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.processing));
            } else if (orderDetails.getOrder_status() == 3) {
                orderHolder.txtStatus.setText("Delivered");
                orderHolder.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.completed));
            } else {
                orderHolder.txtStatus.setText("Cancelled");
                orderHolder.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.cancelled));
            }

            orderHolder.txtOrderamount.setText(context.getString(R.string.rupee) + ". " + orderDetails.getOrder_amount());
            orderHolder.btnViewOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, "Order Id is " + orderDetails.getOrderid(), Toast.LENGTH_SHORT).show();
                    int count = 0;
                    String orderid = orderDetails.getOrderid();
                    String amount = "" + orderDetails.getOrder_amount();
                    ArrayList<CartItem> cartItems = new ArrayList<>();
                    for (int i = 0; i < orderList.size(); i++) {
                        OrderDetails orderDetails1 = orderList.get(i);
                        if (orderDetails1.getOrderid().equals(orderid)) {

                            cartItems.add(orderDetails1.getCartItems());
                            count += orderDetails1.getCartItems().getProduct_count();
                        }
                    }
                    orderDetails.setProduct_count(count);
                    showCustomDialog(context, orderDetails, cartItems);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    private static class OnViewGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final static int maxHeight = 600;
        private View view;

        public OnViewGlobalLayoutListener(View view) {
            this.view = view;
        }

        @Override
        public void onGlobalLayout() {
            if (view.getHeight() > maxHeight)
                view.getLayoutParams().height = maxHeight;
        }
    }

    public void showCustomDialog(final Context context, OrderDetails orderDetail, ArrayList<CartItem> cartItems) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_order_details, null, false);
        //findviewby ids
        TextView txtOrderid = (TextView) view.findViewById(R.id.txtOrderid);
        txtOrderid.setText(orderDetail.getOrderid());
        TextView txtSubtotal = (TextView) view.findViewById(R.id.txtSubtotal);
        txtSubtotal.setText(context.getString(R.string.rupee) + ". " + (orderDetail.getOrder_amount() - orderDetail.getDelivery_fee()));
        TextView txtDeliveryFee = (TextView) view.findViewById(R.id.txtDeliveryFee);
        TextView txtTotal = (TextView) view.findViewById(R.id.txtTotal);
        txtDeliveryFee.setText(context.getString(R.string.rupee) + ". " + orderDetail.getDelivery_fee());
        txtTotal.setText(context.getString(R.string.rupee) + ". " + (orderDetail.getOrder_amount()));

        ImageView img_close = (ImageView) view.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new OnViewGlobalLayoutListener(recyclerView));
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerView.setAdapter(new OrderRecyclerViewAdapter(recyclerView, cartItems));

        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }


    public static class OrderHolder {
        TextView txtOrderid, txtOrderamount, txtDate, txtStatus;
        ImageView imgStatus;
        Button btnViewOrder;
    }


    public static class OrderRecyclerViewAdapter
            extends RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder> {

        private ArrayList<CartItem> mCartlistImageUri;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final TextView txtName, txtDescription, txtPrice, txtQuantity;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image_cartlist);
                txtName = (TextView) view.findViewById(R.id.txtName);
                txtDescription = (TextView) view.findViewById(R.id.txtDescription);
                txtPrice = (TextView) view.findViewById(R.id.txtPrice);
                txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
            }
        }

        public OrderRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<CartItem> wishlistImageUri) {
            mCartlistImageUri = wishlistImageUri;
            mRecyclerView = recyclerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_single_item, parent, false);
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
            final CartItem cartItem = mCartlistImageUri.get(position);


            final Uri uri = Uri.parse(cartItem.getProduct_photo());
            holder.mImageView.setImageURI(uri);

            holder.txtPrice.setText("" + cartItem.getProduct_price());
            holder.txtName.setText(cartItem.getProduct_name());
            // holder.txtQuantity.setText(""+cartItem.getProduct_count());
            holder.txtDescription.setText(cartItem.getProduct_description());

            holder.txtQuantity.setText("" + cartItem.getProduct_count());
            //Set click action


        }

        @Override
        public int getItemCount() {
            return mCartlistImageUri.size();
        }
    }


}
