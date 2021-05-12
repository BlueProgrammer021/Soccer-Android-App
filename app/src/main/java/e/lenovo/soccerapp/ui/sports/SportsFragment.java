package e.lenovo.soccerapp.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Sports;

public class SportsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);

        LiveData<List<Sports>> sports = AppDatabase.getInstance(getContext()).sportsDao().getAllSports();
        RecyclerView sportList = view.findViewById(R.id.sports_view);
        SportsAdapter adapter = new SportsAdapter(sports);
        adapter.setContext(getContext());
        sportList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
        sportList.setAdapter(adapter);

        Button add = view.findViewById(R.id.btn_add_sport);
        add.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_sports_to_addUpgSportFragment);
            Toast.makeText(getActivity(), "Add Sport", Toast.LENGTH_LONG).show();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SportsViewModel sportsViewModel = new ViewModelProvider(this).get(SportsViewModel.class);
        sportsViewModel.getAllSports().observe(getViewLifecycleOwner(), sports -> {

        });
    }

}