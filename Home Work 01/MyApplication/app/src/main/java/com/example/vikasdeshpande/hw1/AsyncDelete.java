package com.example.vikasdeshpande.hw1;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sainishanthdilly on 9/19/17.
 */

public class AsyncDelete extends AsyncTask<String,Void, String> {

    ReadCallBack readCallBack;

    AsyncDelete(ReadCallBack readCallBack){
        this.readCallBack = readCallBack;

    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();

        String url = strings[0];

        RequestBody formBody = new FormBody.Builder()
                .add("token", strings[1])
                .add("id",strings[2])
                .build();

        Request request =new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response res = null;
        try {
            res = client.newCall(request).execute();
            return res.body().string();

        }
        catch (Exception e){

            Log.d("Exception Occured",e.getMessage());

        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        readCallBack.callbackDelete(s);
        super.onPostExecute(s);
    }

    static interface ReadCallBack{

        void callbackDelete(String delete);

    }
}
