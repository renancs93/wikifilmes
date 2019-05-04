package br.edu.ifsp.scl.wikifilmessdm.Service

import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointsApi {

    @GET("/")
    fun getFilmByTitle(@Query("t") title: String): Call<Movie>

}