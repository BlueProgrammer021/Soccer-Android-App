package e.lenovo.soccerapp.ui.athlete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.Athletes;

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.ViewHolder> {

    Context mCxt;
    List<Athletes> mAthletes;
    OnAthleteListener mOnAthleteListener;

    public AthleteAdapter(Context cxt, List<Athletes> athletes, OnAthleteListener onAthleteListener) {
        this.mCxt = cxt;
        this.mAthletes = athletes;
        this.mOnAthleteListener = onAthleteListener;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AthleteAdapter.ViewHolder holder, int position) {
        @NonNull
        @NotNull
        Athletes athlete = mAthletes.get(position);
        holder.athleteId.setText(Integer.toString(athlete.getAthleteId()));
        holder.athletefName.setText(athlete.getAthleteFirstName());
        holder.athletelName.setText(athlete.getAthleteLastName());
        holder.athleteCounrty.setText(athlete.getAthleteCountry());
        holder.athleteSid.setText(Integer.toString(athlete.getSportId()));
        holder.athleteTown.setText(athlete.getAthleteTown());
    }

    @NonNull
    @NotNull
    @Override
    public AthleteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_row, parent, false);
        return new ViewHolder(view, mOnAthleteListener);
    }

    @Override
    public int getItemCount() { if(mAthletes != null) return mAthletes.size(); else return 0;}

    public interface OnAthleteListener {
        void onAthleteClick(int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView athleteId, athletefName, athletelName, athleteSid, athleteTown, athleteCounrty;
        OnAthleteListener onAthleteListener;

        public ViewHolder(@NonNull @NotNull View itemView, OnAthleteListener onAthleteListener) {
            super(itemView);

            athleteId = itemView.findViewById(R.id.athletes_id);
            athletefName = itemView.findViewById(R.id.athletes_fname);
            athletelName = itemView.findViewById(R.id.athletes_lname);
            athleteTown = itemView.findViewById(R.id.athletes_town);
            athleteCounrty = itemView.findViewById(R.id.athletes_country);
            athleteSid = itemView.findViewById(R.id.athletes_sport_id);
            this.onAthleteListener = onAthleteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAthleteListener.onAthleteClick(getAbsoluteAdapterPosition());
        }
    }
}
