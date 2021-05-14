package e.lenovo.soccerapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SportsDAO
{
    @Query("SELECT * FROM sports WHERE sid = :searchId")
    List<Sports> searchById(int searchId);
    @Query("SELECT * FROM sports ORDER BY sid DESC")
    List<Sports> getAllSports();
    @Query("SELECT sid FROM sports")
    List<Integer> getAllSidSports();
    @Query("SELECT sport_name FROM sports")
    List<String> getAllSnameSports();
    @Insert
    void insertSport(Sports sport);
    @Update
    void updateSport(Sports sport);
    @Query("DELETE FROM sports WHERE sid = :id")
    void deleteSport(int id);
}
