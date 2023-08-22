package com.indev.ruraldevelopment.Activity.Adapter;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indev.ruraldevelopment.Activity.Activity.CommitteeMemberView;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.restApi.APIClient;
import com.indev.ruraldevelopment.Activity.restApi.RuralApi;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCommittee extends RecyclerView.Adapter<AdapterCommittee.ViewHolder> {
    private final Context context;
    ArrayList<Committee_Table>committee_tablesArrayList;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPrefHelper;
    public AdapterCommittee(Context context, ArrayList<Committee_Table> committee_tablesArrayList) {
        this.context = context;
        this.committee_tablesArrayList = committee_tablesArrayList;
        sqliteHelper = new SqliteHelper(context);
        sharedPrefHelper=new SharedPrefHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_committee_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String memberRole=sqliteHelper.getColumnName("role_name","member_role"," where role_id='"+committee_tablesArrayList.get(position).getRole_id()+"'");
        holder.tvName.setText(" : "+committee_tablesArrayList.get(position).getMember_name());
        holder.tvPhone_no.setText(" : "+committee_tablesArrayList.get(position).getMobile_no());
        holder.tvRole.setText(" : "+memberRole);
//        if(committee_tablesArrayList.get(position).getStatus()!=null) {
//            if ((committee_tablesArrayList.get(position).getStatus().equals("0"))) {
//                holder.bt_active.setText("InActive");
//                holder.bt_active.setEnabled(false);
//            }
//        }

        if (committee_tablesArrayList.get(position).getImage() != null && committee_tablesArrayList.get(position).getImage().length() > 200) {
            byte[] decodedString = Base64.decode(committee_tablesArrayList.get(position).getImage(), Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.iv_camera.setImageBitmap(bitmap);

        } else {
            try {
                Picasso.get()
                        .load(BASE_URL1 + committee_tablesArrayList.get(position).getImage())
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(holder.iv_camera);
            } catch (Exception e) {
                Log.d("Exception", "" + e);
            }
        }


        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommitteeMemberView.class);
                int id= committee_tablesArrayList.get(position).getMember_id();
                intent.putExtra("member_id", id);
                String memberRole=sqliteHelper.getColumnName("role_name","member_role"," where role_id='"+committee_tablesArrayList.get(position).getRole_id()+"'");
                intent.putExtra("member_name", committee_tablesArrayList.get(position).getMember_name());
                intent.putExtra("mobile_no", committee_tablesArrayList.get(position).getMobile_no());
                intent.putExtra("role_id",committee_tablesArrayList.get(position).getRole_id());
                intent.putExtra("image", committee_tablesArrayList.get(position).getImage());

                context.startActivity(intent);

            }
        });

        holder.bt_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                // builder.setIcon(R.drawable.ic_dialog_alert);
                builder.setTitle("Alert!");
                builder.setIcon(R.drawable.alert);
                builder.setMessage(R.string.are_you_sure_to_want_to_InActive);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to inactive from list

                        Committee_Table committee_table=new Committee_Table();
                        committee_table.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                        committee_table.setMember_id(committee_tablesArrayList.get(position).getMember_id());
                        Gson gson = new Gson();
                        String data = gson.toJson(committee_table);
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
                        UpdateActiveButton(body, String.valueOf(committee_tablesArrayList.get(position).getLocal_id()),holder);
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    private void UpdateActiveButton(RequestBody body, String localId, ViewHolder holder){
        ProgressDialog dialog = ProgressDialog.show(context, "", "Please Wait", true);
        APIClient.getRuralClient().create(RuralApi.class).UpdateActiveCommite(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_survey_id = jsonObject.optString("last_stake_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables

                        sqliteHelper.UpdateActiveButton("committee_member", "local_id='" + localId + "'");
                         holder.bt_active.setEnabled(false);
                        Toast.makeText(context, "" + message, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return committee_tablesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhone_no, tvRole;
        ImageView iv_camera;
        CardView btnView;
        ImageView btnEdit, btnDlt;
        Button bt_active;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone_no = itemView.findViewById(R.id.tvPhone_no);
            tvRole = itemView.findViewById(R.id.tvRole);
            iv_camera = itemView.findViewById(R.id.mCamera);
            btnView = itemView.findViewById(R.id.memberView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            bt_active = itemView.findViewById(R.id.bt_active);
//            btnDlt = itemView.findViewById(R.id.btnDlt);


        }
    }
}
