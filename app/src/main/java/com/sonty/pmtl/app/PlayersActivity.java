package com.sonty.pmtl.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class PlayersActivity extends Activity {

    private Intent detailIntent;
    private mySqliteHelper db_helper;
    private SQLiteDatabase database;

    private AdapterView.OnItemClickListener playerClickedHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            ListView lv = (ListView)parent;
            Cursor c = (Cursor)lv.getItemAtPosition(position);
            detailIntent.putExtra("player", c.getString(0) );
            startActivity(detailIntent);
        }
    };
    private AdapterView.OnItemLongClickListener playerOnItemLongClickHandler = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView parent, View v, int position, long id) {
            ListView lv = (ListView)parent;
            Cursor c = (Cursor)lv.getItemAtPosition(position);
            detailIntent.putExtra("player", c.getString(0) );
            startActivity(detailIntent);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        db_helper = new mySqliteHelper(this);
        if ( db_helper != null ) {
            database = db_helper.getReadableDatabase();
        }

        Intent intent = getIntent();
        String[] value = new String[]{intent.getStringExtra("country")};

        detailIntent = new Intent ( this, PlayerDetailActivity.class );

        if ( database != null ) {
            Cursor cursor = database.rawQuery("select pm_id as _id, name, role, age, market_date from players where country=? order by market_date desc",value);

            String[] fromColumns = {"_id", "name", "role", "age", "market_date"};
            int[] toViews = {R.id.pm_id, R.id.name, R.id.role, R.id.age, R.id.market_date};

            ListView listView = (ListView) findViewById(R.id.playerTable);
            if (cursor.moveToFirst()) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.player_table, cursor, fromColumns, toViews, 0);
                listView.setAdapter(adapter);
            }

            listView.setOnItemClickListener(playerClickedHandler);
            listView.setOnItemLongClickListener(playerOnItemLongClickHandler);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players, menu);
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
