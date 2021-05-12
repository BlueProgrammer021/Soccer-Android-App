package e.lenovo.soccerapp.ui.athlete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.ui.sports.SportsViewModel;

public class AthleteFragment extends Fragment {

    private AthleteViewModel athleteViewModel;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        view = inflater.inflate(R.layout.fragment_athlete, container, false);
        RecyclerView athleteView = view.findViewById(R.id.athletes_view);
        Button addAthlete = view.findViewById(R.id.btn_add_athlete);
        Button updAthlete = view.findViewById(R.id.btn_update_athlete);

        addAthlete.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_athletes_to_addUpgAthleteFragment);

        });
        updAthlete.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_athletes_to_addUpgAthleteFragment);
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        // TODO: Use the ViewModel
    }
}