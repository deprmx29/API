package politani.trpl.bola.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import politani.trpl.bola.R;
import politani.trpl.bola.httpclient.ApiClient;
import politani.trpl.bola.httpclient.ApiInterface;
import politani.trpl.bola.model.ResponseAllLeagues;
import politani.trpl.bola.ui.home.adaptorAllLeague;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeActivity extends AppCompatActivity {
    private RecyclerView rvLeague;
    private adaptorAllLeague adapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvLeague=findViewById(R.id.rv_league);
        adapter=new adaptorAllLeague(this);
        rvLeague.setHasFixedSize(true);
        rvLeague.setLayoutManager(new GridLayoutManager(this, 2));
        rvLeague.setAdapter(adapter);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        getApiAllLeague();
    }

    public void getApiAllLeague(){
        Call<ResponseAllLeagues> api=apiInterface.getAllLeague();
        api.enqueue(new Callback<ResponseAllLeagues>() {
            @Override
            public void onResponse(Call<ResponseAllLeagues> call, Response<ResponseAllLeagues> response) {

                if (response.isSuccessful()){
                    adapter.setLeagueList(response.body().getAllLeague());
                }
            }

            @Override
            public void onFailure(Call<ResponseAllLeagues> call, Throwable t) {

            }
        });
    }
}
