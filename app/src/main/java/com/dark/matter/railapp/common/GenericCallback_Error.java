package com.dark.matter.railapp.common;

import android.content.Context;
import android.widget.Toast;

import com.dark.matter.railapp.BuildConfig;

import io.reactivex.functions.Consumer;


public class GenericCallback_Error<T> implements Consumer<T> {
    Context mContext;
    boolean showGenericToast;
    OriginalResponse mListener;

    // For background services
    public GenericCallback_Error(Context context, boolean showGenericToast,
                                 OriginalResponse listener) {
        this.mContext = context;
        this.showGenericToast = showGenericToast;
        this.mListener = listener;
    }

    // For Activities
    /*public GenericCallback_Error(Activity activity, boolean showGenericToast,
                                           OriginalResponse listener) {
        this.mListener = listener;
        this.mActivity = activity;
        this.mContext = activity;
        this.showGenericToast = showGenericToast;
    }*/

    // For Activities - with handle401
    /*public GenericCallBack(Activity activity, boolean showGenericToast,
                           OriginalResponse originalResponse, boolean handle401) {
        this.originalResponse = originalResponse;
        this.mActivity = activity;
        this.mContext = activity.getBaseContext();
        this.showGenericToast = showGenericToast;
        this.handle401 = handle401;
    }*/

    /*@Override
    public void onNext(Response<T> response) {
        if (response.code() == 200 && response != null
                && response.isSuccessful() && response.body() != null) {
            mListener.rawResponse(true, response.body());
        } else if (response.code() == 401 && handle401) {
            mListener.rawResponse(false, null);
            Utilities.showSessionExpiredDialog(mActivity);
        } else {
            mListener.rawResponse(false, null);
            if (showGenericToast) {
                Toast.makeText(mContext,
                        R.string.respnse_fail, Toast.LENGTH_LONG)
                        .show();
            }

            if (BuildConfig.DEBUG) {
                Utilities.showLog("API", "Unexpected Response Received! "
                        + mContext.getPackageName());
            }
        }
    }*/

    /*@Override
    public void onError(Throwable e) {
        mListener.rawResponse(false, null);
        if (showGenericToast) {
            Toast.makeText(mContext,
                    R.string.respnse_fail, Toast.LENGTH_LONG)
                    .show();
        }
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }*/

    /*@Override
    public void onComplete() {
        mListener.rawResponse(true, null);
        Utilities.showLog(GenericCallback_Error.class.getSimpleName(),
                "The response service Observable has ended!");
    }*/

    @Override
    public void accept(T t) throws Exception {
        if (t instanceof Throwable) {
            mListener.rawResponse(false, null);
            if (showGenericToast) {
                //Toast.makeText(mContext, toast_respnse_fail, Toast.LENGTH_LONG).show();
            }
            if (BuildConfig.DEBUG) {
                ((Throwable) t).printStackTrace();
            }
        }
    }


}
