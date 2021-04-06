package e.lenovo.soccerapp.ui.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.ui.sports.SportsViewModel;

public class TeamFragment extends Fragment {

    private TeamViewModel teamViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        // TODO: Use the ViewModel
    }
}