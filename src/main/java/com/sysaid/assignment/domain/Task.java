package com.sysaid.assignment.domain;

import java.io.Serializable;
import java.util.Objects;


/**
 * representing simple task
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	private String activity;
	private Float accessibility;
	private String type;
	private Integer participants;
	private Float price;
	private String link;
	private String key;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Float getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(Float accessibility) {
		this.accessibility = accessibility;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getParticipants() {
		return participants;
	}

	public void setParticipants(Integer participants) {
		this.participants = participants;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return Objects.equals(activity, task.activity) && Objects.equals(accessibility, task.accessibility) && Objects.equals(type, task.type) && Objects.equals(participants, task.participants) && Objects.equals(price, task.price) && Objects.equals(link, task.link) && Objects.equals(key, task.key);
	}

	@Override
	public int hashCode() {
		return Objects.hash(activity, accessibility, type, participants, price, link, key);
	}
}

