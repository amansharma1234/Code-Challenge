package com.quora.challenge.runner;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import com.quora.challenge.data.Query;
import com.quora.challenge.data.Question;
import com.quora.challenge.data.Result;

public class QueryParser {

	/* Map for all topics and their co-ordinates */
	private HashMap<Integer, Point2D> topicsMap = new HashMap<Integer, Point2D>();
	
	/* Map for all the questions */
	private HashMap<Integer, Question> questionsMap = new HashMap<Integer, Question>();
	
	/* Query List */
	private List<Query> queries = new ArrayList<Query>();
	
	/* Map having all topics and there distances from the given question co-ordinate */
	private HashMap<Integer, Double> topicDistanceMap = new HashMap<Integer, Double>();

	/* process query based on type */
	private void processQueries() {
		for (Query query : queries) {
			if (query.getType().equals("t")) {
				processTopicsQuery(query);
			} else if (query.getType().equals("q")) {
				processQuestionsQuery(query);
			}
		}

	}
	
	/* Get info from the input */
	public void getInfoFromInput(List<String> lines) {
		String firstLine = lines.get(0);
		String[] strs = firstLine.split(" ");

		int numberOfTopics = Integer.parseInt(strs[0]);
		int numberOfQuestions = Integer.parseInt(strs[1]);
		int numberOfQueries = Integer.parseInt(strs[2]);

		for (int i = 1; i < lines.size(); i++) {
			String[] params = lines.get(i).split(" ");
			if (numberOfTopics != 0) {
				topicsMap.put(Integer.parseInt(params[0]),
						new Point2D.Double(Double.parseDouble(params[1]),
								Double.parseDouble(params[2])));
				numberOfTopics--;
			} else if (numberOfQuestions != 0) {
				getQuestions(params);
				numberOfQuestions--;
			} else if (numberOfQueries != 0) {
				queries.add(new Query(params[0], Integer.parseInt(params[1]),
						Double.parseDouble(params[2]), Double
								.parseDouble(params[3])));
				numberOfQueries--;
			}
		}
		processQueries();
	}

	/* process topics query */
	private void processTopicsQuery(Query query) {
		Result[] topicResults = new Result[topicsMap.size()];
		int i = 0;
		for (Entry<Integer, Point2D> topic : topicsMap.entrySet()) {
			double distance = query.calculateDistance(topic.getValue());
			topicResults[i] = new Result(distance, topic.getKey());
			i++;
		}

		sortAndDisplayResult(topicResults, query.getNumResults());
	}

	/* process questions query */
	private void processQuestionsQuery(Query query) {
		Result[] questionsResults = new Result[questionsMap.size()];

		// traverses to questionsMap to generate distance of all topics tagged
		for (Entry<Integer, Question> question : questionsMap.entrySet()) {
			if (question.getValue().getNumberOfTopics() > 0) {
				HashSet<Integer> topicsTagged = question.getValue()
						.getTopicsTagged();
				calculateDistanceForQuestions(topicsTagged, query);
			}
		}

		int i = 0;

		// traverses questionsMap again to set minimum distance in the array questionsResults
		for (Entry<Integer, Question> question : questionsMap.entrySet()) {
			if (question.getValue().getNumberOfTopics() > 0) {
				HashSet<Integer> topicsTagged = question.getValue()
						.getTopicsTagged();
				double dist = getMinimumDistanceTagged(topicsTagged);
				questionsResults[i] = new Result(dist, question.getKey());
				i++;
			}
		}
		
		sortAndDisplayResult(questionsResults, query.getNumResults());
	}
 
	/* Sorts and diplays the result */
	private void sortAndDisplayResult(Result[] result,int n) {
		Arrays.sort(result);
		n = (n > result.length) ? result.length : n;
		for (int i = 0; i < n; i++) {
			System.out.print(result[i].getId() + " ");
		}
		System.out.println();
	}
	
	/* returns distance of the topic which has minimum value in the set */
	private double getMinimumDistanceTagged(HashSet<Integer> setOfTopicsTagged) {
		double distance = Integer.MAX_VALUE;
		for (Integer topicTagged : setOfTopicsTagged) {
			if (topicDistanceMap.containsKey(topicTagged)) {
				double dist = topicDistanceMap.get(topicTagged);
				if (distance > dist) {
					distance = dist;
				}
			}
		}
		return distance;
	}

	/* calculates the distance of tagged topics and puts them in the map */
	private void calculateDistanceForQuestions(HashSet<Integer> topicsTagged,
			Query query) {

		for (Integer topicTagged : topicsTagged) {
			Point2D point = topicsMap.get(topicTagged);
			double dist = query.calculateDistance(point);
			topicDistanceMap.put(topicTagged, dist);
		}
	}



	/* get all the questions */
	private void getQuestions(String[] params) {
		HashSet<Integer> setOfTaggedTopics = new HashSet<>();
		for (int i = 2; i < params.length; i++) {
			setOfTaggedTopics.add(Integer.parseInt(params[i]));
		}

		if (Integer.parseInt(params[1]) > 0) {
			questionsMap
					.put(Integer.parseInt(params[0]),
							new Question(Integer.parseInt(params[1]),
									setOfTaggedTopics));
		}
	}

}
