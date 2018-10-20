package com.dark.matter.railapp.common;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import io.reactivex.functions.Consumer;
import retrofit2.Response;


public class GenericCallback_Success<T> implements Consumer<Response<T>> {
    Context mContext;
    boolean showGenericToast;
    OriginalResponse mListener;
    Activity mActivity;
    boolean handle401 = true;

    // For background services
    public GenericCallback_Success(Context context, boolean showGenericToast,
                                   OriginalResponse listener,
                                   boolean handle401) {
        this.mContext = context;
        this.showGenericToast = showGenericToast;
        this.mListener = listener;
        this.handle401 = handle401;
    }

    // For Activities
    public GenericCallback_Success(Activity activity, boolean showGenericToast,
                                   OriginalResponse listener) {
        this.mListener = listener;
        this.mActivity = activity;
        this.mContext = activity;
        this.showGenericToast = showGenericToast;
    }

    // For Activities - with handle401
    /*public GenericCallBack(Activity activity, boolean showGenericToast,
                           OriginalResponse originalResponse, boolean handle401) {
        this.originalResponse = originalResponse;
        this.mActivity = activity;
        this.mContext = activity.getBaseContext();
        this.showGenericToast = showGenericToast;
        this.handle401 = handle401;
    }*/

    @Override
    public void accept(Response<T> response) throws Exception {

        if (response.code() == 200 && response != null
                && response.isSuccessful() && response.body() != null) {
            mListener.rawResponse(true, response.body());
        } else if (response.code() == 401 && handle401) {
            mListener.rawResponse(false, null);

        } else {
            mListener.rawResponse(false, null);
            if (showGenericToast) {
                //Toast.makeText(mContext, toast_respnse_fail, Toast.LENGTH_LONG).show();
            }


        }

    }

}
