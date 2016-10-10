package hp.rf.com.htmltext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(),"control");
        webView.loadUrl("file:///android_asset/index.html");

    }
    public class JsInterface{
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(MainActivity.this,toast , Toast.LENGTH_SHORT).show();
            log("show toast success");
        }
        public void log(final String msg){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    //webView.loadUrl("javascript : log("+"'"+msg+"'"+")");
                    //webView.loadUrl("file:///android_asset/index2.html");
                    //webView.loadUrl("https://www.baidu.com/?tn=47018152_dg");
                    Intent intent = new Intent(MainActivity.this,TestActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
