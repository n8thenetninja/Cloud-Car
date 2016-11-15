package com.example.nate.cloudcar;

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

public class MainActivity extends AppCompatActivity {

    SeekBar throttle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        throttle = (SeekBar) findViewById(R.id.throttle);

        //String messageStr = "Hello Android!";

        final int server_port = 5005;
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
}
