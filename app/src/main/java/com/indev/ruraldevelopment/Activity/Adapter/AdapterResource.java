package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL4;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.Add_members;
import com.indev.ruraldevelopment.Activity.Activity.Add_resources;
import com.indev.ruraldevelopment.Activity.Activity.List_of_monitor_resources;
import com.indev.ruraldevelopment.Activity.Activity.Resorces_view;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterResource extends RecyclerView.Adapter<AdapterResource.ViewHolder> {
    private final Context context;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    ArrayList<ResourceMultipleImagePojo>resourceMultipleImagePojoArrayList;
    SharedPrefHelper sharedPreferences;
    SqliteHelper sqliteHelper;
    String image="";
    String id_type_id="";
    String resource_Type_name="";
    String name="";

    public AdapterResource(Context context, ArrayList<ResourceMappingPojo> resourceModelArrayList) {
        this.context = context;
        this.resourceMappingPojoArrayList = resourceModelArrayList;
        resourceMultipleImagePojoArrayList = new ArrayList<>();
        sharedPreferences=new SharedPrefHelper(context);
        sqliteHelper=new SqliteHelper(context);
    }

    @NonNull
    @Override
    public AdapterResource.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_resorces, parent, false);
        return new AdapterResource.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResource.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.tvResourcetype.setText(": "+ resourceMappingPojoArrayList.get(position).getResource_type_id());
        resource_Type_name = sqliteHelper.getTypeNamesss(String.valueOf(resourceMappingPojoArrayList.get(position).getResource_id()));
        holder.tvResourcetype.setText(" : "+resource_Type_name);

        holder.tletitude.setText(":"+resourceMappingPojoArrayList.get(position).getLatitude());
        holder.tlongtude.setText(":"+resourceMappingPojoArrayList.get(position).getLongitude());

        holder.tvresourcename.setText(" : "+ resourceMappingPojoArrayList.get(position).getResource_mapping_name());
//        holder.tvDate.setText(resourceModelArrayList.get(position).getDate());
        try {
            image = sqliteHelper.getColumnName("resource_mapping_image", "resuorce_mapping_image", " where resource_mapping_id='" + resourceMappingPojoArrayList.get(position).getResource_mapping_id() + "'");
            if (!image.equals("") && image.length() > 200) {
                byte[] decodedString = Base64.decode(image, Base64.NO_WRAP);
                InputStream inputStream = new ByteArrayInputStream(decodedString);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.iv_camera.setImageBitmap(bitmap);
            } else {
                try {
                    Picasso.get()
                            .load(BASE_URL4+image)
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(holder.iv_camera);
                }catch (Exception e){
                    Log.d("Exception",""+e);
                }
            }

    } catch (Exception e) {
            Log.d("Exception", "" + e);
        }

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource_Type_name = sqliteHelper.getTypeNamesss(String.valueOf(resourceMappingPojoArrayList.get(position).getResource_id()));

                Intent intent = new Intent(context, Resorces_view.class);
                intent.putExtra("resource_mapping_id", resourceMappingPojoArrayList.get(position).getResource_mapping_id());
                intent.putExtra("resource_type_id", resource_Type_name);
                intent.putExtra("resource_mapping_name", resourceMappingPojoArrayList.get(position).getResource_mapping_name());
                intent.putExtra("resource_status", resourceMappingPojoArrayList.get(position).getResource_status());
                intent.putExtra("address", resourceMappingPojoArrayList.get(position).getAddress());
                intent.putExtra("description", resourceMappingPojoArrayList.get(position).getDescription());
                intent.putExtra("latitude",resourceMappingPojoArrayList.get(position).getLatitude());
                intent.putExtra("longitude",resourceMappingPojoArrayList.get(position).getLongitude());


                //             intent.putExtra("image",resourceModelArrayList.get(position).getIv_camera());
                context.startActivity(intent);
            }

        });
        holder.btnMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, List_of_monitor_resources.class);
                intent.putExtra("resource_type_id", resourceMappingPojoArrayList.get(position).getResource_id());
                intent.putExtra("resource_mapping_name", resourceMappingPojoArrayList.get(position).getResource_mapping_name());
                id_type_id= String.valueOf(resourceMappingPojoArrayList.get(position).getResource_id());
                sharedPreferences.setString("type_id",id_type_id);
                sharedPreferences.setString("name",resourceMappingPojoArrayList.get(position).getResource_mapping_name());
                sharedPreferences.setString("resource_mapping_id", String.valueOf(resourceMappingPojoArrayList.get(position).getResource_mapping_id()));
                context.startActivity(intent);


            }
        });

//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, Add_resources.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                intent.putExtra("type", "edit");
////                intent.putExtra("id",AdapterResource.get(0).getLocal_id());
//                context.startActivity(intent);
//            }
//        });

//        holder.btnDlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "This data cannot be deleted.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
    }

    @Override
    public int getItemCount() {
        return resourceMappingPojoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvResourcetype, tvresourcename, tvaddress, tvDate, tvDescription,tvStatus,tletitude,tlongtude;
        ImageView iv_camera;
        CardView btnView;
        Button btnMonitor;
        ImageView btnEdit, btnDlt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResourcetype = itemView.findViewById(R.id.tvResourcetype);
            tvresourcename = itemView.findViewById(R.id.tvresourcename);
            // tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            iv_camera = itemView.findViewById(R.id.iv_camera);
            btnMonitor = itemView.findViewById(R.id.btnMonitor);
            btnView = itemView.findViewById(R.id.btnView);
            tvaddress = itemView.findViewById(R.id.tvAddress);
//            btnDlt = itemView.findViewById(R.id.btnDlt);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            tletitude = itemView.findViewById(R.id.tletitude);
            tlongtude = itemView.findViewById(R.id.tlongtude);

        }
    }
}
