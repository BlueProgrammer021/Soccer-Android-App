package e.lenovo.soccerapp.ui.sports;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
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
        Button add = view.findViewById(R.id.btn_add);
        Button can = view.findViewById(R.id.btn_cnl);
        Spinner sportGender = view.findViewById(R.id.sport_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.genders, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sportGender.setAdapter(adapter);

        add.setOnClickListener(v -> {
            EditText sportId = view.findViewById(R.id.sport_id);
            EditText sportName = view.findViewById(R.id.sport_name);
            EditText sportCategory = view.findViewById(R.id.sport_category);
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

                HomeActivity.INSTANCE.sportsDao().insertSport(spo);
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

        });

        can.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_addUpgSportFragment_to_nav_sports));
        return view;
    }
}