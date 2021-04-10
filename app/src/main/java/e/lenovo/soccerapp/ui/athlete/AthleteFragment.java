package e.lenovo.soccerapp.ui.athlete;

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

public class AthleteFragment extends Fragment {

    private AthleteViewModel athleteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        return inflater.inflate(R.layout.fragment_athlete, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        // TODO: Use the ViewModel
    }
}