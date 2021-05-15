package e.lenovo.soccerapp.ui.matches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import e.lenovo.soccerapp.HomeActivity;
import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.Matches;

import static e.lenovo.soccerapp.HomeActivity.db;

public class MatchesFragment extends Fragment  implements MatchesAdapter.OnMatchListener {

    private MatchesViewModel mViewModel;
    int dPos;
    Query matches = db.collection("matches");
    FirestoreRecyclerOptions<Matches> opt = new FirestoreRecyclerOptions.Builder<Matches>()
            .setQuery(matches, Matches.class)
            .build();
    MatchesAdapter ada = new MatchesAdapter(getContext(), opt, this);

    public static MatchesFragment newInstance() {
        return new MatchesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        Button addMatch = view.findViewById(R.id.match_add);
        Button delMatch = view.findViewById(R.id.match_delete);
        RecyclerView mrcv = view.findViewById(R.id.match_view);
        matches = db.collection("matches");
        opt = new FirestoreRecyclerOptions.Builder<Matches>()
                .setQuery(matches, Matches.class)
                .build();
        ada = new MatchesAdapter(getContext(), opt, this);
        mrcv.setHasFixedSize(true);
        mrcv.setLayoutManager(new LinearLayoutManager(getContext()));
        mrcv.setAdapter(ada);

        Button updMatch =  view.findViewById(R.id.match_update);

        addMatch.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_matches_to_addUpgMatchFragment);
            ada.notifyDataSetChanged();
        });

        delMatch.setOnClickListener(v -> {
            db.collection("matches").document(ada.getItem(dPos).getMatch_id()).delete();
            Toast.makeText(getContext(), "Document successfully deleted", Toast.LENGTH_SHORT).show();
            ada.notifyDataSetChanged();
            /*db.collection("matches")
                    .document(did.get(dPos))
                    .delete();
            did.remove(dPos);
            ada.notifyDataSetChanged();*/
            //Toast.makeText(getContext(), did.get(dPos).toString(), Toast.LENGTH_LONG).show();
        });

        updMatch.setVisibility(View.GONE);

        /*updMatch.setOnClickListener(v -> {
            db.collection("matches").document(ada.getItem(dPos).getMatch_id()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Matches match = documentSnapshot.toObject(Matches.class);
                    Bundle bun = new Bundle();
                    bun.putString("mode", "upd");
                    bun.putString("match_id", Objects.requireNonNull(match).getMatch_id());
                    bun.putString("match_date", match.getMatchDate());
                    bun.putString("match_town", match.getMatchTown());
                    bun.putString("match_country", match.getMatchCountry());
                    bun.putString("sport_type", match.getMatchTypeSport());
                    bun.putInt("match_sport", Integer.parseInt(String.valueOf(match.getMatchSportId())));
                    bun.putStringArrayList("match_part", (ArrayList<String>) match.getParticipate());
                    bun.putIntegerArrayList("match_part_score", (ArrayList<Integer>) match.getMatch_part_score());

                    Navigation.findNavController(view).navigate(R.id.action_nav_matches_to_addUpgMatchFragment, bun);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getContext(), "Something went wrong : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });*/

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MatchesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onMatchClick(int pos) {
        dPos = pos;
    }

    @Override
    public void onStart() {
        super.onStart();
        ada.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        ada.stopListening();
    }
}