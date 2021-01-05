package com.ehb.apptodoplanning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    private NavController mNavControler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavControler = Navigation.findNavController(this, R.id.nav_host);

    }

    //create obtion menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);//resource omzetten naar scherm

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_add://uitvoeren als men op de add knop heeft geklikt , R. = resource
                Toast.makeText(this,"Pressed add", Toast.LENGTH_LONG).show(); //Toast is een pop up scherm
                //ga naar de insert naviagatie pagina, voor een nieuwe item.
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToPageView(View v) {
        switch(v.getId()) {
            case R.id.im_icon_calendar:
                //Toast.makeText(this,"Pressed calendar", Toast.LENGTH_LONG).show();
                mNavControler.navigate(R.id.go_to_calendarFragment);
                break;
            case R.id.im_icon_listIcon:
               // Toast.makeText(this,"Pressed list view", Toast.LENGTH_LONG).show();
                mNavControler.navigate(R.id.go_to_listFragment);
                break;
        }
    }
}
