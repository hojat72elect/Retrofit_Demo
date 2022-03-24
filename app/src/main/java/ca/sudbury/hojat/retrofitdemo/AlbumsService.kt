package ca.sudbury.hojat.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Hojat Ghasemi,
 * 2022-03-24
 * https://github.com/hojat72elect
 *
 * We use this interface for defining the whole internet connections of our app. For example, the URL endpoints that our app will connect to, are defined in this interface.
 */
interface AlbumsService {
    /**
     * This function will return a retrofit response object of type "Albums". The keyword "suspend" is meant to use kotlin coroutines with Retrofit in this project. If you're not interested in using kotlin coroutines, you can remove the "suspend" modifier.
     * In fact, this function will perform a HTTP Get request.
     * Please pay attention that the GET request in this function only uses the end point of our URL.
     */
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>
}