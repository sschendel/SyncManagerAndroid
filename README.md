SyncManager 2-way sync library for Android
===========================================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SyncManagerAndroid-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1353)

Simple 2-way sync implementation to plug into your implementation of [AbstractThreadedSyncAdapter.OnPerformSync][android-atsa].

See [Transferring Data Using Sync Adapters][android-sync] for more info on Android sync framework integration.

There are a handful of tutorials to help you plug into the Android Sync framework, but they usually leave you with an 
empty OnPerformSync implementation, and leave those details up to you.

This library provides a simple 2-way sync implementation to plug into your Android Sync framework integration.

[Javadoc][javadoc]

See full source demo Google Tasks list Android app: [SyncManagerAndroid-DemoGoogleTasks][demo]


[android-sync]: http://developer.android.com/training/sync-adapters/index.html
[android-atsa]: http://developer.android.com/reference/android/content/AbstractThreadedSyncAdapter.html
[javadoc]: http://sschendel.github.io/SyncManagerAndroid/doc/index.html
[demo]: https://github.com/sschendel/SyncManagerAndroid-DemoGoogleTasks
