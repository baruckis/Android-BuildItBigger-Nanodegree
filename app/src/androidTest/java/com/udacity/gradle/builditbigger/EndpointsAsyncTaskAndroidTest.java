package com.udacity.gradle.builditbigger;

import android.os.ConditionVariable;
import android.test.AndroidTestCase;

/**
 * Created by Andrius-Baruckis.
 */
public class EndpointsAsyncTaskAndroidTest extends AndroidTestCase implements EndpointsAsyncTask.Callbacks {

    private ConditionVariable mWait;

    public void testVerifyEndpointsAsyncTaskNonEmptyResponse() {

        mWait = new ConditionVariable();

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();

        mWait.block();
    }

    @Override
    public void onAsyncTaskLoaded(String result) {
        assertNotNull(result);
        assertNotSame(result, "");
        mWait.open();
    }

    @Override
    public void onAsyncTaskError() {
        fail();
        mWait.open();
    }
}
