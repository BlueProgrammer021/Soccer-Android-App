package e.lenovo.soccerapp.ui.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.ui.sports.SportsViewModel;

public class TeamFragment extends Fragment {

    private TeamViewModel teamViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        Button add_athlete = (Button) view.findViewById(R.id.btn_add_team);
        Button upd_athlete = (Button) view.findViewById(R.id.btn_update_team);

        add_athlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_teams_to_addUpgTeamFragment);
                Toast.makeText(getActivity(), "Add Team", Toast.LENGTH_LONG).show();
            }
        });

        upd_athlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_teams_to_addUpgTeamFragment);
                Toast.makeText(getActivity(), "Update Team", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        // TODO: Use the ViewModel
    }
}