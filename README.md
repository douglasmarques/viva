# Vivant Test


## Description

This app was developed using Android Studio (2.2 Preview 4 - Canary Channel).
Target Android API is 23 and minimum is 19.

## Architecture

[The Model-View-Presenter pattern](https://codelabs.developers.google.com/codelabs/android-testing/#3)

The app was developed following the MVP pattern, separating the internal data model, from a passive view through a presenter that handles the logic of our application.

## Screens

The screens were developing using material design paterns and following the test specifications

**Intro:** 3 steps showing the most important features, it is possible to skip and go to login screen

**Login:** User and password to sign-in the app. There isn't a remote login service to authenticate the user, you need to use the following user:

- user: test@test.com
- pass: test1234

**PhotoList:** Photos retrieved from Vivant photo service. Using longClick is possible to save photos to gallery and a normal click goes to photo details.

**Photo Details:** Photo's name, photographer, likes and caption. Clicking on the heart is possible to un/favourite the photo.


## 3rd party Libraries

- [appintro](https://github.com/PaoloRotolo/AppIntro): AppIntro helps you make a intro(tutorial) for your app
- [butterknife](http://jakewharton.github.io/butterknife/): Easy way for binding(inject) views
- [retrofit 2](http://square.github.io/retrofit/): HTTP client for making request
- [picasso](http://square.github.io/picasso/): Library for loading and caching remote images
- [gson](https://github.com/google/gson): Gson is a Java library that can be used to convert Java Objects into their JSON representation