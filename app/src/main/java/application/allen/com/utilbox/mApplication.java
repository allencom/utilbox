package application.allen.com.utilbox;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by allen
 * 2017/10/25.
 * UTILBOX
 */

public class mApplication extends Application {

    public ExecutorService fixedThreadPool ;
    public static mApplication Appcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        Appcontext = this;
        fixedThreadPool  = Executors.newFixedThreadPool(3);
    }



    public static mApplication getInstance(){
        return Appcontext;
    }

}
