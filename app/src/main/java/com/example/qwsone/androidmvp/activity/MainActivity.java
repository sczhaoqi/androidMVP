package com.example.qwsone.androidmvp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qwsone.androidmvp.R;
import com.example.qwsone.androidmvp.application.DemoApplication;
import com.example.qwsone.androidmvp.model.UpdateAppInfo;
import com.example.qwsone.androidmvp.presenter.MainPresenter;
import com.example.qwsone.androidmvp.services.DownLoadService;
import com.example.qwsone.androidmvp.utils.CheckUpdateUtils;
import com.example.qwsone.androidmvp.view.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements MainView {

    private AlertDialog.Builder sDialog;
    MainPresenter presenter;
    @BindView(R.id.main_showContent)
    TextView mShowTxt;
    @BindView(R.id.bt_scanner) Button ss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDatas();
        ButterKnife.bind(this);

        //网络检查版本是否需要更新
        CheckUpdateUtils.checkUpdate("apk", "1.0.0", new CheckUpdateUtils.CheckCallBack() {
            @Override
            public void onSuccess(UpdateAppInfo updateInfo) {
                String isForce=updateInfo.data.getLastForce();//是否需要强制更新
                String downUrl= updateInfo.data.getUpdateurl();//apk下载地址
                String updateinfo = updateInfo.data.getUpgradeinfo();//apk更新详情
                String appName = updateInfo.data.getAppname();
                Log.e("更新地址",downUrl);
                if(isForce.equals("1")&& !TextUtils.isEmpty(updateinfo)){//强制更新
                    forceUpdate(MainActivity.this,appName,downUrl,updateinfo);
                }else{//非强制更新
                    //正常升级
                    normalUpdate(MainActivity.this,appName,downUrl,updateinfo);
                }
            }

            @Override
            public void onError() {

                noneUpdate(MainActivity.this);
            }
        });
    }

    /**
     * 强制更新
     * @param context
     * @param appName
     * @param downUrl
     * @param updateinfo
     */
    private void forceUpdate(final Context context, final String appName, final String downUrl, final String updateinfo) {
        sDialog = new AlertDialog.Builder(context);
        sDialog.setTitle(appName+"又更新咯！");
        sDialog.setMessage(updateinfo);
        sDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!canDownloadState()) {
                    showDownloadSetting();
                    return;
                }
                // AppInnerDownLoder.downLoadApk(MainActivity.this,downUrl,appName);
                dialog.dismiss();
                // 启动后台服务下载apk
                startService(new Intent(MainActivity.this, DownLoadService.class));

            }
        }).setCancelable(false).create().show();
    }

    /**
     * 正常更新
     * @param context
     * @param appName
     * @param downUrl
     * @param updateinfo
     */
    private void normalUpdate(Context context, final String appName, final String downUrl, final String updateinfo) {
        sDialog = new AlertDialog.Builder(context);
        sDialog.setTitle(appName+"又更新咯！");
        sDialog.setMessage(updateinfo);
        sDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!canDownloadState()) {
                    showDownloadSetting();
                    return;
                }
                // AppInnerDownLoder.downLoadApk(MainActivity.this,downUrl,appName)


            }
        }).setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).create().show();

    }


    /**
     * 无需更新
     * @param context
     */
    private void noneUpdate(Context context) {
        sDialog = new AlertDialog.Builder(context);
        sDialog.setTitle("版本更新")
                .setMessage("当前已是最新版本无需更新")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false) .create().show();
    }
    private void showDownloadSetting() {
        String packageName = "com.android.providers.downloads";
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        if (intentAvailable(intent)) {
            startActivity(intent);
        }
    }

    private boolean intentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    private boolean canDownloadState() {
        try {
            int state = this.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void loadDatas() {
        presenter = new MainPresenter();
        presenter.addTaskListener(this);
        presenter.getString();
    }
    @Override
    public void onShowString(String str) {
        mShowTxt.setText(str);
    }
    // 你也可以使用简单的扫描功能，但是一般扫描的样式和行为都是可以自定义的，这里就写关于自定义的代码了
    // 可以把这个方法作为一个点击事件
    @OnClick(R.id.bt_scanner )   //给 button1 设置一个点击事件
    public void showToast(){
        ss.setText("ButterKnife");
        Toast.makeText(this, "is a click", Toast.LENGTH_SHORT).show();
    }
}
