package se.cloudworks.labb4;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

//Some code came from https://github.com/karlstad-business-school/ISGC12-code-examples as part of lectures and/or repositories with example code
//This have been modified but som original elements may still be there.
//Also from https://developer.android.com/training/volley/requestqueue

public class VolleySingleton {
    //sets up variables and objects
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    //constructor that sets the context and requestqueue, gets called internally
    private VolleySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();

    }

    //runs when we call the volley function, creates or returns a instance of the class
    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    //creates the requestqueue, most of this code is directly from android developer pages https://developer.android.com/training/volley/requestqueue
    //boilercode for how to set ut the queue the right way
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // Instantiate the cache
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap

            // Set up the network to use HttpURLConnection as the HTTP client.
            Network network = new BasicNetwork(new HurlStack());

            // Instantiate the RequestQueue with the cache and network.
            requestQueue = new RequestQueue(cache, network);

            // Start the queue
            requestQueue.start();
        }
        return requestQueue;
    }
    //adds the request to the queue, declares a genereic type with the <T>
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
