package com.example.asuspc.bismillah;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LIST_STATE = "liststate";
    private Parcelable mListState = null;
    private LihatGambar lihatGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lihatGambar = (LihatGambar) findViewById(R.id.gambar);
    }

    public void hapusGambar(View v){
        lihatGambar.hapusGambar();
    }


}

