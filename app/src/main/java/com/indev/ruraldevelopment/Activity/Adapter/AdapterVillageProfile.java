package com.indev.ruraldevelopment.Activity.Adapter;


import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.ViewVillageProfile;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterVillageProfile extends RecyclerView.Adapter<AdapterVillageProfile.ViewHolder> {
    private final Context context;
    ArrayList<VillageProfileModel> villageProfileModelArrayList;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;
    SqliteHelper sqliteHelper;
    String image="";

    public AdapterVillageProfile(Context context, ArrayList<VillageProfileModel> villageProfileModelArrayList) {
        this.context = context;
        this.villageProfileModelArrayList = villageProfileModelArrayList;
        multipleImagePojoArrayList = new ArrayList<>();
        sqliteHelper = new SqliteHelper(context);
    }

    @NonNull
    @Override
    public AdapterVillageProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_village_profile, parent, false);
        return new AdapterVillageProfile.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVillageProfile.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvPopulation.setText(villageProfileModelArrayList.get(position).getPopulation());
        holder.tvPradhan_name.setText(villageProfileModelArrayList.get(position).getPradhan_name());
        holder.tvContactNumber.setText(villageProfileModelArrayList.get(position).getMobile_no());

        try {
            image = sqliteHelper.getColumnName("village_profile_img","village_profile_image", "where village_profile_id='"+villageProfileModelArrayList.get(position).getVillage_profile_id()+"'");
            if (!image.equals("") && image.length() > 200) {
                byte[] decodedString = Base64.decode(image, Base64.NO_WRAP);
                InputStream inputStream = new ByteArrayInputStream(decodedString);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.iv_camera.setImageBitmap(bitmap);
            } else {
                try {
                    Picasso.get()
                            .load(BASE_URL6+image)
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(holder.iv_camera);
                }catch (Exception e){
                    Log.d("Exception",""+e);
                }
            }
        }catch (Exception e){
            Log.e("Fail",""+e);
        }

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewVillageProfile.class);
                intent.putExtra("village_profile_id",villageProfileModelArrayList.get(position).getVillage_profile_id());
                intent.putExtra("population", villageProfileModelArrayList.get(position).getPopulation());
                intent.putExtra("pradhan_name", villageProfileModelArrayList.get(position).getPradhan_name());
                intent.putExtra("mobile_no", villageProfileModelArrayList.get(position).getMobile_no());
                context.startActivity(intent);
            }
        });

//        if (villageProfileModelArrayList.get(position).() != null && villageProfileModelArrayList.get(position).getIv_camera().length() > 200) {
//            byte[] decodedString = Base64.decode(villageProfileModelArrayList.get(position).getIv_camera(), Base64.NO_WRAP);
//            InputStream inputStream = new ByteArrayInputStream(decodedString);
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            holder.iv_camera.setImageBitmap(bitmap);
//        } else {
//            holder.iv_camera.setImageResource(R.drawable.ic_baseline_add_24);
//        }
    }


    @Override
    public int getItemCount() {
        return villageProfileModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPopulation, tvPradhan_name, tvContactNumber;
        ImageView iv_camera;
        Button btnEdit,btnView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
            tvPradhan_name = itemView.findViewById(R.id.tvPradhan_name);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            iv_camera = itemView.findViewById(R.id.iv_camera);
            btnView = itemView.findViewById(R.id.btnView);


        }
    }
}




