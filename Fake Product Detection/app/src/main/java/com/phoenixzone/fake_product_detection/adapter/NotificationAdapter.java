package com.phoenixzone.fake_product_detection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.utility.NotificationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh on 10/31/2018.
 */

public class NotificationAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public NotificationAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(NotificationInfo object) {
        list.add(object);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        final NotificationHolder notificationHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.single_notification, parent, false);
            notificationHolder = new NotificationHolder();
            notificationHolder.txtDate = (TextView) row.findViewById(R.id.time);
            notificationHolder.txtDescription = (TextView) row.findViewById(R.id.notification);
            notificationHolder.txtTitle = (TextView) row.findViewById(R.id.orderid);
            row.setTag(notificationHolder);
        } else {
            notificationHolder = (NotificationHolder) row.getTag();
        }
        try {
            NotificationInfo notificationInfo = (NotificationInfo) getItem(position);
            notificationHolder.txtTitle.setText(notificationInfo.getTitle());
            notificationHolder.txtDescription.setText(notificationInfo.getNotification());
            notificationHolder.txtDate.setText(notificationInfo.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }

    public static class NotificationHolder {
        TextView txtTitle, txtDescription, txtDate;
    }
}
