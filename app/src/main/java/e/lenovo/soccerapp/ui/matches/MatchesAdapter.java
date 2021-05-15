package e.lenovo.soccerapp.ui.matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.Matches;

public class MatchesAdapter extends FirestoreRecyclerAdapter<Matches ,MatchesAdapter.MatchesHolder> {

    Context mCtx;
    OnMatchListener mOnMatchListener;

    public MatchesAdapter(Context mCtx, FirestoreRecyclerOptions<Matches> opt, OnMatchListener mOnMatchListener) {
        super(opt);
        this.mCtx = mCtx;
        this.mOnMatchListener = mOnMatchListener;
    }

    @NonNull
    @NotNull
    @Override
    public MatchesAdapter.MatchesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row, parent, false);
        return new MatchesHolder(view, mOnMatchListener);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MatchesHolder holder, int position, @NonNull @NotNull Matches match) {
        holder.matchDate.setText(match.getMatchDate());
        holder.matchTown.setText(match.getMatchTown());
        holder.matchCountry.setText(match.getMatchCountry());
        holder.matchSportId.setText(Integer.toString(match.getMatchSportId()));
        holder.matchTypeSport.setText(match.getMatchTypeSport());
        for (int i = 0; i < match.getParticipate().size(); i++) {
            holder.matchParticipant.append(match.getParticipate().get(i));
            holder.matchParticipant.append(" : ");
            holder.matchParticipantScore.append(String.valueOf(match.getMatch_part_score().get(i)));
            holder.matchParticipantScore.append(" : ");
        }
    }

    public interface OnMatchListener {
        void onMatchClick(int pos);
    }

    public static class MatchesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView matchDate, matchTown, matchCountry, matchSportId, matchTypeSport, matchParticipant, matchParticipantScore;
        OnMatchListener onmatchListener;

        public MatchesHolder(@NonNull @NotNull View itemView, OnMatchListener onMatchListener) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.matches_date);
            matchTown = itemView.findViewById(R.id.matches_town);
            matchCountry = itemView.findViewById(R.id.matches_country);
            matchSportId = itemView.findViewById(R.id.matches_sport_id);
            matchTypeSport = itemView.findViewById(R.id.matches_type_sport);
            matchParticipant = itemView.findViewById(R.id.matches_participate);
            matchParticipantScore = itemView.findViewById(R.id.matches_participate_score);
            this.onmatchListener = onMatchListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) { onmatchListener.onMatchClick(getAbsoluteAdapterPosition()); }
    }

}
