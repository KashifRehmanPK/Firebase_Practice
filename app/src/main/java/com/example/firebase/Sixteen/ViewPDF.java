package com.example.firebase.Sixteen;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.firebase.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewPDF extends AppCompatActivity {

    WebView pdfview;
   // private WebView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);


        pdfview = findViewById(R.id.viewpdf);
        pdfview.getSettings().setJavaScriptEnabled(true);
        pdfview.getSettings().setAllowFileAccessFromFileURLs(true);
        pdfview.getSettings().setAllowUniversalAccessFromFileURLs(true);




        String filename = getIntent().getStringExtra("filename");
        String fileurl = getIntent().getStringExtra("fileurl");

        //Log.d(TAG, "onCreate: /////////////////////////"+fileurl);

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("Opening .....!!!");


        pdfview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                pd.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                pd.dismiss();

            }
        });


        String url = "";
        try {
            url = URLEncoder.encode(fileurl, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String viewerUrl = "http://docs.google.com/gview?embedded=true&url=" + url;
        pdfview.loadUrl(viewerUrl);






        //                          -----------------PDF.js------------------------



        /*
        String fileUrl = getIntent().getStringExtra("fileurl");

        String pdfViewerUrl = "file:///android_asset/pdfjs/web/viewer.html?file=" + fileUrl;
        pdfview.loadUrl(pdfViewerUrl);
*/





     //             1              -----------------PDF.js------------------------
/*

        String fileUrl = getIntent().getStringExtra("fileurl");


       // String fileUrl1 = "https://example.com/path/to/pdf/file.pdf"; // the PDF file from a different origin
        String proxyUrl = "https://cors-anywhere.herokuapp.com/"; // a proxy service that allows CORS requests
        String pdfViewerUrl = "file:///android_asset/pdfjs/web/viewer.html?file=" + proxyUrl + fileUrl;


        pdfview.setWebViewClient(new WebViewClient());
        pdfview.loadUrl(pdfViewerUrl);

*/




        //             1              -----------------PDF.js------------------------

       /* String proxyUrl = "https://cors-anywhere.herokuapp.com/";
        String encodedFileUrl = null;
        try {
            encodedFileUrl = URLEncoder.encode(fileurl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String pdfViewerUrl = "file:///android_asset/pdfjs/web/viewer.html?file=" + proxyUrl + encodedFileUrl;

        pdfview.loadUrl(pdfViewerUrl);
*/


    }


}