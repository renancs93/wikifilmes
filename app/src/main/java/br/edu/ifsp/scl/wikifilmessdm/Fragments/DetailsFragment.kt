package br.edu.ifsp.scl.wikifilmessdm.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import br.edu.ifsp.scl.wikifilmessdm.R
import kotlinx.android.synthetic.main.details_fragment.*

@SuppressLint("ValidFragment")
class DetailsFragment @SuppressLint("ValidFragment") constructor(private var movie: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutView = inflater.inflate(R.layout.details_fragment, null)


        return layoutView
    }

    init {
        if (movie != null){

            field_title.text = getString(R.string.txt_name) + movie
//            field_title.text = getString(R.string.txt_name) + movie.title.toString()
//            field_year.text = getString(R.string.txt_year) + movie.year
//            field_type.text = getString(R.string.txt_type) + movie.type
//            field_imdb_rating.text = getString(R.string.txt_imdb_rating) + movie.imdbRating
//            field_actors.text = getString(R.string.txt_actors) + movie.actors
//            field_director.text = getString(R.string.txt_director) + movie.director
//            field_production.text = getString(R.string.txt_production) + movie.production
//            field_genre.text = getString(R.string.txt_genre) + movie.genre
//            field_writer.text = getString(R.string.txt_writer) + movie.writer
//            field_plot.text = getString(R.string.txt_plot) + movie.plot
        }
    }

}