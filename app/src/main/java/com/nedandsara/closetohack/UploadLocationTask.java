package com.nedandsara.closetohack;

import android.app.Activity;
import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.model.*;

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

    protected Void doInBackground(MainActivity... activities){        // Initialize the Amazon Cognito credentials provider
        activity = activities[0];

        // log into AWS
        credentialsProvider = new CognitoCachingCredentialsProvider(
                activity.getBaseContext(), // Context
                "us-east-1:b1cdf9f9-d80d-4cfe-a032-9d1146f0d164", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        mapper = new DynamoDBMapper(ddbClient);

        // save User to AWS
        User user = new User();
        user.setUserID("NEW USER");
        user.setLocation(activity.mLastLocation.toString());
        mapper.save(user);

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
