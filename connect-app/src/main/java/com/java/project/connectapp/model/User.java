package com.java.project.connectapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    @JsonIgnore
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    @JsonManagedReference
    @JsonIgnore
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private List<Posts> posts;

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    @JsonIgnore
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver")
    @JsonManagedReference
    @JsonIgnore
    private List<FriendRequest> receivedFriendRequests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	public List<FriendRequest> getSentFriendRequests() {
		return sentFriendRequests;
	}

	public void setSentFriendRequests(List<FriendRequest> sentFriendRequests) {
		this.sentFriendRequests = sentFriendRequests;
	}

	public List<FriendRequest> getReceivedFriendRequests() {
		return receivedFriendRequests;
	}

	public void setReceivedFriendRequests(List<FriendRequest> receivedFriendRequests) {
		this.receivedFriendRequests = receivedFriendRequests;
	}
}
