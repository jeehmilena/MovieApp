package br.com.movieapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import br.com.movieapp.R;
import br.com.movieapp.model.pojo.Result;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static br.com.movieapp.view.activity.HomeActivity.FILME_KEY;

public class DetalheActivity extends AppCompatActivity {

    private TextView titulo;
   // private TextView descricao;
    private TextView popularidade;
    private TextView data;
    private ImageView imgFundo;
    private ImageView imgFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        initViews();

        if(getIntent() != null && getIntent().getExtras() != null){
            Result result = getIntent().getExtras().getParcelable(FILME_KEY);
            titulo.setText(result.getTitle());
           // descricao.setText(result.getOverview());
            popularidade.setText(result.getPopularity().toString());
            data.setText(result.getReleaseDate());
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(imgFilme);
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getBackdropPath()).into(imgFundo);

        }


    }


    private void initViews(){
        titulo = findViewById(R.id.text_titulo);
      //  descricao = findViewById(R.id.text_descricao);
        popularidade = findViewById(R.id.text_popularidade);
        data = findViewById(R.id.text_data);
        imgFilme = findViewById(R.id.img_filme);
        imgFundo = findViewById(R.id.img_fundo);
    }
}
