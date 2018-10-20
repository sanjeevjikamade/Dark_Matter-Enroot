package com.dark.matter.railapp.network;

import com.dark.matter.railapp.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    //private static Retrofit retrofit = null;
    static OkHttpClient.Builder okHttpClientBuilder;

    private ApiServiceFactory() {

    }

    public static ApiService getApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClientBuilder = new OkHttpClient.Builder();

        OkHttpClient okHttpClient = okHttpClientBuilder
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Logger for Api call
                //.addInterceptor(headerInterceptor)
                //.certificatePinner(getCertificatePinner())
                //.addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS) // 30 seconds Connection Timeout
                .readTimeout(60, TimeUnit.SECONDS) // 60 seconds Read Timeout
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        //if (retrofit == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // provides RxJava2 webservice call support here.
                .client(okHttpClient) // Sets OkHttpClient.
                .addConverterFactory(providesGsonConverterFactory()) // Set Gson converter here
                .baseUrl(ApiService.BASE_URL).build();
        //}
        return retrofit.create(ApiService.class);
    }

    /**
     * It provide Gson instance to convert Json to Pojo.
     *
     * @return
     */
    private static GsonConverterFactory providesGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.create());
    }

}
