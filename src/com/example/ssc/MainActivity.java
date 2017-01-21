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
		dialog.setMessage("���ڼ�����......");
		init();
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		// WebView����web��Դ
		webView.loadUrl("60.205.139.178");
		// ����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setAppCacheEnabled(false); 
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// ����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
				view.loadUrl(url);
				return true;
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if (newProgress == 100) {
					// ��ҳ�������
					dismissLoadingDialog();
				} else {
					// ������
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
                webView.goBack();//������һҳ��
                return true;
            }
            else
            {
                System.exit(0);//�˳�����
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
