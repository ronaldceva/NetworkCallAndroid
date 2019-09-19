package com.codevinci.ceva.networkcall;

import com.codevinci.ceva.networkcall.model.CertificatePinsModel;
import com.codevinci.ceva.networkcall.network.BaseCall;
import com.codevinci.ceva.networkcall.network.ConnectionProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class CevaNetworkCall {

    private BaseCall baseCall;
    private List<CertificatePinsModel> certificatePinsModelsList = new ArrayList<>();
    private String baseUrl;
    private long readWriteTimeout;

    /**
     *
     * @param baseUrl
     * @param readWriteTimeout
     * @return
     */
    public CevaNetworkCall init(String baseUrl, long readWriteTimeout) {
        return new CevaNetworkCall(baseUrl, readWriteTimeout);
    }

    /**
     *
     * @param baseUrl
     * @param readWriteTimeout
     */
    public CevaNetworkCall(String baseUrl, long readWriteTimeout) {
        this.baseUrl = baseUrl;
        this.readWriteTimeout = readWriteTimeout;
        ConnectionProvider connectionProvider = new ConnectionProvider();

        baseCall = new BaseCall(connectionProvider, baseUrl, readWriteTimeout, certificatePinsModelsList);
    }

    /**
     *
     * @return
     */
    public List<CertificatePinsModel> getCertificatePinsModels() {
        return certificatePinsModelsList;
    }

    /**
     *
     * @param hostname
     * @param sha256pin
     */
    public void addSSLPin(String hostname, String sha256pin) {

        CertificatePinsModel certificatePinsModel = new CertificatePinsModel(hostname, sha256pin);
        certificatePinsModelsList.add(certificatePinsModel);

        //update NetworkCall class
        new CevaNetworkCall(baseUrl,readWriteTimeout);
    }

    /**
     *
     * @param params
     * @return Observable<String>
     */
    public Observable<String> post(String params){
        return baseCall.post(params);
    }

}
