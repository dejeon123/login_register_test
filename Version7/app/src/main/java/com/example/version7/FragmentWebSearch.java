package com.example.version7;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentWebSearch extends Fragment {

    private WebView webView;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_web_search, container,false);

        progressBar = v.findViewById(R.id.progressBar);

        webView = v.findViewById(R.id.webview);
        webView.setVisibility(View.INVISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(getActivity(),"Page is loading", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"Page Loaded",Toast.LENGTH_SHORT).show();

            }

        });
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_BACK && keyEvent.getAction()== MotionEvent.ACTION_UP
                && webView.canGoBack()){
                    return true;
                }
                return false;
            }
        });


        webView.loadUrl("https://edushare-services.herokuapp.com/v2.0/search/googletemplate");


        return v;
    }


}
