package com.builder.dealocal;

import java.io.IOException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


public class EventsActivity extends Activity {
	final String TAG = EventsActivity.class.getSimpleName();
	ListView eventsList;
	 OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventsList = (ListView) findViewById(R.id.eventsList);
        client = new OkHttpClient();
        try {
			run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
       
    }
    public void run() throws Exception {
        Request request = new Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build();

        client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

	            Headers responseHeaders = response.headers();
	            for (int i = 0; i < responseHeaders.size(); i++) {
	              System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
	            }

	            System.out.println(response.body().string());
	          }
				
			
			@Override
			public void onFailure(Request arg0, IOException throwable) {
				throwable.printStackTrace();
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class getEvents extends AsyncTask<String, Integer, String>
    {
    	OkHttpClient client = new OkHttpClient();

		@Override
		protected String doInBackground(String... apiEndPoint) {
			Request request = new Request.Builder()
            .url(apiEndPoint[0])
            .build();

        Response response = null;
		try {
			response = client.newCall(request).execute();

	        return response.body().string();
		} catch (IOException e) {
			Log.d(TAG, "Failed to get any responce");
			e.printStackTrace();
		}
		return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);
		}
    	
    }
}
