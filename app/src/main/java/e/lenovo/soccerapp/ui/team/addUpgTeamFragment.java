package e.lenovo.soccerapp.ui.team;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;


public class addUpgTeamFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_upg_team, container, false);
        Spinner sportId = view.findViewById(R.id.sport_id);
        List<Integer> sid = AppDatabase.getInstance(getContext()).sportsDao().getAllSidSports();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, sid);
        sportId.setAdapter(adapter);
        EditText tid = view.findViewById(R.id.team_id);
        EditText tname = view.findViewById(R.id.team_name);
        EditText tarena = view.findViewById(R.id.team_arena);
        EditText tcourt = view.findViewById(R.id.team_court);
        EditText tcountry = view.findViewById(R.id.team_country);
        EditText test = view.findViewById(R.id.team_est);
        Button add = (Button) view.findViewById(R.id.btn_add);
        Button can = (Button) view.findViewById(R.id.btn_cnl);

        can.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_addUpgTeamFragment_to_nav_teams);
            tid.clearFocus();
            tid.setText("");
            tname.clearFocus();
            tname.setText("");
            tarena.clearFocus();
            tarena.setText("");
            tcourt.clearFocus();
            tcourt.setText("");
            tcountry.clearFocus();
            tcountry.setText("");
            test.clearFocus();
            test.setText("");
            sportId.clearFocus();
            sportId.setSelection(0);
        });
        return view;
    }
}