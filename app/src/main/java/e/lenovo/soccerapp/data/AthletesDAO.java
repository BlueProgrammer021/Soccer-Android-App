package e.lenovo.soccerapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AthletesDAO
{
    @Query("SELECT * FROM athletes WHERE aid = :searchId")
    public List<Athletes> searchById(int searchId);
    @Insert
    public void insertAthlete(Athletes athlete);
    @Update
    public void updateAthlete(Athletes athlete);
    @Query("DELETE FROM athletes WHERE aid = :id")
    public void deleteAthlete(int id);
}
