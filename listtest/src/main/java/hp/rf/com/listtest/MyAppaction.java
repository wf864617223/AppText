package hp.rf.com.listtest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by hp on 2016/5/23.
 */
public class MyAppaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
