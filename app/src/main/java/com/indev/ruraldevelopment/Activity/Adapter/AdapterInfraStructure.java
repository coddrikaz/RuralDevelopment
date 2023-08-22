package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;

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

import com.indev.ruraldevelopment.Activity.Activity.InfraStraMonitoringList;
import com.indev.ruraldevelopment.Activity.Activity.InfraView;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.baseline_Listing;
import com.indev.ruraldevelopment.BaselineListing;
import com.indev.ruraldevelopment.R;
import com.indev.ruraldevelopment.baseline_question;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterInfraStructure extends RecyclerView.Adapter<AdapterInfraStructure.ViewHolder> {
    private final Context context;
    ArrayList<InfraStructureModel> infraStructureModelArrayList;
    SharedPrefHelper sharedPreferences;
    SqliteHelper sqliteHelper;
    String id_type_id="";
    String resource_Type_name="";
    String baseline="";
    String Subresource_Type_name="";
    String Subresource_id="";




    public AdapterInfraStructure(Context context, ArrayList<InfraStructureModel> infraStructureModelArrayList) {
        this.context = context;
        this.infraStructureModelArrayList = infraStructureModelArrayList;
        sharedPreferences=new SharedPrefHelper(context);
        sqliteHelper=new SqliteHelper(context);

    }

    @NonNull
    @Override
    public AdapterInfraStructure.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_infrastructure, parent, false);
        return new AdapterInfraStructure.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInfraStructure.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvStructureName.setText(" : "+infraStructureModelArrayList.get(position).getInfra_structure_name());
        holder.tvCaretakerName.setText(" : "+infraStructureModelArrayList.get(position).getCaretaker());
        holder.tvMobile_no.setText(" : "+infraStructureModelArrayList.get(position).getMobile_no());
        Subresource_id =infraStructureModelArrayList.get(position).getSubresource_id();
        resource_Type_name = sqliteHelper.getTypeName(String.valueOf(infraStructureModelArrayList.get(position).getResource_id()));
        Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStructureModelArrayList.get(position).getSubresource_id()));
        holder.tvresourcename.setText(" : "+resource_Type_name);
        holder.tvSubresourcename.setText(" : "+Subresource_Type_name);


        if (Subresource_Type_name.equals("E-muskaan")){
            holder.infra_structure_baseline.setVisibility(View.VISIBLE);
        }else {
            holder.infra_structure_baseline.setVisibility(View.GONE);
        }

        if (infraStructureModelArrayList.get(position).getImage() != null && infraStructureModelArrayList.get(position).getImage().length() > 200) {
            byte[] decodedString = Base64.decode(infraStructureModelArrayList.get(position).getImage(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.iv_camera.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL2+infraStructureModelArrayList.get(position).getImage())
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(holder.iv_camera);
            }catch (Exception e){
                Log.d("Exception",""+e);
            }

        }



        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(context, InfraView.class);
                    Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStructureModelArrayList.get(position).getSubresource_id()));
                    resource_Type_name = sqliteHelper.getTypeName(String.valueOf(infraStructureModelArrayList.get(position).getResource_id()));

                    sharedPreferences.setString("resource", id_type_id);
                    sharedPreferences.setString("Subresource_Type_name", Subresource_Type_name);
                    sharedPreferences.setString("resource_Type_name", resource_Type_name);
                    sharedPreferences.setString("resource_mapping_id", String.valueOf(infraStructureModelArrayList.get(position).getResource_id()));
                    sharedPreferences.setString("Subresource_mapping_id", String.valueOf(infraStructureModelArrayList.get(position).getSite_name()));

//                intent.putExtra("resourceId",infraStructureModelArrayList.get(position).getResource_id());
                    int id = infraStructureModelArrayList.get(position).getInfra_id();
                    intent.putExtra("infra_id", id);
                    intent.putExtra("image", infraStructureModelArrayList.get(position).getImage());
                    intent.putExtra("site_name", infraStructureModelArrayList.get(position).getSite_name());
                    intent.putExtra("infra_structure_name", infraStructureModelArrayList.get(position).getInfra_structure_name());
                    intent.putExtra("caretaker", infraStructureModelArrayList.get(position).getCaretaker());
                    intent.putExtra("mobile_no", infraStructureModelArrayList.get(position).getMobile_no());
                    intent.putExtra("start_date", infraStructureModelArrayList.get(position).getStart_date());
                    intent.putExtra("end_date", infraStructureModelArrayList.get(position).getEnd_date());
                    intent.putExtra("address", infraStructureModelArrayList.get(position).getAddress());
                    intent.putExtra("amount", infraStructureModelArrayList.get(position).getAmount());
                    intent.putExtra("expected_beneficial", infraStructureModelArrayList.get(position).getExpected_beneficial());
                    intent.putExtra("description", infraStructureModelArrayList.get(position).getDescription());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        holder.infra_structure_baseline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context , BaselineListing.class);
                Subresource_id=infraStructureModelArrayList.get(position).getSubresource_id();
                resource_Type_name = sqliteHelper.getTypeName(String.valueOf(infraStructureModelArrayList.get(position).getResource_id()));
                Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStructureModelArrayList.get(position).getSubresource_id()));
                int id = infraStructureModelArrayList.get(position).getInfra_id();
                intent.putExtra("infra_idddd",String.valueOf(id));
                intent.putExtra("infra_structure_name", infraStructureModelArrayList.get(position).getInfra_structure_name());
                intent.putExtra("resource_name", resource_Type_name);
                intent.putExtra("baseline", baseline);
                intent.putExtra("Subresource_Type_name", Subresource_Type_name);
                intent.putExtra("Subresource_id", Subresource_id);
                int resourse_id=infraStructureModelArrayList.get(position).getResource_id();
                intent.putExtra("resource_id", String.valueOf(resourse_id));
                intent.putExtra("infra_structure_name", infraStructureModelArrayList.get(position).getInfra_structure_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               context.startActivity(intent);

            }
        });
        holder.infra_structure_Monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, InfraStraMonitoringList.class);
                resource_Type_name = sqliteHelper.getTypeName(String.valueOf(infraStructureModelArrayList.get(position).getResource_id()));
                Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStructureModelArrayList.get(position).getSubresource_id()));
                int id = infraStructureModelArrayList.get(position).getInfra_id();
                intent.putExtra("infra_idddd",String.valueOf(id));
                intent.putExtra("infra_structure_name", infraStructureModelArrayList.get(position).getInfra_structure_name());
                intent.putExtra("village_id", infraStructureModelArrayList.get(position).getVillage_id());
                intent.putExtra("resource_name", resource_Type_name);
                intent.putExtra("Subresource_Type_name", Subresource_Type_name);
                intent.putExtra("Subresource_id", infraStructureModelArrayList.get(position).getSubresource_id());
                int resourse_id=infraStructureModelArrayList.get(position).getResource_id();
                intent.putExtra("resource_id", String.valueOf(resourse_id));
                intent.putExtra("infra_structure_name", infraStructureModelArrayList.get(position).getInfra_structure_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return infraStructureModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubresourcename,tvresourcename, tvNameoftheInfra, tvSiteName,tvStructureName,tvMobile_no,tvEndDate,tvStartDate,tvAddress,tvAmount,tvCaretakerName;
        ImageView iv_camera;
        CardView btnView;
        Button infra_structure_Monitor,infra_structure_baseline;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStructureName = itemView.findViewById(R.id.tvNameoftheInfra);
            tvCaretakerName = itemView.findViewById(R.id.tvCaretakerName);
            tvMobile_no = itemView.findViewById(R.id.tvMobile_no);
            iv_camera = itemView.findViewById(R.id.iv_camera);
            btnView = itemView.findViewById(R.id.btnView);
            infra_structure_Monitor = itemView.findViewById(R.id.infra_structure_Monitor);
            tvresourcename = itemView.findViewById(R.id.tvresourcename);
            tvSubresourcename = itemView.findViewById(R.id.tvSubresourcename);
            infra_structure_baseline = itemView.findViewById(R.id.infra_structure_baseline);

        }
    }
}
