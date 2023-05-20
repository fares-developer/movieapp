package com.example.movieapp.data.remote
import com.example.movieapp.data.model.Cast
import com.example.movieapp.data.model.Credits
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.model.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val apiKey = "8bf11ef65a5f84b75c57754fdd37baf7"

interface MovieApiService{
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") api_key:String = apiKey): Results

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String = apiKey): Results

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String = apiKey): Results

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String = apiKey): Results

    @GET("{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") idMovie: String = "238",
        @Query("api_key") api_key:String = apiKey,
    ):MovieModel

    @GET("{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") idMovie: String = "238",
        @Query("api_key") api_key:String = apiKey,
    ):Credits

}