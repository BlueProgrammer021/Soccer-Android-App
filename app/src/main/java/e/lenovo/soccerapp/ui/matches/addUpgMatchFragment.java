package e.lenovo.soccerapp.ui.matches;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;

public class addUpgMatchFragment extends Fragment {

    private final int countAth = 2;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_upg_match, container, false);
        EditText matchDate = view.findViewById(R.id.match_date);
        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
        };

        matchDate.setOnClickListener(v -> {
            new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            matchDate.setText(sdf.format(myCalendar.getTime()));
        });

        RadioGroup matchRadio = view.findViewById(R.id.match_radio_group);
        matchRadio.check(R.id.match_radio_single);
        EditText matchTown = view.findViewById(R.id.match_town);
        EditText matchCountry = view.findViewById(R.id.match_country);
        Spinner sp1 = view.findViewById(R.id.match_athlete1);
        Spinner sp2 = view.findViewById(R.id.match_athlete2);
        List<String> teams = AppDatabase.getInstance(getContext()).teamsDao().getAllTName();
        List<Integer> athletes = AppDatabase.getInstance(getContext()).athletesDao().getAllAid();
        ArrayAdapter ad1 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, teams);
        ArrayAdapter ad2 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, athletes);
        sp1.setAdapter(ad1);
        sp2.setAdapter(ad1);
        Button addAthlete = view.findViewById(R.id.match_add_athlete);
        Button add = view.findViewById(R.id.btn_add);
        Button cnl = view.findViewById(R.id.btn_cnl);
        matchRadio.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.match_radio_teams) {
                addAthlete.setVisibility(View.GONE);
                sp1.setAdapter(ad1);
                sp2.setAdapter(ad1);
            } else {
                addAthlete.setVisibility(View.VISIBLE);
                sp1.setAdapter(ad2);
                sp2.setAdapter(ad2);
                // R.id.match_radio_single
            }
        });

        addAthlete.setOnClickListener(v -> {
            LinearLayout linearl = v.findViewById(R.id.match_linear);
            EditText edt = new EditText(getContext());
            edt.setHint("Score");
            linearl.addView(edt);
        });

        add.setOnClickListener(v -> {
            Map<String, Object> match = new HashMap<>();
            //Firebase add to collection
        });

        cnl.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_addUpgMatchFragment_to_nav_matches));
        return view;
    }
}