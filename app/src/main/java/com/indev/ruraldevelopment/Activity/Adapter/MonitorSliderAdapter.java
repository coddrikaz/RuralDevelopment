package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL4;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MonitorSliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    ArrayList<MonitorMultipleImagePojo>monitormultipleImagePojoArrayList;

    public MonitorSliderAdapter(ArrayList<MonitorMultipleImagePojo> monitormultipleImagePojoArrayList) {
        this.monitormultipleImagePojoArrayList = monitormultipleImagePojoArrayList;
    }

    @Override
    public SliderAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monitor_slider, parent, false);
        return new SliderAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.Holder viewHolder, int position) {
        if (monitormultipleImagePojoArrayList.get(position).getMonitor_resource_image() != null && monitormultipleImagePojoArrayList.get(position).getMonitor_resource_image().length() > 200) {
            byte[] decodedString = Base64.decode(monitormultipleImagePojoArrayList.get(position).getMonitor_resource_image(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            viewHolder.imageView.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL7+monitormultipleImagePojoArrayList.get(position).getMonitor_resource_image())
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(viewHolder.imageView);
            }catch (Exception e){
                Log.d("Exception",""+e);
            }

        }
    }

    @Override
    public int getCount() {
        return monitormultipleImagePojoArrayList.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ZoomageView imageView;


        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }



}
