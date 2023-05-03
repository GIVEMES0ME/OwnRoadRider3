package com.example.ownroadrider;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }


    private ImageButton advancedSearch;  //하단 바 검색 버튼
    private ImageButton map;    //지도 버튼

    private EditText startpoint;
    private EditText endpoint;

    private ImageButton pathSearchButton;

    private ImageButton homeButton_m;
    private ImageButton categoryButton_m;
    private ImageButton mapButton_m;
    private ImageButton detailSearchButton_m;
    private ImageButton mypageButton_m;

    private RadioGroup themeRg; // 테마 선택 라디오 그룹
    private ImageButton landscape1;
    private ImageButton landscape2;
    private ImageButton landscape3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();

        advancedSearch = findViewById(R.id.detailSearchButton);
        startpoint=findViewById(R.id.startPointSearch);
        endpoint=findViewById(R.id.finishPointSearch);
        pathSearchButton=findViewById(R.id.pathSearchButton);

        homeButton_m=findViewById(R.id.homeButton);

        homeButton_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //카테고리
        categoryButton_m=findViewById(R.id.categoryButton);

        categoryButton_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"카테고리 선택",Toast.LENGTH_LONG).show();
            }
        });
        //맵
        mapButton_m=findViewById(R.id.mapButton);

        mapButton_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Map_view.class);
                startActivity(intent);
            }
        });

        //검색
        detailSearchButton_m=findViewById(R.id.detailSearchButton);

        detailSearchButton_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdvancedSearchActivity.class);
                startActivity(intent);
            }
        });
        //마이페이지
        mypageButton_m=findViewById(R.id.mypageButton);

        mypageButton_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"내정보 선택",Toast.LENGTH_LONG).show();
            }
        });

        pathSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.this, MapSearchActivity.class);
                intent.putExtra("start",startpoint.getText().toString());
                intent.putExtra("end",endpoint.getText().toString());
                startActivity(intent);
            }
        });

        themeRg = findViewById(R.id.themeRadioGroup);           //라디오 그룹
        landscape1 = findViewById(R.id.landscapeImageButton1);
        landscape2 = findViewById(R.id.landscapeImageButton2);
        landscape3 = findViewById(R.id.landscapeImageButton3);

        themeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                if(checked == R.id.theme1){
                    landscape1.setImageResource(R.drawable.small);
                    landscape2.setImageResource(R.drawable.sacheon);
                    landscape3.setImageResource(R.drawable.bak);
                }
                else if(checked == R.id.theme2){
                    landscape1.setImageResource(R.drawable.tongyeong_dongpirang);
                    landscape2.setImageResource(R.drawable.sachoen_seacablecar);
                    landscape3.setImageResource(R.drawable.hapcheon_haeinsa);
                }
            }
        });

        landscape1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });




    }

}