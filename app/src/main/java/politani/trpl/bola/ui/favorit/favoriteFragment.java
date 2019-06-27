package politani.trpl.bola.ui.favorit;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import politani.trpl.bola.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class favoriteFragment extends Fragment {
    String idLeague;

    public favoriteFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String idLeague){
        Fragment f=  new favoriteFragment();
        Bundle bundle=new Bundle();
        bundle.putString("idLeague" , idLeague);
        f.setArguments(bundle);
        return f;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            idLeague=getArguments().getString("idLeague");
        }
    }
}
