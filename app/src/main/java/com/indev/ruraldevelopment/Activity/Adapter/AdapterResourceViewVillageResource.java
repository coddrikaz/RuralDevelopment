package com.indev.ruraldevelopment.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class AdapterResourceViewVillageResource extends RecyclerView.Adapter<AdapterResourceViewVillageResource.ViewHolder> {
    private final Context context;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    SqliteHelper sqliteHelper;
    String resource_Type_name="";


    public AdapterResourceViewVillageResource(Context context, ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList) {
        this.context = context;
        this.resourceMappingPojoArrayList = resourceMappingPojoArrayList;
        sqliteHelper = new SqliteHelper(context);
    }

    @NonNull
    @Override
    public AdapterResourceViewVillageResource.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cutom_view_resource, parent, false);
        return new AdapterResourceViewVillageResource.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterResourceViewVillageResource.ViewHolder holder, int position) {
//        holder.ResourceType.setText(resourceMappingPojoArrayList.get(position).getResource_type_id());
        resource_Type_name=sqliteHelper.getTypeNamesss(String.valueOf(resourceMappingPojoArrayList.get(position).getResource_id()));
        holder.ResourceType.setText(resource_Type_name);
        holder.ResourceName.setText(resourceMappingPojoArrayList.get(position).getResource_mapping_name());
        holder.ResourceStatus.setText(resourceMappingPojoArrayList.get(position).getResource_status());
    }


    @Override
    public int getItemCount() {
        return resourceMappingPojoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ResourceType, ResourceName, ResourceStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ResourceType = itemView.findViewById(R.id.ResourceType);
            ResourceName = itemView.findViewById(R.id.ResourceName);
            ResourceStatus = itemView.findViewById(R.id.ResourceStatus);

        }
    }
}
