package com.h.abassira.videostreaminglecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.h.abassira.videostreaminglecture.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String viedoLink="https://firebasestorage.googleapis.com/v0/b/mcc-course-d99c9.appspot.com/o/pexels-barbara-olsen-7880881.mp4?alt=media&token=f5c5ded6-64d1-4da5-8afd-2c5b728f8ce0";
// "https://firebasestorage.googleapis.com/v0/b/mcc-course-d99c9.appspot.com/o/production%20ID_3818213.mp4?alt=media&token=145b7f97-a8bf-4122-858f-65e7c7a7353b"
    ActivityMainBinding binding;
    PlayerView pv;
    SimpleExoPlayer player;

    boolean playwhenReady= true;
    long currentPosition = 0;
    int currentWindow= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pv = binding.pLayerView;

    }
    private void initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);

        MediaItem item =  MediaItem.fromUri(viedoLink);
        player.setMediaItem(item);
        player.setPlayWhenReady(playwhenReady);
        player.seekTo(currentWindow,currentPosition);
        player.prepare();

    }

    private void releasePlayer(){
        if (player != null){
            playwhenReady = player.getPlayWhenReady();
           currentWindow = player.getCurrentWindowIndex();
           currentPosition = player.getCurrentPosition();
           player = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null){
            initPlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

}