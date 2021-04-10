package e.lenovo.soccerapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamsDAO
{
    @Query("SELECT * FROM teams WHERE tid = :searchId")
    public List<Teams> searchById(int searchId);
    @Insert
    public void insertTeam(Teams team);
    @Update
    public void updateTeam(Teams team);
    @Query("DELETE FROM teams WHERE tid = :id")
    public void deleteTeam(int id);
}
