package e.lenovo.soccerapp.ui.sports;

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
import e.lenovo.soccerapp.data.Sports;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    Context mcontext;
    @NonNull
    List<Sports> mSports;
    OnSportListener mOnSportListener;

    public SportsAdapter(Context cxt, @NonNull List<Sports> sports, OnSportListener onSportListener) {
        this.mcontext = cxt;
        this.mSports = sports;
        this.mOnSportListener = onSportListener;
    }

    @NonNull
    @NotNull
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_row, parent, false);
        return new ViewHolder(view, mOnSportListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        @NonNull
        @NotNull
        Sports sport = mSports.get(position);
        holder.sid.setText(Integer.toString(sport.getSportId()));
        holder.sname.setText(sport.getSportName());
        holder.scategory.setText(sport.getSportCategory());
        holder.sgender.setText(sport.getGender());

    }

    @Override
    public int getItemCount() { if (mSports != null) return mSports.size(); else return 0; }

    public interface OnSportListener {
        void onSportClick(int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView sid, sname, scategory, sgender;
        OnSportListener onSportListener;

        public ViewHolder(View itemView, OnSportListener onSportListener) {
            super(itemView);
            sid = itemView.findViewById(R.id.sports_id);
            sname = itemView.findViewById(R.id.sports_name);
            scategory = itemView.findViewById(R.id.sports_category);
            sgender = itemView.findViewById(R.id.sports_gender);
            this.onSportListener = onSportListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSportListener.onSportClick(getAbsoluteAdapterPosition());
        }
    }
}
