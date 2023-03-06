package com.example.newsyllabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.firebase.firestore.OnProgressListener;

import java.net.URLEncoder;

public class PdfViewer extends AppCompatActivity {
    WebView webview;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        webview = findViewById(R.id.webView);
        toolbar = findViewById(R.id.pdftool);

        setSupportActionBar(toolbar);


       // toolbar.setTitle(pdfname);


        //progressbar = findViewById(R.id.progressbar);
        //progressbar.setVisibility(View.VISIBLE);

        String pdfurl = getIntent().getStringExtra("pdfurl");
        String pdfname = getIntent().getStringExtra("pdfname");
        //String url = "http://drive.google.com/viewerng/viewer?embedded=true&url="+ pdfurl;


        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(pdfname);
        pd.setMessage("Opening...");

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webview.loadUrl("javascript:(function() {" +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                pd.dismiss();
            }
        });
        String url = "";
        try {
            url = URLEncoder.encode(pdfurl, "UTF-8");
        } catch (Exception ex) {

        }

        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        // webview.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);


//       webview.setWebChromeClient(new WebChromeClient(){
//
//           @Override
//           public void onProgressChanged(WebView view, int newProgress) {
//               super.onProgressChanged(view, newProgress);
//               if (newProgress == 100){
//                   progressbar.setVisibility(View.GONE);
//               }
//           }
//       });
//       webview.loadUrl(url);

        // displayWebView();


    }

//    private void displayWebView() {
//        String intent = getIntent().getStringExtra("pdfurl");
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+ intent);
//                return true;
//            }
//        });
//        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+intent);
//    }
}