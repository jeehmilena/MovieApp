package br.com.movieapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.movieapp.R;
import br.com.movieapp.model.pojo.Result;
import br.com.movieapp.view.interfaces.OnClickList;

public class RecyclerFilmeAdapter extends RecyclerView.Adapter<RecyclerFilmeAdapter.ViewHolder> {

    private List<Result> listResult;
    private OnClickList onClickList;

    public RecyclerFilmeAdapter(List<Result> listResult, OnClickList onClickList) {
        this.listResult = listResult;
        this.onClickList = onClickList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = listResult.get(position);
        holder.onBind(result);
        holder.itemView.setOnClickListener(v ->{
            onClickList.onClick(result);
        });

    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }


    public void atualizaLista(List<Result> novaLista){
        this.listResult.clear();
        this.listResult = novaLista;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgFilme);
            textView = itemView.findViewById(R.id.txtTitulo);
        }

        public void onBind(Result filme) {

            textView.setText(filme.getOriginalTitle());

            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+ filme.getPosterPath()).into(imageView);
        }
    }
}
