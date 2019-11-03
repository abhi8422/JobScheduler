package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.SystemClock;
import android.util.Log;

public class ExampleScheduler extends JobService {
    private static final String TAG = "ExampleScheduler";
    private boolean JobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job Started");
        dobackground(params);
        return true;
    }

    private void dobackground(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(JobCancelled){
                    return;
                }
                for (int i = 0; i <10 ; i++) {
                    Log.d(TAG, "run: "+i);
                    SystemClock.sleep(1000);
                }
                Log.d(TAG, "Job Finished");
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job cancelled before completion");
        JobCancelled=true;
        return true;
    }
}
