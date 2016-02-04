/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.baruckis.nanodegree.builditbigger.backend;

import com.baruckis.nanodegree.javajoketellinglibrary.JokeTellingClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.builditbigger.nanodegree.baruckis.com",
    ownerName = "backend.builditbigger.nanodegree.baruckis.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that tells a joke from java library */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        JokeTellingClass jokeLibrary = new JokeTellingClass();
        String joke = jokeLibrary.getJoke();
        response.setData(joke);
        return response;
    }

}
