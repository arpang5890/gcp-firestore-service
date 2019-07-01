package com.gitgub.arpang5890.dao;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class BaseDao {

	@Autowired
	protected Firestore firestoreDb;

	protected String collectionName;

	protected CollectionReference getCollection() {
		return this.firestoreDb.collection(collectionName);
	}

	protected WriteResult getWriteResult(ApiFuture<WriteResult> apiFuture) {
		try {
			return apiFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected DocumentSnapshot getDocumentSnapshot(ApiFuture<DocumentSnapshot> apiFuture) {
		try {
			return apiFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected QuerySnapshot getQuerySnapShot(ApiFuture<QuerySnapshot> apiFuture) {
		try {
			return apiFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
