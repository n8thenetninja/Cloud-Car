/*
* Cloud Car User Control Interface App.
* This app is part of the Cloud Car project: https://github.com/n8thenetninja/Cloud-Car
*
* This app was originally submitted as part of an EET Senior Project, MSU Denver, Fall 2016
*
* License: GPL-3.0
* Author: Nathan Larson (AKA: N8theNetNinja)
* In pursuit of degree: BS Electrical Engineering Technology, Concentration: Computer Engineering
* */

package com.example.nate.cloudcar;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
//import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
//import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class MainActivity extends Activity implements SensorEventListener, GestureDetector.OnGestureListener {

    private SeekBar throttle; // Throttle slider
    private TextView gestureInfo; // Temporary TextView for showing camera position
    private GestureDetectorCompat DetectMe; // Gesture detector for camera position
    private SensorManager mSensorManager; // Sensor manager for accelerometer steering
    private Sensor tilt; // Accelerometer sensor for steering
    final int server_port = 5005; // UDP port for control signals
    private int camX = 17; // Camera position X
    private int camY = 17; // Camera position Y
    private int camSenCount = 0; // Sensor output divider counter to slow camera movement

    private String path;
    private VideoView mVideoView;
    private String IP = "192.168.1.101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        throttle = (SeekBar) findViewById(R.id.throttle); // Throttle slider
        gestureInfo = (TextView) findViewById(R.id.gestureInfo); // Temporary TextView for showing Camera Position
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // Steering sensor manager
        tilt = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Steering seneor
        DetectMe = new GestureDetectorCompat(this,this); // Camera movement gesture detector

        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        path = "rtsp://" + IP + ":8555/unicast";
        mVideoView.setVideoPath(path);

        mVideoView.setHardwareDecoder(true);
        mVideoView.setBufferSize(100);
        //mVideoView.getBufferPercentage();
        //mVideoView.setDrawingCacheEnabled(true);
        //mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        //throttle.requestFocus();


        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                             @Override
                                             public void onPrepared(MediaPlayer mediaPlayer) {
                                                 mediaPlayer.setPlaybackSpeed(1.0f);
                                                 //mediaPlayer.setBufferSize(50);


                                             }
        });

        throttle.setOnSeekBarChangeListener( // Seekbar listener for throttle slider
                new SeekBar.OnSeekBarChangeListener() {
                    //int redValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        socketSend("chan0", (progress+8));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override // Accelerometer sensor listener
    public void onSensorChanged(SensorEvent event) {
        float xTilt = event.values[0];
        float yTilt = event.values[1];
        float zTilt = event.values[2];
        steer((int)yTilt);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, tilt,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    // Steering control method (This could be improved upon... But it works for now)
    public void steer(int yTilt) {
        int pos = 17;
        if (yTilt == 0) {
            pos = 17;
        }
        else if (yTilt == -1) {
            pos = 18;
        }
        else if (yTilt == -2) {
            pos = 19;
        }
        else if (yTilt == -3) {
            pos = 20;
        }
        else if (yTilt == -4) {
            pos = 21;
        }
        else if (yTilt == -5) {
            pos = 22;
        }
        else if (yTilt == -6) {
            pos = 23;
        }
        else if (yTilt == -7) {
            pos = 24;
        }
        else if (yTilt == -8) {
            pos = 25;
        }
        else if (yTilt == -9) {
            pos = 26;
        }
        else if (yTilt == 1) {
            pos = 16;
        }
        else if (yTilt == 2) {
            pos = 15;
        }
        else if (yTilt == 3) {
            pos = 14;
        }
        else if (yTilt == 4) {
            pos = 13;
        }
        else if (yTilt == 5) {
            pos = 12;
        }
        else if (yTilt == 6) {
            pos = 11;
        }
        else if (yTilt == 7) {
            pos = 10;
        }
        else if (yTilt == 8) {
            pos = 9;
        }
        else if (yTilt == 9) {
            pos = 8;
        }
        socketSend("chan1",pos);
    }
    // UDP socket packet sender
    public void socketSend(final String chan, final int pos) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int msgLength = chan.length();
                    DatagramSocket s = new DatagramSocket();
                    InetAddress local = InetAddress.getByName(IP);
                    byte[] message = chan.getBytes();
                    DatagramPacket chanPacket = new DatagramPacket(message,msgLength,local,server_port);
                    s.send(chanPacket);
                    msgLength = Integer.toString(pos).length();
                    message = Integer.toString(pos).getBytes();
                    DatagramPacket posPacket = new DatagramPacket(message,msgLength,local,server_port);
                    s.send(posPacket);
                }
                catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.currentThread().interrupted();


    }

    @Override // Detect touch input for camera movement
    public boolean onTouchEvent(MotionEvent event){
        this.DetectMe.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override // Listener to move camera
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        distanceX /= 4;
        distanceY /= 4;
        camSenCount++;
        if (camSenCount > 1) {
            camSenCount = 0;
            if ((int)distanceX < 0 && camX < 26) {
                camX++;
                gestureInfo.setText("camX: " + camX + "\ncamY: " + camY);
                socketSend("chan2",camX);
            }
            else if ((int)distanceX > 0 && camX > 8) {
                camX--;
                gestureInfo.setText("camX: " + camX + "\ncamY: " + camY);
                socketSend("chan2",camX);
            }
            if ((int)distanceY > 0 && camY < 26) {
                camY++;
                gestureInfo.setText("camX: " + camX + "\ncamY: " + camY);
                socketSend("chan3",camY);
            }
            else if ((int)distanceY < 0 && camY > 8) {
                camY--;
                gestureInfo.setText("camX: " + camX + "\ncamY: " + camY);
                socketSend("chan3",camY);
            }
        }


        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
