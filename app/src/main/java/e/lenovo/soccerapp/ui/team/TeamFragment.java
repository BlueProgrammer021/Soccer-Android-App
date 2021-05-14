package e.lenovo.soccerapp.ui.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Athletes;
import e.lenovo.soccerapp.data.Teams;
import e.lenovo.soccerapp.ui.athlete.AthleteAdapter;
import e.lenovo.soccerapp.ui.athlete.addUpgAthleteFragment;

public class TeamFragment extends Fragment implements TeamAdapter.OnTeamListener {

    private TeamViewModel teamViewModel;

    int dPos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        RecyclerView trc = view.findViewById(R.id.teams_view);
        Button add = view.findViewById(R.id.btn_add_team);
        Button upd = view.findViewById(R.id.btn_update_team);
        Button del = view.findViewById(R.id.btn_delete_team);
        List<Teams> teamsList = AppDatabase.getInstance(getContext()).teamsDao().getAllTeams();
        TeamAdapter ada = new TeamAdapter(getContext(), teamsList, this);
        trc.setAdapter(ada);
        trc.addItemDecoration(new DividerItemDecoration(trc.getContext(), DividerItemDecoration.VERTICAL));

        add.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_nav_teams_to_addUpgTeamFragment));

        upd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("mode", "Upd");
            bundle.putInt("dPos", dPos);
            addUpgTeamFragment adsFrag = new addUpgTeamFragment();
            adsFrag.setArguments(bundle);
            Navigation.findNavController(v).navigate(R.id.action_nav_teams_to_addUpgTeamFragment, bundle);
        });

        del.setOnClickListener(v -> {
            AppDatabase.getInstance(getContext()).teamsDao().deleteTeam(teamsList.get(dPos).getTeamId());
            Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            ada.notifyDataSetChanged();
            List<Teams> re = AppDatabase.getInstance(getContext()).teamsDao().getAllTeams();
            TeamAdapter reA = new TeamAdapter(getContext(), re, this);
            trc.setAdapter(reA);
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onTeamClick(int pos) {
        dPos = pos;
    }
}