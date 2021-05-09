package e.lenovo.soccerapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sports.class, Athletes.class, Teams.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static final String DB_NAME = "MyDb";

    private static AppDatabase INSTANCE;

    public abstract SportsDAO sportsDao();

    public abstract AthletesDAO athletesDao();

    public abstract TeamsDAO teamsDao();

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
