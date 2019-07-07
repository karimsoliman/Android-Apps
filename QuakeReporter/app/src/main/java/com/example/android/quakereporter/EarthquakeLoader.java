package com.example.android.quakereporter;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<earthquake>> {

    private String Url;

    public EarthquakeLoader(Context context, String url){
        super(context);
        this.Url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<earthquake> loadInBackground() {
        return QueryUtils.fetchEarthquakeReport(this.Url);
    }
}
