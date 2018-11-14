package com.example.edu.newaudiorecord;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRecord,btnStop,btnPlay;
    MediaRecorder myRecorder;
    String outputfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = (Button)findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(this);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);
        outputfile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setOutputFile(outputfile);

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRecord:
                try { myRecorder.prepare();}
                catch (IOException e) { e.printStackTrace();}
                myRecorder.start();
                btnRecord.setEnabled(false);
                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);
                break;
            case R.id.btnStop:
                myRecorder.stop();
                btnStop.setEnabled(false);
                btnRecord.setEnabled(true);
                btnPlay.setEnabled(true);
                myRecorder.release();
                myRecorder = null;
                break;
            case R.id.btnPlay:
                MediaPlayer mplayer = new MediaPlayer();
                try {mplayer.setDataSource(outputfile);}
                catch (IOException e) { e.printStackTrace();}
                mplayer.start();
                break;
        }

    }
}
