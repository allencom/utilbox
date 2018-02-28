package activity.allen.com.utilbox;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

public class NetImageActivity extends FinalActivity {


    @ViewInject(id = R.id.imageView,click ="click")
    ImageView imageView;


    private ContentLoadingProgressBar loadingProgressBar;

    private FinalHttp fh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        FinalActivity.initInjectedView(this);
        Log.e("a","this is Netimageactivity");
        Bundle extras  =getIntent().getExtras();
        Bitmap photo = extras.getParcelable("data");
        Drawable drawable = new BitmapDrawable(photo);
        imageView.setImageDrawable(drawable);

        final Handler mHandler =new Handler();

        final Runnable mRunnable =new Runnable() {
            @Override
            public void run() {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        };

        mRunnable.run();
        new  Thread(mRunnable).start();

    }


    public  void click()
    {
        AjaxParams param=new AjaxParams();
        param.put("","");
        fh.get("",param , new AjaxCallBack<String>(){
            @Override
            public void onStart() {
                super.onStart();

                loadingProgressBar.show();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                loadingProgressBar.setProgress((int)current);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    private class ProgressThread extends Thread {
        private Handler handler;
        private List<String> data;



        public ProgressThread(Handler handler,List<String> data) {
            this.data = data;
            this.handler = handler;

        }
        @Override
        public void run() {

            Message msg = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putInt("state", 1);
            String a =new StringBuilder("1asdasd").append("123123123").toString();

            msg.setData(b);
            handler.sendMessage(msg);
        }

    }
    private final Handler handler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {
            List<String> data =new ArrayList<String>();



        }
    };

        private class ProgressTask extends AsyncTask<ArrayList<String>, Integer, Integer> {


        @Override
        protected void onPreExecute() {
            // 先显示ProgressDialog

        }

        @Override
        protected Integer doInBackground(ArrayList<String>[] arrayLists) {

            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {

        }
        }

    }
