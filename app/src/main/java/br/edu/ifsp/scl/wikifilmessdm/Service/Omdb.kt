package br.edu.ifsp.scl.wikifilmessdm.Service

import br.edu.ifsp.scl.wikifilmessdm.Constants
import br.edu.ifsp.scl.wikifilmessdm.MainActivity
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Omdb(mainActivity: MainActivity) {

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    var callback: MovieCallback? = null

    // Instancia cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                // Requisição interceptada
                val reqIntercept: Request = chain.request()
                // Cria nova requisição com e adiciona o cabeçalho
                val url = reqIntercept.url().newBuilder().addQueryParameter("apikey", Constants.API_KEY_OMDB).build()
                val newReq: Request = reqIntercept.newBuilder()
                    .url(url)
                    .method(reqIntercept.method(), reqIntercept.body())
                    .build()

                return chain.proceed(newReq)
            }
        })
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.URL_BASE)
            .client(okHttpClientBuilder.build())
            .build()

    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val endpointsApi: EndpointsApi = retrofit.create(EndpointsApi::class.java)

    fun findMovie(title: String){

        endpointsApi.getFilmByTitle(title).enqueue(

            object : Callback<Movie>{
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    call.cancel()
                    callback?.onRequestFail(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val body = response.body()
                    if (body != null){

                        if (body.response.equals("False")){
                            callback?.onResponseFail(body)
                        }
                        else{
                            callback?.onResponse(body)
                        }
                    }
                }

            }
        )
    }

    fun searchListMovies(title: String){

    }

    interface MovieCallback{
        fun onResponse(obj: Movie)
        fun onResponseFail(obj: Movie)
        fun onRequestFail(err: Throwable)
    }
}