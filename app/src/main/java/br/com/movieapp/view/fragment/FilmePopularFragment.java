package br.com.movieapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.movieapp.R;
import br.com.movieapp.model.pojo.Result;
import br.com.movieapp.view.activity.DetalheActivity;
import br.com.movieapp.view.adapters.RecyclerFilmeAdapter;
import br.com.movieapp.view.interfaces.OnClickList;
import br.com.movieapp.viewmodel.FilmePopularFragmentViewModel;

import static br.com.movieapp.view.activity.HomeActivity.FILME_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmePopularFragment extends Fragment implements OnClickList {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerFilmeAdapter adapter;
    private List<Result> listaFilmes = new ArrayList<>();
    private FilmePopularFragmentViewModel viewModel;



    public FilmePopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filme_popular, container, false);

        initViews(view);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        viewModel.getAllFilmes("bde8033d3274c91b292a5293c6349052");

        viewModel.getListaFilme().observe(this, results -> {
            adapter.atualizaLista(results);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });


        return view;
    }


    private void initViews(View view){
        recyclerView = view.findViewById(R.id.recyclerViewFilmes);
        progressBar = view.findViewById(R.id.progress_bar);
        adapter = new RecyclerFilmeAdapter(listaFilmes, this);
        viewModel = ViewModelProviders.of(this).get(FilmePopularFragmentViewModel.class);
    }

    @Override
    public void onClick(Result result) {
        Intent intent = new Intent(getContext(), DetalheActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(FILME_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
