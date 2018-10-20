package com.dark.matter.railapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dark.matter.railapp.common.GenericCallback_Error;
import com.dark.matter.railapp.common.GenericCallback_Success;
import com.dark.matter.railapp.common.OriginalResponse;
import com.dark.matter.railapp.network.ApiServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.dark.matter.railapp.common.Utilities.isInternetAvailable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTrainDetails();
    }

    public void getTrainDetails() {
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
                    Log.e("","response"+response.toString());
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

        ApiServiceFactory.getApiService().getTrainDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(MainActivity.this, true, successRes),
                        new GenericCallback_Error<>(MainActivity.this, true, errorRes));
    }
}
