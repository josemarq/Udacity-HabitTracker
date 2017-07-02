package josemarq.habittracker;

/**
 * Created by josemarquez on 1/7/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

class HabitsDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "habitos.db";
    private SQLiteDatabase db;

    public HabitsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("HabitsDBHelper", "onCreate is Running");


        final String SQL_CREATE_HABITS_TABLE =
                "CREATE TABLE IF NOT EXISTS " +
                        HabitsContract.HabitsEntry.HABITS_TABLE_NAME + " (" +
                        HabitsContract.HabitsEntry.habitName + " TEXT NOT NULL, " +
                        HabitsContract.HabitsEntry.habitCounter + " INTEGER NOT NULL, " +
                        HabitsContract.HabitsEntry.habitObservations + " TEXT NULL " +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitsContract.HabitsEntry.HABITS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * insertRecord into DB
     *
     * @param contentValues the values with the data to insert new the record into the DB
     */
    public void insertRecord(ContentValues contentValues) {
        db = getWritableDatabase();
        try {
            db.insert(HabitsContract.HabitsEntry.HABITS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * updateRecord into DB
     *
     * @param recordId      the id of the record to be updated
     * @param contentValues the date with the new balues
     */
    public void updateRecord(int recordId, ContentValues contentValues) {
        db = getWritableDatabase();
        try {
            Log.i("Update Records: ", "Row = " + recordId);
            db.update(
                    HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                    contentValues,
                    HabitsContract.HabitsEntry._ID + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * deleteRecord from DB
     *
     * @param recordId the id to be deleted
     */
    public void deleteRecord(int recordId) {
        db = getWritableDatabase();
        try {
            Log.i("Delete Records: ", "Row = " + recordId);
            db.delete(
                    HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                    HabitsContract.HabitsEntry._ID + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * getRow from DB with specific ID
     *
     * @param recordId the id of the record to get
     * @return a Cursor object with the record values
     */
    public Cursor getRow(int recordId) {
        Cursor record;
        String table = HabitsContract.HabitsEntry.HABITS_TABLE_NAME;
        String selection = HabitsContract.HabitsEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(recordId)};
        db = getReadableDatabase();
        try {
            record = db.query(true, table, null, selection, selectionArgs, null, null, null, null);
            record.moveToFirst();
            record.close();
            db.close();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }
    }

    /**
     * dropBD
     * Delete DB
     *
     * @param context activity context
     */
    public void dropDB(Context context) {
        try {
            Log.i("Drop Table: ", "Successfully");
            context.deleteDatabase(DATABASE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}