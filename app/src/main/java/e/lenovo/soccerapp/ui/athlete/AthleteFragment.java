package e.lenovo.soccerapp.ui.athlete;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Athletes;
import e.lenovo.soccerapp.data.Sports;
import e.lenovo.soccerapp.ui.sports.SportsAdapter;
import e.lenovo.soccerapp.ui.sports.SportsFragment;
import e.lenovo.soccerapp.ui.sports.addUpgSportFragment;

public class AthleteFragment extends Fragment implements AthleteAdapter.OnAthleteListener{

    private AthleteViewModel athleteViewModel;
    View view;
    int dPos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        view = inflater.inflate(R.layout.fragment_athlete, container, false);
        RecyclerView athleteView = view.findViewById(R.id.athletes_view);
        List<Athletes> athletes = AppDatabase.getInstance(getContext()).athletesDao().getAllAthletes();
        AthleteAdapter adapter = new AthleteAdapter(getContext(), athletes, this);
        athleteView.setLayoutManager(new LinearLayoutManager(getContext()));
        athleteView.setAdapter(adapter);
        athleteView.addItemDecoration(new DividerItemDecoration(athleteView.getContext(), DividerItemDecoration.VERTICAL));

        Button addAthlete = view.findViewById(R.id.btn_add_athlete);
        Button updAthlete = view.findViewById(R.id.btn_update_athlete);
        Button delAthlete = view.findViewById(R.id.btn_delete_athlete);

        EditText searchId = view.findViewById(R.id.athlete_search);
        ImageButton searchBtn = view.findViewById(R.id.btn_search_athlete);

        searchId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Athletes> searchedById = AppDatabase.getInstance(getContext()).athletesDao().getAllAthletes();
                AthleteAdapter searchedA = new AthleteAdapter(getContext(), searchedById, AthleteFragment.this::onAthleteClick);
                athleteView.setAdapter(searchedA);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchBtn.setOnClickListener(v -> {
            List<Athletes> searchedById = AppDatabase.getInstance(getContext()).athletesDao().searchById(Integer.parseInt(searchId.getText().toString()));
            AthleteAdapter searchedA = new AthleteAdapter(getContext(), searchedById, this);
            athleteView.setAdapter(searchedA);
        });

        addAthlete.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_nav_athletes_to_addUpgAthleteFragment));

        delAthlete.setOnClickListener(v -> {
            AppDatabase.getInstance(getContext()).sportsDao().deleteSport(athletes.get(dPos).getAthleteId());
            Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            List<Athletes> re = AppDatabase.getInstance(getContext()).athletesDao().getAllAthletes();
            AthleteAdapter reA = new AthleteAdapter(getContext(), re, this);
            athleteView.setAdapter(reA);
        });

        updAthlete.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("mode", "Upd");
            bundle.putInt("dPos", dPos);
            addUpgAthleteFragment adsFrag = new addUpgAthleteFragment();
            adsFrag.setArguments(bundle);
            Navigation.findNavController(view).navigate(R.id.action_nav_athletes_to_addUpgAthleteFragment, bundle);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onAthleteClick(int pos) {
        dPos = pos;
    }
}