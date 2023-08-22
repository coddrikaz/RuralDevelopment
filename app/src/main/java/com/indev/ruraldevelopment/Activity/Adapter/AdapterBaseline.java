package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL8;

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

import com.indev.ruraldevelopment.Activity.Activity.Infra_Monitoring_view;
import com.indev.ruraldevelopment.Activity.BaselineViewDetails;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterBaseline extends RecyclerView.Adapter<AdapterBaseline.ViewHolder> {

    private final Context context;
    ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList;
    SharedPrefHelper sharedPreferences;
    SqliteHelper sqliteHelper;
    String image,Subresource_Type_name="";
    String id_type_id="",resource_name="",Subresource_id="",science_lab="";



    public AdapterBaseline(Context context, ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList) {
        this.context = context;
        this.infraStruMonitoringPojoArrayList = infraStruMonitoringPojoArrayList;
        infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        sharedPreferences=new SharedPrefHelper(context);
        sqliteHelper= new SqliteHelper(context);

    }


    @NonNull
    @Override
    public AdapterBaseline.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.baseline,parent,false);
        return new AdapterBaseline.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBaseline.ViewHolder holder, int position) {
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



        holder.card_Viewsss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intentdd =new Intent(context, BaselineViewDetails.class);
                    sharedPreferences.setString("resource", id_type_id);
                    Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStruMonitoringPojoArrayList.get(position).getSubresource_id()));
                    sharedPreferences.setString("resource_mapping_id", String.valueOf(infraStruMonitoringPojoArrayList.get(position).getResource_id()));
                    String id = infraStruMonitoringPojoArrayList.get(position).getLocal_id();
                    intentdd.putExtra("local_id", id);
                    intentdd.putExtra("resource_id", infraStruMonitoringPojoArrayList.get(position).getResource_id());
                    intentdd.putExtra("Subresource_Type_name",Subresource_Type_name);
                    intentdd.putExtra("Subresource_id", infraStruMonitoringPojoArrayList.get(position).getSubresource_id());
                    intentdd.putExtra("infra_idss", infraStruMonitoringPojoArrayList.get(position).getInfra_id());
                    intentdd.putExtra("date_of_monitoring", infraStruMonitoringPojoArrayList.get(position).getDate_of_monitoring());
                    intentdd.putExtra("village_id", infraStruMonitoringPojoArrayList.get(position).getVillage_id());
                    intentdd.putExtra("infra_monitoring_id", infraStruMonitoringPojoArrayList.get(position).getInfra_monitoring_id());
                    intentdd.putExtra("infra_monitoring_id", infraStruMonitoringPojoArrayList.get(position).getInfra_monitoring_id());
                    intentdd.putExtra("question1", infraStruMonitoringPojoArrayList.get(position).getAvg_shortfall_attendence());
                    intentdd.putExtra("question2", infraStruMonitoringPojoArrayList.get(position).getEffective_teaching_through_chalk_board());
                    intentdd.putExtra("question3", infraStruMonitoringPojoArrayList.get(position).getTlm_method_teaching());
                    intentdd.putExtra("question4", infraStruMonitoringPojoArrayList.get(position).getDigital_method_teaching_increase_attendance());
                    intentdd.putExtra("question5", infraStruMonitoringPojoArrayList.get(position).getEdu_method_ingovt_need_pedagogy());
                    intentdd.putExtra("question6", infraStruMonitoringPojoArrayList.get(position).getDigital_method_teaching());
                    intentdd.putExtra("question7", infraStruMonitoringPojoArrayList.get(position).getSupport_from_govt_econtent_it_infrastruc());
                    intentdd.putExtra("question8", infraStruMonitoringPojoArrayList.get(position).getDigital_add_help_stud());
                    intentdd.putExtra("question9", infraStruMonitoringPojoArrayList.get(position).getInstallation_emuskaan());
                    intentdd.putExtra("question10", infraStruMonitoringPojoArrayList.get(position).getIs_emuskan_being());
                    intentdd.putExtra("question11", infraStruMonitoringPojoArrayList.get(position).getIs_there_installed_library());
                    intentdd.putExtra("question12", infraStruMonitoringPojoArrayList.get(position).getIs_school_undertaken());
                    intentdd.putExtra("question13", infraStruMonitoringPojoArrayList.get(position).getIs_there_a_kitchen_garden());
                    intentdd.putExtra("question14", infraStruMonitoringPojoArrayList.get(position).getIs_there_science_lab());
                    sharedPreferences.setString("resource_name",resource_name);
                    intentdd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentdd.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intentdd);
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
        CardView card_Viewsss;
        TextView tv_Village,tv_date_monitoring,tv_Resource_Name,tv_kitchen_garden,tv_emuskan,tv_library,tv_science_lab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Village = itemView.findViewById(R.id.tv_Village);
            tv_date_monitoring = itemView.findViewById(R.id.tv_date_monitoring);
            iv_camera_list = itemView.findViewById(R.id.iv_camera_list);
            tv_Resource_Name = itemView.findViewById(R.id.tv_Resource_Name);
            card_Viewsss = itemView.findViewById(R.id.card_Viewsss);
        }
    }


}
