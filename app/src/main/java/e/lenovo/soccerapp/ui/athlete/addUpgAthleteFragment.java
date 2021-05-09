package e.lenovo.soccerapp.ui.athlete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.Athletes;

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
        add.setOnClickListener(v -> {
            EditText id = view.findViewById(R.id.athlete_id);
            EditText fname = view.findViewById(R.id.athlete_fname);
            EditText lname = view.findViewById(R.id.athlete_lname);
            EditText country = view.findViewById(R.id.athlete_country);
            EditText town = view.findViewById(R.id.athlete_town);
            Spinner sportId = view.findViewById(R.id.sport_id);
            if (id.getText() == null || fname.getText() == null || lname.getText() == null
            || country.getText() == null || town.getText() == null || sportId.getSelectedItem() == null) {
                Toast.makeText(getActivity(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                Athletes ath = new Athletes();
                try {
                    ath.setAthleteId(Integer.parseInt(id.getText().toString()));
                    ath.setAthleteFirstName(fname.getText().toString());
                    ath.setAthleteLastName(lname.getText().toString());
                    ath.setAthleteCountry(country.getText().toString());
                    ath.setAthleteTown(town.getText().toString());
                    ath.setSportId(Integer.parseInt(sportId.getSelectedItem().toString()));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }

                HomeActivity.INSTANCE.athletesDao().insertAthlete(ath);

                //saveAthlete(ath);
            }
        });

        can.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_addUpgAthleteFragment_to_nav_athletes));
        return view;
    }

}