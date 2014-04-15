package com.sonty.pmtl.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class PlayerDetailActivity extends Activity {

    private mySqliteHelper db_helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        db_helper = new mySqliteHelper(this);
        if ( db_helper != null ) {
            database = db_helper.getReadableDatabase();
        }

        Intent intent = getIntent();
        String[] value = new String[]{intent.getStringExtra("player")};

        if ( database != null ) {
            Cursor cursor = database.rawQuery("select pm_id as _id,* from players where pm_id=?",value);

            String[] fromColumns = {"name", "age", "tac", "hea", "pas", "pos"};
            int[] toViews = {R.id.playerName, R.id.playerAge, R.id.playerTackling, R.id.playerHeading, R.id.playerPassing, R.id.playerPositioning};

            if (cursor.moveToFirst()) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.activity_player_detail, cursor, fromColumns, toViews, 0);
                GridLayout l = (GridLayout)findViewById(R.layout.activity_player_detail);
//                listView.setAdapter(adapter);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
