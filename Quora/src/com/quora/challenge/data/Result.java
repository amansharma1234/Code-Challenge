package com.quora.challenge.data;

/**
 * Result saves the distance along with id 
 * implements Comparable to sort according to distance and
 * then with the id
 * @author amansharma
 *
 */
public class Result implements Comparable<Result> {

	private double distance;
	private int id;
	
	public Result(double distance, int integer) {
		this.distance = distance;
		this.id = integer;
	}
	
	@Override
	public int compareTo(Result o) {
		if(distance - o.distance > 0) {
			return 1;
		} else if(distance - o.distance < 0) {
			return -1;
		} else {
			return o.id - this.id;
		}
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Result [distance=" + distance + ", id=" + id + "]";
	}

	
	
}
