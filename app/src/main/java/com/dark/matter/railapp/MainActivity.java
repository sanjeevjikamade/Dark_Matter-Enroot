package com.dark.matter.railapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.dark.matter.railapp.common.GenericCallback_Error;
import com.dark.matter.railapp.common.GenericCallback_Success;
import com.dark.matter.railapp.common.OriginalResponse;
import com.dark.matter.railapp.databinding.ActivityMainBinding;
import com.dark.matter.railapp.model.GetTrainDetailsResponse;
import com.dark.matter.railapp.network.ApiServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.dark.matter.railapp.common.Utilities.isInternetAvailable;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

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

    public void getTrains(String enteredString) {
        if (!isInternetAvailable(this)) {
            return;
        }
        //showProgress();
        OriginalResponse<String> successRes =
                (boolean statusCode, String response) -> {
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

                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    //hideProgress();
                    return;

                };
        String URL = "https://api.railwayapi.com/v2/suggest-train/train/" + enteredString + "/apikey/myapikey/";
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
                    binding.text.setText(response.getCurrentStation().toString());
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
        String URL = "https://api.railwayapi.com/v2/live/train/" + trainNumber + "/date/20-10-2018/apikey/mhem63khmr";
        ApiServiceFactory.getApiService().getTrainDetails(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(MainActivity.this, true, successRes),
                        new GenericCallback_Error<>(MainActivity.this, true, errorRes));
    }
}
