package com.nedandsara.closetohack;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Looper;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.sns.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.model.*;


import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.location.LocationServices;

/**
 * Created by nbfriend on 7/27/2015.
 *
 *
 * Location APIs: https://developers.google.com/android/reference/com/google/android/gms/location/package-summary
 */
public class UploadLocationTask extends AsyncTask<MainActivity, Void, Void> {

    private MainActivity activity;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonDynamoDBClient ddbClient;
    private DynamoDBMapper mapper;

    //  private Integer progressStatus;

    protected Void doInBackground(MainActivity... activities){
        activity = activities[0];

    //        new AlertDialog.Builder(activity).setMessage("DO IN BACKGROUND: " + Calendar.getInstance().getTimeInMillis()).create().show();

        // Initialize the Amazon Cognito credentials provider to log into AWS
        credentialsProvider = new CognitoCachingCredentialsProvider(
                activity.getBaseContext(), // Context
                "us-east-1:b1cdf9f9-d80d-4cfe-a032-9d1146f0d164", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );


        // Connect to the Cloud DB
        ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        mapper = new DynamoDBMapper(ddbClient);

     //   new AlertDialog.Builder(activity).setMessage("PRE - SAVE: " + Calendar.getInstance().getTimeInMillis()).create().show();

        // save User to AWS
        User user = new User();
        user.setUserID("NEW USER");
        user.setLocation(activity.mLastLocation.toString() + " TIME: " +
                new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        mapper.save(user);

    //    new AlertDialog.Builder(activity).setMessage("POST - SAVE: " + Calendar.getInstance().getTimeInMillis()).create().show();

        return null;
    }


    // This is called each time you call publishProgress()
    protected void onProgressUpdate(Void... progress) {
//        progressStatus = progress[0];
    }

    // This is called when doInBackground() is finished
    protected void onPostExecute(Void result) {
        //showNotification("Downloaded " + result + " bytes");
    }

}
