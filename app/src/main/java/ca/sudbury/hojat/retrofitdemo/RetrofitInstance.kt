package ca.sudbury.hojat.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Hojat Ghasemi,
 * 2022-03-24
 * https://github.com/hojat72elect
 */
class RetrofitInstance {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        /**
         * This is an Http logging interceptor.
         *
         * we first create an object of type "HttpLoggingInterceptor", then add it as
         * an interceptor to an object of type "OkHttpClient" and finally, add that
         * OkHttpClient as the client to our "Retrofit.Builder" class. Doing this,
         * we'll have a logger for all internet connections of our app.
         *
         * In your logcat search for "okHttp" and you'll see those network logs (if the
         * logger level is on BODY or something like that, you will be able to see all
         * the HTTP method calls- such as GET, Post and so on). It even logs the result of our queries to the API.
         */
        val interceptor = HttpLoggingInterceptor().apply {
            this.level =
                HttpLoggingInterceptor.Level.BODY // The level of logging is request and response lines and their respective headers and bodies.
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor) // adding a logging interceptor to the builder class for our OkHttpClient.
                // the time that app has to establish a connection with the server (default is 10 seconds).
                .connectTimeout(30, TimeUnit.SECONDS)
                // maximum time gap between arrivals of 2 data packets that we wait
                .readTimeout(20, TimeUnit.SECONDS)
                // maximum time gap between 2 data packets we're sending to the server
                .writeTimeout(25, TimeUnit.SECONDS)
            /*
            * In real life examples you need to decide the amount of the timeouts according to
            * factors such as "Internet speed", "performance of the server" and "performance of
            * the device".
            */
        }.build()

        /**
         * This function returns an object of type "Retrofit" which will be used for
         * controlling all Retrofit interactions in our app.
         */
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // Setting the HTTP client used for requests.
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}