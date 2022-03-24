package ca.sudbury.hojat.retrofitdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "retService" is a nullable object which complies with AlbumsService interface.
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumsService::class.java)
        /*
        * Using a coroutine livedata builder, we can get the
        * retrofit response object as a livedata (LiveData is a
        * data holder class).
        * in this line, we create a LiveData object with the help of kotlin coroutines.
        * */
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response) // The Response<Albums> will be added into LiveData object.
        }

        // And now, MainActivity will observe the LiveData object.
        responseLiveData.observe(this, Observer {
            // here we take out our needed info out of the retrofit response
            val albumsList = it.body()?.listIterator() // response body
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()// A single record of the list
                    Log.i("MYTAG", albumsItem.title)
                }
            }
        })

    }
}