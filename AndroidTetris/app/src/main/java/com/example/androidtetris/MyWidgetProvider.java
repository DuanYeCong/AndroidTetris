package com.example.androidtetris;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by duanyecong on 17-10-14.
 */

public class MyWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appId = appWidgetIds[i];
            Intent intent = new Intent(context, Homepage.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appId);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent pendIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget_provider);
            views.setOnClickPendingIntent(R.id.widget_icon, pendIntent);
            appWidgetManager.updateAppWidget(appId,views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
