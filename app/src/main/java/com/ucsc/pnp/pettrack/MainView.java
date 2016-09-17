package com.ucsc.pnp.pettrack;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainView extends AppCompatActivity {
    GoogleMap googleMap;
    Marker petpointer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        showLocation("He he");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_view, menu);
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

    public void showLocation(String petname){

         LatLng newpsition = new LatLng(21 , 57);
        if(googleMap!=null) {
            petpointer = googleMap.addMarker(new MarkerOptions().position(newpsition).title(petname));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(newpsition.latitude, newpsition.longitude), 12.0f));
        }
    }
    public class LocationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
            showAlert("Pet NAme");
        }
    }

    public void showAlert(final String Petname){
        AlertDialog alertDialog = new AlertDialog.Builder(MainView.this).create();
        alertDialog.setTitle("Pet Alert");
        alertDialog.setMessage("Your pet is out of range :- PetName ::-- "+Petname);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showLocation(Petname);
                    }
                });
        alertDialog.show();
    }

}
