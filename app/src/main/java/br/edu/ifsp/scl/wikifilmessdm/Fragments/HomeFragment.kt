package br.edu.ifsp.scl.wikifilmessdm.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import br.edu.ifsp.scl.wikifilmessdm.MainActivity
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import br.edu.ifsp.scl.wikifilmessdm.R
import br.edu.ifsp.scl.wikifilmessdm.Service.Omdb
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutView = inflater.inflate(br.edu.ifsp.scl.wikifilmessdm.R.layout.home_fragment, null)
        val omdb = Omdb(activity as MainActivity)

        layoutView.btn_search.setOnClickListener {

            var txtMovie: String = field_search.text.toString()

            if (txtMovie.isNotEmpty()) {
                omdb.findMovie(txtMovie)
            }
        }

        layoutView.btn_clear.setOnClickListener {

            field_search.text?.clear()
            result_card.visibility = View.GONE

        }

        omdb.callback = object : Omdb.MovieCallback{
            override fun onRequestFail(err: Throwable) {

                Toast.makeText(this@HomeFragment.context, err.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponseFail(obj: Movie) {
                result_card.visibility = View.VISIBLE
                field_title.text = obj.error
            }

            override fun onResponse(obj: Movie) {

                result_card.visibility = View.VISIBLE

                if (obj.response.equals("True")){
                    field_title.text = getString(R.string.txt_name) + obj.title
                    field_year.text = getString(R.string.txt_year) + obj.year
                    field_type.text = getString(R.string.txt_type) + obj.type
                    field_imdb_rating.text = getString(R.string.txt_imdb_rating) + obj.imdbRating
                    field_actors.text = getString(R.string.txt_actors) + obj.actors
                    field_director.text = getString(R.string.txt_director) + obj.director
                    field_production.text = getString(R.string.txt_production) + obj.production
                    field_genre.text = getString(R.string.txt_genre) + obj.genre
                    field_writer.text = getString(R.string.txt_writer) + obj.writer
                    field_plot.text = getString(R.string.txt_plot) + obj.plot

                    //Load Image Poster
                    val posterUrl = obj.poster
                    if (posterUrl != "") {
                        field_poster.visibility = View.VISIBLE
                        field_poster.loadPicasso(posterUrl)
                    }
                    else{
                        field_poster.visibility = View.GONE
                    }
                }
            }
        }

        return layoutView
    }

}


private fun ImageView.loadPicasso(urlPoster: String) {
    Picasso.get().load(urlPoster).into(this)
}