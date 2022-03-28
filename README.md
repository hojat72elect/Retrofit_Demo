## Retrofit Demo

This Repository is my template for implementing RESTFul APIs interaction in the android app. There are 3 steps you need
to go through in order to add Retrofit to your project:
<br/><img alt="Retrofit integration steps" src="doc_assets/3 steps of using Retrofit in android project.png" width="60%" height="60%"><br/>

----------------------------------

The first step for adding Retrofit to your project is to copy the line below and paste it into the "dependencies"
section of the gradle file at "app/build.gradle".👇👇👇

<img alt="Gradle dependency for Retrofit library" src="doc_assets/Retrofit Gradle dependency.png" width="60%" height="60%">

The compiler will prompt you to change the version to the latest stable version automatically.

----------------------------------

### JSON/XML converter libraries

Retrofit on its own, can only send and receive HTTP request/response bodies in the form of OkHttp objects. So, you as
the library's user can use any of the converters below for converting the response body (which is usually a JSON or XML
file) into data objects identifiable by kotlin/java 👇👇👇

<img alt="Converters compatible with Retrofit" src="doc_assets/converters.png" width="80%" height="80%">

In this project I have used <a href="https://github.com/google/gson">Gson</a> converter (but Moshi is currently the most
famous library for doing so).

----------------------------------
After adding the dependency for Gson, the dependency section of "build.gradle" file will be like this:

```
// Retrofit dependency
implementation 'com.squareup.retrofit2:retrofit:2.9.0'

// Gson dependency
implementation 'com.google.code.gson:gson:2.9.0'
```

----------------------------------

### Kotlin Coroutines

<a href="https://github.com/Kotlin/kotlinx.coroutines">Kotlin coroutines</a> make multithreading and asynchronous
programming in Android world a lot easier. In this project, I have used kotlin coroutines for handling network
interactions asynchronously.  
Add the dependency for kotlin coroutines to your "app/build.gradle" file (the latest stable version might be different
when you implement this project).<br/><br/>And remember, since it's an android app, we need 2 dependencies; one
for <a href="https://github.com/Kotlin/kotlinx.coroutines">kotlin coroutines core library</a> and another dependency
for <a href="https://github.com/Kotlin/kotlinx.coroutines/tree/master/ui/kotlinx-coroutines-android">
kotlinx-coroutines-android</a> module which provides required context for using kotlin coroutines in an Android app
👇👇👇

```
    // kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    
    // module for using coroutines in Android context
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
```

----------------------------------

### MVVM architecture

Separation of Concerns (SoC) is all about dividing a software system into smaller modules, each one of these modules is
responsible for a single concern.

In this app, the flow of data between back-end (web) and front-end (all the activities and fragments of the app) is
abstracted into its own independent module named ViewModel. Consequently, the whole app is laid out in
Model-View-ViewModel (MVVM) architecture. <br><br> In the Android world, the
libraries <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a>
and <a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a> are used for performing
the ViewModel layer and its connection with View layer of the app.<br/></br> Add these dependencies to your gradle
scripts:

```
// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
// LiveData
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
```

----------------------------------

### Annotation processing:

Annotation processing allows you to generate code at compile time based on the annotations in your source code. It's
usually used to get rid of repetitive boilerplate code (and Retrofit makes extensive use of annotations for controlling
the network connection).<br/><br/>Add this dependency to your Gradle script to use annotation processor for your
Retrofit library 👇👇👇

```
// Annotation processor
implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
```

And at the end, the "plugins" section of the app level build,gradle file will look like this:

```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-android-extensions'
    id 'kotlin-kapt'
}
```

----------------------------------

### Logging Interceptor:

We use <a href="https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor">OkHttp-logging-interceptor</a>
for logging the body of all HTTP requests and responses of our app. In order to add this library to our project, add
this dependency (the latest stable version might be different from what came here):

```
implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
```

----------------------------------

### User Permissions:

Since this app will connect to internet, you need to add permissions to AndroidManifest.xml

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

----------------------------------

### web service provider:

Of course you will be using this template project for accessing to your own web services that you have as the back-end
of your app; but in this example project, I'm going to connect to the web API proveded
by <a href="https://jsonplaceholder.typicode.com/">JSONPlaceholder</a>.<br/>
The JSON file that I will be accessing and parsing in this project is accessible
in <a href="https://jsonplaceholder.typicode.com/albums">here</a>.

----------------------------------

### Convert JSON to kotlin data class:

The easiest way for converting the JSON data (received through internet communication) into kotlin objects is to
install "JSON to kotlin class" plugin for Android Studio/IntelliJ IDEA. The icon of this plugin looks like this:
<br/>
<img alt="JSON to kotlin class plugin" src="doc_assets/JSON to kotlin plugin.png" width="40" height="40">
<br/><br/>
After installing this plugin, you just need to copy your favorite JSON info and in your IDE right click in the package
and go to "New/kotlin data class from JSON". give a name to yout class and you will have 2 data classes which will
represent this JSON file in your app project.
----------------------------------

#### Some additional notes:

* URL management<br/>
  Please remember that when you're manipulating various endpoints from a single base URL, it's better to treat those
  URLs like this:
  <br/><img alt="URL management" src="doc_assets/URL management.png" width="90%" height="90%">

* Retrofit library was created on top of okHTTP library and uses its classes to perform network operations. HTTP logging
  interceptor is a feature which belongs to okHTTP library which logs all the network operations happened in our app.
  This is extremely useful for debugging purposes.

* Configure Timeouts:
  Imagine an example android app of a product delivery company. Sales reps would use it to get orders from different
  shop owners of all areas in a country. This app is supposed to communicate with a REST API that provides real time
  updates; so the app has to frequently download data and display it using a RecyclerView. You might test the app inside
  the company with high-speed internet there; but when users in all over the country (or world) use it, they probably
  don't have high-quality network connection.

In such situations that a bad quality of internet connection might cause the app not downloading all of its needed data,
you might be able to partially solve the issue by increasing the "connection timeout" and "read timeout" of the Retrofit
instance.

* GET and POST:

GET and POST are the most common HTTP methods used in a client app.<br/><br/>
**GET** requests are to request some data from server; we never use GET request to create, delete, or update data (only for reading).<br/>The data received by GET request can be cached if the client requests them to remain in the browser history. But never do that with sensitive data because it will be a breach of user's privacy.

**POST** requests are used to send data to the server and create new records in server's database. 

----------------------------------

**That's it! now you have anything you need for going through this project 👏👏 You can read the source code for
understanding what's going on 🤓<br/>Don't worry! All the code files are heavily documented! You will enjoy reading the
source code of this project; I promise....😁😁**