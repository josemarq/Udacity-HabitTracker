package josemarq.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
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

        // Update the record Id 1
        contentValues.put(HabitsContract.HabitsEntry.habitCounter, 100);
        habitsDBHelper.updateRecord(1, contentValues);

        // Get the record Id 1
        Cursor habitRecord = habitsDBHelper.getRow(1);;
        Log.i("getRecord ", habitRecord.toString());

        // Delete the record
        habitsDBHelper.deleteRecord(1);

        // Delete the database
        habitsDBHelper.dropDB(this);

    }

}
