package com.quora.challenge.data;
import java.util.HashSet;

/**
 * Denotes a question with numberOfTopics tagged and topicIds
 * @author amansharma
 *
 */
public class Question {

	private int numberOfTopics;
	private HashSet<Integer> topicsTagged = new HashSet<>();

	public Question(int n, HashSet<Integer> set) {
		numberOfTopics = n;
		topicsTagged = set;
	}
	
	public int getNumberOfTopics() {
		return numberOfTopics;
	}

	public void setNumberOfTopics(int numberOfTopics) {
		this.numberOfTopics = numberOfTopics;
	}

	public HashSet<Integer> getTopicsTagged() {
		return topicsTagged;
	}

	public void setTopicsTagged(HashSet<Integer> topicsTagged) {
		this.topicsTagged = topicsTagged;
	}

	@Override
	public String toString() {
		return "Question [numberOfTopics=" + numberOfTopics + ", topicsTagged="
				+ topicsTagged + "]";
	}
	
	

}
