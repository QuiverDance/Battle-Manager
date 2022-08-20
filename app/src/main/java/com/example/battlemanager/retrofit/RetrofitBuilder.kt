package com.example.battlemanager.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceFactory {
    companion object {
        inline fun <reified T> createService(): T {
            /**
             * baseUrl 부분에 서버 url 을 대입해야 한다.
             * api token 이 필요한 경우 github 에 public 으로 노출되지 않도록 관리하고 프로가드 난독화 rule 설정해야 한다.
             * 난독화하지 않을 경우 apk 를 디컴파일 시켜서 api token 값을 확인할 수 있다.
             */
            return Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().serializeNulls().create()
                    ))
//                .client(OkHttpClientFactory.create())
                .build()
                .create(T::class.java)
        }
    }
}