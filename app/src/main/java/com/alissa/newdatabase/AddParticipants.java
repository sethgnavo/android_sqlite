package com.alissa.newdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alissa.newdatabase.crud.Db;
import com.alissa.newdatabase.model.Participant;

public class AddParticipants extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button btnSave;
    private Button btnCancel;
    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        nom = (EditText) findViewById(R.id.edit_nom);
        prenom = (EditText) findViewById(R.id.edit_prenom);
        btnSave = (Button) findViewById(R.id.btn_enregistrer);
        btnCancel = (Button) findViewById(R.id.btn_annuler);
        db = new Db(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_nom;
                String s_prenom;

                s_nom = nom.getText().toString();
                s_prenom = prenom.getText().toString();

                if ((!s_nom.isEmpty()) && (!s_prenom.isEmpty())) {

                    db.addParticipant(new Participant(s_nom, s_prenom));
                    finish();
                } else {
                    Toast.makeText(AddParticipants.this, getString(R.string.erreur), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //db.delete(TABLE_PARTICIPANT, PARTICIPANT_ID + " = ?", new String[]{String.valueOf(p.getId())}); to delete database;

    }
}
