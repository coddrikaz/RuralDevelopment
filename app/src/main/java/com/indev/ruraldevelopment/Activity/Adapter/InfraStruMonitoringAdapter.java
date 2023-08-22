package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL3;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL4;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL8;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.InfraView;
import com.indev.ruraldevelopment.Activity.Activity.Infra_Monitoring_view;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class InfraStruMonitoringAdapter extends RecyclerView.Adapter<InfraStruMonitoringAdapter.ViewHolder> {
    private final Context context;
    ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList;
    SharedPrefHelper sharedPreferences;
    SqliteHelper sqliteHelper;
    String image;
    String id_type_id="",resource_name="",Subresource_id="",science_lab="",Subresource_Type_name="";



    public InfraStruMonitoringAdapter(Context context, ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList) {
        this.context = context;
        this.infraStruMonitoringPojoArrayList = infraStruMonitoringPojoArrayList;
        infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        sharedPreferences=new SharedPrefHelper(context);
        sqliteHelper= new SqliteHelper(context);

    }



    @NonNull
    @Override
    public InfraStruMonitoringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.infrastramonitoringcostumn,parent,false);
        return new ViewHolder(view);



    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull InfraStruMonitoringAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {



        holder.tv_date_monitoring.setText(" : " + infraStruMonitoringPojoArrayList.get(position).getDate_of_monitoring());
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+infraStruMonitoringPojoArrayList.get(position).
        getVillage_id()+"'");

        holder.tv_Village.setText(" : " + village);
        resource_name = sqliteHelper.getTypeName(infraStruMonitoringPojoArrayList.get(position).getResource_id());
        holder.tv_Resource_Name.setText(" : " + resource_name);



        try {
            image = sqliteHelper.getColumnImagesName("infra_monitoring_img", "monitor_infra_image", " where infra_monitoring_id='" + infraStruMonitoringPojoArrayList.get(position).getInfra_monitoring_id() + "'");
            if (!image.equals("") && image.length() > 200) {
                byte[] decodedString = Base64.decode(image, Base64.NO_WRAP);
                InputStream inputStream = new ByteArrayInputStream(decodedString);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.iv_camera_list.setImageBitmap(bitmap);
            } else {
                try {
                    Picasso.get()
                            .load(BASE_URL8+image)
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(holder.iv_camera_list);
                }catch (Exception e){
                    Log.d("Exception",""+e);
                }
            }

        } catch (Exception e) {
            Log.d("Exception", "" + e);
        }


        holder.card_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context,Infra_Monitoring_view.class);


                    sharedPreferences.setString("resource", id_type_id);
                    Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStruMonitoringPojoArrayList.get(position).getSubresource_id()));
                    Subresource_id=infraStruMonitoringPojoArrayList.get(position).getIs_structure_functional();
                    science_lab=infraStruMonitoringPojoArrayList.get(position).getIs_structure_functional();
                    sharedPreferences.setString("resource_mapping_id", String.valueOf(infraStruMonitoringPojoArrayList.get(position).getResource_id()));
                    String id = infraStruMonitoringPojoArrayList.get(position).getLocal_id();
                    intent.putExtra("local_id", id);
                    intent.putExtra("resource_id", infraStruMonitoringPojoArrayList.get(position).getResource_id());
                    intent.putExtra("Subresource_id", infraStruMonitoringPojoArrayList.get(position).getSubresource_id());
                    intent.putExtra("Subresource_Type_name",Subresource_Type_name);
                    intent.putExtra("infra_id", infraStruMonitoringPojoArrayList.get(position).getInfra_id());
                    intent.putExtra("Installation", infraStruMonitoringPojoArrayList.get(position).getIs_it_Installation());
                    intent.putExtra("date_of_monitoring", infraStruMonitoringPojoArrayList.get(position).getDate_of_monitoring());
                    intent.putExtra("is_the_structure_funtional", infraStruMonitoringPojoArrayList.get(position).getIs_structure_functional());
                    intent.putExtra("is_the_structure_damaged", infraStruMonitoringPojoArrayList.get(position).getVisibility_of_jubilant_brand());
                    intent.putExtra("is_there_science_lab", infraStruMonitoringPojoArrayList.get(position).getIs_structure_damaged());
                    intent.putExtra("village_id", infraStruMonitoringPojoArrayList.get(position).getVillage_id());
                    intent.putExtra("infra_monitoring_id", infraStruMonitoringPojoArrayList.get(position).getInfra_monitoring_id());
                    sharedPreferences.setString("infra_idd",Subresource_id);
                    sharedPreferences.setString("resource_name",resource_name);
                    sharedPreferences.setString("science_lab",science_lab);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }



    @Override
    public int getItemCount() {
        return infraStruMonitoringPojoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_camera_list;
        CardView card_View;
        TextView tv_Village,tv_date_monitoring,tv_Resource_Name,tv_kitchen_garden,tv_emuskan,tv_library,tv_science_lab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Village = itemView.findViewById(R.id.tv_Village);
            tv_date_monitoring = itemView.findViewById(R.id.tv_date_monitoring);
            iv_camera_list = itemView.findViewById(R.id.iv_camera_list);
            tv_Resource_Name = itemView.findViewById(R.id.tv_Resource_Name);
            card_View = itemView.findViewById(R.id.card_View);
//            tv_undertaken = itemView.findViewById(R.id.tv_undertaken);
//            tv_kitchen_garden = itemView.findViewById(R.id.tv_kitchen_garden);
//            tv_emuskan = itemView.findViewById(R.id.tv_emuskan);
//            tv_library = itemView.findViewById(R.id.tv_library);
//            tv_science_lab = itemView.findViewById(R.id.tv_science_lab);

        }
    }


}
