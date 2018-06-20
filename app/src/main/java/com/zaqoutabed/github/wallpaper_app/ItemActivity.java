package com.zaqoutabed.github.wallpaper_app;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ItemActivity extends AppCompatActivity {

    private ImageView item;
    private ImageButton back_image_button, set_wallpaper_image_button,download_image_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        item = findViewById(R.id.item);
        back_image_button = findViewById(R.id.back_image_button);
        set_wallpaper_image_button = findViewById(R.id.set_wallpaper_image_button);
        download_image_button = findViewById(R.id.download_image_button);

        Intent intent = getIntent();

        if (intent.hasExtra("imageSource")){
            int imageSource = intent.getIntExtra("imageSource", 0);
            if (imageSource != 0){
                item.setImageResource(imageSource);
                set_wallpaper_image_button.setOnClickListener(v->{
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
                    try {
                        wallpaperManager.setResource(imageSource);
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                download_image_button.setOnClickListener(v->{
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageSource);
                    OutputStream outputStream;
                    File filePath = Environment.getExternalStorageDirectory();
                    File dir = new File(filePath+"/wallpaperApp/");
                    dir.mkdirs();

                    File file = new File(dir, "wallpaper_"+((int)(Math.random()*100000+50000))+"_"+((int)(Math.random()*100000+50000))+".jpg");
                    try {
                        outputStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Log.d("getMessage1", "onCreate: "+e.getMessage());
                    } catch (IOException e) {
                        Log.d("getMessage2", "onCreate: "+e.getMessage());
                        e.printStackTrace();
                    }
                });
            }else {
                Toast.makeText(this, "Image Not Found !", Toast.LENGTH_SHORT).show();

            }
        }
        back_image_button.setOnClickListener(v-> onBackPressed());
    }
}
