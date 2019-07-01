package com.gitgub.arpang5890.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Configuration
public class FireStoreConfig {

	@Value("${gcp.project.id}")
	private String projectId;

	@Bean
	public Firestore initFireStore() {
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(projectId).build();
		return firestoreOptions.getService();
	}
}
