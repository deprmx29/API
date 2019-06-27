package politani.trpl.bola.ui.last;


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
import politani.trpl.bola.model.ResponseLast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class lastFragment extends Fragment {
    String idLeague;

    RecyclerView rvLastMatch;
    lastAdapter adapter;
    ApiInterface apiInterface;

    public lastFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String idLeague){

        Fragment f=  new lastFragment();
        Bundle bundle=new Bundle();
        bundle.putString("idLeague" , idLeague);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_last, container, false);
        rvLastMatch =v.findViewById(R.id.rv_last);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            idLeague=getArguments().getString("idLeague");

            apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

            adapter=new lastAdapter(getActivity(), apiInterface);
            rvLastMatch.setHasFixedSize(true);
            rvLastMatch.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvLastMatch.setAdapter(adapter);

            getApiLastmatch();
        }
    }
    public void getApiLastmatch(){
        Call<ResponseLast> api=apiInterface.getLastEvent(idLeague);
        api.enqueue(new Callback<ResponseLast>() {
            @Override
            public void onResponse(Call<ResponseLast> call, Response<ResponseLast> response) {
                if (response.isSuccessful());
                adapter.setItems(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<ResponseLast> call, Throwable t) {

            }
        });
    }
}
