package hp.rf.com.apptext;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by hp on 2016/5/16.
 */
public class MyAppclication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
