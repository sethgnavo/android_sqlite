package com.alissa.newdatabase.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alissa.newdatabase.databaseHelper.DBHelper;
import com.alissa.newdatabase.model.Participant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth-Pharès Gnavo on September 27, 2015.
 */
public class Db extends DBHelper {
    private static final String TABLE_PARTICIPANT = "participant";
    private static final String PARTICIPANT_ID = "id";
    private static final String PARTICIPANT_NOM = "nom";
    private static final String PARTICIPANT_PRENOM = "prenom";
    private static final String[] COLUMNS = {PARTICIPANT_ID, PARTICIPANT_NOM, PARTICIPANT_PRENOM};

    public Db(Context context) {
        super(context);
    }

    public void addParticipant(Participant p) {

        //1- get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        //Preparation des valeurs a inserer
        values.put(PARTICIPANT_NOM, p.getNom());
        values.put(PARTICIPANT_PRENOM, p.getPrenom());

        // 3. insertion dans la base de donnees
        db.insert(TABLE_PARTICIPANT, null, values);// key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Participant getParticipant(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor = db.query(TABLE_PARTICIPANT, COLUMNS, PARTICIPANT_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        // 3. if we got results get the first one if (cursor != null)
        cursor.moveToFirst();

        // 4. build Participant object
        Participant p = new Participant();
        p.setId(Integer.parseInt(cursor.getString(0)));
        p.setNom(cursor.getString(1));
        p.setPrenom(cursor.getString(2));

        // 5. return Participant
        return p;
    }

    public List<Participant> getAllParticipants() {
        List<Participant> participants = new LinkedList<Participant>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_PARTICIPANT;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build participant and add it to list Participant participant = null;
        if (cursor.moveToFirst()) {
            do {
                Participant participant = new Participant();
                participant.setId(Integer.parseInt(cursor.getString(0)));
                participant.setNom(cursor.getString(1));
                participant.setPrenom(cursor.getString(2));

                // Add participant to participants
                participants.add(participant);
            } while (cursor.moveToNext());
        }
        // return participants
        return participants;
    }

    public int updateParticipant(Participant participant) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        // get nom
        values.put(PARTICIPANT_NOM, participant.getNom());

        // get prenom
        values.put(PARTICIPANT_PRENOM, participant.getPrenom());

        // 3. updating row
        int i = db.update(TABLE_PARTICIPANT, values, PARTICIPANT_ID + " = ?", new String[]{String.valueOf(participant.getId())});

        // 4. close
        db.close();

        return i;
    }

    public void delateParticipant(Participant p) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_PARTICIPANT, PARTICIPANT_ID + " = ?", new String[]{String.valueOf(p.getId())});

        // 3. close
        db.close();
    }


}