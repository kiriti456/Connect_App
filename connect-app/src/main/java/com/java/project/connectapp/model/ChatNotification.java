package com.java.project.connectapp.model;

public class ChatNotification {
	private Long id;
	
	private Long senderId;

    private String receiverName;

	public ChatNotification(Long id, Long senderId, String receiverName) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverName = receiverName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}    
}
