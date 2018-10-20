package com.dark.matter.railapp.common;

/**
 * Created by nayana on 24/8/18.
 */

public interface OriginalResponse<T> {
    void rawResponse(boolean status, T t);
}
