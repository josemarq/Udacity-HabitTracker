package josemarq.habittracker;

import android.provider.BaseColumns;

public class HabitsContract {

    public HabitsContract() {}

    public static final class HabitsEntry implements BaseColumns {

        //Define Table name
        public static final String HABITS_TABLE_NAME = "habitos";

        //Fields of the Tables Habits (id, name, counter, observation)
        public static final String _ID = "rowid";
        public static final String habitName = "nombre";
        public static final String habitCounter = "contador";
        public static final String habitObservations = "observaciones";

    }
}
