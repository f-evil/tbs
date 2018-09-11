package com.easy.tbs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

public class ReadActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {


    private LinearLayout mLlRoot;

    private TbsReaderView mTbsReaderView;

    private File mFile;

    public static Intent goToRead(Context context, File file) {
        Intent i = new Intent(context, ReadActivity.class);
        i.putExtra("file", file);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read);

        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mFile = (File) getIntent().getSerializableExtra("file");

        mTbsReaderView = new TbsReaderView(this, this);
        mLlRoot.addView(
                mTbsReaderView,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));

        if (mFile != null) {
            displayFile(mFile.getAbsolutePath());
        }
    }

    private void displayFile(String file) {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", file);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(file), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        } else {
            if (mFile == null) {
                return;
            }
            try {
                File mFile = new File(file);
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //设置intent的Action属性
                intent.setAction(Intent.ACTION_VIEW);
                //获取文件file的MIME类型
                String type = MimeTypeUtils.getMIMEType(mFile);
                //设置intent的data和Type属性。
                intent.setDataAndType(/*uri*/Uri.fromFile(mFile), type);
                //跳转
                startActivity(intent);

                finish();
            } catch (Exception e) {
                Log.e(">>>>>>>>>>>>>>>","无法打开本文件.");
            }
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTbsReaderView.onStop();
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
