package com.codevinci.ceva.networkcall.network;

import com.codevinci.ceva.networkcall.model.CertificatePinsModel;

import io.reactivex.Observable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCall {

    DefaultService defaultService;

    public BaseCall(ConnectionProvider connectionProvider, String BASE_URL, long timeOut, List<CertificatePinsModel> certificatePinsModels){

        defaultService = connectionProvider.createService(BASE_URL, DefaultService.class,timeOut,certificatePinsModels);
    }

    /**
     *
     * @param urlLink
     * @return
     */
    public Observable<String> post(String urlLink){
        // Use Retrofit to call url and get result.
        return callNetworkPost(urlLink);
    }

    public Observable<String> get(String urlLink){
        // Use Retrofit to call url and get result.
        return callNetworkGet(urlLink);
    }


    
    public Observable<String> callNetworkPost(String url){
        return defaultService .post(url);
    }

    public Observable<String> callNetworkGet(String url){
        return defaultService .post(url);
    }

    public Observable<String> callNetwork(String url,String method){
        if(method.equals(NetworkConstants.METHOD_POST)){
            return callNetworkPost(url);
        }else{
            return callNetworkGet(url);
        }
    }

}