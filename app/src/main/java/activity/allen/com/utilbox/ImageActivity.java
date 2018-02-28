package activity.allen.com.utilbox;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class ImageActivity extends FinalActivity {


    @ViewInject(id = R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        FinalActivity.initInjectedView(this);
        Log.e("a","this is imageactivity");
        Bundle extras  =getIntent().getExtras();
        Bitmap photo = extras.getParcelable("data");
        Drawable drawable = new BitmapDrawable(photo);
        imageView.setImageDrawable(drawable);

    }
}
