package com.quora.challenge.data;
import java.awt.geom.Point2D;

/**
 * Denotes a query with number of results and co-ordinates
 * @author amansharma
 *
 */
public class Query {

	private String type;
	private int numResults;
	private Point2D point;
	
	public Query(String type,int results, double x, double y) {
		this.type = type;
		numResults = results;
		point = new Point2D.Double(x, y);
	}
	
	
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Double calculateDistance(Point2D point) {
		return this.point.distance(point.getX(), point.getY());
	} 

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}



	@Override
	public String toString() {
		return "Query [type=" + type + ", numResults=" + numResults
				+ ", point=" + point + "]";
	}

	
	
	
}
