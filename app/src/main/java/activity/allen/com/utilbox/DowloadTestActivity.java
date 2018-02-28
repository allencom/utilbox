package activity.allen.com.utilbox;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;

public class DowloadTestActivity extends FinalActivity {

   @ViewInject(id=R.id.status)
   private TextView status;

    @ViewInject(id=R.id.progressnum)
    private TextView progressnum;

    @ViewInject(id=R.id.testprogressbar)
    private ProgressBar testprogressBar;

    @ViewInject(id=R.id.testbutton,click="start")
    private  Button testButton;


    private FinalHttp fp= new FinalHttp();

    private Context mContext;

    private  Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dowload_test);
        FinalActivity.initInjectedView(this);
        mContext=this;


        mHandler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                int current = data.getInt("current");
                int count = data.getInt("count");
                testprogressBar.setProgress(current);
                progressnum.setText(""+current/count*100l+"%");
            }
        };


    }


    public void start(View v)
    {

        String target = Environment.getExternalStorageDirectory().toString();


        try{
        Log.e("msg",target);
        fp.download("http://images.ali213.net/picfile/pic/2018/02/24/927_2018022440618198.jpg",target + "/927_2018022440618198.jpg", new AjaxCallBack<File>(){
            @Override
            public void onStart() {
                Log.e("msg","开始下载了");
                System.out.println("开始下载文件");
                testButton.setEnabled(false);
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count,current);
                Log.e("msg",""+current/count*100l+"%");
                Bundle data =new Bundle();
                data.putInt("current",(int)current);
                data.putInt("count",(int)count);
                Message message =new Message();
                message.setData(data);
                mHandler.sendMessage(message);
            }

            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                Log.e("msg","下载成功");
                Toast.makeText(DowloadTestActivity.this,"下载成功",Toast.LENGTH_LONG);
                testButton.setEnabled(true);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {

                super.onFailure(t, errorNo, strMsg);
                Log.e("msg","下载失败"+strMsg);
                testButton.setEnabled(true);
            }
        });}catch (Exception e){
            e.printStackTrace();
        }
    }

    class ProgressThread extends Thread{
        @Override
        public void run() {
            System.out.println("DownloadThread id " + Thread.currentThread().getId());


            String target = Environment.getDataDirectory().getAbsolutePath().toString();
            Log.e("msg",target);



            fp.download("http://images.ali213.net/picfile/pic/2018/02/24/927_2018022440618198.jpg",target  , new AjaxCallBack<File>(){
                @Override
                public void onStart() {
                    Log.e("msg","开始下载了");
                    System.out.println("开始下载文件");
                }

                @Override
                public void onLoading(long count, long current) {
                    Log.e("msg","   "+current/count);
                    Bundle data =new Bundle();
                    data.putInt("current",(int)current);
                    Message message =new Message();
                    message.setData(data);
                    mHandler.sendMessage(message);
                }



                @Override
                public void onSuccess(File file) {
                    Log.e("msg","下载成功");
                    Toast.makeText(DowloadTestActivity.this,"下载成功",Toast.LENGTH_LONG);
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                }
            });
        }
    }



}
