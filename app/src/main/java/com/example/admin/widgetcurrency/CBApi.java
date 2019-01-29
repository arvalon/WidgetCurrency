package com.example.admin.widgetcurrency;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CBApi {

    @GET("/scripts/XML_daily.asp")
    Call<ValCurs> loadExchangeRates(@Query("date_req") String date);

    //        /scripts/XML_daily.asp?date_req=01/01/2015


    @GET("/scripts/XML_daily.asp")
    Call<ValCurs> loadExchangeRates(@Query("date_req") String date, @Query("user_id") int user);

    //        /scripts/XML_daily.asp?date_req=01/01/2015&user_id=2
}
