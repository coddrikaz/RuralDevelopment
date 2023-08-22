package com.indev.ruraldevelopment.Activity.Adapter;


import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL5;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.ServeyView;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterStakeHolderSurvey extends RecyclerView.Adapter<AdapterStakeHolderSurvey.ViewHolder> {
     Context context;
    ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList;
    SharedPrefHelper sharedPreferences;
    String id_type_id="";


    public AdapterStakeHolderSurvey(Context context, ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList) {
        this.context = context;
        this.stakeHolderSurveyModelArrayList = stakeHolderSurveyModelArrayList;
        sharedPreferences=new SharedPrefHelper(context);

    }

    @NonNull
    @Override
    public AdapterStakeHolderSurvey.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_stakeholder_survey, parent, false);
        return new AdapterStakeHolderSurvey.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStakeHolderSurvey.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputDateStr=stakeHolderSurveyModelArrayList.get(position).getDate_of_survey();
        Date date2 = null;
        try {
            date2 = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date2);
        holder.servey_holder_dates.setText(" : "+outputDateStr);
        holder.servey_holder_person_name.setText(" : "+stakeHolderSurveyModelArrayList.get(position).getPerson_name());
        holder.servey_holder_person_degignation.setText(" : "+stakeHolderSurveyModelArrayList.get(position).getDesignation());

        if (stakeHolderSurveyModelArrayList.get(position).getImage() != null && stakeHolderSurveyModelArrayList.get(position).getImage().length() > 200) {
            byte[] decodedString = Base64.decode(stakeHolderSurveyModelArrayList.get(position).getImage(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.iv_camera.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL5+stakeHolderSurveyModelArrayList.get(position).getImage())
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(holder.iv_camera);
            }catch (Exception e){
                Log.d("Exception",""+e);
            }

        }

        holder.servey_view_all_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ServeyView.class);
                int id =stakeHolderSurveyModelArrayList.get(position).getSurvey_id();
                intent.putExtra("survey_id",id);

 //               sharedPreferences.setString("resource",id_type_id);
  //              sharedPreferences.setString("resourceType_id", String.valueOf(stakeHolderSurveyModelArrayList.get(position).getResource_id()));

                intent.putExtra("person_name",stakeHolderSurveyModelArrayList.get(position).getPerson_name());
                intent.putExtra("date_of_survey",stakeHolderSurveyModelArrayList.get(position).getDate_of_survey());
                intent.putExtra("designation",stakeHolderSurveyModelArrayList.get(position).getDesignation());
                intent.putExtra("remark",stakeHolderSurveyModelArrayList.get(position).getRemark());
                intent.putExtra("stakeholder_category_id",stakeHolderSurveyModelArrayList.get(position).getStakeholder_category_id());
                intent.putExtra("heard_about_jubliant_life_science",stakeHolderSurveyModelArrayList.get(position).getHeard_about_jubliant_life_science());
                intent.putExtra("employee_discussed_about_company_activity",stakeHolderSurveyModelArrayList.get(position).getEmployee_discussed_about_company_activity());
                intent.putExtra("equipped_to_manage_operation",stakeHolderSurveyModelArrayList.get(position).getEquipped_to_manage_operation());
                intent.putExtra("mock_drill_related_to_emergency",stakeHolderSurveyModelArrayList.get(position).getMock_drill_related_to_emergency());
                intent.putExtra("discussion_conduct_for_concern",stakeHolderSurveyModelArrayList.get(position).getDiscussion_conduct_for_concern());
                
//                intent.putExtra("image",stakeHolderSurveyModelArrayList.get(position).getImage());
                  sharedPreferences.setString("images",stakeHolderSurveyModelArrayList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stakeHolderSurveyModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView servey_holder_person_degignation, servey_holder_person_name, servey_holder_dates,stakeholder_category;
        CardView servey_view_all_details;
        ImageView iv_camera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            servey_holder_dates=itemView.findViewById(R.id.servey_holder_dates);
            servey_holder_person_name = itemView.findViewById(R.id.servey_holder_person_name);
            servey_holder_person_degignation = itemView.findViewById(R.id.servey_holder_person_degignation);
            servey_view_all_details=itemView.findViewById(R.id.servey_view_all_details);
            iv_camera = itemView.findViewById(R.id.iv_camera);

        }
    }
}



