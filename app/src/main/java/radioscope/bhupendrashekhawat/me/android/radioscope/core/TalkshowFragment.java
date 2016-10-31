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
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTalkshows;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Talkshow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TalkshowFragment  extends Fragment {

    private static final String API_BASE_URL = "http://api.dar.fm/";
    private static final String LOG_TAG = TalkshowFragment.class.getSimpleName();
    private RadioService.DARfmApi darFMApi;
    private Call<AllTalkshows> callTalkshows;
    private AllTalkshows allTalkshows;
    private ArrayList<Talkshow> talkshowItems = new ArrayList<Talkshow>();

    // private ArrayList<String> songList = new ArrayList<String>();
    private ListView listView;

    //ArrayAdapter<String> listAdapter ;
    TalkshowAdapter talkshowAdapter;
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
                .client(httpClient.build())
                .build();

        darFMApi = retrofit.create(RadioService.DARfmApi.class);


        //listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,songList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_talkshow, container, false);

        listView = (ListView) rootView.findViewById(R.id.talkshowListView);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(getActivity(), StreamActivity.class);

                startActivity(intent);*/


                int songIndex = position;
                Talkshow talkshow = talkshowItems.get(songIndex);
                Toast.makeText(getActivity(), "Click on "+ talkshow.getmShowname()
                        , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity() , StreamActivity.class);
                intent.putExtra(SONG_INDEX, songIndex);
                startActivity(intent);

            }
        } );




        talkshowAdapter = new TalkshowAdapter(getActivity(),talkshowItems);
        listView.setAdapter(talkshowAdapter);
        Log.d(LOG_TAG,"TalkshowList size = "+talkshowItems.size());
        getTalkshows();

        //trackAdapter.notifyDataSetChanged();

        return rootView;
    }


    public void updateTalkshows(List<Talkshow> talkshowList){
        Log.d(LOG_TAG,"TalkshowList size , in UpdateTalkshows = "+talkshowList.size());

        Log.d(LOG_TAG,"TalkshowItems size , in UpdateTalkshows after clear = "+talkshowItems.size()+ "supplied TalkshowList= "+talkshowList.size());
        talkshowItems.addAll(talkshowList);
        Log.d(LOG_TAG,"TalkshowItems size , in UpdateTalkshows after add all items = "+talkshowItems.size()+ "supplied TalkshowList= "+talkshowList.size());
        talkshowAdapter = new TalkshowAdapter(getActivity(),talkshowItems);
        Log.d(LOG_TAG,"Items in talkshowAdappter = "+talkshowAdapter.getCount());
        listView.setAdapter(talkshowAdapter);
    }

    public void getTalkshows(){

        callTalkshows = darFMApi.getTalkshows("jazz","json", BuildConfig.DARfm_API_KEY);

        callTalkshows.enqueue(new Callback<AllTalkshows>() {

            @Override
            public void onResponse(Call<AllTalkshows> call, Response<AllTalkshows> response) {


                Log.d(LOG_TAG, "Got response from API, URL called is : "+call.request().url());
                Talkshow curTalkshow;
                allTalkshows = response.body();
                talkshowItems = allTalkshows.getTalkshowList();
                for (int i = 0; i < talkshowItems.size(); i++) {
                    curTalkshow = talkshowItems.get(i);
                    //  songList.add(curTalkshow.getTitle());
                    Log.d(LOG_TAG, "Showname: " + curTalkshow.getmShowname()+" Category : "+curTalkshow.getmShowGenre()+" Showid: "+curTalkshow.getmShowid() );
                }

                Log.d(LOG_TAG,"TalkshowList size inside onResponse = "+talkshowItems.size());
                updateTalkshows(talkshowItems);
                //listAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<AllTalkshows> call, Throwable t) {
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
