package br.edu.ifsp.scl.wikifilmessdm.Service

import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import br.edu.ifsp.scl.wikifilmessdm.Models.SearchMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointsApi {

    @GET("/")
    fun getFilmByTitle(@Query("t") title: String): Call<Movie>

    @GET("/")
    fun getFilmById(@Query("i") id: String): Call<Movie>

    @GET("/")
    fun getFilmsListByTitle(@Query("s") title: String): Call<SearchMovies>

}