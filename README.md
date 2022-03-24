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

Annotation processing allows you to generate code at compile time based on the annotations in your source code. It's usually used to get rid of repetitive boilerplate code (and Retrofit makes extensive use of annotations for controlling the network connection).<br/><br/>Add this dependency to your Gradle script to use annotation processor for your Retrofit library 👇👇👇

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
