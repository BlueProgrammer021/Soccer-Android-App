package e.lenovo.soccerapp.ui.sports;

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


public class addUpgSportFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_upg_sport, container, false);
        Bundle bundle = this.getArguments();
        @NotNull
        String mode = "";
        int pos = 0;
        if (bundle != null) {
            mode = bundle.getString("mode");
            pos = bundle.getInt("dPos");
        }
        Button add = view.findViewById(R.id.btn_add);
        Button can = view.findViewById(R.id.btn_cnl);
        Spinner sportGender = view.findViewById(R.id.sport_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.genders, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sportGender.setAdapter(adapter);

        EditText sportId = view.findViewById(R.id.sport_id);
        EditText sportName = view.findViewById(R.id.sport_name);
        EditText sportCategory = view.findViewById(R.id.sport_category);

        if (mode.equals("Upd")) {
            List<Sports> sports = AppDatabase.getInstance(getContext()).sportsDao().getAllSports();
            Sports sU = sports.get(pos);
            sportId.setText(String.valueOf(sU.getSportId()));
            sportName.setText(sU.getSportName());
            sportCategory.setText(sU.getSportCategory());
            String name = sU.getGender();
            if (adapter.getItem(0).equals(name))
                sportGender.setSelection(0);
            sportGender.setSelection(1);
        }

        String finalMode = mode;
        add.setOnClickListener(v -> {
            if (sportId.getText() == null || sportName.getText() == null || sportCategory.getText() == null) {
                Toast.makeText(getActivity(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                try {
                    int id = Integer.parseInt(sportId.getText().toString());
                    String name = sportName.getText().toString();
                    String category = sportCategory.getText().toString();
                    String gender = sportGender.getSelectedItem().toString();
                    Sports spo = new Sports();
                    spo.setSportId(id);
                    spo.setSportName(name);
                    spo.setSportCategory(category);
                    spo.setGender(gender);

                    if (AppDatabase.getInstance(getContext()).sportsDao().getAllSidSports().contains(id) &&
                    finalMode.equals("Upd")) {
                        AppDatabase.getInstance(getContext()).sportsDao().updateSport(spo);
                    } else {
                        AppDatabase.getInstance(getContext()).sportsDao().insertSport(spo);
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "PK - Already Added Id", Toast.LENGTH_LONG).show();
                } finally {
                    sportId.clearFocus();
                    sportId.setText("");
                    sportName.clearFocus();
                    sportName.setText("");
                    sportCategory.clearFocus();
                    sportCategory.setText("");
                    sportGender.clearFocus();
                    sportGender.setSelection(0);
                    Navigation.findNavController(view).navigate(R.id.action_addUpgSportFragment_to_nav_sports);
                }
            }

        });

        can.setOnClickListener(v -> {
            sportId.clearFocus();
            sportId.setText("");
            sportName.clearFocus();
            sportName.setText("");
            sportCategory.clearFocus();
            sportCategory.setText("");
            sportGender.clearFocus();
            sportGender.setSelection(0);
            Navigation.findNavController(view).navigate(R.id.action_addUpgSportFragment_to_nav_sports);
        });
        return view;
    }
}