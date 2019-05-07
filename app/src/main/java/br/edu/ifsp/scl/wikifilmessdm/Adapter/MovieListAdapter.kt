package br.edu.ifsp.scl.wikifilmessdm.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import br.edu.ifsp.scl.wikifilmessdm.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieListAdapter(private val movies: List<Movie>,
                       private val context: Context
) : Adapter<MovieListAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Pegar os itens por meio da posição recebida e realizar o Bind, colocando as informações do objeto devolvido
        val movie = movies[position]

        holder?.let {
            it.bindView(movie)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun bindView(movie: Movie){

            // propriedades do layout a serem exibidas
            val imdbId = itemView.imdb_id_item
            val title = itemView.title_item
            val year = itemView.year_item
            val type = itemView.type_item
            val poster = itemView.poster_item

            val urlPoster = movie.poster

            imdbId.text = itemView.context.getString(R.string.txt_imdbID) + movie.imdbID
            title.text = itemView.context.getString(R.string.txt_title) + movie.title
            year.text = itemView.context.getString(R.string.txt_year) + movie.year
            type.text = itemView.context.getString(R.string.txt_type) + movie.type

            if (urlPoster != "N/A"){
                poster.loadPicasso(urlPoster)
            }
            else{
                poster.setBackgroundColor(R.color.colorBlack)
            }

        }



    }

}

fun ImageView.loadPicasso(urlPoster: String) {
    Picasso.get().load(urlPoster).into(this)
}
