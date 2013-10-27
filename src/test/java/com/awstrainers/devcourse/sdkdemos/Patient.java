package com.awstrainers.devcourse.sdkdemos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "patients")
public class Patient implements java.io.Serializable  {
	private static final long serialVersionUID = 7336392782218661341L;


	private long id;
	private long timestamp;
	private String payload;
	
	public Patient(long id, long timestamp, String payload) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.payload = payload;
	}

	@DynamoDBHashKey
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@DynamoDBRangeKey
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

    @DynamoDBAttribute
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

	
	
}