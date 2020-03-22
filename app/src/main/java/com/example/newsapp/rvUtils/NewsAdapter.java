package com.example.newsapp.rvUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.newsapp.R;
import com.example.newsapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NoticiasViewHolder> implements View.OnClickListener {
    View.OnClickListener naListener;
    ArrayList<Article> listaNoticias;
    // Article art;

    public NewsAdapter(ArrayList<Article> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        v.setOnClickListener(naListener);
        return new NoticiasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder holder, int position) {
        /*Enlazamos el contenedor del contenido del elemento de la posición position
        * de noticasVH con el VH*/
        holder.bindNews(listaNoticias.get(position));
    }

    @Override
    public int getItemCount() {
        /*Crea tantas cajas como listaNoticias haya*/
        return listaNoticias.size();
    }



    @Override
    public void onClick(View v) {
        if (naListener != null) {
            naListener.onClick(v);
        }
    }

    public static class NoticiasViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTitle;
        TextView tvDescription;
        ImageView imagenHeadLine;

        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.tvAutor);
            tvTitle = itemView.findViewById(R.id.tvTitulo);
            tvDescription = itemView.findViewById(R.id.tvDesc);
            imagenHeadLine = itemView.findViewById(R.id.ivImage);
        }
        /*para volcar la info del tipo articulo en los componentes del layaut que hayamos llamado */
        public void bindNews(Article articulo) {

            tvAuthor.setText(articulo.getAuthor());
            tvTitle.setText(articulo.getTitle());
            tvDescription.setText(articulo.getDescription());

            String url = articulo.getUrl();

            // obtenerUrl(url);

            String imagenUrl = articulo.getUrlToImage();
            Picasso.get().load(imagenUrl).into(imagenHeadLine);

        }

    }

    public void setListener(View.OnClickListener naListener) {
        this.naListener = naListener;
    }



}
