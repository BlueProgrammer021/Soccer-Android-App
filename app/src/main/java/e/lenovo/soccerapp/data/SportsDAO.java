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
    public List<Sports> searchById(int searchId);
    @Insert
    public void insertSport(Sports sport);
    @Update
    public void updateSport(Sports sport);
    @Query("DELETE FROM sports WHERE sid = :id")
    public void deleteSport(int id);
}
