package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL6;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    ArrayList<MultipleImagePojo>multipleImagePojoArrayList;

    public SliderAdapter(ArrayList<MultipleImagePojo>multipleImagePojoArrayList) {
        this.multipleImagePojoArrayList = multipleImagePojoArrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        if (multipleImagePojoArrayList.get(position).getVillage_profile_img() != null && multipleImagePojoArrayList.get(position).getVillage_profile_img().length() > 200) {
            byte[] decodedString = Base64.decode(multipleImagePojoArrayList.get(position).getVillage_profile_img(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            viewHolder.imageView.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL6 + multipleImagePojoArrayList.get(position).getVillage_profile_img())
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(viewHolder.imageView);
            } catch (Exception e) {
                Log.d("Exception", "" + e);
            }
        }
    }

    @Override
    public int getCount() {
        return multipleImagePojoArrayList.size();
    }

    public static class Holder extends SliderViewAdapter.ViewHolder{
        ZoomageView imageView;


        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
