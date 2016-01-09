package com.alissa.newdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alissa.newdatabase.adapters.CustomListAdapter;
import com.alissa.newdatabase.crud.Db;
import com.alissa.newdatabase.model.Participant;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView m_list;
    private Db db;
    private List participants = new LinkedList<Participant>();
    private CustomListAdapter adapter;
    private FloatingActionButton fab;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dialog, menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        db = new Db(this);

        m_list = (ListView) findViewById(R.id.list_participants);
        participants = db.getAllParticipants();
        adapter = new CustomListAdapter(this, participants);
        m_list.setAdapter(adapter);
        m_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                switch (position) {
//                    case (0):
//                        db.delateParticipant(new Participant());//this does not work
//                        break;
//
//                }
//                // Define 'where' part of query.
//                String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
//// Specify arguments in placeholder order.
//                String[] selectionArgs = { String.valueOf(rowId) };
//// Issue SQL statement.
//                db.delete(table_name, selection, selectionArgs);
                Object obj = m_list.getId();
                String selection = obj.toString()+" LIKE ?";
                String [] selectionArgs={String.valueOf(obj)};
                db.delateParticipant(new Participant());
            }
        });

        m_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                return false;
            }
        });
        registerForContextMenu(m_list);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fab);
        fab.startAnimation(animation);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddParticipants.class);
                startActivity(intent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                finish();
                return false;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        participants = db.getAllParticipants();
        adapter = new CustomListAdapter(this, participants);
        m_list.setAdapter(adapter);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fab);
        fab.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //nt activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}