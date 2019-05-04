package br.edu.ifsp.scl.wikifilmessdm.Models

import com.google.gson.annotations.SerializedName

data class SearchMovies(
    @SerializedName("Search")
    var search: List<Search> = listOf(),
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("Response")
    var response: String = ""
)