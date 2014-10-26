package com.rogansoft.sync;

import java.util.List;

import android.util.Log;

/***
 * Manages 2-way synchronization of local and remote datastores.
 * 
 * @author SAS
 *
 * @param <T> - ISyncable data type
 */
public class SyncManager<T extends ISyncable> {
	private static final String TAG = "SyncManager";

	private IDatastore<T> mLocalStore;
	private IDatastore<T> mRemoteStore;

	
	private T findRemoteItemInLocalData(T remoteItem, List<T> localData) {
		T  result = null;
		for(T localItem : localData) {
			String localItemRemoteId = localItem.getRemoteId();
			if (localItemRemoteId != null) {
				if(localItemRemoteId.equals(remoteItem.getRemoteId())) {
					result = localItem;
					break;
				}
			}
		}
		return result;
	}

	private T findLocalItemInRemoteData(T localItem, List<T> remoteData) {
		T result = null;
		for(T remoteItem : remoteData) {
			//Log.d(TAG, serverItem.toString()+" =? "+localItem.toString());
			String remoteItemRemoteId = remoteItem.getRemoteId();
			if (remoteItemRemoteId != null) {
				if(remoteItemRemoteId.equals(localItem.getRemoteId())) {
					result = remoteItem;
					break;
				}
			}
		}
		return result;
	}	
	
	
	private void syncItem(T localItem, T remoteItem) {
		Log.d(TAG, "syncItem...");
		Log.d(TAG, "local seq:"+localItem.getLastUpdatedSequence()+" remote seq:"+remoteItem.getLastUpdatedSequence());
		if (remoteItem.getLastUpdatedSequence() > localItem.getLastUpdatedSequence()) {
			// update local data
			localItem.mapFromRemote(remoteItem);
			localItem.setLastUpdatedSequence(remoteItem.getLastUpdatedSequence());
			localItem = mLocalStore.update(localItem);
		} else if (remoteItem.getLastUpdatedSequence() < localItem.getLastUpdatedSequence()) {
			// update remote data
			remoteItem.mapFromLocal(localItem);
			remoteItem.setLastUpdatedSequence(localItem.getLastUpdatedSequence());
			remoteItem = mRemoteStore.update(remoteItem);
		} else {
			Log.d(TAG, "syncItem...no update to apply...synced already.");
		}
		
	}
	
	private void pull() {
		Log.d(TAG, "pull...");
		List<T> localData = mLocalStore.get();
		List<T> remoteData = mRemoteStore.get();
		
		for(T remoteItem : remoteData) {
			Log.d(TAG, "check remote item: "+remoteItem.toString());
			T localItem = findRemoteItemInLocalData(remoteItem, localData);
			if (localItem == null) {
				// new item from server
				localItem = mLocalStore.create();
				localItem.mapFromRemote(remoteItem);
				Log.d(TAG, "no local found. adding: "+localItem.toString());
				localItem = mLocalStore.add(localItem);
			} else {
				syncItem(localItem, remoteItem);
			}
		}
	}

	private void push() {
		Log.d(TAG, "push...");
		List<T> localData = mLocalStore.get();
		List<T> remoteData = mRemoteStore.get();

		for(T localItem : localData) {
			Log.d(TAG, "check local item: "+localItem.toString());
			
			T remoteItem = findLocalItemInRemoteData(localItem, remoteData);
			if (remoteItem == null) {
				remoteItem = mRemoteStore.create();
				remoteItem.mapFromLocal(localItem);
				remoteItem = mRemoteStore.add(remoteItem);
				Log.d(TAG,"newly added remoteitem:"+remoteItem.toString());
				localItem.setRemoteId(remoteItem.getRemoteId());
				localItem.setLastUpdatedSequence(remoteItem.getLastUpdatedSequence());
				mLocalStore.update(localItem);
			} else {
				syncItem(localItem, remoteItem);
			}
		}
		
	}

	/***
	 * Initiate 2-way sync.
	 */
	public void sync() {
		pull();
		push();
	}


	/***
	 * 
	 * @param localStore - Interface to local datastore
	 * @param remoteStore - Interface to remote datastore
	 */
	public SyncManager(IDatastore<T> localStore, IDatastore<T> remoteStore) {
		mLocalStore = localStore;
		mRemoteStore = remoteStore;
	}
	
}
