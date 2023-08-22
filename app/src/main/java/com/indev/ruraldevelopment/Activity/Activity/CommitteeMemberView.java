package com.indev.ruraldevelopment.Activity.Activity;


import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL1;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CommitteeMemberView extends AppCompatActivity {

    TextView tvMemberName, tvMobileNumber, tvRole;
    String memberName, mobileNumber, role, img;
    ZoomageView image;
    SqliteHelper sqliteHelper;
    Committee_Table committee_table;
    String imageView="";
    int memberId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_member_view);
        getSupportActionBar().setTitle("View Committee Member");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMemberName = findViewById(R.id.tvMemberName);
        tvMobileNumber = findViewById(R.id.tvMemberMobileNumber);
        tvRole = findViewById(R.id.tvRole);
        image = findViewById(R.id.image);
        sqliteHelper = new SqliteHelper(this);
        committee_table = new Committee_Table();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            memberId = bundle.getInt("member_id",0);
            memberName = bundle.getString("member_name", "");
            mobileNumber = bundle.getString("mobile_no", "");
            role = bundle.getString("role_id", "");
            img = bundle.getString("image", "");
        }
        imageView = sqliteHelper.getColumnName("image", "committee_member", " where member_id='" + memberId + "'");
        role = sqliteHelper.getColumnName("role_name", "member_role", " where role_id='" + role + "'");
        tvMemberName.setText(": "+memberName);
        tvMobileNumber.setText(": "+mobileNumber);
        tvRole.setText(": "+role);

        if (img != null && img.length() > 200) {
            byte[] decodedString = Base64.decode(img, Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL1 + imageView)
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                        .into(image);
            } catch (Exception e) {
                Log.d("Exception", "" + e);
            }
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
//            case R.id.home_icon:
//                Intent intent1 = new Intent(this, Login.class);
//                startActivity(intent1);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}