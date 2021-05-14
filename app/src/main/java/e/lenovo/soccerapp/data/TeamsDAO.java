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
    List<Teams> searchById(int searchId);
    @Query("SELECT team_name FROM teams")
    List<String> getAllTName();
    @Query("SELECT tid FROM teams")
    List<Integer> getAllTid();
    @Query("SELECT * FROM teams")
    List<Teams> getAllTeams();
    @Query("SELECT team_country FROM teams")
    List<String> getTcountry();
    @Insert
    void insertTeam(Teams team);
    @Update
    void updateTeam(Teams team);
    @Query("DELETE FROM teams WHERE tid = :id")
    void deleteTeam(int id);
}
