package e.lenovo.soccerapp.data;

import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

public class Matches {

    /*@SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");*/

    String match_id;

    @PropertyName("match_date")
    String match_date;
    @PropertyName("match_town")
    String match_town;
    @PropertyName("match_country")
    String match_country;
    @PropertyName("match_sport")
    int match_sport;
    @PropertyName("sport_type")
    String sport_type;
    @PropertyName("match_part")
    List<String> match_part = new ArrayList<>();
    @PropertyName("match_part_score")
    List<Integer> match_part_score = new ArrayList<>();

    public Matches() {
    }

    public Matches(String match_id, String match_date, String match_town, String match_country, int match_sport, boolean sport_type, List<String> match_part, List<Integer> match_part_score) {
        /*try {
            this.matchDate = dateFor.parse(matchDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        this.match_id = match_id;
        this.match_date = match_date;
        this.match_town = match_town;
        this.match_country = match_country;
        this.match_sport = match_sport;
        if (sport_type) // On true is single
            this.sport_type = "Single";
        else
            this.sport_type = "Teams";
        this.match_part = match_part;
        this.match_part_score = match_part_score;
    }

    @PropertyName("match_id")
    public String getMatch_id() { return match_id; }

    @PropertyName("match_id")
    public void setMatch_id(String match_id) { this.match_id = match_id; }

    @PropertyName("match_date")
    public String getMatchDate() {
        return match_date;
    }

    @PropertyName("match_date")
    public void setMatchDate(String match_date) {
        this.match_date = match_date;
    }

    @PropertyName("match_town")
    public String getMatchTown() {
        return match_town;
    }

    @PropertyName("match_town")
    public void setMatchTown(String match_town) {
        this.match_town = match_town;
    }

    @PropertyName("match_country")
    public String getMatchCountry() {
        return match_country;
    }

    @PropertyName("match_country")
    public void setMatchCountry(String match_country) {
        this.match_country = match_country;
    }

    @PropertyName("match_sport")
    public int getMatchSportId() {
        return match_sport;
    }

    @PropertyName("match_sport")
    public void setMatchSportId(int match_sport) {
        this.match_sport = match_sport;
    }

    @PropertyName("sport_type")
    public String getMatchTypeSport() { return sport_type; }

    @PropertyName("sport_type")
    public void setMatchTypeSport(String sport_type) { this.sport_type = sport_type; }

    @PropertyName("match_part")
    public List<String> getParticipate() {
        return match_part;
    }

    @PropertyName("match_part")
    public void setParticipate(List<String> match_part) {
        this.match_part = match_part;
    }

    @PropertyName("match_part_score")
    public List<Integer> getMatch_part_score() {
        return match_part_score;
    }

    @PropertyName("match_part_score")
    public void setMatch_part_score(List<Integer> participateScore1) { this.match_part_score = participateScore1; }

}
