package ca.sudbury.hojat.retrofitdemo

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Hojat Ghasemi,
 * 2022-03-24
 * https://github.com/hojat72elect
 */
class RetrofitInstance {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // This function returns an object of type "Retrofit"
        // which will be used for controlling all Retrofit
        // interactions in our app.
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}