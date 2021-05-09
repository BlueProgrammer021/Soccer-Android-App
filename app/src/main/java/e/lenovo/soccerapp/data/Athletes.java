package e.lenovo.soccerapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "athletes",
        indices = {@Index(value = {"aid"}, unique = true), @Index(value = {"sid"}, unique = true)},
        foreignKeys = {@ForeignKey(entity = Sports.class, parentColumns = "sid", childColumns = "sid", onDelete = 5)})
public class Athletes
{
    @PrimaryKey
    @ColumnInfo(name = "aid")
    private int athleteId;
    @ColumnInfo(name = "athlete_first_name")
    private String athleteFirstName;
    @ColumnInfo(name = "athlete_last_name")
    private String athleteLastName;
    @ColumnInfo(name = "athlete_town")
    private String athleteTown;
    @ColumnInfo(name = "athlete_country")
    private String athleteCountry;
    @ColumnInfo(name = "sid")
    private int sportId;

    public int getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(int athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteFirstName() {
        return athleteFirstName;
    }

    public void setAthleteFirstName(String athleteFirstName) {
        this.athleteFirstName = athleteFirstName;
    }

    public String getAthleteLastName() {
        return athleteLastName;
    }

    public void setAthleteLastName(String athleteLastName) {
        this.athleteLastName = athleteLastName;
    }

    public String getAthleteTown() {
        return athleteTown;
    }

    public void setAthleteTown(String athleteTown) {
        this.athleteTown = athleteTown;
    }

    public String getAthleteCountry() {
        return athleteCountry;
    }

    public void setAthleteCountry(String athleteCountry) {
        this.athleteCountry = athleteCountry;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}
