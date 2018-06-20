package com.zaqoutabed.github.wallpaper_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private CardView item1, item2, item3, item4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);

        item1.setOnClickListener(v->{
            startSecondActivity(R.drawable.item1);
        });

        item2.setOnClickListener(v->{
            startSecondActivity(R.drawable.item2);
        });

        item3.setOnClickListener(v->{
            startSecondActivity(R.drawable.item3);
        });

        item4.setOnClickListener(v->{
            startSecondActivity(R.drawable.item4);
        });
    }

    private void startSecondActivity(int imageSource){
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("imageSource", imageSource);
        this.startActivity(intent);
    }
}
