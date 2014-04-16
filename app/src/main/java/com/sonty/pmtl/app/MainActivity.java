package com.sonty.pmtl.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends Activity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySqliteHelper db_helper = new mySqliteHelper(this);
        SQLiteDatabase database = db_helper.getReadableDatabase();
        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery("select * from countries order by name", null);
        }
        TableLayout table_layout = (TableLayout) findViewById(R.id.countryLayout);
        intent = new Intent(this, PlayersActivity.class);

        if ((cursor != null) && (cursor.moveToFirst())) {
            do {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                Button apasa = new Button(this);
                apasa.setText(cursor.getString(1));
                apasa.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button b = (Button)v;
                        intent.putExtra("country", b.getText() );
                        startActivity(intent);
                    }
                });

                row.addView(apasa);

                table_layout.addView(row);
            } while (cursor.moveToNext());
        }

        db_helper.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
