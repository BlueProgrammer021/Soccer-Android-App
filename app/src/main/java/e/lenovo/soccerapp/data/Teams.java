package e.lenovo.soccerapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
class Teams
{
    @PrimaryKey
    @ColumnInfo(name = "tid")
    private int teamId;
    @ColumnInfo(name = "team_name")
    private String teamName;
    @ColumnInfo(name = "team_court")
    private String teamCourt;
    @ColumnInfo(name = "team_town")
    private String teamTown;
    @ColumnInfo(name = "team_country")
    private String teamCountry;
    @ForeignKey(entity = Sports.class, parentColumns = "sid", childColumns = "sid", onDelete = 5, onUpdate = 5)
    @ColumnInfo(name = "sid")
    private int sportId;
    @ColumnInfo(name = "team_est")
    private String teamEst;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCourt() {
        return teamCourt;
    }

    public void setTeamCourt(String teamCourt) {
        this.teamCourt = teamCourt;
    }

    public String getTeamTown() {
        return teamTown;
    }

    public void setTeamTown(String teamTown) {
        this.teamTown = teamTown;
    }

    public String getTeamCountry() {
        return teamCountry;
    }

    public void setTeamCountry(String teamCountry) {
        this.teamCountry = teamCountry;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getTeamEst() {
        return teamEst;
    }

    public void setTeamEst(String teamEst) {
        this.teamEst = teamEst;
    }
}
