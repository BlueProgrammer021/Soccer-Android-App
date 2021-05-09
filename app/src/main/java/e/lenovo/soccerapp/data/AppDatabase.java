package e.lenovo.soccerapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sports.class, Athletes.class, Teams.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract SportsDAO sportsDao();

    public abstract AthletesDAO athletesDao();

    public abstract TeamsDAO teamsDao();

}
