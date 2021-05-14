package e.lenovo.soccerapp.ui.team;

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
import e.lenovo.soccerapp.data.Sports;
import e.lenovo.soccerapp.data.Teams;


public class addUpgTeamFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_upg_team, container, false);
        Bundle bundle = this.getArguments();
        @NotNull
        String mode = "";
        int pos = 0;
        if (bundle != null) {
            mode = bundle.getString("mode");
            pos = bundle.getInt("dPos");
        }
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
        Button add = view.findViewById(R.id.btn_add);
        Button can = view.findViewById(R.id.btn_cnl);

        if (mode.equals("Upd")) {
            List<Teams> teams = AppDatabase.getInstance(getContext()).teamsDao().getAllTeams();
            Teams tU = teams.get(pos);
            tid.setText(String.valueOf(tU.getTeamId()));
            tname.setText(tU.getTeamName());
            tarena.setText(tU.getTeamArena());
            tcourt.setText(tU.getTeamCourt());
            tcountry.setText(tU.getTeamCountry());
            int s = tU.getSportId();
            for (int i=0; i<sportId.getCount(); i++) {
                if (sportId.getItemAtPosition(i).equals(s))
                    sportId.setSelection(i);
            }
            test.setText(tU.getTeamEst());
        }

        String finalMode = mode;
        add.setOnClickListener(v -> {
            if (tid.getText() == null || tname.getText() == null || tarena.getText() == null || tcourt.getText() == null
            || tcountry.getText() == null || test.getText() == null || sportId.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Teams te = new Teams();
                    te.setTeamId(Integer.parseInt(tid.getText().toString()));
                    te.setTeamName(tname.getText().toString());
                    te.setTeamArena(tarena.getText().toString());
                    te.setTeamCourt(tcourt.getText().toString());
                    te.setTeamCountry(tcountry.getText().toString());
                    te.setTeamEst(test.getText().toString());
                    if (AppDatabase.getInstance(getContext()).teamsDao().getAllTid().contains(te.getTeamId()) &&
                    finalMode.equals("Upd"))
                        AppDatabase.getInstance(getContext()).teamsDao().updateTeam(te);
                    else
                        AppDatabase.getInstance(getContext()).teamsDao().insertTeam(te);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "PK - Already Added Id", Toast.LENGTH_LONG).show();
                } finally {
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
                    Navigation.findNavController(view).navigate(R.id.action_addUpgTeamFragment_to_nav_teams);
                }
            }
        });

        can.setOnClickListener(v -> {
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
            Navigation.findNavController(view).navigate(R.id.action_addUpgTeamFragment_to_nav_teams);
        });
        return view;
    }
}