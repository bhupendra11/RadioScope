package radioscope.bhupendrashekhawat.me.android.radioscope.core;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import radioscope.bhupendrashekhawat.me.android.radioscope.BuildConfig;
import radioscope.bhupendrashekhawat.me.android.radioscope.R;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.RadioService;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTracks;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackListFragment extends Fragment {

    private static final String API_BASE_URL = "http://api.dar.fm/";
    private static final String LOG_TAG = TrackListFragment.class.getSimpleName();
    private RadioService.DARfmApi darFMApi;
    private Call<AllTracks> callTracks;
    private AllTracks allTracks;
    private ArrayList<Track> trackItems = new ArrayList<Track>();

    // private ArrayList<String> songList = new ArrayList<String>();
    private ListView listView;

    //ArrayAdapter<String> listAdapter ;
    TrackAdapter trackAdapter;
    final String SONG_TITLE = "songTitle";
    final String SONG_INDEX = "songIndex";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isInternetPermissionGranted = isInternetPermissionGranted();



        Log.d(LOG_TAG, "Internet access permission = " +isInternetPermissionGranted);

        /* Adding Logging for Retrofit */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
              //  .client(httpClient.build())
                .build();

        darFMApi = retrofit.create(RadioService.DARfmApi.class);


        //listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,songList);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) rootView.findViewById(R.id.trackListView);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(getActivity(), StreamActivity.class);

                startActivity(intent);*/


                int songIndex = position;
                Track track  = trackItems.get(songIndex);
                Toast.makeText(getActivity(), "Click on "+ track.getTitle()
                        , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity() , StreamActivity.class);
                intent.putExtra(SONG_INDEX, songIndex);
                startActivity(intent);

            }
        } );




        trackAdapter = new TrackAdapter(getActivity(),trackItems);
        listView.setAdapter(trackAdapter);
        Log.d(LOG_TAG,"TrackList size = "+trackItems.size());
        getTracks();

        //trackAdapter.notifyDataSetChanged();

        return rootView;
    }


    public void updateTracks(List<Track> trackList){
        Log.d(LOG_TAG,"TrackList size , in UpdateTracks = "+trackList.size());
       // trackItems.clear();
        Log.d(LOG_TAG,"TrackItems size , in UpdateTracks after clear = "+trackItems.size()+ "supplied TrackList= "+trackList.size());
        trackItems.addAll(trackList);
        Log.d(LOG_TAG,"TrackItems size , in UpdateTracks after add all items = "+trackItems.size()+ "supplied TrackList= "+trackList.size());
        trackAdapter = new TrackAdapter(getActivity(),trackItems);
        Log.d(LOG_TAG,"Items in trackAdappter = "+trackAdapter.getCount());
        listView.setAdapter(trackAdapter);
    }

    public void getTracks(){

        callTracks = darFMApi.getTracks("jazz","json", BuildConfig.DARfm_API_KEY);

        callTracks.enqueue(new Callback<AllTracks>() {

            @Override
            public void onResponse(Call<AllTracks> call, Response<AllTracks> response) {


                Log.d(LOG_TAG, "Got response from API, URL called is : "+call.request().url());
                Track curTrack;
                allTracks = response.body();
                trackItems = allTracks.getTrackList();
                for (int i = 0; i < trackItems.size(); i++) {
                    curTrack = trackItems.get(i);
                    //  songList.add(curTrack.getTitle());
                    Log.d(LOG_TAG, "Song: " + curTrack.getTitle()+" CallSign : "+curTrack.getCallSign()+" Artist: "+curTrack.getArtist());
                }

                Log.d(LOG_TAG,"TrackList size inside onResponse = "+trackItems.size());
                updateTracks(trackItems);
                //listAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<AllTracks> call, Throwable t) {
                Log.d(LOG_TAG, "API call falied , URL called is : "+call.request().url());
                Log.d(LOG_TAG , "Response is :\n" +call.request().body());
            }



        });
    }

    //For android marshmallow and above
    public  boolean isInternetPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(LOG_TAG,"Permission is granted in mm");
                return true;
            } else {

                Log.v(LOG_TAG,"Permission is revoked");
                ActivityCompat.requestPermissions( getActivity(),new String[]{Manifest.permission.INTERNET}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(LOG_TAG,"Permission is granted");
            return true;
        }


    }








}
