package com.example.a1430284.assignment2.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 1430284 on 2016-09-22.
 */
public class NoteTable extends Table<Note> {
    private SQLiteOpenHelper dbh;

    private static final SimpleDateFormat isoISO8601 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");

    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_CREATED = "created";

    /**
     * Create a ContactTable with the DB handler.
     * @param dbh
     */
    public NoteTable(SQLiteOpenHelper dbh) {
        super(dbh, "note");
        this.dbh = dbh;

        addColumn(new Column(COLUMN_TITLE, "TEXT").notNull());
        addColumn(new Column(COLUMN_BODY, "TEXT"));
        addColumn(new Column(COLUMN_REMINDER, "TEXT"));
        addColumn(new Column(COLUMN_CATEGORY, "INTEGER").notNull());
        addColumn(new Column(COLUMN_CREATED, "TEXT").notNull());
    }

    @Override
    public ContentValues toContentValues(Note element) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, element.getTitle());
        values.put(COLUMN_BODY, element.getBody());
        values.put(COLUMN_REMINDER, element.getReminder() != null ? isoISO8601.format(element.getReminder()) : null);
        values.put(COLUMN_CATEGORY, element.getCategory());
        values.put(COLUMN_CREATED, element.getCreated() != null ? isoISO8601.format(element.getCreated()) : null);
        return values;
    }

    @Override
    public Note fromCursor(Cursor cursor) throws DatabaseException {
        Note contact = new Note(cursor.getLong(0));

        // get name and phone number
        contact.setTitle(cursor.getString(1));
        contact.setBody(cursor.getString(2));
        contact.setHasReminder(cursor.getInt(3) > 0);
        contact.setCategory(cursor.getInt(4));

        // last called
        if(!cursor.isNull(5)) {
            try {
                contact.setCreated(isoISO8601.parse(cursor.getString(5)));
            }
            catch (ParseException e) {
                // package a ParseException as a generic DatabaseException
                throw new DatabaseException(e);
            }
        }

        return contact;
    }
    /**
     * Get the SQL CREATE TABLE statement for this table.
     * @return SQL CREATE TABLE statement.
     */
    /*public String getCreateTableStatement() {
        return "CREATE TABLE note (\n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "title TEXT NOT NULL, UNIQUE, \n" +
                "body TEXT, \n" +
                "reminder TEXT,\n" +
                "category INTEGER NOT NULL,\n" +
                "created TEXT NOT NULL\n" +
                ");";
    }*/

    /**
     * Get the SQL DROP TABLE statement for this table.
     * @return SQL DROP TABLE statement.
     */
    public String getDropTableStatement() {
        return "note";
    }

    @Override
    public Long create(Note element) throws DatabaseException {
//        SQLiteDatabase database = dbh.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, element.getTitle());
//        values.put(COLUMN_BODY, element.getBody());
//        values.put(COLUMN_REMINDER, element.getReminder() != null ? isoISO8601.format(element.getReminder()) : null);
//        values.put(COLUMN_CATEGORY, element.getCategory());
//        values.put(COLUMN_CREATED, element.getCreated()!= null ? isoISO8601.format(element.getReminder()) : null);
//
//        // Id of inserted element, -1 if error.
//        long insertId = -1;
//
//        // insert into DB
//        try {
//            insertId = database.insertOrThrow("note", null, values);
//        }
//        catch (SQLException e) {
//            throw new DatabaseException(e);
//        }
//        finally {
//            // close DB no matter what happens
//            database.close();
//        }
//
//        return insertId;

        long id = super.create(element);
        element.setId(id);
        return id;

    }

    @Override
    public String getId(Note element) {
        return String.valueOf(element.getId());
    }

    /**
     * Check that the table has initial data.
     * @return
     */
    public boolean hasInitialData() {
        return true;
    }

    /**
     * Populate table with initial data.
     * Precondition: table has been created.
     * Note: this method is called during the handler's onCreate method where a writable database is opne
     *       trying to get a second writable database will throw an error, hence the parameter.
     * @param database
     */
    public void initialize(SQLiteDatabase database) {
        for(Note notes : NoteData.getData())
            database.insertOrThrow(getName(), null, toContentValues(notes));

    }
}
