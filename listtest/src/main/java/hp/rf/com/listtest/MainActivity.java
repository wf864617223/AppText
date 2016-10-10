package hp.rf.com.listtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


import butterknife.ButterKnife;
//import hp.rf.com.listtest.refresh.PullToRefreshListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @ViewInject(R.id.listview)
    ListView lv;
    //private PullToRefreshListView myListView;

    private List<Picture> pictures = new ArrayList<>();
    private MyAdapter adapter;
    private List<Picture.TngouBean> tngou = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        x.view().inject(this);
        super.onCreate(savedInstanceState);

        //myListView = new PullToRefreshListView(MainActivity.this);
        //myListView.setPullLoadEnabled(true);
        //myListView.setScrollLoadEnabled(true);

        //lv = myListView.getRefreshableView();
        ButterKnife.bind(this);
        final Gson gson = new Gson();
        RequestParams params = new RequestParams("http://www.tngou.net/tnfs/api/news");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"===result===>"+result);
                Picture picture = gson.fromJson(result, Picture.class);
                tngou = picture.getTngou();
                adapter = new MyAdapter(MainActivity.this,tngou);
                Log.i(TAG , "==tngou==>"+tngou.toString());
                lv.setAdapter(adapter);
                lv.notifyAll();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
