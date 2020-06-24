package hkucs.example.videoplayersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;


public class VideoPlayerActivity extends AppCompatActivity{
    public static String VIDEO_URI = "hkucs.course.video";
    private PlayerView playerView;
    private SimpleExoPlayer player;
    String videoUri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerView = findViewById(R.id.player_view);

        Intent intent = getIntent();
        videoUri = intent.getStringExtra(VIDEO_URI);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Create a new Exo Player
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        // Associate the ExoPlayer with the Player View
        playerView.setPlayer(player);
        // Build a DataSource.Factory capable of
        // loading http and local content
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        // Specify the URI to play
        File file = new File(Environment.getExternalStorageDirectory(),
                "test2.mp4");


        Uri uri = Uri.parse(videoUri);

        ExtractorMediaSource mediaSource =
                new ExtractorMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
        // Start loading the media source
        player.prepare(mediaSource);
        // Start playback automatically when ready
        player.setPlayWhenReady(true);
    }
    @Override
    protected void onDestroy() {
        playerView.setPlayer(null);
        player.release();
        player = null;
        super.onDestroy();
    }

}
