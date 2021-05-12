package e.lenovo.soccerapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "sports",
        indices = {@Index(value = {"sid"}, unique = true)})
public class Sports
{
    @PrimaryKey
    @ColumnInfo(name = "sid")
    private int sportId;
    @ColumnInfo(name = "sport_name")
    private String sportName;
    @ColumnInfo(name = "sport_category")
    private String sportCategory;
    @ColumnInfo(name = "sport_gender")
    private String gender;

    public Sports(int sportId, String sportName, String sportCategory, String gender) {
        this.sportId = sportId;
        this.sportName = sportName;
        this.sportCategory = sportCategory;
        this.gender = gender;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportCategory() {
        return sportCategory;
    }

    public void setSportCategory(String sportCategory) {
        this.sportCategory = sportCategory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
