package radioscope.bhupendrashekhawat.me.android.radioscope;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.RadioService.DARfmApi;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.CustomJsonDeserializer;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_BASE_URL = "http://api.dar.fm/";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private DARfmApi darFMApi;
    private Call<List<Track>> callTracks;
    private List<Track> allTracks;
    private ArrayList<Track> trackItems = new ArrayList<Track>();

    private ArrayList<String> songList = new ArrayList<String>();

    ListAdapter listAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isInternetPermissionGranted = isInternetPermissionGranted();

        Button playButton = (Button) findViewById(R.id.start_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StreamActivity.class);
                startActivity(intent);
            }
        });

        Log.d(LOG_TAG, "Internet access permission = " +isInternetPermissionGranted);

        /* Adding Logging for Retrofit */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!


       /* Gson gson = new Gson();
        java.lang.reflect.Type objectType = new TypeToken<List<Map<String,Object>>>(){}.getType();
        List<Map<String,Object>> output  = gson.fromJson(str, objectType);*/

      //  GsonBuilder gsonBuilder = new GsonBuilder();
     //   gsonBuilder.registerTypeAdapter(Book.class, new BookDeserializer());
       // Gson gson = gsonBuilder.create();

      /*  Gson gson = new GsonBuilder()
                .registerTypeAdapter(AllTracks.class, new ContentAdapter()).create();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(buildGsonConverter())
               .client(httpClient.build())
                .build();

        darFMApi = retrofit.create(DARfmApi.class);



        ListView listView = (ListView) findViewById(R.id.trackListView);

        listAdapter = new ArrayAdapter<String>(this,0,songList);


        getTracks();
        listView.setAdapter(listAdapter);

    }


    public void getTracks(){

        callTracks = darFMApi.getTracks("jazz","?",BuildConfig.DARfm_API_KEY);

        callTracks.enqueue(new Callback<List<Track>>() {


            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {


                Log.d(LOG_TAG, "Got response from API, URL called is : "+call.request().url());
                Track curTrack;
                allTracks = response.body();
                trackItems = (ArrayList<Track>) allTracks;
                for (int i = 0; i < trackItems.size(); i++) {
                   curTrack = trackItems.get(i);
                    songList.add(curTrack.getmTitle());
                    Log.d(LOG_TAG, "Song: "+curTrack.getmTitle());
                }
                listAdapter.notify();
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.d(LOG_TAG, "API call falied , URL called is : "+call.request().url());


                Log.d(LOG_TAG , "Response is :\n" +call.request().body());
            }



        });
    }

    //For android marshmallow and above
    public  boolean isInternetPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(LOG_TAG,"Permission is granted in mm");
                return true;
            } else {

                Log.v(LOG_TAG,"Permission is revoked");
                ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.INTERNET}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(LOG_TAG,"Permission is granted");
            return true;
        }


    }

    private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(Track.class, new CustomJsonDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}
