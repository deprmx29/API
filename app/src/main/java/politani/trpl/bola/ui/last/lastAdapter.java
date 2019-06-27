package politani.trpl.bola.ui.last;

import android.app.Activity;
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
import politani.trpl.bola.httpclient.ApiInterface;
import politani.trpl.bola.model.EventsItem;
import politani.trpl.bola.model.ResponseTeam;
import politani.trpl.bola.ui.home.adaptorAllLeague;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class lastAdapter extends RecyclerView.Adapter<lastAdapter.LastViewHolder> {

    List<EventsItem> items=new ArrayList<>();
    Activity activity;
    ApiInterface apiInterface;

    public lastAdapter(Activity activity, ApiInterface apiInterface) {
        this.activity = activity;
        this.apiInterface = apiInterface;
    }

    public void setItems(List<EventsItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new LastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastViewHolder holder, int position) {
        holder.tvhome.setText(items.get(position).getStrHomeTeam());
        holder.tvaway.setText(items.get(position).getStrAwayTeam());
        holder.tvskor.setText(items.get(position).getIntHomeScore()+ " - " + items.get(position).getIntAwayScore());

        setImage(holder.imghome, items.get(position).getIdHomeTeam());
        setImage(holder.imgaway, items.get(position).getIdAwayTeam());
    }

    @Override
    public int getItemCount() {
        if (items !=null){
            return items.size();
        }else{
        return 0;
        }
    }

    public void setImage(final ImageView image, String teamID){
        Call<ResponseTeam> api=apiInterface.getDetailTeam(teamID);
        api.enqueue(new Callback<ResponseTeam>() {
            @Override
            public void onResponse(Call<ResponseTeam> call, Response<ResponseTeam> response) {
                if (response.isSuccessful()){
                    String PathImage=response.body().getTeams().get(0).getStrTeamBadge();
                    Picasso.get().load(PathImage).into(image);
                }
            }

            @Override
            public void onFailure(Call<ResponseTeam> call, Throwable t) {

            }
        });
    }


    class LastViewHolder extends RecyclerView.ViewHolder{
        public TextView tvhome, tvaway, tvskor;
        ImageView imghome, imgaway;

        public LastViewHolder(@NonNull View itemView) {
            super(itemView);
            tvhome=itemView.findViewById(R.id.tvhome);
            tvaway=itemView.findViewById(R.id.tvaway);
            tvskor=itemView.findViewById(R.id.tvscore);
            imghome=itemView.findViewById(R.id.imagehome);
            imgaway=itemView.findViewById(R.id.imageaway);
        }
    }
}
