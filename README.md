SyncManager 2-way sync library for Android
===========================================

Simple 2-way sync implementation to plug into your implementation of [AbstractThreadedSyncAdapter.OnPerformSync][android-atsa].

See [Transferring Data Using Sync Adapters][android-sync] for more info on Android sync framework integration.

There are a handful of tutorials to help you plug into the Android Sync framework, but they usually leave you with an 
empty OnPerformSync implementation, and leave those details up to you.

This library provides a simple 2-way sync implementation to plug into your Android Sync framework integration.

Sync
===========================================
``` python
def python(some):
        pass
```

Using SyncManager
===========================================


1. Link to sync manager Android library project
2. Implement IDatastore for both Local and Remote data sources
3. Implement ISyncable for synced data type
4. 


Sync
===========================================


[android-sync]: http://developer.android.com/training/sync-adapters/index.html
[android-atsa]: http://developer.android.com/reference/android/content/AbstractThreadedSyncAdapter.html