package e.lenovo.soccerapp.ui.matches;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.AppDatabase;
import e.lenovo.soccerapp.data.Matches;
import e.lenovo.soccerapp.data.Sports;

public class addUpgMatchFragment extends Fragment {

    private int countAth = 2;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_upg_match, container, false);

        Bundle bundle = this.getArguments();

        EditText matchDate = view.findViewById(R.id.match_date);

        matchDate.setOnClickListener(v -> {

            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            DatePickerDialog picker = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            matchDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
        });

        TextView sportCat = view.findViewById(R.id.match_sport_cat);

        List<String> match_participants = new ArrayList<>();
        List<Integer> match_participants_score = new ArrayList<>();

        EditText matchTown = view.findViewById(R.id.match_town);
        EditText matchCountry = view.findViewById(R.id.match_country);
        Spinner matchSportId = view.findViewById(R.id.match_sport);
        List<Integer> sportIds = AppDatabase.getInstance(getContext()).sportsDao().getAllSidSports();
        ArrayAdapter spid = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, sportIds);
        matchSportId.setAdapter(spid);
        EditText matchScore1 = view.findViewById(R.id.match_athlete1_score);
        EditText matchScore2 = view.findViewById(R.id.match_athlete2_score);

        Spinner sp1 = view.findViewById(R.id.match_athlete1);
        Spinner sp2 = view.findViewById(R.id.match_athlete2);
        List<String> teams = AppDatabase.getInstance(getContext()).teamsDao().getAllTName();
        List<Integer> athletes = AppDatabase.getInstance(getContext()).athletesDao().getAllAid();
        ArrayAdapter ad1 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, teams);
        ArrayAdapter ad2 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, athletes);
        sp1.setAdapter(ad2);
        sp2.setAdapter(ad2);

        Button addAthlete = view.findViewById(R.id.match_add_athlete);
        Button remAthlete = view.findViewById(R.id.match_remove_athlete);
        Button add = view.findViewById(R.id.btn_add);
        Button cnl = view.findViewById(R.id.btn_cnl);

        remAthlete.setVisibility(View.GONE);

        matchSportId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<Sports> sports = AppDatabase.getInstance(addUpgMatchFragment.this.getContext()).sportsDao().searchById(Integer.parseInt(String.valueOf(matchSportId.getSelectedItem())));
                if (sports.get(0).getSportCategory().equals("Single")) {
                    sportCat.setText("Single");
                    addAthlete.setVisibility(View.VISIBLE);
                    sp1.setAdapter(ad2);
                    sp2.setAdapter(ad2);
                    countAth = 2;
                }
                else {
                    sportCat.setText("Teams");
                    addAthlete.setVisibility(View.GONE);
                    remAthlete.setVisibility(View.GONE);
                    LinearLayout lout = (LinearLayout) view.findViewById(R.id.match_dynamic_linear);
                    lout.removeAllViewsInLayout();
                    countAth = 2;
                    sp1.setAdapter(ad1);
                    sp2.setAdapter(ad1);
                    countAth = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

/*        if (sportCat.getText().toString().equals("Teams")) {

        } else {
            addAthlete.setVisibility(View.VISIBLE);
            sp1.setAdapter(ad2);
            sp2.setAdapter(ad2);
            // R.id.match_radio_single
        }*/

        addAthlete.setOnClickListener(v -> {
            countAth++;
            LinearLayout lout = (LinearLayout) view.findViewById(R.id.match_dynamic_linear);
            Spinner spn = new Spinner(getContext());
            EditText edt = new EditText(getContext());
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            spn.setLayoutParams(parms);
            spn.setTag("100"+countAth);
            spn.setAdapter(ad2);
            lout.addView(spn);
            edt.setLayoutParams(parms);
            edt.setHint("Score");
            edt.setTag("200"+countAth);
            lout.addView(edt);
            if (countAth > 2)
                remAthlete.setVisibility(View.VISIBLE);
            else
                remAthlete.setVisibility(View.GONE);
        });

        remAthlete.setOnClickListener(v -> {
            LinearLayout lout = (LinearLayout) view.findViewById(R.id.match_dynamic_linear);
            lout.removeAllViewsInLayout();
            countAth = 2;
        });


        /*if (bundle != null)
            if (bundle.getString("mode").equals("upd")) {
                matchDate.setText(bundle.getString("match_date"));
                matchTown.setText(bundle.getString("match_town"));
                matchCountry.setText(bundle.getString("match_country"));
                if (bundle.getString("sport_type").equals("Single"))
                    match_single.setSelected(true);
                else
                    match_team.setSelected(true);
                for (int i = 0; i < matchSportId.getCount(); i++) {
                    int m = bundle.getInt("match_sport");
                    if (matchSportId.getItemAtPosition(i).equals(m))
                        matchSportId.setSelection(i);
                }
                for (int i = 0; i < sp1.getCount(); i++) {
                    String m = bundle.getStringArrayList("match_part").get(k);
                    if (spn.getItemAtPosition(i).equals(m))
                        spn.setSelection(i);
                    edt.setText(bundle.getStringArrayList("match_participants_score").get(i));
                }

                if (bundle.getStringArrayList("match_part").size() > 2) {
                    for (int i = bundle.getStringArrayList("match_part").size(); i >= 2; i--) {
                        countAth++;
                        LinearLayout lout = (LinearLayout) view.findViewById(R.id.match_dynamic_linear);
                        Spinner spn = new Spinner(getContext());
                        EditText edt = new EditText(getContext());
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        spn.setLayoutParams(parms);
                        spn.setTag("100" + countAth);
                        spn.setAdapter(ad2);
                        lout.addView(spn);
                        edt.setLayoutParams(parms);
                        edt.setHint("Score");
                        edt.setTag("200" + countAth);
                        lout.addView(edt);
                        for (int k = 0; k < spn.getCount(); k++) {
                            String m = bundle.getStringArrayList("match_part").get(k);
                            if (spn.getItemAtPosition(i).equals(m))
                                spn.setSelection(i);
                            edt.setText(bundle.getStringArrayList("match_participants_score").get(i));
                        }
                        if (countAth > 2)
                            remAthlete.setVisibility(View.VISIBLE);
                        else
                            remAthlete.setVisibility(View.GONE);
                    }
                }
            }*/

        add.setOnClickListener(v -> {
            if (matchDate.getText() == null || matchTown.getText() == null || matchCountry.getText() == null || matchSportId.getSelectedItem() == null
            || matchScore1.getText() == null || matchScore2.getText() == null || sp1.getSelectedItem() == null || sp2.getSelectedItem() == null)
                Toast.makeText(getContext(), "Empty Fields", Toast.LENGTH_LONG).show();
            else {
                if (sportCat.getText().equals("Teams")) {
                    match_participants.add(sp1.getSelectedItem().toString());
                    match_participants.add(sp2.getSelectedItem().toString());
                    match_participants_score.add(Integer.parseInt(matchScore1.getText().toString()));
                    match_participants_score.add(Integer.parseInt(matchScore2.getText().toString()));
                    DocumentReference clref = HomeActivity.db.collection("matches").document();
                    /*Map<String, Object> match = new HashMap<>();
                    match.put("match_id", clref.getId());
                    match.put("match_date", matchDate.getText().toString());
                    match.put("match_town", matchTown.getText().toString());
                    match.put("match_country", matchCountry.getText().toString());
                    match.put("sport_type", "Teams");
                    match.put("match_sport", Integer.parseInt(matchSportId.getSelectedItem().toString()));
                    match.put("match_part", match_participants);
                    match.put("match_part_score", match_participants_score);*/

                    Matches match = new Matches();
                    match.setMatch_id(clref.getId());
                    match.setMatchDate(matchDate.getText().toString());
                    match.setMatchTown(matchTown.getText().toString());
                    match.setMatchCountry(matchCountry.getText().toString());
                    match.setMatchTypeSport("Teams");
                    match.setMatchSportId(Integer.parseInt(matchSportId.getSelectedItem().toString()));
                    match.setParticipate(match_participants);
                    match.setMatch_part_score(match_participants_score);
                    clref.set(match).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                            matchDate.clearFocus();
                            matchDate.setText("");
                            matchTown.clearFocus();
                            matchTown.setText("");
                            matchCountry.clearFocus();
                            matchCountry.setText("");
                            matchSportId.setSelection(0);
                            sp1.setSelection(0);
                            sp2.setSelection(0);
                            matchScore1.clearFocus();
                            matchScore1.setText("");
                            matchScore2.clearFocus();
                            matchScore2.setText("");
                            countAth = 2;

                            Navigation.findNavController(view).navigate(R.id.action_addUpgMatchFragment_to_nav_matches);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getContext(), "Something went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                            /*.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                            matchDate.clearFocus();
                            matchDate.setText("");
                            matchTown.clearFocus();
                            matchTown.setText("");
                            matchCountry.clearFocus();
                            matchCountry.setText("");
                            matchSportId.setSelection(0);
                            sp1.setSelection(0);
                            sp2.setSelection(0);
                            matchScore1.clearFocus();
                            matchScore1.setText("");
                            matchScore2.clearFocus();
                            matchScore2.setText("");
                            countAth = 2;

                            Navigation.findNavController(view).navigate(R.id.action_addUpgMatchFragment_to_nav_matches);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getContext(), "Something went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });*/
                } else {
                    match_participants.add(sp1.getSelectedItem().toString());
                    match_participants.add(sp2.getSelectedItem().toString());
                    match_participants_score.add(Integer.parseInt(matchScore1.getText().toString()));
                    match_participants_score.add(Integer.parseInt(matchScore2.getText().toString()));
                    if (countAth > 2)
                        for (int i = 3; i <= countAth; i++) {
                            try {
                                Spinner randsp = (Spinner) view.findViewWithTag("100"+i);
                                match_participants.add(randsp.getSelectedItem().toString());
                                EditText randed = (EditText) view.findViewWithTag("200"+i);
                                match_participants_score.add(Integer.parseInt(randed.getText().toString()));
                            } catch (NumberFormatException ne) {
                                Toast.makeText(getContext(), "Something went wrong : " + ne.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    DocumentReference clref = HomeActivity.db.collection("matches").document();
                    /*Map<String, Object> match = new HashMap<>();
                    match.put("match_id", clref.getId());
                    match.put("match_date", matchDate.getText().toString());
                    match.put("match_town", matchTown.getText().toString());
                    match.put("match_country", matchCountry.getText().toString());
                    match.put("sport_type", "Single");
                    match.put("match_sport", Integer.parseInt(matchSportId.getSelectedItem().toString()));
                    match.put("match_part", match_participants);
                    match.put("match_part_score", match_participants_score);*/

                    Matches match = new Matches();
                    match.setMatch_id(clref.getId());
                    match.setMatchDate(matchDate.getText().toString());
                    match.setMatchTown(matchTown.getText().toString());
                    match.setMatchCountry(matchCountry.getText().toString());
                    match.setMatchTypeSport("Teams");
                    match.setMatchSportId(Integer.parseInt(matchSportId.getSelectedItem().toString()));
                    match.setParticipate(match_participants);
                    match.setMatch_part_score(match_participants_score);

                    clref.set(match).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                            matchDate.clearFocus();
                            matchDate.setText("");
                            matchTown.clearFocus();
                            matchTown.setText("");
                            matchCountry.clearFocus();
                            matchCountry.setText("");
                            matchSportId.setSelection(0);
                            sp1.setSelection(0);
                            sp2.setSelection(0);
                            matchScore1.clearFocus();
                            matchScore1.setText("");
                            matchScore2.clearFocus();
                            matchScore2.setText("");
                            countAth = 2;

                            Navigation.findNavController(view).navigate(R.id.action_addUpgMatchFragment_to_nav_matches);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getContext(), "Something went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                            /*.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                            matchDate.clearFocus();
                            matchDate.setText("");
                            matchTown.clearFocus();
                            matchTown.setText("");
                            matchCountry.clearFocus();
                            matchCountry.setText("");
                            matchSportId.setSelection(0);
                            sp1.setSelection(0);
                            sp2.setSelection(0);
                            matchScore1.clearFocus();
                            matchScore1.setText("");
                            matchScore2.clearFocus();
                            matchScore2.setText("");
                            countAth = 2;

                            Navigation.findNavController(view).navigate(R.id.action_addUpgMatchFragment_to_nav_matches);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getContext(), "Something went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });*/
                }
            }
        });

        cnl.setOnClickListener(v -> {
            matchDate.clearFocus();
            matchDate.setText("");
            matchTown.clearFocus();
            matchTown.setText("");
            matchCountry.clearFocus();
            matchCountry.setText("");
            matchSportId.setSelection(0);
            sp1.setSelection(0);
            sp2.setSelection(0);
            matchScore1.clearFocus();
            matchScore1.setText("");
            matchScore2.clearFocus();
            matchScore2.setText("");
            countAth = 2;
            Navigation.findNavController(v).navigate(R.id.action_addUpgMatchFragment_to_nav_matches);
        });

        return view;
    }
}