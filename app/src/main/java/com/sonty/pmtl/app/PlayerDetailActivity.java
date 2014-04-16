package com.sonty.pmtl.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;


public class PlayerDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        mySqliteHelper db_helper = new mySqliteHelper(this);
        SQLiteDatabase database = db_helper.getReadableDatabase();

        Intent intent = getIntent();
        String[] value = new String[]{intent.getStringExtra("player")};

        if ( database != null ) {
            Cursor cursor = database.rawQuery("select pm_id as _id,* from players where pm_id=?", value);

            String[] fromColumns = {"name", "age", "tac", "hea", "pas", "pos", "han", "out", "ref", "agi", "fin", "tec", "spe", "str"};
            int[] toViews = {R.id.playerName, R.id.playerAge, R.id.playerTackling,
                    R.id.playerHeading, R.id.playerPassing, R.id.playerPositioning,
                    R.id.playerHandling, R.id.playerCrosses, R.id.playerReflexes,
                    R.id.playerAgility, R.id.playerFinishing, R.id.playerTechnique,
                    R.id.playerSpeed, R.id.playerStrength
            };

            if ((cursor != null) && (cursor.moveToFirst())) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.player_detail, cursor, fromColumns, toViews, 0);
                GridView gv = (GridView)findViewById(R.id.playerDetailTable);
                gv.setAdapter(adapter);
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

}
