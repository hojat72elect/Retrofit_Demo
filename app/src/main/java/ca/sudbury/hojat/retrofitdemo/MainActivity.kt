package ca.sudbury.hojat.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    // "retService" is an object which complies with AlbumsService interface.
    private lateinit var retService: AlbumsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumsService::class.java)
        getRequestWithQueryParameters()
        getRequestWithPathParameters()

    }

    private fun getRequestWithQueryParameters() {

        /*
       * Using a coroutine livedata builder, we can get the
       * retrofit response object as a livedata (LiveData is a
       * data holder class).
       * in this line, we create a LiveData object with the help of kotlin coroutines.
       * */
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3) // the query is userId=3
            emit(response) // The Response<Albums> will be added into LiveData object.
        }

        // And now, MainActivity will observe the LiveData object.
        responseLiveData.observe(this, Observer {
            // here we take out our needed info out of the retrofit response
            val albumsList = it.body()?.listIterator() // response body
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()// A single record of the list
                    Log.i("MYTAG", albumsItem.title) // logs the title of all albums.
                    val result =
                        " Album Title : ${albumsItem.title}\n Album ID : ${albumsItem.id}\n User ID : ${albumsItem.userId}\n\n\n"
                    text_View.append(result)
                    /*
                     * So, this is how you can access JSON data from a RESTFul API via Retrofit library.
                     */
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        // example of path parameter in retrofit:
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(56)
            emit(response)
        }

        // Now we're observing the LiveData above:
        // So in this example, the title of the response that we'll receive by
        // providing albumId=3 (in fact it means "id" is 3) as the path parameter
        // to API will be shown as a Toast message.
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, "The title of the album: $title", Toast.LENGTH_LONG).show()
        })

    }
}

