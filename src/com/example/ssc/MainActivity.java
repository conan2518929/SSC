package com.example.ssc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private WebView webView;
	protected ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在加载中......");
		init();
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		// WebView加载web资源
		webView.loadUrl("60.205.139.178");
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setAppCacheEnabled(false); 
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if (newProgress == 100) {
					// 网页加载完成
					dismissLoadingDialog();
				} else {
					// 加载中
					showLoadingDialog();
				}
			}
		});
	}

	public void showLoadingDialog() {
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void showLoadingDialog(String text) {
		if (text != null) {
			dialog.setMessage(text);
		}
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void showLoadingDialog(int resId) {
		if (resId != 0) {
			dialog.setMessage(getString(resId));
		}
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void dismissLoadingDialog() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
