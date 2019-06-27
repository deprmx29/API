package politani.trpl.bola.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import politani.trpl.bola.R;
import politani.trpl.bola.ui.Team.TeamBola;
import politani.trpl.bola.model.League;

public class adaptorAllLeague extends RecyclerView.Adapter<adaptorAllLeague.LeagueViewHolder> {

    List<League> LeagueList=new ArrayList<>();
    private final Activity activity;
    public static final String DATA_LEAGUE="data_league";
    public static final String NAMA_LEAGUE="nama_league";
    public adaptorAllLeague(Activity activity) {
        this.activity = activity;
    }


    public void setLeagueList(List<League> leagueList) {
        LeagueList = leagueList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_league, parent, false);
        return new LeagueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, final int position) {
    holder.tvliga.setText(LeagueList.get(position).getStrLeague());

        Picasso.get().load(LeagueList.get(position).getStrBadge())
                .into(holder.ivlogo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, TeamBola.class);

                String idLeague=LeagueList.get(position).getIdLeague();
                String namaLeague=LeagueList.get(position).getStrLeague();

                intent.putExtra(DATA_LEAGUE, idLeague);
                intent.putExtra(NAMA_LEAGUE, namaLeague);
                activity.startActivity(intent);
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return LeagueList.size();
    }

    class LeagueViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivlogo;
        public TextView  tvliga;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            ivlogo=itemView.findViewById(R.id.iv_logo);
            tvliga=itemView.findViewById(R.id.tvliga);
        }
    }

}
