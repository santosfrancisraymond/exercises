package com.monstarlab.JavaExercise3;

import java.util.ArrayList;
import java.util.List;

public class Path {

	Vertex originVertex;

	Vertex destinationVertex;

	Integer totalDistance;

	List<Edge> edges;

	public Path(Vertex originVertex, Vertex destinationVertex) {
		this.edges = new ArrayList<Edge>();
		this.originVertex = originVertex;
		this.destinationVertex = destinationVertex;
		this.totalDistance = new Integer(0);
	}

	public Vertex getOriginVertex() {
		return originVertex;
	}

	public void setOriginVertex(Vertex originVertex) {
		this.originVertex = originVertex;
	}

	public Vertex getDestinationVertex() {
		return destinationVertex;
	}

	public void setDestinationVertex(Vertex destinationVertex) {
		this.destinationVertex = destinationVertex;
	}

	public Integer getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Integer totalDistance) {
		this.totalDistance = totalDistance;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public Boolean addEdge(Edge edge) {

		// getEdges().put(edge, new Integer(key));

		return true;
	}

}
