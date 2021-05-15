package e.lenovo.soccerapp.ui.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import e.lenovo.soccerapp.data.Teams;

import e.lenovo.soccerapp.R;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    Context mCtx;
    List<Teams> mTeams;
    OnTeamListener mOnTeamListener;

    public TeamAdapter(Context mCtx, List<Teams> mTeams, OnTeamListener mOnTeamListener) {
        this.mCtx = mCtx;
        this.mTeams = mTeams;
        this.mOnTeamListener = mOnTeamListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_row, parent, false);
        return new ViewHolder(view, mOnTeamListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Teams team = mTeams.get(position);
        holder.teamId.setText(Integer.toString(team.getTeamId()));
        holder.teamName.setText(team.getTeamName());
        holder.teamArena.setText(team.getTeamArena());
        holder.teamCourt.setText(team.getTeamCourt());
        holder.teamCountry.setText(team.getTeamCountry());
        holder.teamSportId.setText(Integer.toString(team.getSportId()));
        holder.teamEst.setText(team.getTeamEst());
    }

    @Override
    public int getItemCount() { if (mTeams.size() != 0) return mTeams.size(); else return 0; }

    public interface OnTeamListener {
        void onTeamClick(int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView teamId, teamName, teamArena, teamCourt, teamCountry, teamSportId, teamEst;
        OnTeamListener onTeamListener;
        public ViewHolder(@NonNull @NotNull View itemView, OnTeamListener onTeamListener) {
            super(itemView);
            teamId = itemView.findViewById(R.id.teams_id);
            teamName = itemView.findViewById(R.id.teams_name);
            teamArena =itemView.findViewById(R.id.teams_arena);
            teamCourt = itemView.findViewById(R.id.teams_court);
            teamCountry = itemView.findViewById(R.id.teams_country);
            teamSportId = itemView.findViewById(R.id.teams_sport_id);
            teamEst = itemView.findViewById(R.id.teams_est);
            this.onTeamListener = onTeamListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTeamListener.onTeamClick(getAbsoluteAdapterPosition());
        }
    }
}
