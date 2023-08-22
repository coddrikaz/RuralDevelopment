package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class InfraMonitorSliderAdapter extends SliderViewAdapter<InfraMonitorSliderAdapter.Holder> {

    ArrayList<InfraStruMultipleImagesPojo>monitormultipleImagePojoArrayList;

    public InfraMonitorSliderAdapter(ArrayList<InfraStruMultipleImagesPojo> monitormultipleImagePojoArrayList) {
        this.monitormultipleImagePojoArrayList = monitormultipleImagePojoArrayList;
    }

    @Override
    public InfraMonitorSliderAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infra_monitor_slider, parent, false);
        return new InfraMonitorSliderAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(InfraMonitorSliderAdapter.Holder viewHolder, int position) {
        if (monitormultipleImagePojoArrayList.get(position).getImage() != null && monitormultipleImagePojoArrayList.get(position).getImage().length() > 200) {
            byte[] decodedString = Base64.decode(monitormultipleImagePojoArrayList.get(position).getImage(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            viewHolder.imageView.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL8+monitormultipleImagePojoArrayList.get(position).getImage())
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

    public class Holder extends ViewHolder{
        ZoomageView imageView;


        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }



}
