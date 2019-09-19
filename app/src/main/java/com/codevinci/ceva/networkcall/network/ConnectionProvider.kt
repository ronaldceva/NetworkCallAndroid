package com.codevinci.ceva.networkcall.network


import com.codevinci.ceva.networkcall.BuildConfig
import com.codevinci.ceva.networkcall.model.CertificatePinsModel
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ConnectionProvider {

    fun <S> createService(baseUrl: String, serviceClass: Class<S>, timeOut:Long?,certificatePins:MutableList<CertificatePinsModel>): S {
        val KEY_TIMEOUT = timeOut?:120

        val builder = OkHttpClient.Builder()
                .connectTimeout(KEY_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(KEY_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(KEY_TIMEOUT, TimeUnit.SECONDS)

        //set interceptor
        if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.networkInterceptors().add(httpLoggingInterceptor)
        }

        //web logic ssl
        val certificatePinner = CertificatePinner.Builder()

                certificatePins.forEach {
                    certificatePinner.add(it.hostname!!,it.sha256pin)
                }


        builder.certificatePinner(certificatePinner.build())
        builder.retryOnConnectionFailure(true)


        val client = builder.build()


        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        return retrofit.create(serviceClass)
    }

}