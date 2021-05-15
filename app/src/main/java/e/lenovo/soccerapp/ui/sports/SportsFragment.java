package e.lenovo.soccerapp.ui.sports;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Sports;

public class SportsFragment extends Fragment implements SportsAdapter.OnSportListener {

    int dPos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);

        @NotNull
        @NonNull
        List<Sports> sports = AppDatabase.getInstance(getContext()).sportsDao().getAllSports();
        RecyclerView sportList = view.findViewById(R.id.sports_view);
        SportsAdapter adapter = new SportsAdapter(getContext(), sports, this);
        sportList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
        sportList.setAdapter(adapter);
        sportList.addItemDecoration(new DividerItemDecoration(sportList.getContext(), DividerItemDecoration.VERTICAL));


        Button add = view.findViewById(R.id.btn_add_sport);
        Button upd = view.findViewById(R.id.btn_update_sport);
        Button del = view.findViewById(R.id.btn_delete_sport);
        EditText searchId = view.findViewById(R.id.sport_search);
        ImageButton searchBtn = view.findViewById(R.id.btn_search_sport);

        searchId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Sports> searchedById = AppDatabase.getInstance(getContext()).sportsDao().getAllSports();
                SportsAdapter searchedA = new SportsAdapter(getContext(), searchedById, SportsFragment.this::onSportClick);
                sportList.setAdapter(searchedA);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchBtn.setOnClickListener(v -> {
            List<Sports> searchedById = AppDatabase.getInstance(getContext()).sportsDao().searchById(Integer.parseInt(searchId.getText().toString()));
            SportsAdapter searchedA = new SportsAdapter(getContext(), searchedById, this);
            sportList.setAdapter(searchedA);
        });

        add.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_nav_sports_to_addUpgSportFragment));

        upd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("mode", "Upd");
            bundle.putInt("dPos", dPos);
            addUpgSportFragment adsFrag = new addUpgSportFragment();
            adsFrag.setArguments(bundle);
            Navigation.findNavController(v).navigate(R.id.action_nav_sports_to_addUpgSportFragment, bundle);
        });

        del.setOnClickListener(v -> {
            try {
                AppDatabase.getInstance(getContext()).sportsDao().deleteSport(sports.get(dPos).getSportId());
                Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            } catch (IndexOutOfBoundsException e) {
                Toast.makeText(getContext(), "No Item Selected", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
            List<Sports> re = AppDatabase.getInstance(getContext()).sportsDao().getAllSports();
            SportsAdapter reA = new SportsAdapter(getContext(), re, this);
            sportList.setAdapter(reA);
            dPos = -1;
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSportClick(int pos) {
        dPos = pos;
    }
}