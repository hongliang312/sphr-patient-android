package com.lightheart.sphr.patient.net;

import com.lightheart.sphr.patient.app.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fucp on 2018-5-23.
 * Description :
 */

public class RetrofitBuilder {

    private static Retrofit retrofit;

    public static synchronized Retrofit buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(OkHttpManager.getInstance())
                    .baseUrl(Constant.BASE_FILE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
