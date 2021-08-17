package com.example.internship_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class UpdateActivity extends AppCompatActivity {

    EditText name,agency,wiki;
    ImageView image;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
//        hook();
//        Intent intent=getIntent();
//        name.setText(intent.getStringExtra("Name"));
//        agency.setText(intent.getStringExtra("agency"));
//        wiki.setText(intent.getStringExtra("wiki"));
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseClass.getDatabase(getApplicationContext()).getDao().update(name.getText().toString()
//                        ,agency.getText().toString()
//                        ,wiki.getText().toString()
//                        ,intent.getStringExtra("Status")
//                        ,intent.getStringExtra("Image")
//                        ,intent.getStringExtra("Id")
//                        , Integer.parseInt(intent.getStringExtra("key")));
//                finish();
//                startActivity(new Intent(UpdateActivity.this,Home.class));
//            }
//        });
    }

//    private void hook() {
//        name=findViewById(R.id.name_update);
//        agency=findViewById(R.id.agency_update);
//        wiki=findViewById(R.id.wikipedia_update);
//        image=findViewById(R.id.image_update);
//        save=findViewById(R.id.save_update);
//    }
}