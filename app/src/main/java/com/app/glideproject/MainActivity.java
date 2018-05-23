package com.app.glideproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hutao.ui.runtimepermission.PermissionActivity;

import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends PermissionActivity {

    private String imgUrl = "http://jsjs.jbzj.com/zu/z=cyd6o3s5rd,g4j6paxa1f&wn=cv?ruv=4o12ud_sb3ij_51j";

    private ImageView imageView;
    private SDFileHelper sdFileHelper;
    private String imgFilaName = "2018-05-23.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        sdFileHelper = new SDFileHelper(this);
    }

    public void dowload(View view) {
        requestRuntimePermissions(
                new String[]{
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                new PermissionListener() {
                    @Override
                    public void granted() {
                        sdFileHelper.savePicture(imgFilaName, imgUrl);
                    }

                    @Override
                    public void denied(List<String> deniedList) {
                        Toast.makeText(MainActivity.this, "没有读写权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void loadImg(View view) {
        String imgPath = SDFileHelper.IMAG_BASE_PATH + "/" + imgFilaName;
        Glide.with(this)
                .load(imgPath)
                .into(imageView);
    }
}
