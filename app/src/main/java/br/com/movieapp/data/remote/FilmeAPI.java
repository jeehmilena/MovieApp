package br.com.movieapp.data.remote;

import br.com.movieapp.model.pojo.Filme;
import br.com.movieapp.model.pojo.Result;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmeAPI {


    @GET("movie/popular")
    Observable<Filme> getAllFilmes(@Query("api_key") String apyKey);
}
