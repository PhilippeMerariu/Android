package com.example.a1430284.assignment2;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 1430284 on 2016-09-22.
 */
public class NoteTable implements CRUDRepository<Long, Note>{
    private SQLiteOpenHelper dbh;

    private static final SimpleDateFormat isoISO8601 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");

    private static final String COLUMN_TITLE = "firstName";
    private static final String COLUMN_BODY = "lastName";
    private static final String COLUMN_REMINDER = "lastCalled";
    private static final String COLUMN_CATEGORY = "phoneNumber";
    private static final String COLUMN_CREATED = "callCount";

    /**
     * Create a ContactTable with the DB handler.
     * @param dbh
     */
    public NoteTable(SQLiteOpenHelper dbh) {
        this.dbh = dbh;
    }

    /**
     * Get the SQL CREATE TABLE statement for this table.
     * @return SQL CREATE TABLE statement.
     */
    public String getCreateTableStatement() {
        return "CREATE TABLE note (\n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "title TEXT NOT NULL, UNIQUE, \n" +
                "body TEXT, \n" +
                "reminder TEXT,\n" +
                "category INTEGER NOT NULL,\n" +
                "created TEXT NOT NULL\n" +
                ");";
    }

    /**
     * Get the SQL DROP TABLE statement for this table.
     * @return SQL DROP TABLE statement.
     */
    public String getDropTableStatement() {
        return "DROP TABLE IF EXISTS contact;";
    }

    @Override
    public Long create(Note element) throws DatabaseException {
        SQLiteDatabase database = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, element.getTitle());
        values.put(COLUMN_BODY, element.getBody());
        values.put(COLUMN_REMINDER, element.getReminder() != null ? isoISO8601.format(element.getReminder()) : null);
        values.put(COLUMN_CATEGORY, element.getCategory());
        values.put(COLUMN_CREATED, element.getCreated()!= null ? isoISO8601.format(element.getReminder()) : null);

        // Id of inserted element, -1 if error.
        long insertId = -1;

        // insert into DB
        try {
            insertId = database.insertOrThrow("note", null, values);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            // close DB no matter what happens
            database.close();
        }

        return insertId;

    }

    @Override
    public Note read(Long key) throws DatabaseException {
        return null;
    }

    @Override
    public List<Note> readAll() throws DatabaseException {
        return null;
    }

    @Override
    public boolean update(Note element) throws DatabaseException {
        return false;
    }

    @Override
    public boolean delete(Note element) throws DatabaseException {
        return false;
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

    }
}
