package com.waterbell.hito.watersource;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.util.EncodingUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class PaymentGatewayPayU extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway_pay_u);

        WebView webViewPayU = (WebView) findViewById(R.id.webViewPayU);
        //webViewPayU.setWebViewClient(new WebViewClient());
        webViewPayU.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = webViewPayU.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String merchant_key="C0Dr8m";
        String salt="3sf0jURk";
        String action1 ="";
        String base_url="https://test.payu.in";
        int error=0;
        String hashString="";
        String txnid ="";
        Log.d("PAYUHOST", Uri.parse("http://yahoo.com").getHost().toString());

        Random rand = new Random();
        String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
        txnid=hashCal("SHA-256",rndm).substring(0,20);

        String hashSequence = "C0Dr8m|" + txnid + "|80.00|bisleri|Hirdesh|hirdesh2008@gmail.com|||||||||||3sf0jURk";
        String code = hashCal("SHA-512", hashSequence);

     //   String hash="";
      //  String hashSequence = "C0Dr8m" + "|" + txnid +"|250|WaterBottle|Hirdesh|hirdesh2008|||||||||||" + salt;
        /*
        String[] hashVarSeq=hashSequence.split("\\|");
        for(String part : hashVarSeq)
                {
                    hashString= (empty(part))?hashString.concat(""):hashString.concat(part);
                    hashString=hashString.concat("|");
                }


        hashString=hashSequence.concat(salt);
       */
       // hashString=hashSequence;
      //  hash=hashCal("SHA-512",hashString);
        action1=base_url.concat("/_payment");

        String postData = "key=C0Dr8m&hash=" +
                code +
                "&txnid=" + txnid +
                "&email=hirdesh2008@gmail.com" +
                "&amount=80.00&phone=08971498048&productinfo=bisleri&firstname=Hirdesh&surl=http://yahoo.com&furl=http://www.twitter.com";

        Log.d("PostData" , postData);

        webViewPayU.postUrl(action1, EncodingUtils.getBytes(postData, "BASE64"));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_gateway_pay_u, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up btnsolid, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String hashCal(String type,String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();


            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) hexString.append("0");
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }

        return hexString.toString();

    }

    public boolean empty(String s)
    {
        if(s== null || s.trim().equals(""))
            return true;
        else
            return false;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String host =  Uri.parse(url).getHost().toString();
            Log.d("OVER_RIDE_URL", url);
            Log.d("OVER_RIDE_HOST", host);

            if (Uri.parse(url).getHost().equals("www.yahoo.com")) {
                // This is my web site, so do not override; let my WebView load the page
                Intent intent = new Intent(PaymentGatewayPayU.this,PaymentSuccess.class );
                startActivity(intent);
                return false;
            } else if (Uri.parse(url).getHost().equals("www.twitter.com")){
                Intent intent = new Intent(PaymentGatewayPayU.this,PaymentFailed.class );
                startActivity(intent);
                return true;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs

            return false;
        }
    }
}
