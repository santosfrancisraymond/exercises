package com.monstarlab.JavaExercise3;

public class Edge {

	String name;

	Vertex firstVertex;

	Vertex secondVertex;

	Integer distance;

	public Edge(Vertex vertex1, Vertex vertex2, Integer distance) {
		this.name = null;
		this.firstVertex = vertex1;
		this.secondVertex = vertex2;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vertex getFirstVertex() {
		return firstVertex;
	}

	public void setFirstVertex(Vertex firstVertex) {
		this.firstVertex = firstVertex;
	}

	public Vertex getSecondVertex() {
		return secondVertex;
	}

	public void setSecondVertex(Vertex secondVertex) {
		this.secondVertex = secondVertex;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Vertex getNeighbor(Vertex current) {

		if (!(current.equals(getFirstVertex()) || current.equals(getSecondVertex()))) {
			return null;
		}
		return (current.equals(getFirstVertex())) ? getSecondVertex() : getFirstVertex();
	}

	public String toString() {
		return this.firstVertex.getVertexNumber() + " " + this.secondVertex.getVertexNumber() + " "
				+ this.getDistance();
	}
}
