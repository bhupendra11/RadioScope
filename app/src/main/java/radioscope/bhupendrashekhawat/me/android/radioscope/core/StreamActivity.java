package radioscope.bhupendrashekhawat.me.android.radioscope.core;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import radioscope.bhupendrashekhawat.me.android.radioscope.R;

public class StreamActivity extends AppCompatActivity {

    private static  final String LOG_TAG = StreamActivity.class.getSimpleName();
    private ExoPlayer exoPlayer;
    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int BUFFER_SEGMENT_COUNT = 256;
    final String SONG_TITLE = "songTitle";
    final String SONG_INDEX = "songIndex";

    private ImageButton btnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //String BASE_URL= "http://feeds.cato.org/~r/CatoDailyPodcast/~5/eSxR_UthAqM/Unraveled-Obamacare-Religious-Liberty-and-Executive-Power.mp3";
       String BASE_URL = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p";



        Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();

        Log.d(LOG_TAG, "BUILT URI FOR STREAMING: " + builtUri);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);


        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        // Measures bandwidth during playback. Can be null if not required.
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
       TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);

        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();

        // 3. Create the player
        final SimpleExoPlayer player =
                ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        // Produces DataSource instances through which media data is loaded.
        /*DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"), (TransferListener<? super DataSource>) bandwidthMeter);*/
        DataSource.Factory dataSourceFactory =  new DefaultDataSourceFactory(this,Util.getUserAgent(this, "RadioScope"));

        // Produces Extractor instances or parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // This is the MediaSource representing the media to be played.
        MediaSource audioSource = new ExtractorMediaSource(builtUri,
                dataSourceFactory, extractorsFactory, null, null);
        // Prepare the player with the source.
        player.prepare(audioSource);

        player.setPlayWhenReady(true);
        btnPlay.setImageResource(R.drawable.btn_pause);

       /* // Build the ExoPlayer and start playback
        ExoPlayer exoPlayer = ExoPlayerFactory.newInstance();
        exoPlayer.prepare(audioRenderer);
        exoPlayer.setPlayWhenReady(true);*/




/* Set on click listeners for the playback controls */

/**
 * Play button click event
 * */

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check for already playing
                if(player.getPlayWhenReady()){
                    if(player!=null){
                        player.setPlayWhenReady(false);
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                    }
                }else{
                    // Resume song
                    if(player!=null){
                        player.setPlayWhenReady(true);
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }
            }
        });
    }

}
