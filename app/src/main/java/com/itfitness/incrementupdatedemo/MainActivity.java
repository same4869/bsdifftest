package com.itfitness.incrementupdatedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.itfitness.incrementupdatedemo.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private TextView tv_version;
    private Button bt_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_version = findViewById(R.id.tv_version);
        bt_update = findViewById(R.id.bt_update);
        tv_version.setText("1.0");
        bt_update.setOnClickListener(v->{
            new Thread(() -> {
                Log.d("kkkkkkkk","dir patch -> " + getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
                File oldApkFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "old.apk");
                File newApkFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "new.apk");
                File patchFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "patch");
                PatchUtil.patchAPK(oldApkFile.getAbsolutePath(),newApkFile.getAbsolutePath(),patchFile.getAbsolutePath());
                //安装APK
                AppUtils.installApp(newApkFile);
            }).start();
        });
    }
}