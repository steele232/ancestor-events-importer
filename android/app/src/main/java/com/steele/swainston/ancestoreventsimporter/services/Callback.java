package com.steele.swainston.ancestoreventsimporter.services;

public interface Callback<T> {
    void onComplete(T result);
}