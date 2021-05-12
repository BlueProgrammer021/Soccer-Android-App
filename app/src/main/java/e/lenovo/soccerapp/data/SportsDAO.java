package e.lenovo.soccerapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SportsDAO
{
    @Query("SELECT * FROM sports WHERE sid = :searchId")
    public LiveData<List<Sports>> searchById(int searchId);
    @Query("SELECT * FROM sports ORDER BY sid DESC")
    public LiveData<List<Sports>> getAllSports();
    @Query("SELECT sid FROM sports")
    public List<Integer> getAllSidSports();
    @Query("SELECT sport_name FROM sports")
    public List<String> getAllSnameSports();
    @Insert
    public void insertSport(Sports sport);
    @Update
    public void updateSport(Sports sport);
    @Query("DELETE FROM sports WHERE sid = :id")
    public void deleteSport(int id);
}
