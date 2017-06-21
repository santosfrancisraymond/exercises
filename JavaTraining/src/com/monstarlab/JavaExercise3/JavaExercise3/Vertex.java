package com.monstarlab.JavaExercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vertex {

	Integer vertexNumber;

	List<Edge> neighborEdges;

	Map<Vertex, Integer> adjacentVertices;

	List<Vertex> shortestPath;

	Integer distance;

	public Vertex(Integer vertexNumber) {
		this.vertexNumber = vertexNumber;
		this.neighborEdges = new ArrayList<Edge>();
		this.adjacentVertices = new HashMap<>();
		this.shortestPath = new LinkedList<>();
		this.distance = Integer.MAX_VALUE;
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

	public Map<Vertex, Integer> getAdjacentVertices() {
		return adjacentVertices;
	}

	public void setAdjacentVertices(Map<Vertex, Integer> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public List<Vertex> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Vertex> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public void addDestination(Vertex destination, Integer distance) {
		getAdjacentVertices().put(destination, distance);
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
