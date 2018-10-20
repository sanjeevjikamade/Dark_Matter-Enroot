package com.dark.matter.railapp.network;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    String BASE_URL = "https://api.railwayapi.com/v2/";

//https://api.railwayapi.com/v2/live/train/12933/date/20-10-2018/apikey/mhem63khmr
    @GET("live/train/12933/date/20-10-2018/apikey/mhem63khmr/")
    Observable<Response<String>> getTrainDetails();

}