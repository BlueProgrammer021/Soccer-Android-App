package e.lenovo.soccerapp.ui.athlete;

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

import java.util.List;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Athletes;

public class addUpgAthleteFragment extends Fragment {

    View view;
    Button add;
    Button can;
    //NotificationManagerCompat notman;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //notman = NotificationManagerCompat.from(getContext());
        /*Notification noti = new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_notifications)
                        .setContentTitle("RoomDB")
                        .setContentText("Athlete Added Successfully")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_upg_athlete, container, false);
        Spinner sportId = view.findViewById(R.id.sport_id);
        List<Integer> sid = AppDatabase.getInstance(getContext()).sportsDao().getAllSidSports();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, sid);
        sportId.setAdapter(adapter);

        add = view.findViewById(R.id.btn_add);
        can = view.findViewById(R.id.btn_cnl);
        EditText id = view.findViewById(R.id.athlete_id);
        EditText fname = view.findViewById(R.id.athlete_fname);
        EditText lname = view.findViewById(R.id.athlete_lname);
        EditText country = view.findViewById(R.id.athlete_country);
        EditText town = view.findViewById(R.id.athlete_town);
        add.setOnClickListener(v -> {
            if (id.getText() == null || fname.getText() == null || lname.getText() == null
            || country.getText() == null || town.getText() == null || sportId.getSelectedItem() == null) {
                Toast.makeText(getActivity(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Athletes ath = new Athletes();
                    ath.setAthleteId(Integer.parseInt(id.getText().toString()));
                    ath.setAthleteFirstName(fname.getText().toString());
                    ath.setAthleteLastName(lname.getText().toString());
                    ath.setAthleteCountry(country.getText().toString());
                    ath.setAthleteTown(town.getText().toString());
                    ath.setSportId(Integer.parseInt(sportId.getSelectedItem().toString()));
                    List<Athletes> allAthletes = AppDatabase.getInstance(getContext()).athletesDao().getAllAthletes();
                    if (allAthletes.contains(ath)) {
                        AppDatabase.getInstance(getContext()).athletesDao().updateAthlete(ath);
                    } else {
                        AppDatabase.getInstance(getContext()).athletesDao().insertAthlete(ath);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }

                //notman.notify(1, noti);

            }
        });

        can.setOnClickListener(v -> {
                Navigation.findNavController(view).navigate(R.id.action_addUpgAthleteFragment_to_nav_athletes);
                id.clearFocus();
                id.setText("");
                fname.clearFocus();
                fname.setText("");
                lname.clearFocus();
                lname.setText("");
                town.clearFocus();
                town.setText("");
                country.clearFocus();
                country.setText("");

        });
        return view;
    }

}