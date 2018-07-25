package com.common.data.remote;

import com.common.amsubaselib.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author：WangLei
 * date:2018/5/23
 * QQ:619321796
 */
public interface BaseAPI {

    /**
     * 固件升级
     */
    @FormUrlEncoded
    @POST("getHardwareVersion.do")
    Observable<ResponseBody> getHardwareVersion(@FieldMap Map<String, String> params);

    String BASE_URL_APP = "http://203.195.168.139:8081/intellingence-web/";

    /**
     * Helper class that sets up a new services
     */
    class Creator {

        public static BaseAPI newApiService(String baseUrl) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(BaseAPI.class);
        }
    }
}
