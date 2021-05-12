package e.lenovo.soccerapp.ui.sports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import e.lenovo.soccerapp.R;
import e.lenovo.soccerapp.data.Sports;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    Context context;
    LiveData<List<Sports>> mSports;

    public SportsAdapter(LiveData<List<Sports>> sports) {
        this.mSports = sports;
    }

    public void setContext(Context context) { this.context = context; }

    @NonNull
    @NotNull
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        @NotNull
        Sports sport = Objects.requireNonNull(mSports.getValue()).get(position);
        holder.getSid().setText(sport.getSportId());
        holder.getSname().setText(sport.getSportName());
        holder.getScategory().setText(sport.getSportCategory());
        holder.getSgender().setText(sport.getGender());

    }

    @Override
    public int getItemCount() { if (mSports.getValue() == null) return 0; else return mSports.getValue().size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sid, sname, scategory, sgender;
        public ViewHolder(View itemView) {
            super(itemView);
            sid = itemView.findViewById(R.id.sports_id);
            sname = itemView.findViewById(R.id.sports_name);
            scategory = itemView.findViewById(R.id.sports_category);
            sgender = itemView.findViewById(R.id.sports_gender);
        }

        public TextView getSid() { return sid; }

        public TextView getSname() { return sname; }

        public TextView getScategory() { return scategory; }

        public TextView getSgender() { return sgender; }
    }
}
