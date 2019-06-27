package politani.trpl.bola.ui.next;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import politani.trpl.bola.R;
import politani.trpl.bola.httpclient.ApiClient;
import politani.trpl.bola.httpclient.ApiInterface;
import politani.trpl.bola.model.ResponseNext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class nextFragment extends Fragment {
    String idLeague;
    RecyclerView rvNextMatch;
    nextAdapter adapter;
    ApiInterface apiInterface;

    public nextFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String idLeague){
        Fragment f= new nextFragment();
        Bundle bundle=new Bundle();
        bundle.putString("idLeague", idLeague);
        f.setArguments(bundle);
        return f;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_next, container, false);
        rvNextMatch = v.findViewById(R.id.rv_next);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            idLeague=getArguments().getString("idLeague");

            apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
            adapter=new nextAdapter(getActivity(), apiInterface);

            rvNextMatch.setHasFixedSize(true);
            rvNextMatch.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvNextMatch.setAdapter(adapter);

            getApiNextMatch();
        }
    }
    public void getApiNextMatch(){
        Call<ResponseNext> api=apiInterface.getNextEvent(idLeague);
        api.enqueue(new Callback<ResponseNext>() {
            @Override
            public void onResponse(Call<ResponseNext> call, Response<ResponseNext> response) {
                if(response.isSuccessful());
                adapter.setItems(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<ResponseNext> call, Throwable t) {

            }
        });
    }
}
