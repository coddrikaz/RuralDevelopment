package com.indev.ruraldevelopment.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class AdapterMemberViewVillageResource extends RecyclerView.Adapter<AdapterMemberViewVillageResource.ViewHolder> {
   private final Context context;
   ArrayList<Committee_Table>committee_tableArrayList;
   SqliteHelper sqliteHelper;
   SharedPrefHelper sharedPrefHelper;
   String village_id="";

    public AdapterMemberViewVillageResource(Context context, ArrayList<Committee_Table> committee_tableArrayList) {
        this.context = context;
        this.committee_tableArrayList = committee_tableArrayList;
        sqliteHelper=new SqliteHelper(context);
        sharedPrefHelper=new SharedPrefHelper(context);
    }

    @NonNull
    @Override
    public AdapterMemberViewVillageResource.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_profile_member, parent, false);
        return new AdapterMemberViewVillageResource.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterMemberViewVillageResource.ViewHolder holder, int position) {
//        village_id = sharedPrefHelper.getString("village_id", "");
//        committee_tableArrayList = sqliteHelper.getCommitteeDataInVillage(village_id);
        String memberRole=sqliteHelper.getColumnName("role_name","member_role"," where role_id='"+committee_tableArrayList.get(position).getRole_id()+"'");
        holder.Name.setText(committee_tableArrayList.get(position).getMember_name());
        holder.MobileNumber.setText(committee_tableArrayList.get(position).getMobile_no());
        holder.Role.setText(memberRole);
    }



    @Override
    public int getItemCount() {
        return committee_tableArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, MobileNumber, Role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            MobileNumber = itemView.findViewById(R.id.MobileNumber);
            Role = itemView.findViewById(R.id.Role);

        }
    }
}

