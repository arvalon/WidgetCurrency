package com.example.admin.widgetcurrency;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class ExchangeWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(),
                Context.MODE_PRIVATE
        );
        if(prefs != null) {
            String rate = prefs.getString(MainActivity.USDTORUB, "UNKNOWN");
            updateView(context, rate);
        }
    }

    public static RemoteViews updateView(Context context, String rate) {
        RemoteViews rv = new RemoteViews(
                context.getPackageName(),
                R.layout.currency
        );

        rv.setTextViewText(R.id.exchange_rate, "USD: " + rate);

        rv.setOnClickPendingIntent(
                R.id.exchange_rate,
                getPendingIntent(context)
        );
        return rv;
    }

    public static PendingIntent getPendingIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                33,
                i,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
