package josemarq.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        HabitsDBHelper habitsDBHelper = new HabitsDBHelper(this);

        // Create a new record
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitsContract.HabitsEntry.habitName, "Yoga");
        contentValues.put(HabitsContract.HabitsEntry.habitCounter, 1);
        contentValues.put(HabitsContract.HabitsEntry.habitObservations, "Text Sample");
        habitsDBHelper.insertRecord(contentValues);

        // Update the record
        contentValues.put(HabitsContract.HabitsEntry.habitCounter, 100);
        habitsDBHelper.updateRecord(1, contentValues);

        // Get the record
        Cursor habitRecord = habitsDBHelper.getRow(1);
        Log.i("getRecord ", habitRecord.toString());

        // Delete the record
        habitsDBHelper.deleteRecord(1);

        // Delete the database
        habitsDBHelper.dropDB(this);


    }

    //BUTTONS FOR CREATE EXAMPLES RECORDS AND DROP TABLE
    public void onClickBtnDrop(View v)
    {
        Toast.makeText(this, "Drop DB", Toast.LENGTH_LONG).show();
        HabitsDBHelper habitsDBHelper = new HabitsDBHelper(this);
        habitsDBHelper.dropDB(this);
    }
    public void onClickBtnInsert(View v)
    {
        Toast.makeText(this, "Insert new record DB", Toast.LENGTH_LONG).show();
        HabitsDBHelper habitsDBHelper = new HabitsDBHelper(this);

        // Create a new record with button
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitsContract.HabitsEntry.habitName, "Spinning");
        contentValues.put(HabitsContract.HabitsEntry.habitCounter, 1);
        contentValues.put(HabitsContract.HabitsEntry.habitObservations, "Sample with Button");
        habitsDBHelper.insertRecord(contentValues);
    }
}
