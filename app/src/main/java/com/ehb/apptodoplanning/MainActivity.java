package com.ehb.apptodoplanning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity{

    private NavController mNavControler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavControler = Navigation.findNavController(this, R.id.nav_host);
        AndroidThreeTen.init( this ); // was voor de database

        //floating button over alle pagina
        FloatingActionButton fab = findViewById( R.id.bt_addToList );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = mNavControler.getCurrentDestination().getLabel().toString();
                Toast popupToast = Toast.makeText( getApplicationContext(),label,Toast.LENGTH_LONG );
                popupToast.show();

                if ("fragment_home".equals( label )) {
                    mNavControler.navigate( R.id.action_homeFragment_to_addItemFragment );
                } else if ("fragment_list".equals( label )) {
                    mNavControler.navigate( R.id.action_listFragment_to_addItemFragment );
                } else if ("fragment_calendar".equals( label )) {
                    mNavControler.navigate( R.id.action_calendarFragment_to_addItemFragment );
                }
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);//resource omzetten naar scherm

        return super.onCreateOptionsMenu(menu);
    }

}
