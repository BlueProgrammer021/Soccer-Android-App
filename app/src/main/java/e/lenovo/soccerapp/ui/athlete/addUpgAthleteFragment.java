package e.lenovo.soccerapp.ui.athlete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
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
        Bundle bundle = this.getArguments();
        @NotNull
        String mode = "";
        int pos = 0;
        if (bundle != null) {
            mode = bundle.getString("mode");
            pos = bundle.getInt("dPos");
        }
        Spinner sportId = view.findViewById(R.id.a_sport_id);
        List<Integer> sid = AppDatabase.getInstance(getContext()).sportsDao().getAllSidSports();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, sid);
        sportId.setAdapter(adapter);

        add = view.findViewById(R.id.btn_add);
        can = view.findViewById(R.id.btn_cnl);
        EditText id = view.findViewById(R.id.athlete_id);
        EditText fname = view.findViewById(R.id.athlete_fname);
        EditText lname = view.findViewById(R.id.athlete_lname);
        EditText country = view.findViewById(R.id.athlete_country);
        EditText town = view.findViewById(R.id.athlete_town);

        if (mode.equals("Upd")) {
            List<Athletes> athletes = AppDatabase.getInstance(getContext()).athletesDao().getAllAthletes();
            Athletes aU = athletes.get(pos);
            id.setText(String.valueOf(aU.getAthleteId()));
            fname.setText(aU.getAthleteFirstName());
            lname.setText(aU.getAthleteLastName());
            town.setText(aU.getAthleteTown());
            country.setText(aU.getAthleteCountry());
            int s = aU.getSportId();
            for (int i=0; i<sportId.getCount(); i++) {
                if (sportId.getItemAtPosition(i).equals(s))
                    sportId.setSelection(i);
            }
        }
        String finalMode = mode;
        add.setOnClickListener(v -> {
            if (id.getText() == null || fname.getText() == null || lname.getText() == null
            || country.getText() == null || town.getText() == null || sportId.getSelectedItem() == null) {
                Toast.makeText(getActivity(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Athletes ath = new Athletes();
                    ath.setAthleteId(Integer.parseInt(id.getText().toString()));
                    ath.setAthleteFirstName(fname.getText().toString());
                    ath.setAthleteLastName(lname.getText().toString());
                    ath.setAthleteCountry(country.getText().toString());
                    ath.setAthleteTown(town.getText().toString());
                    ath.setSportId(Integer.parseInt(sportId.getSelectedItem().toString()));
                    if (AppDatabase.getInstance(getContext()).athletesDao().getAllAid().contains(ath.getAthleteId()) &&
                            finalMode.equals("Upd")) {
                        AppDatabase.getInstance(getContext()).athletesDao().updateAthlete(ath);
                    } else {
                        AppDatabase.getInstance(getContext()).athletesDao().insertAthlete(ath);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "PK - Already Added Id", Toast.LENGTH_LONG).show();
                } finally {
                    id.clearFocus();
                    id.setText("");
                    fname.clearFocus();
                    fname.setText("");
                    lname.clearFocus();
                    lname.setText("");
                    town.clearFocus();
                    town.setText("");
                    country.clearFocus();
                    country.setText("");
                    Navigation.findNavController(view).navigate(R.id.action_addUpgAthleteFragment_to_nav_athletes);
                }
            }
        });

        can.setOnClickListener(v -> {id.clearFocus();
            id.setText("");
            fname.clearFocus();
            fname.setText("");
            lname.clearFocus();
            lname.setText("");
            town.clearFocus();
            town.setText("");
            country.clearFocus();
            country.setText("");
            Navigation.findNavController(view).navigate(R.id.action_addUpgAthleteFragment_to_nav_athletes);
        });
        return view;
    }

}