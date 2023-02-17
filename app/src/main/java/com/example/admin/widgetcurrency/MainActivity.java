package com.example.admin.widgetcurrency;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<ValCurs> {
    
    private static final String TAG = "myWidget";

    public static final String USDTORUB = "USDTORUB";

    private Retrofit retrofit;

    private String url = "http://www.cbr.ru";

    private CBApi cbApi;

    private int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context ctx = getApplicationContext();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            widgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
            );
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        cbApi = retrofit.create(CBApi.class);
    }

    public void requestRate(View view) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 08/06/2016 - запрос в таком виде!

        String requestDate = String.format(
                Locale.ENGLISH,
                "%02d/%02d/%4d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)
        );

        Log.d(TAG, "Request date: "+ requestDate);

        cbApi.loadExchangeRates(requestDate).enqueue(this);
    }

    @Override
    public void onResponse(Call<ValCurs> call, Response<ValCurs> response) {
        Log.d(TAG, "onResponse");

        String rate = "";

        for(Valute v : response.body().getValute())
        {
            Log.d(TAG, "Currency: " + v.getCharCode() + ", rate: "+ v.getValue());
            if(v.getCharCode().equals("USD"))
            {
                rate = v.getValue();
                break;
            }
        }
        SharedPreferences prefs = getSharedPreferences(
                getPackageName(),
                MODE_PRIVATE
        );
        if(prefs != null)
        {
            prefs.edit().putString(USDTORUB, rate).apply();
        }
        setReturnResult(rate);
    }

    private void setReturnResult(String rate) {
        //PendingIntent pendingIntent = ExchangeWidgetProvider.getPendingIntent(this);
        RemoteViews rv = ExchangeWidgetProvider.updateView(this, rate);

        AppWidgetManager manager = AppWidgetManager
                .getInstance(getApplicationContext());

        manager.updateAppWidget(widgetId, rv);

        Intent res = new Intent();
        res.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setResult(RESULT_OK, res);
        finish();
    }

    @Override
    public void onFailure(Call<ValCurs> call, Throwable t) {
        Log.d(TAG, "onFailure " + t);
    }
}
