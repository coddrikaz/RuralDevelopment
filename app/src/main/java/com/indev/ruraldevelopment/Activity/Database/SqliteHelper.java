package com.indev.ruraldevelopment.Activity.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.indev.ruraldevelopment.Activity.Model.BlockPojo;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Model.DistrictPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Model.Infra_ResourceModel;
import com.indev.ruraldevelopment.Activity.Model.InfrasubResourceType;
import com.indev.ruraldevelopment.Activity.Model.MemberRolePojo;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourcePojo;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.Activity.Model.Stakeholder_CategoryPojo;
import com.indev.ruraldevelopment.Activity.Model.StatePojo;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SqliteHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "rural.db";
    static final int DATABASE_VERSION = 1;
    String DB_PATH_SUFFIX = "/databases/";
    int version;
    Context ctx;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Committee_Table.CREATE_TABLE);
        db.execSQL(ResourcePojo.CREATE_TABLE);
        db.execSQL(MemberRolePojo.CREATE_TABLE);
        db.execSQL(ResourceMappingPojo.CREATE_TABLE);
        db.execSQL(VillagePojo.CREATE_TABLE);
        db.execSQL(StatePojo.CREATE_TABLE);
        db.execSQL(ResourceMultipleImagePojo.CREATE_TABLE);
        db.execSQL(DistrictPojo.CREATE_TABLE);
        db.execSQL(Stakeholder_CategoryPojo.CREATE_TABLE);
        db.execSQL(BlockPojo.CREATE_TABLE);
        db.execSQL(MonitorModel.CREATE_TABLE);
        db.execSQL(VillageProfileModel.CREATE_TABLE);
        db.execSQL(TrainingModel.CREATE_TABLE);
        db.execSQL(InfraStructureModel.CREATE_TABLE);
        db.execSQL(StakeHolderSurveyModel.CREATE_TABLE);
        db.execSQL(MultipleImagePojo.CREATE_TABLE);
        db.execSQL(MonitorMultipleImagePojo.CREATE_TABLE);
        db.execSQL(TrainingMultipleImagePojo.CREATE_TABLE);
        db.execSQL(InfraStruMonitoringPojo.CREATE_TABLE);
        db.execSQL(InfraStruMultipleImagesPojo.CREATE_TABLE);
        db.execSQL(Infra_ResourceModel.CREATE_TABLE);
        db.execSQL(InfrasubResourceType.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        Log.e("version", "outside" + version);

        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public void dropTable(String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM'" + tablename + "'");
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
    }

    public void deleteData(String table,String column, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean ss=db.delete(table, column+"=" + id, null) > 0;

    }

    public void saveMasterTable(ContentValues contentValues, String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        long idsds = db.insert(tablename, null, contentValues);
        Log.d("LOG", idsds + "id");
        db.close();
    }


    public long InfraStructureMonitoring(InfraStruMonitoringPojo infraStruMonitoringPojo){
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("subresource_id", infraStruMonitoringPojo.getSubresource_id());
                values.put("infra_monitoring_id", infraStruMonitoringPojo.getInfra_monitoring_id());
                values.put("infra_id", infraStruMonitoringPojo.getInfra_id());
                values.put("resource_id", infraStruMonitoringPojo.getResource_id());
                values.put("village_id", infraStruMonitoringPojo.getVillage_id());
                values.put("infra_structure_name", infraStruMonitoringPojo.getInfra_structure_name());
                values.put("avg_shortfall_attendence", infraStruMonitoringPojo.getAvg_shortfall_attendence());
                values.put("effective_teaching_through_chalk_board", infraStruMonitoringPojo.getEffective_teaching_through_chalk_board());
                values.put("tlm_method_teaching", infraStruMonitoringPojo.getTlm_method_teaching());
                values.put("digital_method_teaching", infraStruMonitoringPojo.getDigital_method_teaching());
                values.put("edu_method_ingovt_need_pedagogy", infraStruMonitoringPojo.getEdu_method_ingovt_need_pedagogy());
                values.put("digital_method_teaching_increase_attendance", infraStruMonitoringPojo.getDigital_method_teaching_increase_attendance());
                values.put("support_from_govt_econtent_it_infrastruc", infraStruMonitoringPojo.getSupport_from_govt_econtent_it_infrastruc());
                values.put("digital_add_help_stud", infraStruMonitoringPojo.getDigital_add_help_stud());
                values.put("installation_emuskaan", infraStruMonitoringPojo.getInstallation_emuskaan());
                values.put("is_emuskan_being", infraStruMonitoringPojo.getIs_emuskan_being());
                values.put("is_school_undertaken", infraStruMonitoringPojo.getIs_school_undertaken());
                values.put("is_there_a_kitchen_garden", infraStruMonitoringPojo.getIs_there_a_kitchen_garden());
                values.put("date_of_monitoring", infraStruMonitoringPojo.getDate_of_monitoring());
                values.put("is_structure_functional", infraStruMonitoringPojo.getIs_structure_functional());
                values.put("is_structure_damaged", infraStruMonitoringPojo.getIs_structure_damaged());
                values.put("visibility_of_jubilant_brand", infraStruMonitoringPojo.getVisibility_of_jubilant_brand());
                values.put("is_there_installed_library", infraStruMonitoringPojo.getIs_there_installed_library());
                values.put("is_there_science_lab", infraStruMonitoringPojo.getIs_there_science_lab());
                values.put("description", infraStruMonitoringPojo.getDescription());
                values.put("state_id", infraStruMonitoringPojo.getState_id());
                values.put("block_id", infraStruMonitoringPojo.getBlock_id());
                values.put("created_at", infraStruMonitoringPojo.getCreated_at());
                values.put("app_version", infraStruMonitoringPojo.getApp_version());
                values.put("is_it_Installation", infraStruMonitoringPojo.getIs_it_Installation());

                values.put("longitude", infraStruMonitoringPojo.getLongitude());
                values.put("latitude", infraStruMonitoringPojo.getLatitude());
                values.put("district_id",infraStruMonitoringPojo.getDistrict_id());
                values.put("status", "0");
                values.put("user_id", infraStruMonitoringPojo.getUser_id());
                values.put("baseline", infraStruMonitoringPojo.getBaseline());
                values.put("monitoring", infraStruMonitoringPojo.getMonitoring());
                values.put("flag ","0");
//                values.put("images ",infraStruMonitoringPojo.getImages());
//                values.put("description", infraStruMonitoringPojo.getDescription());

//                values.put("flag", "0");

                ids = db.insert("infra_monitoring", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }
//    @SuppressLint("Range")
//    public ArrayList<InfraStruMonitoringPojo> getInfraCountMonitoring () {
//        ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            if (db != null && db.isOpen() && !db.isReadOnly()) {
//                String query = " select Count(flag),flag from  infra_monitoring where flag = 0";
//                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    cursor.moveToFirst();
//                    while (!cursor.isAfterLast()) {
//                        InfraStruMonitoringPojo infraStruMonitoringPojo = new InfraStruMonitoringPojo();
//                        infraStruMonitoringPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
////
//                        infraStruMonitoringPojoArrayList.add(infraStruMonitoringPojo);
//                        cursor.moveToNext();
//                    }
//                }
//                db.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            db.close();
//        }
//        return infraStruMonitoringPojoArrayList;
//    }


    @SuppressLint("Range")
    public ArrayList<InfraStruMonitoringPojo> getInfraStructureMonitoringList(String infra_id) {
        ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = " select * from  infra_monitoring  where infra_id ='"+infra_id+"'and monitoring=monitoring";

                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStruMonitoringPojo infraStruMonitoringPojo = new InfraStruMonitoringPojo();
                        infraStruMonitoringPojo.setIs_it_Installation(cursor.getString(cursor.getColumnIndex("is_it_Installation")));
                        infraStruMonitoringPojo.setVisibility_of_jubilant_brand(cursor.getString(cursor.getColumnIndex("visibility_of_jubilant_brand")));
                        infraStruMonitoringPojo.setIs_structure_functional(cursor.getString(cursor.getColumnIndex("is_structure_functional")));
                        infraStruMonitoringPojo.setIs_structure_damaged(cursor.getString(cursor.getColumnIndex("is_structure_damaged")));
                        infraStruMonitoringPojo.setInfra_monitoring_id(cursor.getString(cursor.getColumnIndex("infra_monitoring_id")));
                        infraStruMonitoringPojo.setVillage_id(cursor.getString(cursor.getColumnIndex("village_id")));
                        infraStruMonitoringPojo.setLocal_id(cursor.getString(cursor.getColumnIndex("local_id")));
                        infraStruMonitoringPojo.setInfra_id(cursor.getString(cursor.getColumnIndex("infra_id")));
                        infraStruMonitoringPojo.setDate_of_monitoring(cursor.getString(cursor.getColumnIndex("date_of_monitoring")));
                        infraStruMonitoringPojo.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        infraStruMonitoringPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
                        infraStruMonitoringPojoArrayList.add(infraStruMonitoringPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStruMonitoringPojoArrayList;
    }



    public ArrayList<InfraStruMonitoringPojo> getInfraStructureMonitoringListbaseline(String infra_id) {
        ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = " select * from  infra_monitoring  where infra_id ='"+infra_id+"' and baseline=baseline ";

                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStruMonitoringPojo infraStruMonitoringPojo = new InfraStruMonitoringPojo();
                        infraStruMonitoringPojo.setIs_it_Installation(cursor.getString(cursor.getColumnIndex("is_it_Installation")));
                        infraStruMonitoringPojo.setBaseline(cursor.getString(cursor.getColumnIndex("baseline")));
                        infraStruMonitoringPojo.setVisibility_of_jubilant_brand(cursor.getString(cursor.getColumnIndex("visibility_of_jubilant_brand")));
                        infraStruMonitoringPojo.setIs_structure_functional(cursor.getString(cursor.getColumnIndex("is_structure_functional")));
                        infraStruMonitoringPojo.setIs_structure_damaged(cursor.getString(cursor.getColumnIndex("is_structure_damaged")));
                        infraStruMonitoringPojo.setInfra_monitoring_id(cursor.getString(cursor.getColumnIndex("infra_monitoring_id")));
                        infraStruMonitoringPojo.setIs_school_undertaken(cursor.getString(cursor.getColumnIndex("is_school_undertaken")));
                        infraStruMonitoringPojo.setIs_there_a_kitchen_garden(cursor.getString(cursor.getColumnIndex("is_there_a_kitchen_garden")));
                        infraStruMonitoringPojo.setIs_there_installed_library(cursor.getString(cursor.getColumnIndex("is_there_installed_library")));
                        infraStruMonitoringPojo.setIs_there_science_lab(cursor.getString(cursor.getColumnIndex("is_there_science_lab")));
                        infraStruMonitoringPojo.setAvg_shortfall_attendence(cursor.getString(cursor.getColumnIndex("avg_shortfall_attendence")));
                        infraStruMonitoringPojo.setEdu_method_ingovt_need_pedagogy(cursor.getString(cursor.getColumnIndex("edu_method_ingovt_need_pedagogy")));
                        infraStruMonitoringPojo.setDigital_method_teaching_increase_attendance(cursor.getString(cursor.getColumnIndex("digital_method_teaching_increase_attendance")));
                        infraStruMonitoringPojo.setDigital_method_teaching(cursor.getString(cursor.getColumnIndex("digital_method_teaching")));
                        infraStruMonitoringPojo.setDigital_add_help_stud(cursor.getString(cursor.getColumnIndex("digital_add_help_stud")));
                        infraStruMonitoringPojo.setSupport_from_govt_econtent_it_infrastruc(cursor.getString(cursor.getColumnIndex("support_from_govt_econtent_it_infrastruc")));
                        infraStruMonitoringPojo.setIs_emuskan_being(cursor.getString(cursor.getColumnIndex("is_emuskan_being")));
                        infraStruMonitoringPojo.setInstallation_emuskaan(cursor.getString(cursor.getColumnIndex("installation_emuskaan")));
                        infraStruMonitoringPojo.setEffective_teaching_through_chalk_board(cursor.getString(cursor.getColumnIndex("effective_teaching_through_chalk_board")));
                        infraStruMonitoringPojo.setTlm_method_teaching(cursor.getString(cursor.getColumnIndex("tlm_method_teaching")));
                        infraStruMonitoringPojo.setVillage_id(cursor.getString(cursor.getColumnIndex("village_id")));
                        infraStruMonitoringPojo.setLocal_id(cursor.getString(cursor.getColumnIndex("local_id")));
                        infraStruMonitoringPojo.setInfra_id(cursor.getString(cursor.getColumnIndex("infra_id")));
                        infraStruMonitoringPojo.setDate_of_monitoring(cursor.getString(cursor.getColumnIndex("date_of_monitoring")));
                        infraStruMonitoringPojo.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        infraStruMonitoringPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
                        infraStruMonitoringPojoArrayList.add(infraStruMonitoringPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStruMonitoringPojoArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<InfraStruMonitoringPojo> getInfraStructureMonitoring() {
        ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = " select * from  infra_monitoring where flag = 0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStruMonitoringPojo infraStruMonitoringPojo = new InfraStruMonitoringPojo();

                        infraStruMonitoringPojo.setSubresource_id(cursor.getString(cursor.getColumnIndex("subresource_id")));
                        infraStruMonitoringPojo.setState_id(cursor.getString(cursor.getColumnIndex("state_id")));
                        infraStruMonitoringPojo.setVillage_id(cursor.getString(cursor.getColumnIndex("village_id")));
                        infraStruMonitoringPojo.setDistrict_id(cursor.getString(cursor.getColumnIndex("district_id")));
                        infraStruMonitoringPojo.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        infraStruMonitoringPojo.setInfra_structure_name(cursor.getString(cursor.getColumnIndex("infra_structure_name")));
                        infraStruMonitoringPojo.setIs_structure_functional(cursor.getString(cursor.getColumnIndex("is_structure_functional")));
                        infraStruMonitoringPojo.setIs_structure_damaged(cursor.getString(cursor.getColumnIndex("is_structure_damaged")));
                        infraStruMonitoringPojo.setVisibility_of_jubilant_brand(cursor.getString(cursor.getColumnIndex("visibility_of_jubilant_brand")));
                        infraStruMonitoringPojo.setLocal_id(cursor.getString(cursor.getColumnIndex("local_id")));
                        infraStruMonitoringPojo.setInfra_id(cursor.getString(cursor.getColumnIndex("infra_id")));
                        infraStruMonitoringPojo.setInfra_monitoring_id(cursor.getString(cursor.getColumnIndex("infra_monitoring_id")));
                        infraStruMonitoringPojo.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                        infraStruMonitoringPojo.setState_id(cursor.getString(cursor.getColumnIndex("state_id")));
                        infraStruMonitoringPojo.setBlock_id(cursor.getString(cursor.getColumnIndex("block_id")));
                        infraStruMonitoringPojo.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        infraStruMonitoringPojo.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        infraStruMonitoringPojo.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));
                        infraStruMonitoringPojo.setDate_of_monitoring(cursor.getString(cursor.getColumnIndex("date_of_monitoring")));
                        infraStruMonitoringPojo.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        infraStruMonitoringPojo.setIs_there_science_lab(cursor.getString(cursor.getColumnIndex("is_there_science_lab")));
                        infraStruMonitoringPojo.setIs_there_installed_library(cursor.getString(cursor.getColumnIndex("is_there_installed_library")));
                        infraStruMonitoringPojo.setIs_school_undertaken(cursor.getString(cursor.getColumnIndex("is_school_undertaken")));
                        infraStruMonitoringPojo.setIs_there_a_kitchen_garden(cursor.getString(cursor.getColumnIndex("is_there_a_kitchen_garden")));
                        infraStruMonitoringPojo.setAvg_shortfall_attendence(cursor.getString(cursor.getColumnIndex("avg_shortfall_attendence")));
                        infraStruMonitoringPojo.setEffective_teaching_through_chalk_board(cursor.getString(cursor.getColumnIndex("effective_teaching_through_chalk_board")));
                        infraStruMonitoringPojo.setTlm_method_teaching(cursor.getString(cursor.getColumnIndex("tlm_method_teaching")));
                        infraStruMonitoringPojo.setDigital_method_teaching(cursor.getString(cursor.getColumnIndex("digital_method_teaching")));
                        infraStruMonitoringPojo.setEdu_method_ingovt_need_pedagogy(cursor.getString(cursor.getColumnIndex("edu_method_ingovt_need_pedagogy")));
                        infraStruMonitoringPojo.setDigital_method_teaching_increase_attendance(cursor.getString(cursor.getColumnIndex("digital_method_teaching_increase_attendance")));
                        infraStruMonitoringPojo.setSupport_from_govt_econtent_it_infrastruc(cursor.getString(cursor.getColumnIndex("support_from_govt_econtent_it_infrastruc")));
                        infraStruMonitoringPojo.setDigital_add_help_stud(cursor.getString(cursor.getColumnIndex("digital_add_help_stud")));
                        infraStruMonitoringPojo.setInstallation_emuskaan(cursor.getString(cursor.getColumnIndex("installation_emuskaan")));
                        infraStruMonitoringPojo.setIs_emuskan_being(cursor.getString(cursor.getColumnIndex("is_emuskan_being")));
                        infraStruMonitoringPojo.setIs_it_Installation(cursor.getString(cursor.getColumnIndex("is_it_Installation")));
                        infraStruMonitoringPojoArrayList.add(infraStruMonitoringPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStruMonitoringPojoArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<InfraStruMultipleImagesPojo> inframlistingimages(String infra_monitoring_id) {
        ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = " select infra_monitoring_img from monitor_infra_image where infra_monitoring_id='"+infra_monitoring_id+"' ";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStruMultipleImagesPojo infraStruMultipleImagesPojo = new InfraStruMultipleImagesPojo();
                        infraStruMultipleImagesPojo.setImage(cursor.getString(cursor.getColumnIndex("infra_monitoring_img")));
                        infraStruMultipleImagesPojoArrayList.add(infraStruMultipleImagesPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStruMultipleImagesPojoArrayList;
    }



    public long infrasavemonitoringimages(InfraStruMultipleImagesPojo infraStruMultipleImagesPojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("infra_monitoring_img", infraStruMultipleImagesPojo.getInfra_monitoring_img());
                values.put("user_id", infraStruMultipleImagesPojo.getUser_id());
                values.put("infra_monitoring_id", infraStruMultipleImagesPojo.getMonitor_image_id());
                values.put("status", "0");
                ids = db.insert("monitor_infra_image", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }









    public long saveHousehold(Committee_Table committee_table) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("member_id", committee_table.getMember_id());
                values.put("member_name", committee_table.getMember_name());
                values.put("mobile_no", committee_table.getMobile_no());
                values.put("role_id", committee_table.getRole_id());
                values.put("app_version", committee_table.getApp_version());
                values.put("image", committee_table.getImage());
                values.put("resource_id", committee_table.getResource_id());
                values.put("user_id", committee_table.getUser_id());
                values.put("state_id", committee_table.getState_id());
                values.put("district_id", committee_table.getDistrict_id());
                values.put("block_id", committee_table.getBlock_id());
                values.put("village_id", committee_table.getVillage_id());
                values.put("village_profile_id", committee_table.getVillage_profile_id());
                values.put("latitude", committee_table.getLatitude());
                values.put("longitude", committee_table.getLongitude());
                values.put("created_at", committee_table.getCreated_at());
                values.put("status", committee_table.getStatus());
                values.put("flag", "0");

                ids = db.insert("committee_member", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long updateHouseholdMember(Committee_Table committee_table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("local_id", committee_table.getLocal_id());
                values.put("member_name", committee_table.getMember_name());
                values.put("mobile_no", committee_table.getMobile_no());
                values.put("role_id", committee_table.getRole_id());
                values.put("image", committee_table.getImage());

                ids = db.update("committee_member", values, "local_Id = ?", new String[]{id});
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHousehold(ResourceMappingPojo resourceMappingPojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("resource_mapping_id", resourceMappingPojo.getResource_mapping_id());
                values.put("resource_mapping_name", resourceMappingPojo.getResource_mapping_name());
                values.put("resource_id", resourceMappingPojo.getResource_id());
                values.put("resource_status", resourceMappingPojo.getResource_status());
                values.put("address", resourceMappingPojo.getAddress());
                values.put("description", resourceMappingPojo.getDescription());
                values.put("app_version", resourceMappingPojo.getApp_version());
                values.put("image", resourceMappingPojo.getImage());
                values.put("user_id", resourceMappingPojo.getUser_id());
                values.put("state_id", resourceMappingPojo.getState_id());
                values.put("district_id", resourceMappingPojo.getDistrict_id());
                values.put("block_id", resourceMappingPojo.getBlock_id());
                values.put("resource_mapping_date", resourceMappingPojo.getResource_mapping_date());
                values.put("village_profile_id", resourceMappingPojo.getVillage_profile_id());
                values.put("village_id", resourceMappingPojo.getVillage_id());
                values.put("resource_id", resourceMappingPojo.getResource_id());
                values.put("latitude", resourceMappingPojo.getLatitude());
                values.put("longitude", resourceMappingPojo.getLongitude());
                values.put("created_at", resourceMappingPojo.getCreated_at());
                values.put("contact_mob_no", resourceMappingPojo.getContact_mob_no());
                values.put("contact_person", resourceMappingPojo.getContact_person());
                values.put("status", resourceMappingPojo.getStatus());
                values.put("flag", "0");


                ids = db.insert("resource_mapping", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long saveVillageImage(MultipleImagePojo multipleImagePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("village_profile_id", multipleImagePojo.getVillage_profile_id());
                values.put("village_profile_img", multipleImagePojo.getVillage_profile_img());
                values.put("status", multipleImagePojo.getStatus());
                ids = db.insert("village_profile_image", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHousehold(ResourceMultipleImagePojo resourceMultipleImagePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("resource_mapping_id", resourceMultipleImagePojo.getResource_mapping_id());
                values.put("user_id", resourceMultipleImagePojo.getUser_id());
                values.put("resource_mapping_image", resourceMultipleImagePojo.getResource_mapping_image());
                values.put("status", resourceMultipleImagePojo.getStatus());
                ids = db.insert("resuorce_mapping_image", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long saveHousehold(TrainingMultipleImagePojo trainingMultipleImagePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("training_id", trainingMultipleImagePojo.getTraining_id());
                values.put("training_image_id", trainingMultipleImagePojo.getTraining_image_id());
                values.put("user_id", trainingMultipleImagePojo.getUser_id());
                values.put("training_image_name", trainingMultipleImagePojo.getTraining_image_name());
                values.put("status", trainingMultipleImagePojo.getStatus());
                ids = db.insert("training_image", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHousehold(MonitorMultipleImagePojo monitorMultipleImagePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("monitor_resource_id", monitorMultipleImagePojo.getMonitor_resource_id());
                values.put("user_id", monitorMultipleImagePojo.getUser_id());
                values.put("monitor_resource_image", monitorMultipleImagePojo.getMonitor_resource_image());
                values.put("status", monitorMultipleImagePojo.getStatus());
                ids = db.insert("moniter_resource_image", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long saveHousehold(MonitorModel monitorModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("monitor_resource_id", monitorModel.getMonitor_resource_id());
                values.put("resource_mapping_id", monitorModel.getResource_mapping_id());
                values.put("date_of_monitor", monitorModel.getDate_of_monitor());
                values.put("farming_near_structure", monitorModel.getFarming_near_structure());
//                values.put("sewage_at_pond", monitorModel.getSewage_at_pond());
                values.put("structure_functional",monitorModel.getStructure_functional());
                values.put("damaged_structure", monitorModel.getDamaged_structure());
                values.put("jubliant_visibility_board_near_structure", monitorModel.getJubliant_visibility_board_near_structure());
                values.put("description", monitorModel.getDescription());
                values.put("app_version", monitorModel.getApp_version());
                values.put("image", monitorModel.getImage());
                values.put("resource_id", monitorModel.getResource_id());
                values.put("user_id", monitorModel.getUser_id());
                values.put("state_id", monitorModel.getState_id());
                values.put("district_id", monitorModel.getDistrict_id());
                values.put("block_id", monitorModel.getBlock_id());
                values.put("village_id", monitorModel.getVillage_id());
                values.put("latitude", monitorModel.getLatitude());
                values.put("longitude", monitorModel.getLongitude());
                values.put("created_at", monitorModel.getCreated_at());
                values.put("status", monitorModel.getStatus());
                values.put("flag", "0");

//                values.put("iv_camera", monitorModel.getIv_camera());

                ids = db.insert("monitor_resource", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHouseholdR(StakeHolderSurveyModel stakeHolderSurveyModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("survey_id", stakeHolderSurveyModel.getSurvey_id());
                values.put("date_of_survey", stakeHolderSurveyModel.getDate_of_survey());
                values.put("financial_year", stakeHolderSurveyModel.getFinancial_year());
                values.put("person_name", stakeHolderSurveyModel.getPerson_name());
                values.put("designation", stakeHolderSurveyModel.getDesignation());
                values.put("stakeholder_category_id", stakeHolderSurveyModel.getStakeholder_category_id());
                values.put("heard_about_jubliant_life_science", stakeHolderSurveyModel.getHeard_about_jubliant_life_science());
                values.put("employee_discussed_about_company_activity", stakeHolderSurveyModel.getEmployee_discussed_about_company_activity());
                values.put("equipped_to_manage_operation ", stakeHolderSurveyModel.getEquipped_to_manage_operation());
                values.put("mock_drill_related_to_emergency", stakeHolderSurveyModel.getMock_drill_related_to_emergency());
                values.put("discussion_conduct_for_concern", stakeHolderSurveyModel.getDiscussion_conduct_for_concern());
                values.put("app_version", stakeHolderSurveyModel.getApp_version());
                values.put("image", stakeHolderSurveyModel.getImage());
                values.put("resource_id", stakeHolderSurveyModel.getResource_id());
                values.put("user_id", stakeHolderSurveyModel.getUser_id());
                values.put("state_id", stakeHolderSurveyModel.getState_id());
                values.put("district_id", stakeHolderSurveyModel.getDistrict_id());
                values.put("block_id", stakeHolderSurveyModel.getBlock_id());
                values.put("village_id", stakeHolderSurveyModel.getVillage_id());
                values.put("latitude", stakeHolderSurveyModel.getLatitude());
                values.put("longitude", stakeHolderSurveyModel.getLongitude());
                values.put("created_at", stakeHolderSurveyModel.getCreated_at());
                values.put("status", stakeHolderSurveyModel.getStatus());
                values.put("remark", stakeHolderSurveyModel.getRemark());
                values.put("flag", "0");

                ids = db.insert("stakeholder_survey", null, values);
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return ids;
    }

    public long saveHousehold(VillageProfileModel villageProfileModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("village_profile_id", villageProfileModel.getVillage_profile_id());
                values.put("population", villageProfileModel.getPopulation());
                values.put("pradhan_name", villageProfileModel.getPradhan_name());
                values.put("mobile_no", villageProfileModel.getMobile_no());
                values.put("app_version", villageProfileModel.getApp_version());
                values.put("image", villageProfileModel.getImage());
                values.put("resource_id", villageProfileModel.getResource_id());
                values.put("user_id", villageProfileModel.getUser_id());
                values.put("state_id", villageProfileModel.getState_id());
                values.put("district_id", villageProfileModel.getDistrict_id());
                values.put("block_id", villageProfileModel.getBlock_id());
                values.put("village_id", villageProfileModel.getVillage_id());
                values.put("latitude", villageProfileModel.getLatitude());
                values.put("longitude", villageProfileModel.getLongitude());
                values.put("created_at", villageProfileModel.getCreated_at());
                values.put("status", villageProfileModel.getStatus());
                values.put("flag", villageProfileModel.getFlag());

                ids = db.insert("village_profile", null, values);
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long updateHousehold(VillageProfileModel villageProfileModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("population", villageProfileModel.getPopulation());
                values.put("pradhan_name", villageProfileModel.getPradhan_name());
                values.put("mobile_no", villageProfileModel.getMobile_no());
                values.put("image", villageProfileModel.getImage());
                values.put("flag", villageProfileModel.getFlag());
                ids = db.update("village_profile", values, "village_profile_id = ?", new String[]{String.valueOf(id)});
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHousehold(TrainingModel trainingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("training_id", trainingModel.getTraining_id());
                values.put("training_name", trainingModel.getTraining_name());
                values.put("start_date", trainingModel.getStart_date());
                values.put("trainer_name", trainingModel.getTrainer_name());
                values.put("male", trainingModel.getMale());
                values.put("female", trainingModel.getFemale());
                values.put("total_attendance", trainingModel.getTotal_attendance());
                values.put("objective", trainingModel.getObjective());
                values.put("app_version", trainingModel.getApp_version());
                values.put("resource_id", trainingModel.getResource_id());
                values.put("user_id", trainingModel.getUser_id());
                values.put("state_id", trainingModel.getState_id());
                values.put("district_id", trainingModel.getDistrict_id());
                values.put("block_id", trainingModel.getBlock_id());
                values.put("village_id", trainingModel.getVillage_id());
                values.put("latitude", trainingModel.getLatitude());
                values.put("longitude", trainingModel.getLongitude());
                values.put("created_at", trainingModel.getCreated_at());
                values.put("status", trainingModel.getStatus());
                values.put("flag", "0");

                ids = db.insert("training", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    public long updatTraingeHousehold(TrainingModel trainingModel, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("training_id", trainingModel.getTraining_id());
                values.put("training_name", trainingModel.getTraining_name());
                values.put("start_date", trainingModel.getStart_date());
                values.put("end_date", trainingModel.getEnd_date());
                values.put("total_attendance", trainingModel.getTotal_attendance());
                //               values.put("training_image_name", trainingModel.getTraining_image_name());

                ids = db.update("training", values, "local_Id = ?", new String[]{id});
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }


    public long saveHousehold(InfraStructureModel infraStructureModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ids = 0;
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                ContentValues values = new ContentValues();
                values.put("infra_id", infraStructureModel.getInfra_id());
                values.put("site_name", infraStructureModel.getSite_name());
                values.put("infra_structure_name", infraStructureModel.getInfra_structure_name());
                values.put("start_date", infraStructureModel.getStart_date());
                values.put("end_date", infraStructureModel.getEnd_date());
                values.put("subresource_id", infraStructureModel.getSubresource_id());
                values.put("address", infraStructureModel.getAddress());
                values.put("mobile_no", infraStructureModel.getMobile_no());
                values.put("amount", infraStructureModel.getAmount());
                values.put("caretaker", infraStructureModel.getCaretaker());
                values.put("expected_beneficial", infraStructureModel.getExpected_beneficial());
                values.put("description", infraStructureModel.getDescription());
                values.put("app_version", infraStructureModel.getApp_version());
                values.put("image", infraStructureModel.getImage());
                values.put("resource_id", infraStructureModel.getResource_id());
                values.put("user_id", infraStructureModel.getUser_id());
                values.put("state_id", infraStructureModel.getState_id());
                values.put("district_id", infraStructureModel.getDistrict_id());
                values.put("block_id", infraStructureModel.getBlock_id());
                values.put("village_id", infraStructureModel.getVillage_id());
                values.put("latitude", infraStructureModel.getLatitude());
                values.put("longitude", infraStructureModel.getLongitude());
                values.put("created_at", infraStructureModel.getCreated_at());
                values.put("status", infraStructureModel.getStatus());
                values.put("flag", "0");


                ids = db.insert("infra_structure", null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }

        return ids;
    }

    @SuppressLint("Range")
    public ArrayList<StakeHolderSurveyModel> getStackHolderSurvey(String village_id) {
        ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  stakeholder_survey where village_id='"+village_id+"'order by local_id desc";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
                        stakeHolderSurveyModel.setSurvey_id(cursor.getInt(cursor.getColumnIndex("survey_id")));
                        stakeHolderSurveyModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        stakeHolderSurveyModel.setDate_of_survey(cursor.getString(cursor.getColumnIndex("date_of_survey")));
                        stakeHolderSurveyModel.setFinancial_year(cursor.getString(cursor.getColumnIndex("financial_year")));
                        stakeHolderSurveyModel.setPerson_name(cursor.getString(cursor.getColumnIndex("person_name")));
                        stakeHolderSurveyModel.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
                        stakeHolderSurveyModel.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                        stakeHolderSurveyModel.setStakeholder_category_id(cursor.getString(cursor.getColumnIndex("stakeholder_category_id")));
                        stakeHolderSurveyModel.setHeard_about_jubliant_life_science(cursor.getString(cursor.getColumnIndex("heard_about_jubliant_life_science")));
                        stakeHolderSurveyModel.setEmployee_discussed_about_company_activity(cursor.getString(cursor.getColumnIndex("employee_discussed_about_company_activity")));
                        stakeHolderSurveyModel.setEquipped_to_manage_operation(cursor.getString(cursor.getColumnIndex("equipped_to_manage_operation")));
                        stakeHolderSurveyModel.setMock_drill_related_to_emergency(cursor.getString(cursor.getColumnIndex("mock_drill_related_to_emergency")));
                        stakeHolderSurveyModel.setDiscussion_conduct_for_concern(cursor.getString(cursor.getColumnIndex("discussion_conduct_for_concern")));
                        stakeHolderSurveyModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        stakeHolderSurveyModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return stakeHolderSurveyModelArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Committee_Table> getCommitteeData(String village_id) {
        ArrayList<Committee_Table> committee_tablesArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  committee_member where village_id='" + village_id + "' And status=1 or flag=0 order by local_id desc";


//                if (qq.equals("")) {
//                    query = "select * from  committee_member where village_id='\" + village_id + \"' And status=1 And flag=0 order by local_id desc";
//                }else {
//                    query = "select * from  committee_member where village_id='" + village_id + "' And status=1 order by local_id desc";
//
//                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Committee_Table committee_table = new Committee_Table();
                        committee_table.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        committee_table.setMember_id(cursor.getInt(cursor.getColumnIndex("member_id")));
                        committee_table.setMember_name(cursor.getString(cursor.getColumnIndex("member_name")));
                        committee_table.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        committee_table.setRole_id(cursor.getString(cursor.getColumnIndex("role_id")));
                        committee_table.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        committee_table.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        committee_table.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        committee_table.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        committee_table.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        committee_table.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        committee_table.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        committee_table.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        committee_table.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        committee_table.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        committee_table.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        committee_table.setFlag(cursor.getString(cursor.getColumnIndex("flag")));


                        committee_tablesArrayList.add(committee_table);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return committee_tablesArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Committee_Table> getCommitteeDataInVillage(String village_id,String unique_id) {
        ArrayList<Committee_Table> committee_tablesArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query= null;
            if (db != null && db.isOpen() && !db.isReadOnly()) {

                if (unique_id.equals("")){
                    query = "select * from committee_member where village_id='" + village_id + "' and status=1  ";

                }else {
                    query = "select * from committee_member where village_id='" + village_id + "' and village_profile_id='"+unique_id+"'and status=1 or flag=0 ";

                }

                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Committee_Table committee_table = new Committee_Table();
                        committee_table.setMember_name(cursor.getString(cursor.getColumnIndex("member_name")));
                        committee_table.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        committee_table.setRole_id(cursor.getString(cursor.getColumnIndex("role_id")));
                        committee_table.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        committee_tablesArrayList.add(committee_table);
                        cursor.moveToNext();
                    }
                }

                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return committee_tablesArrayList;
    }


    @SuppressLint("Range")
    public ArrayList<Committee_Table> getCommitteeSearchData(String member_name, String village_id) {
        ArrayList<Committee_Table> committee_tablesArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "";
                if (member_name.equals("")) {

                    query = "select * from committee_member where village_id='"+village_id+"'order by local_id desc ";
                } else {

                    query = "select * from committee_member where member_name Like '" + member_name + "%' and village_id='"+village_id+"'order by local_id desc";
                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Committee_Table committee_table = new Committee_Table();
//                        committee_table.setMember_id(cursor.getInt(cursor.getColumnIndex("member_id")));
//                        committee_table.setMember_name(cursor.getString(cursor.getColumnIndex("member_name")));
//                        committee_table.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        committee_table.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        committee_table.setMember_id(cursor.getInt(cursor.getColumnIndex("member_id")));
                        committee_table.setMember_name(cursor.getString(cursor.getColumnIndex("member_name")));
                        committee_table.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        committee_table.setRole_id(cursor.getString(cursor.getColumnIndex("role_id")));
                        committee_table.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        committee_table.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        committee_table.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        committee_table.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        committee_table.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        committee_table.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        committee_table.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        committee_table.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        committee_table.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        committee_table.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        committee_table.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        committee_table.setFlag(cursor.getString(cursor.getColumnIndex("flag")));

                        committee_tablesArrayList.add(committee_table);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return committee_tablesArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<ResourceMappingPojo> getResourceSearchData(String resource_mapping_name, String village_id) {
        ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "";
                if (resource_mapping_name.equals("")) {

                    query = "select * from resource_mapping where village_id='"+village_id+"'order by local_id desc ";
                } else {

                    query = "select * from resource_mapping where resource_mapping_name Like '" + resource_mapping_name + "%'and village_id='"+village_id+"'order by local_id desc";
                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMappingPojo resourceMappingPojo = new ResourceMappingPojo();
                        resourceMappingPojo.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        resourceMappingPojo.setResource_mapping_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("resource_mapping_id"))));
                        resourceMappingPojo.setResource_mapping_name(cursor.getString(cursor.getColumnIndex("resource_mapping_name")));
                        //resourceMappingPojo.setResource_type_id(cursor.getString(cursor.getColumnIndex("resource_type_id")));
                        resourceMappingPojo.setResource_status(cursor.getString(cursor.getColumnIndex("resource_status")));
                        resourceMappingPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        resourceMappingPojo.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        resourceMappingPojo.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        resourceMappingPojo.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        resourceMappingPojo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        resourceMappingPojo.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        resourceMappingPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        resourceMappingPojo.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        resourceMappingPojo.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        resourceMappingPojo.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        resourceMappingPojo.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        resourceMappingPojo.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        resourceMappingPojo.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        resourceMappingPojo.setResource_mapping_date(cursor.getString(cursor.getColumnIndex("resource_mapping_date")));
                        resourceMappingPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
                        resourceMappingPojoArrayList.add(resourceMappingPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return resourceMappingPojoArrayList;
    }




    @SuppressLint("Range")
    public ArrayList<ResourceMappingPojo> getResourceMappingData( String village_id) {
        ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<ResourceMappingPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from resource_mapping where village_id='"+village_id+"' and village_profile_id=0 order by local_id desc";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMappingPojo resourceMappingPojo = new ResourceMappingPojo();
                        resourceMappingPojo.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        resourceMappingPojo.setResource_mapping_id(Integer.parseInt(String.valueOf(cursor.getInt(cursor.getColumnIndex("resource_mapping_id")))));
                        resourceMappingPojo.setResource_mapping_name(cursor.getString(cursor.getColumnIndex("resource_mapping_name")));
                        //resourceMappingPojo.setResource_type_id(cursor.getString(cursor.getColumnIndex("resource_type_id")));
                        resourceMappingPojo.setResource_status(cursor.getString(cursor.getColumnIndex("resource_status")));
                        resourceMappingPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        resourceMappingPojo.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        resourceMappingPojo.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        resourceMappingPojo.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        resourceMappingPojo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        resourceMappingPojo.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        resourceMappingPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        resourceMappingPojo.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        resourceMappingPojo.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        resourceMappingPojo.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        resourceMappingPojo.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        resourceMappingPojo.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        resourceMappingPojo.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        resourceMappingPojo.setResource_mapping_date(cursor.getString(cursor.getColumnIndex("resource_mapping_date")));
                        resourceMappingPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));


                        cursor.moveToNext();

                        resourceMappingPojoArrayList.add(resourceMappingPojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return resourceMappingPojoArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<ResourceMappingPojo> getResourceMappingDataVillage(String village_profile_id) {
        ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<ResourceMappingPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                if (village_profile_id != "0") {
                    String query = "select * from resource_mapping where village_id='" + village_profile_id + "'";
                    Cursor cursor = db.rawQuery(query, null);
                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            ResourceMappingPojo resourceMappingPojo = new ResourceMappingPojo();
                            resourceMappingPojo.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                            resourceMappingPojo.setResource_mapping_id(Integer.parseInt(String.valueOf(cursor.getInt(cursor.getColumnIndex("resource_mapping_id")))));
                            resourceMappingPojo.setResource_mapping_name(cursor.getString(cursor.getColumnIndex("resource_mapping_name")));
//                          resourceMappingPojo.setResource_type_id(cursor.getString(cursor.getColumnIndex("resource_type_id")));
                            resourceMappingPojo.setResource_status(cursor.getString(cursor.getColumnIndex("resource_status")));
                            resourceMappingPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                            resourceMappingPojo.setResource_mapping_date(cursor.getString(cursor.getColumnIndex("resource_mapping_date")));
                            resourceMappingPojo.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                            resourceMappingPojo.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                            resourceMappingPojo.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                            resourceMappingPojo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                            resourceMappingPojo.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                            resourceMappingPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                            resourceMappingPojo.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                            resourceMappingPojo.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                            resourceMappingPojo.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                            resourceMappingPojo.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                            resourceMappingPojo.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                            resourceMappingPojo.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                            resourceMappingPojo.setFlag(cursor.getString(cursor.getColumnIndex("flag")));


                            cursor.moveToNext();

                            resourceMappingPojoArrayList.add(resourceMappingPojo);
                        }
                        db.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return resourceMappingPojoArrayList;
    }

    @SuppressLint("Range")
    public HashMap<String, Integer>getResourcess() {
        HashMap<String, Integer> resourcePojoss = new HashMap<>();
        ResourcePojo resourcePojo;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select resource_id,resource_name from resource";

                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        resourcePojo = new ResourcePojo();
                        resourcePojo.setResource_name(cursor.getString(cursor.getColumnIndex("resource_name")));
                        resourcePojo.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        resourcePojoss.put(resourcePojo.getResource_name(), Integer.valueOf(resourcePojo.getResource_id()));
                        cursor.moveToNext();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return resourcePojoss;
    }
//    public HashMap<String, Integer> getResource() {
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        ResourcePojo resourcePojo;
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        try {
//            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
//                String query = "select resource_id,resource_name from resource";
////                String query = "select * from infrasub_resource_type where infrasub_resource_id='" + resource_id + "'";
//                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    cursor.moveToFirst();
//                    while (!cursor.isAfterLast()) {
//                        resourcePojo = new ResourcePojo();
//                        resourcePojo.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
//                        resourcePojo.setResource_name(cursor.getString(cursor.getColumnIndex("resource_name")));
//
//                        cursor.moveToNext();
//                        hashMap.put(resourcePojo.getResource_id(), Integer.parseInt(resourcePojo.getResource_name()));
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqLiteDatabase.close();
//        }
//        return hashMap;
//    }


    @SuppressLint("Range")
    public LinkedHashMap<String, Integer> getDataResource(String resource_id) {
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
        InfrasubResourceType infrasubResourceType;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select infrasub_resource_name,infrasub_resource_id from infrasub_resource_type where infraresource_id='" + resource_id + "'ORDER BY infrasub_resource_id ASC";
//                String query = "select * from infrasub_resource_type where infrasub_resource_id='" + resource_id + "'";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        infrasubResourceType = new InfrasubResourceType();
                        infrasubResourceType.setInfrasub_resource_id(cursor.getString(cursor.getColumnIndex("infrasub_resource_id")));
                        infrasubResourceType.setInfrasub_resource_name(cursor.getString(cursor.getColumnIndex("infrasub_resource_name")));
                        cursor.moveToNext();
                        hashMap.put(infrasubResourceType.getInfrasub_resource_name(), Integer.parseInt(infrasubResourceType.getInfrasub_resource_id()));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return hashMap;
    }

    @SuppressLint("Range")
    public LinkedHashMap<String, Integer> getMemberRole() {
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
        MemberRolePojo memberRolePojo;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select role_id,role_name from member_role ORDER BY role_id ASC";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        memberRolePojo = new MemberRolePojo();
                        memberRolePojo.setRole_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("role_id"))));
                        memberRolePojo.setRole_name(cursor.getString(cursor.getColumnIndex("role_name")));
                        cursor.moveToNext();
                        hashMap.put(memberRolePojo.getRole_name(), Integer.parseInt(String.valueOf(memberRolePojo.getRole_id())));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return hashMap;
    }

    @SuppressLint("Range")
    public HashMap<String, Integer> getVillage() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        VillagePojo villagePojo;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select village_id,village_name from village";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        villagePojo = new VillagePojo();
                        villagePojo.setVillage_id(cursor.getString(cursor.getColumnIndex("village_id")));
                        villagePojo.setVillage_name(cursor.getString(cursor.getColumnIndex("village_name")));
                        cursor.moveToNext();
                        hashMap.put(villagePojo.getVillage_name(), Integer.parseInt(villagePojo.getVillage_id()));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return hashMap;
    }

    @SuppressLint("Range")
    public String ValueCommon(String columnName, String query) {
        String sum = "";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.d("querfff",query);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex(columnName));
        return sum;
    }

    @SuppressLint("Range")
    public HashMap<String, Integer> getCategory() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Stakeholder_CategoryPojo stakeholder_categoryPojo;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select stakeholder_category_id,stakeholder_category_name from stakeholder_category";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        stakeholder_categoryPojo = new Stakeholder_CategoryPojo();
                        stakeholder_categoryPojo.setStakeholder_category_id(cursor.getString(cursor.getColumnIndex("stakeholder_category_id")));
                        stakeholder_categoryPojo.setStakeholder_category_name(cursor.getString(cursor.getColumnIndex("stakeholder_category_name")));
                        cursor.moveToNext();
                        hashMap.put(stakeholder_categoryPojo.getStakeholder_category_name(), Integer.parseInt(stakeholder_categoryPojo.getStakeholder_category_id()));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return hashMap;
    }

    @SuppressLint("Range")
    public String getColumnName(String colName, String table, String whr) {
        String column = "";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select " + colName + " " + " from " + table + " " + whr, null);
            if (cursor.moveToFirst())
                column = cursor.getString(cursor.getColumnIndex(colName)).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return column;
    }


    @SuppressLint("Range")
    public String GetValueCommonaaaa(String columnName,String query) {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Log.d("querfff",query);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex(columnName));
        return sum;
    }

    @SuppressLint("Range")
    public String GetValueCommon(String columnName,String query) {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Log.d("querfff",query);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex(columnName));
        return sum;
    }

    @SuppressLint("Range")
    public String getColumnImagesName(String colName, String table, String whr) {
        String column = "";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select " + colName + " " + " from " + table + " " + whr, null);
            if (cursor.moveToFirst())
                column = cursor.getString(cursor.getColumnIndex(colName)).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return column;
    }


    @SuppressLint("Range")
    public ArrayList<ResourceMultipleImagePojo> getimage(String unique_id) {
        ArrayList<ResourceMultipleImagePojo> arrayList = new ArrayList<ResourceMultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//              String query = " select *,(select resource_mapping_id from resource_mapping where multiple_images.unique_id=resource_mapping.resource_mapping_id) as resource_mapping from multiple_images where unique_id='" + unique_id + "'";
                String query = " select *,(select resource_mapping_id from resource_mapping where resuorce_mapping_image.resource_mapping_id=resource_mapping.resource_mapping_id) as resource_mapping from resuorce_mapping_image where resource_mapping_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMultipleImagePojo resourceMultipleImagePojo = new ResourceMultipleImagePojo();
                        resourceMultipleImagePojo.setResource_mapping_image(cursor.getString(cursor.getColumnIndex("resource_mapping_image")));
//                        resourceMultipleImagePojo.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
//                        resourceMultipleImagePojo.setResource_mapping_id(cursor.getString(cursor.getColumnIndex("resource_mapping_id")));

                        cursor.moveToNext();

                        arrayList.add(resourceMultipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<TrainingMultipleImagePojo> getTrainingImage(String unique_id) {
        ArrayList<TrainingMultipleImagePojo> arrayList = new ArrayList<TrainingMultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//                String query = " select *,(select training_id from training where training_image_name.training_id=training.training_id) as training from training_image_name where training_id='" + unique_id + "'";
                String query = " select training_image_name from training_image where training_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        TrainingMultipleImagePojo trainingMultipleImagePojo = new TrainingMultipleImagePojo();
                        trainingMultipleImagePojo.setTraining_image_name(cursor.getString(cursor.getColumnIndex("training_image_name")));
//                        resourceMultipleImagePojo.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
//                        resourceMultipleImagePojo.setResource_mapping_id(cursor.getString(cursor.getColumnIndex("resource_mapping_id")));

                        cursor.moveToNext();

                        arrayList.add(trainingMultipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }


    @SuppressLint("Range")
    public ArrayList<MonitorMultipleImagePojo> getMonitorImage(String unique_id) {
        ArrayList<MonitorMultipleImagePojo> arrayList = new ArrayList<MonitorMultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//
                String query = " select monitor_resource_image from moniter_resource_image where monitor_resource_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MonitorMultipleImagePojo monitorMultipleImagePojo = new MonitorMultipleImagePojo();
                        monitorMultipleImagePojo.setMonitor_resource_image(cursor.getString(cursor.getColumnIndex("monitor_resource_image")));

                        cursor.moveToNext();
                        arrayList.add(monitorMultipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }



    @SuppressLint("Range")
    public ArrayList<ResourceMultipleImagePojo> getResourceimage(String unique_id) {
        ArrayList<ResourceMultipleImagePojo> arrayList = new ArrayList<ResourceMultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//              String query = " select *,(select resource_mapping_id from resource_mapping where multiple_images.unique_id=resource_mapping.resource_mapping_id) as resource_mapping from multiple_images where unique_id='" + unique_id + "'";
                String query = " select resource_mapping_image from resuorce_mapping_image where resource_mapping_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMultipleImagePojo resourceMultipleImagePojo = new ResourceMultipleImagePojo();
                        resourceMultipleImagePojo.setResource_mapping_image(cursor.getString(cursor.getColumnIndex("resource_mapping_image")));
                        cursor.moveToNext();

                        arrayList.add(resourceMultipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }


    @SuppressLint("Range")
    public ArrayList<ResourceMultipleImagePojo> getVillageResourceforSYnc(String unique_id) {
        ArrayList<ResourceMultipleImagePojo> arrayList = new ArrayList<ResourceMultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//              String query = " select *,(select resource_mapping_id from resource_mapping where multiple_images.unique_id=resource_mapping.resource_mapping_id) as resource_mapping from multiple_images where unique_id='" + unique_id + "'";
                String query = " select resource_mapping_image from resuorce_mapping_image where resource_mapping_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMultipleImagePojo resourceMultipleImagePojo = new ResourceMultipleImagePojo();
                        resourceMultipleImagePojo.setImage(cursor.getString(cursor.getColumnIndex("resource_mapping_image")));
                        cursor.moveToNext();

                        arrayList.add(resourceMultipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<MultipleImagePojo> getVimage(String unique_id) {
        ArrayList<MultipleImagePojo> arrayList = new ArrayList<MultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
//              String query = " select *,(select resource_mapping_id from resource_mapping where multiple_images.unique_id=resource_mapping.resource_mapping_id) as resource_mapping from multiple_images where unique_id='" + unique_id + "'";
                String query = " select village_profile_img from village_profile_image where village_profile_id='" + unique_id + "'";

                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MultipleImagePojo multipleImagePojo = new MultipleImagePojo();
                        multipleImagePojo.setImage(cursor.getString(cursor.getColumnIndex("village_profile_img")));
                        cursor.moveToNext();

                        arrayList.add(multipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }



    @SuppressLint("Range")
    public ArrayList<MultipleImagePojo> getVillageImage(String unique_id) {
        ArrayList<MultipleImagePojo> arrayList = new ArrayList<MultipleImagePojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from village_profile_image where village_profile_id='" + unique_id + "'";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MultipleImagePojo multipleImagePojo = new MultipleImagePojo();
                        multipleImagePojo.setVillage_profile_img(cursor.getString(cursor.getColumnIndex("village_profile_img")));
                        multipleImagePojo.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        multipleImagePojo.setVillage_profile_id(cursor.getString(cursor.getColumnIndex("village_profile_id")));

                        cursor.moveToNext();

                        arrayList.add(multipleImagePojo);
                    }
                    db.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();

        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<MonitorModel> getMonitorData(String village_id,String resource_mapping_id) {
        ArrayList<MonitorModel> monitorModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  monitor_resource where village_id='" + village_id + "'and resource_mapping_id='" + resource_mapping_id + "' order by local_id desc";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MonitorModel monitorModel = new MonitorModel();
                        monitorModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        monitorModel.setMonitor_resource_id(cursor.getInt(cursor.getColumnIndex("monitor_resource_id")));
                        monitorModel.setResource_mapping_id(cursor.getInt(cursor.getColumnIndex("resource_mapping_id")));
                        monitorModel.setDate_of_monitor(cursor.getString(cursor.getColumnIndex("date_of_monitor")));
                        monitorModel.setFarming_near_structure(cursor.getString(cursor.getColumnIndex("farming_near_structure")));
                        monitorModel.setStructure_functional(cursor.getString(cursor.getColumnIndex("structure_functional")));
                        monitorModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        monitorModel.setDamaged_structure(cursor.getString(cursor.getColumnIndex("damaged_structure")));
                        monitorModel.setJubliant_visibility_board_near_structure(cursor.getString(cursor.getColumnIndex("jubliant_visibility_board_near_structure")));
                        monitorModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        monitorModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        monitorModelArrayList.add(monitorModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return monitorModelArrayList;
    }


    @SuppressLint("Range")
    public ArrayList<VillageProfileModel> getVillageProfileData(int village_profile_id) {
        ArrayList<VillageProfileModel> villageProfileModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  village_profile where village_profile_id='"+village_profile_id+"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        VillageProfileModel villageProfileModel = new VillageProfileModel();

                        villageProfileModel.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        villageProfileModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        villageProfileModel.setPopulation(cursor.getString(cursor.getColumnIndex("population")));
                        villageProfileModel.setPradhan_name(cursor.getString(cursor.getColumnIndex("pradhan_name")));
                        villageProfileModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        villageProfileModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        cursor.moveToNext();
                        villageProfileModelArrayList.add(villageProfileModel);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return villageProfileModelArrayList;
    }
    @SuppressLint("Range")
    public ArrayList<VillageProfileModel> getVillageProfileDataView(int village, int villageProfileId) {
        ArrayList<VillageProfileModel> villageProfileModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  village_profile where village_id='"+village+"'  and village_profile_id='"+villageProfileId+"' order by local_id desc";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        VillageProfileModel villageProfileModel = new VillageProfileModel();

                        villageProfileModel.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        villageProfileModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        villageProfileModel.setPopulation(cursor.getString(cursor.getColumnIndex("population")));
                        villageProfileModel.setPradhan_name(cursor.getString(cursor.getColumnIndex("pradhan_name")));
                        villageProfileModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        cursor.moveToNext();
                        villageProfileModelArrayList.add(villageProfileModel);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return villageProfileModelArrayList;
    }
    @SuppressLint("Range")
    public ArrayList<VillageProfileModel> getVillageProfileDataVillageType(String village_profile_id) {
        ArrayList<VillageProfileModel> villageProfileModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  village_profile where village_profile_id='"+village_profile_id+"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        VillageProfileModel villageProfileModel = new VillageProfileModel();

                        villageProfileModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        villageProfileModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        villageProfileModel.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        villageProfileModel.setPopulation(cursor.getString(cursor.getColumnIndex("population")));
                        villageProfileModel.setPradhan_name(cursor.getString(cursor.getColumnIndex("pradhan_name")));
                        villageProfileModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
//                        villageProfileModel.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                        cursor.moveToNext();
                        villageProfileModelArrayList.add(villageProfileModel);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return villageProfileModelArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<MultipleImagePojo> getMultipleImage(int uniq_id) {
        ArrayList<MultipleImagePojo> villageProfileModelArrayList = new ArrayList<MultipleImagePojo>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  village_profile_image where village_profile_id='"+uniq_id+"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MultipleImagePojo multipleImagePojo = new MultipleImagePojo();

                        multipleImagePojo.setVillage_profile_img(cursor.getString(cursor.getColumnIndex("village_profile_img")));
                        multipleImagePojo.setVillage_profile_id(String.valueOf(cursor.getInt(cursor.getColumnIndex("village_profile_id"))));
                        cursor.moveToNext();
                        villageProfileModelArrayList.add(multipleImagePojo);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return villageProfileModelArrayList;
    }


    @SuppressLint("Range")
    public ArrayList<MonitorMultipleImagePojo> getMoniorMultipleImage(int uniq_id) {
        ArrayList<MonitorMultipleImagePojo> monitorMultipleImagePojoArrayList = new ArrayList<MonitorMultipleImagePojo>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select monitor_resource_image from  moniter_resource_image where monitor_resource_id='"+uniq_id+"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MonitorMultipleImagePojo monitorMultipleImagePojo = new MonitorMultipleImagePojo();

                        monitorMultipleImagePojo.setImage(cursor.getString(cursor.getColumnIndex("monitor_resource_image")));
                        cursor.moveToNext();
                        monitorMultipleImagePojoArrayList.add(monitorMultipleImagePojo);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return monitorMultipleImagePojoArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<TrainingMultipleImagePojo> getTrainingMultipleImage(int uniq_id) {
        ArrayList<TrainingMultipleImagePojo> trainingMultipleImagePojoArrayList = new ArrayList<TrainingMultipleImagePojo>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select training_image_name from  training_image where training_id='"+uniq_id+"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        TrainingMultipleImagePojo trainingMultipleImagePojo = new TrainingMultipleImagePojo();

                        trainingMultipleImagePojo.setTraining_image_name(cursor.getString(cursor.getColumnIndex("training_image_name")));
                        cursor.moveToNext();
                        trainingMultipleImagePojoArrayList.add(trainingMultipleImagePojo);
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return trainingMultipleImagePojoArrayList;
    }



    @SuppressLint("Range")
    public ArrayList<TrainingModel> getTrainingData(String village_id) {
        ArrayList<TrainingModel> trainingModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  training where village_id='"+village_id+"'order by local_id desc";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        TrainingModel trainingModel = new TrainingModel();
                        trainingModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        trainingModel.setTraining_id(cursor.getInt(cursor.getColumnIndex("training_id")));
                        trainingModel.setTraining_name(cursor.getString(cursor.getColumnIndex("training_name")));
                        trainingModel.setStart_date(cursor.getString(cursor.getColumnIndex("start_date")));

                        trainingModel.setTrainer_name(cursor.getString(cursor.getColumnIndex("trainer_name")));
                        trainingModel.setMale(cursor.getString(cursor.getColumnIndex("male")));
                        trainingModel.setFemale(cursor.getString(cursor.getColumnIndex("female")));
                        trainingModel.setObjective((cursor.getString(cursor.getColumnIndex("objective"))));
                        trainingModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        trainingModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        trainingModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        trainingModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        trainingModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        trainingModel.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
                        trainingModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        trainingModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        trainingModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        trainingModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        trainingModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        trainingModel.setTotal_attendance(cursor.getString(cursor.getColumnIndex("total_attendance")));
                        //                       trainingModel.setTraining_image_name(cursor.getString(cursor.getColumnIndex("training_image_name")));


                        trainingModelArrayList.add(trainingModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return trainingModelArrayList;
    }


    @SuppressLint("Range")
    public ArrayList<InfraStructureModel> getInfraStructureDataEmuskan() {
        ArrayList<InfraStructureModel> infraStructureModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select site_name from infra_structure where site_name='"+ 3 +"'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStructureModel infraStructureModel = new InfraStructureModel();
                        infraStructureModel.setSite_name(cursor.getString(cursor.getColumnIndex("site_name")));
                        infraStructureModelArrayList.add(infraStructureModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStructureModelArrayList;
    }



    @SuppressLint("Range")
    public ArrayList<InfraStructureModel> getInfraStructureData(String village_id) {
        ArrayList<InfraStructureModel> infraStructureModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  infra_structure where village_id='"+village_id+"'order by local_id desc";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStructureModel infraStructureModel = new InfraStructureModel();
                        infraStructureModel.setInfra_id(cursor.getInt(cursor.getColumnIndex("infra_id")));
                        infraStructureModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        infraStructureModel.setSubresource_id(cursor.getString(cursor.getColumnIndex("subresource_id")));
                        infraStructureModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        infraStructureModel.setInfra_structure_name(cursor.getString(cursor.getColumnIndex("infra_structure_name")));
                        infraStructureModel.setCaretaker(cursor.getString(cursor.getColumnIndex("caretaker")));
                        infraStructureModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        infraStructureModel.setStart_date(cursor.getString(cursor.getColumnIndex("start_date")));
                        infraStructureModel.setEnd_date(cursor.getString(cursor.getColumnIndex("end_date")));
                        infraStructureModel.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        infraStructureModel.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                        infraStructureModel.setExpected_beneficial(cursor.getString(cursor.getColumnIndex("expected_beneficial")));
                        infraStructureModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        infraStructureModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        infraStructureModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        infraStructureModelArrayList.add(infraStructureModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStructureModelArrayList;
    }

    // INFRA SYNC DATA

    @SuppressLint("Range")
    public ArrayList<InfraStructureModel> getInfraForSyn() {
        ArrayList<InfraStructureModel> infraStructureModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  infra_structure where flag=0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStructureModel infraStructureModel = new InfraStructureModel();
                        infraStructureModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        infraStructureModel.setInfra_id(cursor.getInt(cursor.getColumnIndex("infra_id")));
                        infraStructureModel.setSubresource_id(cursor.getString(cursor.getColumnIndex("subresource_id")));
//                        infraStructureModel.setSite_name(cursor.getString(cursor.getColumnIndex("effective_teaching_through_chalk_board")));
                        infraStructureModel.setInfra_structure_name(cursor.getString(cursor.getColumnIndex("infra_structure_name")));
                        infraStructureModel.setCaretaker(cursor.getString(cursor.getColumnIndex("caretaker")));
                        infraStructureModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        infraStructureModel.setStart_date(cursor.getString(cursor.getColumnIndex("start_date")));
                        infraStructureModel.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        infraStructureModel.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                        infraStructureModel.setExpected_beneficial(cursor.getString(cursor.getColumnIndex("expected_beneficial")));
                        infraStructureModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        infraStructureModel.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));
                        infraStructureModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        infraStructureModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        infraStructureModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        infraStructureModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        infraStructureModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        infraStructureModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        infraStructureModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        infraStructureModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        infraStructureModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        infraStructureModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        infraStructureModel.setInfra_status_id(cursor.getString(cursor.getColumnIndex("infra_status_id")));

                        infraStructureModelArrayList.add(infraStructureModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStructureModelArrayList;
    }

    //STAKEHOLDER SURVEY SYNC DATA

    @SuppressLint("Range")
    public ArrayList<StakeHolderSurveyModel> getSurveyForSyn() {
        ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  stakeholder_survey where flag=0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
                        stakeHolderSurveyModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        stakeHolderSurveyModel.setDate_of_survey(cursor.getString(cursor.getColumnIndex("date_of_survey")));
                        stakeHolderSurveyModel.setFinancial_year(cursor.getString(cursor.getColumnIndex("financial_year")));
                        stakeHolderSurveyModel.setPerson_name(cursor.getString(cursor.getColumnIndex("person_name")));
                        stakeHolderSurveyModel.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
                        stakeHolderSurveyModel.setStakeholder_category_id(cursor.getString(cursor.getColumnIndex("stakeholder_category_id")));
                        stakeHolderSurveyModel.setHeard_about_jubliant_life_science(cursor.getString(cursor.getColumnIndex("heard_about_jubliant_life_science")));
                        stakeHolderSurveyModel.setEmployee_discussed_about_company_activity(cursor.getString(cursor.getColumnIndex("employee_discussed_about_company_activity")));
                        stakeHolderSurveyModel.setEquipped_to_manage_operation(cursor.getString(cursor.getColumnIndex("equipped_to_manage_operation")));
                        stakeHolderSurveyModel.setMock_drill_related_to_emergency(cursor.getString(cursor.getColumnIndex("mock_drill_related_to_emergency")));
                        stakeHolderSurveyModel.setDiscussion_conduct_for_concern(cursor.getString(cursor.getColumnIndex("discussion_conduct_for_concern")));
                        stakeHolderSurveyModel.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));

                        stakeHolderSurveyModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        stakeHolderSurveyModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        stakeHolderSurveyModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        stakeHolderSurveyModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        stakeHolderSurveyModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        stakeHolderSurveyModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        stakeHolderSurveyModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        stakeHolderSurveyModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        stakeHolderSurveyModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        stakeHolderSurveyModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        stakeHolderSurveyModel.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                        stakeHolderSurveyModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));


                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return stakeHolderSurveyModelArrayList;
    }


    //RESOURCE MAPPING SYNC DATA

    @SuppressLint("Range")
    public ArrayList<ResourceMappingPojo> getResourceForSyn(int unique_id) {
        ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  resource_mapping where flag=0 and village_profile_id='" + unique_id + "'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        ResourceMappingPojo resourceMappingPojo = new ResourceMappingPojo();
                        resourceMappingPojo.setResource_mapping_name(cursor.getString(cursor.getColumnIndex("resource_mapping_name")));
                        resourceMappingPojo.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        resourceMappingPojo.setResource_mapping_id(Integer.parseInt(String.valueOf(cursor.getInt(cursor.getColumnIndex("resource_mapping_id")))));
                        resourceMappingPojo.setResource_status(cursor.getString(cursor.getColumnIndex("resource_status")));
                        resourceMappingPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        resourceMappingPojo.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        resourceMappingPojo.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));
                        resourceMappingPojo.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        resourceMappingPojo.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        resourceMappingPojo.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        resourceMappingPojo.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        resourceMappingPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        resourceMappingPojo.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        resourceMappingPojo.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        resourceMappingPojo.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        resourceMappingPojo.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        resourceMappingPojo.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        resourceMappingPojo.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        resourceMappingPojo.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        resourceMappingPojo.setResource_mapping_date(cursor.getString(cursor.getColumnIndex("resource_mapping_date")));
                        resourceMappingPojo.setContact_mob_no(cursor.getString(cursor.getColumnIndex("contact_mob_no")));
                        resourceMappingPojo.setContact_person(cursor.getString(cursor.getColumnIndex("contact_person")));

                        resourceMappingPojoArrayList.add(resourceMappingPojo);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return resourceMappingPojoArrayList;
    }

    //TRAINING SYNC DATA

    @SuppressLint("Range")
    public ArrayList<TrainingModel> getTrainingForSyn() {
        ArrayList<TrainingModel> trainingModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from training where flag=0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        TrainingModel trainingModel = new TrainingModel();
                        trainingModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        trainingModel.setTraining_name(cursor.getString(cursor.getColumnIndex("training_name")));
                        trainingModel.setStart_date(cursor.getString(cursor.getColumnIndex("start_date")));
                        trainingModel.setTraining_id(cursor.getInt(cursor.getColumnIndex("training_id")));

                        trainingModel.setTrainer_name(cursor.getString(cursor.getColumnIndex("trainer_name")));
                        trainingModel.setMale(cursor.getString(cursor.getColumnIndex("male")));
                        trainingModel.setFemale(cursor.getString(cursor.getColumnIndex("female")));
                        trainingModel.setObjective(cursor.getString(cursor.getColumnIndex("objective")));
                        trainingModel.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));

                        trainingModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        trainingModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        trainingModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        trainingModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        trainingModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        trainingModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        trainingModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        trainingModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        trainingModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        trainingModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        trainingModel.setTotal_attendance(cursor.getString(cursor.getColumnIndex("total_attendance")));
                        //                       trainingModel.setTraining_image_name(cursor.getString(cursor.getColumnIndex("training_image_name")));

                        trainingModelArrayList.add(trainingModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return trainingModelArrayList;
    }

    //COMMITTEE MEMBER SYNC DATA

    @SuppressLint("Range")
    public ArrayList<Committee_Table> getCommitteeMemberForSyn(int unique_id) {
        ArrayList<Committee_Table> committee_tableArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  committee_member where flag=0 and village_profile_id='" + unique_id + "'";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Committee_Table committee_table = new Committee_Table();
                        committee_table.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        committee_table.setMember_name(cursor.getString(cursor.getColumnIndex("member_name")));
                        committee_table.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        committee_table.setRole_id(cursor.getString(cursor.getColumnIndex("role_id")));
                        committee_table.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));
                        committee_table.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        committee_table.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        committee_table.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        committee_table.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        committee_table.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        committee_table.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        committee_table.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        committee_table.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        committee_table.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        committee_table.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        committee_table.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        committee_table.setStatus(cursor.getString(cursor.getColumnIndex("status")));

                        committee_tableArrayList.add(committee_table);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return committee_tableArrayList;
    }


    //MONITORING RESOURCES SYNC DATA

    @SuppressLint("Range")
    public ArrayList<MonitorModel> getMonitorResourceForSyn() {
        ArrayList<MonitorModel> monitorModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  monitor_resource where flag=0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        MonitorModel monitorModel = new MonitorModel();
                        monitorModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        monitorModel.setMonitor_resource_id(cursor.getInt(cursor.getColumnIndex("monitor_resource_id")));
                        monitorModel.setDate_of_monitor(cursor.getString(cursor.getColumnIndex("date_of_monitor")));
                        monitorModel.setFarming_near_structure(cursor.getString(cursor.getColumnIndex("farming_near_structure")));
                        monitorModel.setStructure_functional(cursor.getString(cursor.getColumnIndex("structure_functional")));
                        monitorModel.setDamaged_structure(cursor.getString(cursor.getColumnIndex("damaged_structure")));
                        monitorModel.setJubliant_visibility_board_near_structure(cursor.getString(cursor.getColumnIndex("jubliant_visibility_board_near_structure")));
                        monitorModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        monitorModel.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));
                        monitorModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        monitorModel.setResource_mapping_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("resource_mapping_id"))));
                        monitorModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        monitorModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        monitorModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        monitorModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        monitorModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        monitorModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        monitorModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        monitorModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                        monitorModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        monitorModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        monitorModel.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));

                        monitorModelArrayList.add(monitorModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return monitorModelArrayList;
    }

    //VILLAGE SYNC DATA

    @SuppressLint("Range")
    public ArrayList<VillageProfileModel> getVillageForSyn() {
        ArrayList<VillageProfileModel> villageProfileModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  village_profile where flag=0";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        VillageProfileModel villageProfileModel = new VillageProfileModel();
                        villageProfileModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                        villageProfileModel.setPopulation(cursor.getString(cursor.getColumnIndex("population")));
                        villageProfileModel.setPradhan_name(cursor.getString(cursor.getColumnIndex("pradhan_name")));
                        villageProfileModel.setVillage_profile_id(cursor.getInt(cursor.getColumnIndex("village_profile_id")));
                        villageProfileModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));
                        villageProfileModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        villageProfileModel.setApp_version(cursor.getString(cursor.getColumnIndex("app_version")));

                        villageProfileModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        villageProfileModel.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                        villageProfileModel.setState_id(cursor.getInt(cursor.getColumnIndex("state_id")));
                        villageProfileModel.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                        villageProfileModel.setBlock_id(cursor.getInt(cursor.getColumnIndex("block_id")));
                        villageProfileModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        villageProfileModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                        villageProfileModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        villageProfileModel.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                        villageProfileModel.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));

                        villageProfileModelArrayList.add(villageProfileModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return villageProfileModelArrayList;
    }



    @SuppressLint("Range")
    public ArrayList<InfraStructureModel> getInfraSearch(String name_of_the_infra,String village_id) {
        ArrayList<InfraStructureModel> infraStructureModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "";
                if (name_of_the_infra.equals("")) {

                    //  query = "select * from farmer_registration INNER JOIN users ON farmer_registration.user_id = users.id ";
                    query = "select * from infra_structure where village_id='"+village_id+"'order by local_id desc";
                } else {

                    query = "select * from infra_structure where infra_structure_name Like '" + name_of_the_infra + "%' and village_id='"+village_id+"'order by local_id desc";
                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        InfraStructureModel infraStructureModel = new InfraStructureModel();
                        infraStructureModel.setInfra_id(cursor.getInt(cursor.getColumnIndex("infra_id")));
                        infraStructureModel.setSite_name(cursor.getString(cursor.getColumnIndex("site_name")));
                        infraStructureModel.setInfra_structure_name(cursor.getString(cursor.getColumnIndex("infra_structure_name")));
                        infraStructureModel.setCaretaker(cursor.getString(cursor.getColumnIndex("caretaker")));
                        infraStructureModel.setStart_date(cursor.getString(cursor.getColumnIndex("start_date")));
                        infraStructureModel.setEnd_date(cursor.getString(cursor.getColumnIndex("end_date")));
                        infraStructureModel.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        infraStructureModel.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                        infraStructureModel.setExpected_beneficial(cursor.getString(cursor.getColumnIndex("expected_beneficial")));
                        infraStructureModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        infraStructureModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                        infraStructureModel.setMobile_no(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        infraStructureModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));
                        infraStructureModel.setResource_id(cursor.getInt(cursor.getColumnIndex("resource_id")));

                        infraStructureModelArrayList.add(infraStructureModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return infraStructureModelArrayList;
    }


    @SuppressLint("Range")
    public ArrayList<StakeHolderSurveyModel> getSurveySearch(String name_of_the_survey, String village_id) {
        ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "";
                if (name_of_the_survey.equals("")) {

                    //  query = "select * from farmer_registration INNER JOIN users ON farmer_registration.user_id = users.id ";
                    query = "select * from stakeholder_survey where village_id='"+village_id+"'order by local_id desc ";
                } else {

                    query = "select * from stakeholder_survey where person_name Like '" + name_of_the_survey + "%' and village_id='"+village_id+"'order by local_id desc";
                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
                        stakeHolderSurveyModel.setSurvey_id(cursor.getInt(cursor.getColumnIndex("survey_id")));
                        stakeHolderSurveyModel.setDate_of_survey(cursor.getString(cursor.getColumnIndex("date_of_survey")));
                        stakeHolderSurveyModel.setPerson_name(cursor.getString(cursor.getColumnIndex("person_name")));
                        stakeHolderSurveyModel.setFinancial_year(cursor.getString(cursor.getColumnIndex("financial_year")));
                        stakeHolderSurveyModel.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
                        stakeHolderSurveyModel.setStakeholder_category_id(cursor.getString(cursor.getColumnIndex("stakeholder_category_id")));
                        stakeHolderSurveyModel.setHeard_about_jubliant_life_science(cursor.getString(cursor.getColumnIndex("heard_about_jubliant_life_science")));
                        stakeHolderSurveyModel.setEmployee_discussed_about_company_activity(cursor.getString(cursor.getColumnIndex("employee_discussed_about_company_activity")));
                        stakeHolderSurveyModel.setEquipped_to_manage_operation(cursor.getString(cursor.getColumnIndex("equipped_to_manage_operation")));
                        stakeHolderSurveyModel.setMock_drill_related_to_emergency(cursor.getString(cursor.getColumnIndex("mock_drill_related_to_emergency")));
                        stakeHolderSurveyModel.setDiscussion_conduct_for_concern(cursor.getString(cursor.getColumnIndex("discussion_conduct_for_concern")));
                        stakeHolderSurveyModel.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        stakeHolderSurveyModel.setVillage_id(cursor.getInt(cursor.getColumnIndex("village_id")));

                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return stakeHolderSurveyModelArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<StakeHolderSurveyModel> getStakeholderSurveyData() {
        ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "select * from  stake_holder_survey";
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
//                        stakeHolderSurveyModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
//

                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return stakeHolderSurveyModelArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<StakeHolderSurveyModel> getStakeholderSurveySeachData(String name) {
        ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (db != null && db.isOpen() && !db.isReadOnly()) {
                String query = "";
                if (name.equals("")) {

                    //  query = "select * from farmer_registration INNER JOIN users ON farmer_registration.user_id = users.id ";
                    query = "select * from stakeholder_survey ";
                } else {

                    query = "select * from stakeholder_survey where name Like '" + name + "'%";
                }
                @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
                        stakeHolderSurveyModel.setSurvey_id(cursor.getInt(cursor.getColumnIndex("survey_id")));
                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
//                        stakeHolderSurveyModel.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
//                        stakeHolderSurveyModelArrayList.add(stakeHolderSurveyModel);
                        cursor.moveToNext();
                    }
                }
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return stakeHolderSurveyModelArrayList;
    }


    public long updateId(String table, String whr, String last_activity_id, String col) {
        long look_id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            if(db != null && db.isOpen() && !db.isReadOnly())
            {
                ContentValues values = new ContentValues();
                values.put("status", "1");
                values.put("flag", "1");
                values.put(col, last_activity_id);
                look_id = db.update(table, values, whr, null);
                db.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            db.close();
        }
        return look_id;
    }

    public long updateResourceImage(String table, String whr, String last_activity_id, String col) {
        long look_id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            if(db != null && db.isOpen() && !db.isReadOnly())
            {
                ContentValues values = new ContentValues();
                values.put("status", "1");
                values.put(col, last_activity_id);
                look_id = db.update(table, values, whr, null);
                db.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            db.close();
        }
        return look_id;
    }

    public long UpdateActiveButton(String table, String whr) {
        long look_id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            if(db != null && db.isOpen() && !db.isReadOnly())
            {
                ContentValues values = new ContentValues();
                values.put("status", "0");
                look_id = db.update(table, values, whr, null);
                db.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            db.close();
        }
        return look_id;
    }

    @SuppressLint("Range")
    public int getCloumnName(String colName, String table, String whr) {
        int column = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + colName + " " + " from " + table + " " + whr , null);
        if (cursor.moveToFirst())
            column = cursor.getInt(cursor.getColumnIndex(colName));
        return column;
    }

    @SuppressLint("Range")
    public String getTypeName(String resource_id) {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select resource_name from infra_resource where resource_id ='" + resource_id + "' ", null);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex("resource_name"));
        return sum;
    }
    @SuppressLint("Range")
    public String getTypeNamesss(String resource_id) {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select resource_name from resource where resource_id ='" + resource_id + "' ", null);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex("resource_name"));
        return sum;
    }

    @SuppressLint("Range")
    public String getResourceName(String Subresource_id) {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select infrasub_resource_name from infrasub_resource_type where infrasub_resource_id ='"+Subresource_id+"' ", null);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex("infrasub_resource_name"));
        return sum;
    }


    @SuppressLint("Range")
    public String getEmuskanResourceName() {
        String sum = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select infrasub_resource_name from infrasub_resource_type where infrasub_resource_id ='" + 3 + "' ", null);
        if (cursor.moveToFirst())
            sum = cursor.getString(cursor.getColumnIndex("infrasub_resource_name"));
        return sum;
    }
    ///////////...................................Engagement Level Data Download....................///////////////////////////////////
    @SuppressLint("Range")
    public HashMap<String, Integer>Downloadinfra_resourceData() {
        HashMap<String, Integer> engagementLevel = new HashMap<>();
        Infra_ResourceModel infra_resourceModel;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
                String query = "select * from infra_resource";

                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        infra_resourceModel = new Infra_ResourceModel();
                        infra_resourceModel.setResource_name(cursor.getString(cursor.getColumnIndex("resource_name")));
                        infra_resourceModel.setResource_id(cursor.getString(cursor.getColumnIndex("resource_id")));
                        infra_resourceModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        engagementLevel.put(infra_resourceModel.getResource_name(), Integer.valueOf(infra_resourceModel.getResource_id()));
                        cursor.moveToNext();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return engagementLevel;
    }
}