package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baruckis.nanodegree.androidjokeshowinglibrary.JokeShowingActivity;
import com.baruckis.nanodegree.builditbigger.MessageDialog;
import com.baruckis.nanodegree.builditbigger.Utils;
import com.baruckis.nanodegree.javajoketellinglibrary.JokeTellingClass;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.Callbacks {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            MessageDialog.newInstance(
                    R.drawable.ic_info_black_24dp,
                    getString(R.string.about_title),
                    getString(R.string.about_body,
                            getString(R.string.app_name),
                            Utils.getVersionName(this),
                            Utils.getVersionCode(this)))
                    .show(getFragmentManager(), MessageDialog.TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        JokeTellingClass jokeLibrary = new JokeTellingClass();
        String joke = jokeLibrary.getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_LONG).show();
    }

    public void launchJokeActivity(View view){
        Intent intent = new Intent(this, JokeShowingActivity.class);
        JokeTellingClass jokeLibrary = new JokeTellingClass();
        String joke = jokeLibrary.getJoke();
        intent.putExtra(JokeShowingActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    public void runAsyncTask(View view) {

        mProgressDialog = ProgressDialog.show(this, "", getString(R.string.loading_msg), true);

        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void onAsyncTaskLoaded(String result) {

        mProgressDialog.dismiss();

        Intent intent = new Intent(this, JokeShowingActivity.class);
        intent.putExtra(JokeShowingActivity.JOKE_KEY, result);
        startActivity(intent);
    }

    @Override
    public void onAsyncTaskError() {

        mProgressDialog.dismiss();

        Toast.makeText(this, getString(R.string.async_task_error_msg), Toast.LENGTH_LONG).show();
    }
}
