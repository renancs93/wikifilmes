package br.edu.ifsp.scl.wikifilmessdm.Models

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("Response")
    var response: String = "",
    @SerializedName("Error")
    var error: String = ""
)