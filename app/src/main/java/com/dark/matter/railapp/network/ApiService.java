package com.dark.matter.railapp.network;


import com.dark.matter.railapp.model.GetTrainDetailsResponse;
import com.dark.matter.railapp.model.GetTrainResponse;
import com.dark.matter.railapp.model.TrainNameSuggesationResponse;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiService {

    String BASE_URL = "https://api.railwayapi.com/v2/";

    //https://api.railwayapi.com/v2/live/train/12933/date/20-10-2018/apikey/mhem63khmr
    @GET
    Observable<Response<GetTrainDetailsResponse>> getTrainDetails(@Url String url);

    @GET
    Observable<Response<GetTrainDetailsResponse>> getTrain(@Url String url);

    //https://api.railwayapi.com/v2/suggest-train/train/shiv/apikey/myapikey/
    @GET
    Observable<Response<TrainNameSuggesationResponse>> getTrains(@Url String url);

    @GET
    Observable<Call<JsonElement>> getLocalTrains(@Url String url);



}