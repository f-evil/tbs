package com.easy.tbs;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.smtt.sdk.QbSdk;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button zip;
    private Button mWord;
    private Button mExcel;
    private Button mPpt;
    private Button mTxt;

    private String rootPath;

    private boolean init = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        rootPath = "/mnt/shared/Other/1/";
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/1/";

    }

    private void initView() {
        mWord = (Button) findViewById(R.id.word);
        mExcel = (Button) findViewById(R.id.excel);
        mPpt = (Button) findViewById(R.id.ppt);
        mTxt = (Button) findViewById(R.id.txt);
        zip = (Button) findViewById(R.id.zip);

        zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "初始化中", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ZipUtils.UnZipFolder(
                                    rootPath + "5.zip",
                                    "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + "com.easy.tbs");
                            QbSdk.initX5Environment(App.bsnContext.get(), cb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        mWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!init) {
                    Toast.makeText(MainActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(ReadActivity.goToRead(MainActivity.this, new File(rootPath + "1.docx")));
            }
        });

        mPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!init) {
                Toast.makeText(MainActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                return;
            }
                startActivity(ReadActivity.goToRead(MainActivity.this, new File(rootPath + "2.pptx")));
            }
        });

        mExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!init) {
                    Toast.makeText(MainActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(ReadActivity.goToRead(MainActivity.this, new File(rootPath + "3.xlsx")));
            }
        });

        mTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!init) {
                    Toast.makeText(MainActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(ReadActivity.goToRead(MainActivity.this, new File(rootPath + "4.txt")));
            }
        });


    }


    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onCoreInitFinished() {
            Log.e("onCoreInitFinished", "onCoreInitFinished");
            init = true;
            Toast.makeText(MainActivity.this, "初始化完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onViewInitFinished(boolean b) {
            Log.e("onViewInitFinished", b + "");

        }
    };


}
