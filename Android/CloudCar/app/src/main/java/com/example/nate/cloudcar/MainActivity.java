package com.example.nate.cloudcar;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class MainActivity extends Activity implements SensorEventListener {

    private SeekBar throttle;
    private SensorManager mSensorManager;
    private Sensor tilt;
    final int server_port = 5005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        throttle = (SeekBar) findViewById(R.id.throttle);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tilt = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //String messageStr = "Hello Android!";


        try {
            DatagramSocket s = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            InetAddress local = InetAddress.getByName("192.168.1.119");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //int msg_length = messageStr.length();
        //byte[] message = messageStr.getBytes();
        //DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
        //s.send(p);

        throttle.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //int redValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        /*
                        String chan1 = "chan0";
                        int msgLength = chan1.length();
                        progress += 8;
                        try {
                            DatagramSocket s = new DatagramSocket();
                            InetAddress local = InetAddress.getByName("192.168.1.119");
                            byte[] message = chan1.getBytes();
                            DatagramPacket p1 = new DatagramPacket(message,msgLength,local,server_port);
                            s.send(p1);
                            String pos = Integer.toString(progress);
                            msgLength = pos.length();
                            message = pos.getBytes();
                            DatagramPacket p2 = new DatagramPacket(message,msgLength,local,server_port);
                            s.send(p2);
                        }
                        catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (SocketException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        */
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

    @Override
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

    public void steer(int yTilt) {
        int pos = 17;
        if (yTilt == 0) {
            pos = 17;
        }
        else if (yTilt == 1) {
            pos = 18;
        }
        else if (yTilt == 2) {
            pos = 19;
        }
        else if (yTilt == 3) {
            pos = 20;
        }
        else if (yTilt == 4) {
            pos = 21;
        }
        else if (yTilt == 5) {
            pos = 22;
        }
        else if (yTilt == 6) {
            pos = 23;
        }
        else if (yTilt == 7) {
            pos = 24;
        }
        else if (yTilt == 8) {
            pos = 25;
        }
        else if (yTilt == 9) {
            pos = 26;
        }
        else if (yTilt == -1) {
            pos = 16;
        }
        else if (yTilt == -2) {
            pos = 15;
        }
        else if (yTilt == -3) {
            pos = 14;
        }
        else if (yTilt == -4) {
            pos = 13;
        }
        else if (yTilt == -5) {
            pos = 12;
        }
        else if (yTilt == -6) {
            pos = 11;
        }
        else if (yTilt == -7) {
            pos = 10;
        }
        else if (yTilt == -8) {
            pos = 9;
        }
        else if (yTilt == -9) {
            pos = 8;
        }
        socketSend("chan1",pos);
    }

    public void socketSend(final String chan, final int pos) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int msgLength = chan.length();
                    DatagramSocket s = new DatagramSocket();
                    InetAddress local = InetAddress.getByName("192.168.1.119");
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

    }
}
