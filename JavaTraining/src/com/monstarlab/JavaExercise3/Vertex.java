package com.monstarlab.JavaExercise3;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	Integer vertexNumber;

	List<Edge> neighborEdges;

	public Vertex(Integer vertexNumber) {
		this.vertexNumber = vertexNumber;
		this.neighborEdges = new ArrayList<Edge>();
	}

	public Integer getVertexNumber() {
		return vertexNumber;
	}

	public void setVertexNumber(Integer vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	public List<Edge> getNeighborEdges() {
		return neighborEdges;
	}

	public void setNeighborEdges(List<Edge> neighborEdges) {
		this.neighborEdges = neighborEdges;
	}

	public void addNeighbor(Edge edge) {

		if (getNeighborEdges().contains(edge)) {
			return;
		}

		getNeighborEdges().add(edge);

	}

	public String toString() {
		return this.vertexNumber.toString();
	}

}
