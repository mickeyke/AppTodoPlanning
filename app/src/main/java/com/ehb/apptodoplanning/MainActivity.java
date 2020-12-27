package com.ehb.apptodoplanning;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}