package ca.sudbury.hojat.retrofitdemo

import retrofit2.Response
import retrofit2.http.*

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

    /**
     * This function will filter the returned albums according to the
     * query parameter provided to it.
     *
     * Query Parameters:
     * In some situations, we need to get sorted data from a RESTFul API.
     * For example, imagine that you want to display all the albums that
     * belong to the user ID 3. In order to do so, we use an HTTP GET request
     *  with Query Parameters. This url will do so:
     * "https://jsonplaceholder.typicode.com/albums?userId=3"
     */
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    /**
     * Retrofit Path parameters:
     * we define path parameters as named replacements in the URL, and
     * they depend on API implementation. For example, the API that
     * I'm using in this project allows us to access "id" column of
     * JSON file as a path parameter. "https://jsonplaceholder.typicode.com/albums/5"
     * "id" is defined as a http path in this API.
     * Pay attention that a path parameter is added in the middle of {}
     */
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>

    /**
     * This function is defining a POST request to the server (the function is annotated with "POST" and API's endpoint for updating albums).
     * The input "AlbumsItem" object will be sent as the body of a POST request.
     */
    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem): Response<AlbumsItem>

}