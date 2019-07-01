package com.gitgub.arpang5890.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gitgub.arpang5890.dto.UserSearch;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;

@Repository
public class FireStoreUserDao extends BaseDao {

	private static final String COLLECTION_NAME = "user";

	public FireStoreUserDao() {
		this.collectionName = COLLECTION_NAME;
	}

	public String saveUser(final Map<String, Object> user) {
		DocumentReference docRef = getCollection().document();
		String newId = docRef.getId();
		WriteResult writeResult = getWriteResult(docRef.set(user));
		System.out.println("Document insert time : " + writeResult.getUpdateTime());
		return newId;
	}

	public void updateUser(final String userId, final Map<String, Object> user) {
		DocumentReference docRef = getCollection().document(userId);
		WriteResult writeResult = getWriteResult(docRef.update(user));
		System.out.println("Document update time : " + writeResult.getUpdateTime());
	}

	public void deleteUser(final String userId) {
		WriteResult writeResult = getWriteResult(getCollection().document(userId).delete());
		System.out.println("Document delete time : " + writeResult.getUpdateTime());
	}

	public User getUser(final String userId) {
		DocumentSnapshot documentSnapshot = getDocumentSnapshot(getCollection().document(userId).get());
		return getUser(documentSnapshot);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		List<QueryDocumentSnapshot> documentSnapshots = getQuerySnapShot(getCollection().get()).getDocuments();
		List<QueryDocumentSnapshot> documents = documentSnapshots;
		for (DocumentSnapshot document : documents) {
			users.add(getUser(document));
		}
		return users;
	}

	public List<User> searchUsers(final UserSearch userSearch) {
		List<User> users = new ArrayList<>();
		Query query = null;
		if (userSearch.getGender() != null) {
			query = getCollection().whereEqualTo("gender", userSearch.getGender().toString());
		}
		if (userSearch.getAge() != null && query != null) {
			query = query.whereEqualTo("age", userSearch.getAge());
		}
		if (userSearch.getAge() != null && query == null) {
			query = getCollection().whereEqualTo("age", userSearch.getAge());
		}
		for (DocumentSnapshot document : getQuerySnapShot(query.get()).getDocuments()) {
			users.add(getUser(document));
		}
		return users;
	}

	private User getUser(final DocumentSnapshot document) {
		User user = document.toObject(User.class);
		user.setId(document.getId());
		return user;
	}
}
