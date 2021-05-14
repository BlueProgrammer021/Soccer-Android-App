package e.lenovo.soccerapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AthletesDAO
{
    @Query("SELECT * FROM athletes")
    List<Athletes> getAllAthletes();
    @Query("SELECT * FROM athletes WHERE aid = :searchId")
    List<Athletes> searchById(int searchId);
    @Query("SELECT aid FROM athletes")
    List<Integer> getAllAid();
    @Insert
    void insertAthlete(Athletes athlete);
    @Update
    void updateAthlete(Athletes athlete);
    @Query("DELETE FROM athletes WHERE aid = :id")
    void deleteAthlete(int id);
}
