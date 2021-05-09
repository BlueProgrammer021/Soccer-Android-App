package e.lenovo.soccerapp.ui.athlete;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Athletes;
import e.lenovo.soccerapp.data.AthletesDAO;

public class addUpgAthleteFragment extends Fragment {

        View view;
        Button add;
        Button can;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_upg_athlete, container, false);
        add = (Button) view.findViewById(R.id.btn_add);
        can = (Button) view.findViewById(R.id.btn_cnl);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = view.findViewById(R.id.athlete_id);
                EditText fname = view.findViewById(R.id.athlete_fname);
                EditText lname = view.findViewById(R.id.athlete_lname);
                EditText country = view.findViewById(R.id.athlete_country);
                EditText town = view.findViewById(R.id.athlete_town);
                Spinner sportId = view.findViewById(R.id.sport_id);
                Athletes ath = new Athletes();
                ath.setAthleteId(Integer.parseInt(id.getText().toString()));
                ath.setAthleteFirstName(String.valueOf(fname.getText().toString()));
                ath.setAthleteLastName(String.valueOf(lname.getText().toString()));
                ath.setAthleteCountry(String.valueOf(country.getText().toString()));
                ath.setAthleteTown(String.valueOf(town.getText().toString()));
                ath.setSportId(Integer.parseInt(sportId.getSelectedItem().toString()));

                saveAthlete(ath);
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addUpgAthleteFragment_to_nav_athletes);
            }
        });
        return view;
    }

    public void saveAthlete(Athletes ath) {
        AppDatabase db = AppDatabase.getDbInstance(this.getContext());

        db.athletesDao().insertAthlete(ath);
    }
}