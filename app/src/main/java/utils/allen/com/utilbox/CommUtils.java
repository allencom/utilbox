package utils.allen.com.utilbox;

/**
 * Created by Administrator on 2017/6/21.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
/*import net.tsz.afinal.FinalHttp;
 import net.tsz.afinal.http.AjaxCallBack;*/
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

/*import com.mobitax.util.CommUtil;

 import com.mobitax.application.GlobleApplication;*/

public class CommUtils {
    /**
     * Toast提示框
     *
     * @param message
     *            需要提示的消息
     * @param context
     *            当前Activity的Context对象
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    /**
     * Toast提示框
     *
     * @param message
     *            需要提示的消息
     * @param context
     *            当前Activity的Context对象
     */
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Alert提示框
     *
     * @param msg
     *            需要提示的消息
     * @param context
     *            当前Activity的Context对象
     */
    public static void showAlert(String msg, final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Alert提示框
     *
     * @param msg
     *            需要提示的消息
     * @param context
     *            当前Activity的Context对象
     */
    public static void showAlert(String msg, final Context context,
                                 OnClickListener confirClick) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("确定", confirClick);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String getLocalData(final Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("userInfo",
                Context.MODE_WORLD_READABLE);
        return sp.getString(key, "");
    }

    public static void setLocalData(final Context context, String key,
                                    String value) {

        SharedPreferences sp = context.getSharedPreferences("userInfo",
                Context.MODE_WORLD_READABLE);
        ;
        Editor edit = sp.edit();
        edit.putString(key, value);

        edit.commit();

    }

    public static void loginOut(final Context context) {

		/*
		 * Intent intent = new Intent();
		 * intent.setAction("com.aisino.mobioaclient.service.LocacService");
		 * context.stopService(intent);
		 *
		 * intent = new Intent();
		 * intent.setAction("com.aisino.mobioaclient.service.SubDataService");
		 * context.stopService(intent);
		 *
		 * intent = new Intent();
		 * intent.setAction("com.aisino.mobioaclient.page.pedometer.StepService"
		 * ); context.stopService(intent);
		 */

        SharedPreferences sp = context.getSharedPreferences("userInfo",
                Context.MODE_WORLD_READABLE);
        ;
        Editor edit = sp.edit();
        edit.putString("USERNAME", "");
        edit.putString("PASSWORD", "");
        edit.putString("IS_LOGIN", "");
        edit.putString("USERID", "");
        edit.putString("USER_REAL_NAME", "");
        edit.putString("PUSH_USER_ID", "");
        edit.putString("ORG_ID", "");
        edit.putString("IS_TRACK_RUNNING", "");
        edit.putString("COOK_STR", null);
        edit.clear();

        edit.commit();

        // GlobleApplication.getInstance().exitAll();
    }

    /**
     * Alert提示框
     *
     * @param msg
     *            需要提示的消息
     * @param context
     *            当前Activity的Context对象
     *
     */
    public static void showAlert(String msg, final Context context,
                                 OnClickListener confirClick, OnClickListener cancelClick) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("确定", confirClick)
                .setNegativeButton("取消", cancelClick);
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * 退出应用，带提示
     *
     * @param context
     */
    public static void quitApp(final Context context) {

        new AlertDialog.Builder(context).setTitle("退出移动诺诺财税")
                .setMessage("确认是否立即退出移动诺诺财税？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // System.exit(0);

                        System.exit(0);
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作

                    }
                }).show();
    }

    /**
     * 直接退出应用，无提示
     *
     * @param context
     */
    public static void quitAppDirect(final Context context) {

        System.out.println("推出");
        // GlobleApplication.getInstance().exit();

    }

    public static boolean isServiceRunning(Context mContext, String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {

            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 消息提示
     *
     * @param msg
     *            提示的消息
     * @param context
     *            当前Activity的Context对象
     */
    public static void showToast(String msg, final Context context) {
        Toast toast = new Toast(context);
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();// 显示
    }

    /**
     * 获取AndroidManifest.xml中App meteData
     *
     * @param metaDataName
     * @param context
     * @return
     */
    public static String getAppMetaData(String metaDataName,
                                        final Context context) {
        ApplicationInfo info;
        String strData = "";
        try {
            String a = context.getPackageName();
            info = context.getPackageManager().getApplicationInfo(a,
                    PackageManager.GET_META_DATA);
            strData = info.metaData.getString(metaDataName);
            System.out.println(strData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strData;
    }

    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        Log.v("网络状态", "连通状态...........");

                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.v("error", e.toString());
        }
        Log.v("网络状态", "网络不通...........");

        return false;
    }

    /**
     * 判断对象是否为空
     *
     * @param value
     * @return boolean
     */
    public static boolean isNullOrBlank(Object value) {
        if (null == value || value.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNullOrBlank(List value) {
        if (null == value || value.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String objectString(Object value) {

        if (value == null || "".equals(value)) {
            return "";
        } else {
            return value.toString();
        }

    }

    /**
     * 根据经纬度获取对应地址,此处的经纬度须使用Google或者高德地图的经纬度；<br>
     * 若使用百度地图经纬度，须经过百度API接口(BMap.Convertor.transMore(points,2,callback))的转换；
     *
     * @param longitude
     *            经度
     * @param latitude
     *            纬度
     * @return 详细街道地址
     */

	/*
	 * public static void getAddressByBaidu(double longitude,double
	 * latitude,AjaxCallBack callback){
	 *
	 * String
	 * url="http://api.map.baidu.com/geoconv/v1/?coords="+longitude+","+latitude
	 * +"&from=1&to=5&ak=x2v6wRqZA6DrMOX7TjHp2CBG";
	 * System.out.println("url:"+url); FinalHttp fh = new FinalHttp();
	 * fh.get(url, callback) ;
	 *
	 * }
	 */

    /**
     * 根据经纬度获取对应地址,此处的经纬度须使用Google或者高德地图的经纬度；<br>
     * 若使用百度地图经纬度，须经过百度API接口(BMap.Convertor.transMore(points,2,callback))的转换；
     *
     * @param //longitude
     *            经度
     * @param //latitude
     *            纬度
     * @return 详细街道地址
     */

	/*
	 * public static void getAddressByLoc(double longitude,double
	 * latitude,AjaxCallBack callback){
	 *
	 * String url=
	 * "http://api.map.baidu.com/geocoder/v2/?ak=x2v6wRqZA6DrMOX7TjHp2CBG&callback=renderReverse&location="
	 * +latitude+","+longitude+"&output=json&pois=0";
	 * System.out.println("url:"+url); FinalHttp fh = new FinalHttp();
	 * fh.get(url, callback) ;
	 *
	 * }
	 */



    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }

    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @param //view
     * @param //param
     * @throws JSONException
     */

    public static void tel(String telNum, Context context) {

        try {
            if (telNum.indexOf("01") == 0) {
                telNum = telNum.substring(1, telNum.length());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                + telNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public static void sms(String telNum, String message, Context context) {

        try {

            if (telNum.indexOf("01") == 0) {
                telNum = telNum.substring(1, telNum.length());
            }

            System.out.println(telNum);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Uri uri = Uri.parse("smsto:" + telNum);

        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

        intent.putExtra("sms_body", CommUtils.objectString(message));

        context.startActivity(intent);

    }

    public static boolean validCompIsContainer(View temp) {
        if (temp instanceof ViewGroup && !(temp instanceof AdapterView)) {
            return true;
        } else {
            return false;
        }
    }






}