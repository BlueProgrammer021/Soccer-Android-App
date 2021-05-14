package e.lenovo.soccerapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sports.class, Athletes.class, Teams.class}, version = 11, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public static AppDatabase INSTANCE;
    private static final String DB_NAME = "MyDb";

    public abstract SportsDAO sportsDao();

    public abstract AthletesDAO athletesDao();

    public abstract TeamsDAO teamsDao();

    // Room DB Builder
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    /*private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SportsDAO sportsDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            sportsDao = db.sportsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sportsDao.insertSport(new Sports(1, "Random1", "Random1", "Male"));
            sportsDao.insertSport(new Sports(2, "Random2", "Random2", "Male"));
            sportsDao.insertSport(new Sports(3, "Random3", "Random3", "Female"));
            return null;
        }
    }*/

}
