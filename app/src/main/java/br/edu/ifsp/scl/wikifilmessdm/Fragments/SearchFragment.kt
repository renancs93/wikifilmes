package br.edu.ifsp.scl.wikifilmessdm.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.edu.ifsp.scl.wikifilmessdm.Adapter.MovieListAdapter
import br.edu.ifsp.scl.wikifilmessdm.MainActivity
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import br.edu.ifsp.scl.wikifilmessdm.R
import br.edu.ifsp.scl.wikifilmessdm.Service.Omdb
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment(), View.OnClickListener {

    lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutView = inflater.inflate(R.layout.fragment_search, null)
        val omdb = Omdb(activity as MainActivity)

        layoutView.btn_search.setOnClickListener{

            var txtMovie: String = field_search.text.toString()

            if (txtMovie.isNotEmpty()) {
                omdb.searchListMovies(txtMovie)
            }

        }

//        layoutView.btn_clear.setOnClickListener {
//
//            field_search.text?.clear()
//            layoutView.list_movies_recycler.adapter = null
//            layoutView.list_movies_recycler.adapter?.notifyDataSetChanged()
//        }

        //click listeners
        layoutView.btn_clear.setOnClickListener(this)

        //Retorno da requisição
        omdb.callback = object : Omdb.MovieCallback{
            override fun onResponse(obj: Movie) {

                if (obj.response.equals("True")) {
                    val movie = obj
                    val movieList = obj.search

//                    total_results_tv.text = getString(R.string.txt_total_results) + movie.totalResults

                    val recyclerView = list_movies_recycler

                    if (movieList.size > 0) {
                        recyclerView.adapter = MovieListAdapter(movieList, ctx/*this@SearchFragment.context!!*/)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onResponseFail(obj: Movie) {
                clear()
                Toast.makeText(this@SearchFragment.context, obj.error, Toast.LENGTH_LONG).show()
            }

            override fun onRequestFail(err: Throwable) {
                Toast.makeText(this@SearchFragment.context, err.message, Toast.LENGTH_LONG).show()
            }


        }

        return layoutView
    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.btn_clear -> {
               clear()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (context != null){
            this.ctx = context as Context
        }
    }

    fun clear(){
        field_search.text?.clear()
        list_movies_recycler.adapter = null
        list_movies_recycler.list_movies_recycler.adapter?.notifyDataSetChanged()
    }


}
