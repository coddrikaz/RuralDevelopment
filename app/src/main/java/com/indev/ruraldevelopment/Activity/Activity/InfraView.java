package com.indev.ruraldevelopment.Activity.Activity;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InfraView extends AppCompatActivity {
    TextView tvResourcetype, tvSiteName, tvStructureName, tvMobile_no, tvEndDate, tvStartDate, tvAddress, tvCaretakerName, tvAmount,tvExpectedBeneficial, tvDescription;
    String resource_id,Subresource_id,Subresourece_name, resourece_name, site, structure_name, mobile_no, start_date, end_date, amount, address, expected_beneficial, description, caretaker_name;
    String img;
    String Subresource_Type_name="",resource_Type_name="";
    int infraId=0;
    InfraStructureModel infraStructureModel;
    ZoomageView image;
    String imageView="";
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_view);
        getSupportActionBar().setTitle("Infra Structure Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvResourcetype=findViewById(R.id.tvResource);
        tvSiteName = findViewById(R.id.tvSiteName);
        tvStructureName = findViewById(R.id.tvStructureName);
        tvMobile_no = findViewById(R.id.tvMobile_no);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvAddress = findViewById(R.id.tvAddress);
        tvAmount = findViewById(R.id.tvAmount);
        tvCaretakerName = findViewById(R.id.tvCaretakerName);
        tvExpectedBeneficial = findViewById(R.id.tvExpectedBeneficial);
        tvDescription = findViewById(R.id.tvDescription);
        image = findViewById(R.id.zmimage);
        sqliteHelper = new SqliteHelper(this);
        sharedPreferences = new SharedPrefHelper(this);
        infraStructureModel = new InfraStructureModel();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            infraId = bundle.getInt("infra_id", 0);
            site = bundle.getString("site_name", "");
            structure_name = bundle.getString("infra_structure_name", "");
            caretaker_name = bundle.getString("caretaker", "");
            mobile_no = bundle.getString("mobile_no", "");
            start_date = bundle.getString("start_date", "");
            end_date = bundle.getString("end_date", "");
            address = bundle.getString("address", "");
            amount = bundle.getString("amount", "");
            expected_beneficial = bundle.getString("expected_beneficial", "");
            description = bundle.getString("description", "");
            img = bundle.getString("image", "");

        }
        imageView = sqliteHelper.getColumnName("image", "infra_structure", " where infra_id='"+infraId+"'");


        resource_id=sharedPreferences.getString("resource_mapping_id", "");
        Subresource_Type_name=sharedPreferences.getString("Subresource_Type_name", "");
        resource_Type_name=sharedPreferences.getString("resource_Type_name", "");
        Subresource_id=sharedPreferences.getString("Subresource_mapping_id", "");

        resourece_name = sqliteHelper.getColumnName("resource_name", "infra_resource", " where resource_id ='" + resource_id + "'");
        Subresourece_name = sqliteHelper.getColumnName("infrasub_resource_name", "infrasub_resource_type", " where infrasub_resource_id ='" + Subresource_id + "'");
        tvResourcetype.setText(": " +resource_Type_name);
        tvSiteName.setText(": " +Subresource_Type_name);
//        tvSiteName.setText(": " + site);
        tvStructureName.setText(": " + structure_name);
        tvStartDate.setText(": " + start_date);
//        tvEndDate.setText(": " + end_date);
        tvMobile_no.setText(": " + mobile_no);
        tvAmount.setText(": " + amount);
        tvAddress.setText(": " + address);
        tvCaretakerName.setText(": " + caretaker_name);
        tvExpectedBeneficial.setText(": " + expected_beneficial);
        tvDescription.setText(": " + description);



        if (img != null && img.length() > 200) {
            byte[] decodedString = Base64.decode(img, Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL2 + imageView)
                        .placeholder(R.drawable.infrastructure_development)
                        .into(image);
            } catch (Exception e) {
                Log.d("Exception", "" + e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }