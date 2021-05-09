package e.lenovo.soccerapp.ui.team;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import e.lenovo.soccerapp.R;


public class addUpgTeamFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_upg_team, container, false);
        Button add = (Button) view.findViewById(R.id.btn_add);
        Button can = (Button) view.findViewById(R.id.btn_cnl);

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addUpgTeamFragment_to_nav_teams);
            }
        });
        return view;
    }
}