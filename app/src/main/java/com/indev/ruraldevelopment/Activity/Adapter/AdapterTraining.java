package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL3;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL4;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Activity.AddTraining;
import com.indev.ruraldevelopment.Activity.Activity.CommitteeMemberView;
import com.indev.ruraldevelopment.Activity.Activity.Moniter_View;
import com.indev.ruraldevelopment.Activity.Activity.TrainingView;
import com.indev.ruraldevelopment.Activity.Activity.ViewVillageProfile;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterTraining extends RecyclerView.Adapter<AdapterTraining.ViewHolder> {
    private final Context context;
    ArrayList<TrainingModel> trainingModelArrayList;
    ArrayList<TrainingMultipleImagePojo> trainingMultipleImagePojoArrayList;
    SharedPrefHelper sharedPrefHelper;
    String id = "0";
    String image = "";
    SqliteHelper sqliteHelper;


    public AdapterTraining(Context context, ArrayList<TrainingModel> trainingModelArrayList) {
        this.context = context;
        this.trainingModelArrayList = trainingModelArrayList;
        trainingMultipleImagePojoArrayList = new ArrayList<>();
        sharedPrefHelper = new SharedPrefHelper(context);
        sqliteHelper = new SqliteHelper(context);

    }

    @NonNull
    @Override
    public AdapterTraining.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_training, parent, false);
        return new AdapterTraining.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTraining.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvNameofTraining.setText(" : "+trainingModelArrayList.get(position).getTraining_name());
        holder.tvNameofTrainer.setText(" : "+trainingModelArrayList.get(position).getTrainer_name());
        holder.tvStartDate.setText(" : "+trainingModelArrayList.get(position).getStart_date());
        holder.tvTotalAttendance.setText(" : "+trainingModelArrayList.get(position).getTotal_attendance());


        try {
            image = sqliteHelper.getColumnName("training_image_name", "training_image", "where training_id='" + trainingModelArrayList.get(position).getTraining_id() + "'");

            if (!image.equals("") && image.length() > 200) {
                byte[] decodedString = Base64.decode(image, Base64.NO_WRAP);
                InputStream inputStream = new ByteArrayInputStream(decodedString);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.iv_camera.setImageBitmap(bitmap);
            } else {
                try {
                    Picasso.get()
                            .load(BASE_URL3 + image)
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(holder.iv_camera);
                } catch (Exception e) {
                    Log.d("Exception", "" + e);
                }
            }

        } catch (Exception e) {
            Log.d("Exception", "" + e);
        }

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrainingView.class);
//                int resource_id = trainingModelArrayList.get(position).getResource_id();
//                int id =trainingModelArrayList.get(position).getTraining_id();
//                intent.putExtra("training_id",id);
                intent.putExtra("training_id", trainingModelArrayList.get(position).getTraining_id());
                intent.putExtra("training_name", trainingModelArrayList.get(position).getTraining_name());
                intent.putExtra("start_date", trainingModelArrayList.get(position).getStart_date());
                intent.putExtra("trainer_name", trainingModelArrayList.get(position).getTrainer_name());
                intent.putExtra("male", trainingModelArrayList.get(position).getMale());
                intent.putExtra("female", trainingModelArrayList.get(position).getFemale());
                intent.putExtra("total_attendance", trainingModelArrayList.get(position).getTotal_attendance());
                intent.putExtra("objective", trainingModelArrayList.get(position).getObjective());
//                intent.putExtra("resource_id", resource_id);
                intent.putExtra("training_image_name", trainingModelArrayList.get(position).getTraining_image_name());
                context.startActivity(intent);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTraining.class);
                intent.putExtra("type", "edit");
                intent.putExtra("id", trainingModelArrayList.get(position).getLocal_id());
                context.startActivity(intent);
            }
        });
//
//        holder.ll_training.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, TrainingView.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return trainingModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameofTraining, tvStartDate, tvEndDate, tvTotalAttendance, tvNameofTrainer;
        ImageView iv_camera;
        ImageView btnEdit, btnDlt;
        LinearLayout ll_training;
        CardView btnView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameofTraining = itemView.findViewById(R.id.tvNameofTraining);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);
            tvTotalAttendance = itemView.findViewById(R.id.tvTotalAttendance);
            iv_camera = itemView.findViewById(R.id.iv_camera);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnView = itemView.findViewById(R.id.trainingView);
            ll_training = itemView.findViewById(R.id.ll_training);
            tvNameofTrainer = itemView.findViewById(R.id.tvNameofTrainer);


        }
    }

}