package br.com.movieapp.repository;

import br.com.movieapp.model.pojo.Filme;
import br.com.movieapp.model.pojo.Result;
import io.reactivex.Observable;

import static br.com.movieapp.data.remote.RetrofitService.getApiService;

public class FilmeRepository {

    public Observable<Filme> getFilmes(String apiKey){
        return getApiService().getAllFilmes(apiKey);
    }
}
