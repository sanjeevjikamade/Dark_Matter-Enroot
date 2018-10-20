package com.dark.matter.railapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dark.matter.railapp.common.GenericCallback_Error;
import com.dark.matter.railapp.common.GenericCallback_Success;
import com.dark.matter.railapp.common.OriginalResponse;
import com.dark.matter.railapp.databinding.ActivityMainBinding;
import com.dark.matter.railapp.model.GetTrainDetailsResponse;
import com.dark.matter.railapp.model.TrainNameSuggesationResponse;
import com.dark.matter.railapp.network.ApiServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.dark.matter.railapp.common.Utilities.isInternetAvailable;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String APIKEY = "aslaerogy5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setTrainDetails(this);
        binding.setTrainIdString("");

        //getTrainDetails();

        binding.editSearchTrain.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TextWatcherTest", "afterTextChanged:\t" + s.toString());
                if (s.length() > 3) {
                    binding.setTrainIdString(s.toString());
                }
            }
        });

    }

    public void onSendClick(String enteredString) {
        getTrains(enteredString);
    }

    public void onSendClick() {
        Intent i = new Intent(this, StatusListAcitivity.class);
        startActivity(i);
    }
    public void onShowMap() {
        Intent i = new Intent(this, TrainsListAcitivity.class);
        startActivity(i);
    }

    public void getTrains(String enteredString) {
        if (!isInternetAvailable(this)) {
            return;
        }
        //showProgress();
        OriginalResponse<TrainNameSuggesationResponse> successRes =
                (boolean statusCode, TrainNameSuggesationResponse response) -> {
                    //hideProgress();
                    if (!statusCode) {
                        return;
                    }
                    Log.e("", "response" + response.toString());
                    //binding.text.setText(response.getCurrentStation().toString());
                    //if (response.getStatus() != SUCCESS_CODE) {
                    //Toast.makeText(this, toast_respnse_fail, Toast.LENGTH_SHORT).show();
                    //return;
                    //}
                    getTrainDetails(response.getTrains().get(0).getNumber());
                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    //hideProgress();
                    return;

                };
        String URL = "https://api.railwayapi.com/v2/suggest-train/train/" + enteredString + "/apikey/" + APIKEY + "/";
        ApiServiceFactory.getApiService().getTrains(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(MainActivity.this, true, successRes),
                        new GenericCallback_Error<>(MainActivity.this, true, errorRes));
    }

    public void getTrainDetails(String trainNumber) {
        if (!isInternetAvailable(this)) {
            return;
        }
        //showProgress();
        OriginalResponse<GetTrainDetailsResponse> successRes =
                (boolean statusCode, GetTrainDetailsResponse response) -> {
                    //hideProgress();
                    if (!statusCode) {
                        return;
                    }
                    Log.e("", "response" + response.toString());
                    binding.text.setText(response.getPosition());
                    //if (response.getStatus() != SUCCESS_CODE) {
                    //Toast.makeText(this, toast_respnse_fail, Toast.LENGTH_SHORT).show();
                    //return;
                    //}

                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    //hideProgress();
                    return;

                };
        String URL = "https://api.railwayapi.com/v2/live/train/" + trainNumber + "/date/20-10-2018/apikey/" + APIKEY + "";
        ApiServiceFactory.getApiService().getTrainDetails(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(MainActivity.this, true, successRes),
                        new GenericCallback_Error<>(MainActivity.this, true, errorRes));
    }

}
