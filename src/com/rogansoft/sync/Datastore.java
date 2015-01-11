package com.rogansoft.sync;

import java.util.List;

/***
 * Datastore interface used by SyncManager. A Datastore implementation should be created for
 * both the remote and local datastore.
 * 
 *  Local datastore - Should provide CRUD implementation to local data (e.g. SQLite)
 *  Remote datastore - Should provide CRUD implementation to remote data (e.g. api over HTTP)
 * 
 * @author SAS
 *
 * @param <T> - Syncable data type implementation
 */
public interface Datastore<T extends Syncable> {
	/***
	 * Return full list of syncable data.
	 * 
	 * @return list of syncable data
	 */
	List<T> get();
	
	/***
	 * Create new instance of syncable data. 
	 * 
	 * @return new instance of syncable data.
	 */
	T create();
	
	/***
	 * Add item to datastore.
	 * 
	 * @param item - syncable item to be added to datastore.
	 * @return Added syncable item 
	 */
	T add(T item);
	
	/***
	 * Update existing item in datastore
	 * 
	 * @param item - Syncable item to update
	 * @return Updated syncable item
	 */
	T update(T item);
}
