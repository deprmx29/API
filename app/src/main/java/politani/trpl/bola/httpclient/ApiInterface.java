package politani.trpl.bola.httpclient;


import politani.trpl.bola.model.Response;
import politani.trpl.bola.model.ResponseAllLeagues;
import politani.trpl.bola.model.ResponseLast;
import politani.trpl.bola.model.ResponseNext;
import politani.trpl.bola.model.ResponseTeam;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search_all_leagues.php?s=Soccer")
    Call<ResponseAllLeagues>getAllLeague();

    @GET("eventspastleague.php")
    Call<ResponseLast>getLastEvent(@Query("id")String id);

    @GET("eventsnextleague.php")
    Call<ResponseNext>getNextEvent(@Query("id")String id);

    @GET("lookupteam.php")
    Call<ResponseTeam> getDetailTeam(@Query("id") String teamId);
}