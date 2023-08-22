package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.Moniter_View;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterMonitor extends RecyclerView.Adapter<AdapterMonitor.ViewHolder> {
    private final Context context;
    ArrayList<MonitorModel> monitorModelArrayList;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    SharedPrefHelper sharedPrefHelper;
    SqliteHelper sqliteHelper;

    String image="";

    public AdapterMonitor(Context context, ArrayList<MonitorModel> monitorModelArrayList, ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList) {
        this.context = context;
        this.monitorModelArrayList = monitorModelArrayList;
        this.resourceMappingPojoArrayList = resourceMappingPojoArrayList;
        sharedPrefHelper = new SharedPrefHelper(context);
        sqliteHelper= new SqliteHelper(context);
    }

    @NonNull
    @Override
    public AdapterMonitor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_monitor_resources, parent, false);
        return new AdapterMonitor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMonitor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //       holder.tvResource.setText(sharedPrefHelper.getString("reourceType",""));
        String resourceName=sqliteHelper.getColumnName("resource_mapping_name","resource_mapping"," where resource_mapping_id='"+monitorModelArrayList.get(position).getResource_mapping_id()+"'");
        String resourceType=sqliteHelper.getColumnName("resource_name","resource"," where resource_id='"+monitorModelArrayList.get(position).getResource_id()+"'");
        holder.tvResourceName.setText(" : "+resourceName);
        holder.tvResourceee.setText(" : "+resourceType);
        holder.tvDate.setText(" : "+monitorModelArrayList.get(position).getDate_of_monitor());

        image = sqliteHelper.getColumnName("monitor_resource_image","moniter_resource_image", "where monitor_resource_id='"+monitorModelArrayList.get(position).getMonitor_resource_id()+"'");


        if (image != null && image.length() > 200) {
            byte[] decodedString = Base64.decode(image, Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.iv_camera.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL7+image)
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(holder.iv_camera);
            }catch (Exception e){
                Log.d("Exception",""+e);
            }
        }

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Moniter_View.class);
                String resourceName=sqliteHelper.getColumnName("resource_mapping_name","resource_mapping"," where resource_mapping_id='"+monitorModelArrayList.get(position).getResource_mapping_id()+"'");
                String resourceType=sqliteHelper.getColumnName("resource_name","resource"," where resource_id='"+monitorModelArrayList.get(position).getResource_id()+"'");
                intent.putExtra("monitor_resource_id",monitorModelArrayList.get(position).getMonitor_resource_id());
                intent.putExtra("resource_name",resourceName);
                intent.putExtra("resource_type_id", resourceType);
                intent.putExtra("date_of_monitor",monitorModelArrayList.get(position).getDate_of_monitor());
                intent.putExtra("farming_near_structure",monitorModelArrayList.get(position).getFarming_near_structure());
               // intent.putExtra("sewage_at_pond",monitorModelArrayList.get(position).getSewage_at_pond());
                intent.putExtra("damaged_structure",monitorModelArrayList.get(position).getDamaged_structure());

                intent.putExtra("jubliant_visibility_board_near_structure",monitorModelArrayList.get(position).getJubliant_visibility_board_near_structure());
                intent.putExtra("structure_functional",monitorModelArrayList.get(position).getStructure_functional());
                intent.putExtra("description",monitorModelArrayList.get(position).getDescription());
                intent.putExtra("image",monitorModelArrayList.get(position).getImage());
                context.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return monitorModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView btnView;
        TextView tvResourceName, tvDate, tvDescription,tvResourceee;
        ImageView iv_camera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResourceee = itemView.findViewById(R.id.tvResourceee);
            tvResourceName = itemView.findViewById(R.id.tvResourceName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            iv_camera = itemView.findViewById(R.id.iv_camera);
            btnView=itemView.findViewById(R.id.btnView);


        }
    }
}
