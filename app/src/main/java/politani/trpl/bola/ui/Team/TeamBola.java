package politani.trpl.bola.ui.Team;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import politani.trpl.bola.R;
import politani.trpl.bola.ui.favorit.favoriteFragment;
import politani.trpl.bola.ui.home.adaptorAllLeague;
import politani.trpl.bola.ui.last.lastFragment;
import politani.trpl.bola.ui.next.nextFragment;

public class TeamBola extends AppCompatActivity {

    private static final String SELECTED_MENU = "selected menu";
    public String idLeague;
    public String namaLeague;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment= null;
            if(item.getItemId()== R.id.navigation_last){
                fragment= lastFragment.newInstance(idLeague);
            }else if (item.getItemId()==R.id.navigation_next){
                fragment= nextFragment.newInstance(idLeague);
            }else if (item.getItemId()==R.id.navigation_favorite){
                fragment= favoriteFragment.newInstance(idLeague);
            }

            if(fragment!=null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        idLeague=getIntent().getStringExtra(adaptorAllLeague.DATA_LEAGUE);
        namaLeague=getIntent().getStringExtra(adaptorAllLeague.NAMA_LEAGUE);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(namaLeague);
        }


        if (savedInstanceState !=null){
            savedInstanceState.getInt(SELECTED_MENU);
            }else{
        navView.setSelectedItemId(R.id.navigation_last);
    }

}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
