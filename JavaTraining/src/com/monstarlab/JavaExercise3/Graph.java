package com.monstarlab.JavaExercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {


	List<Vertex> vertices;
	HashMap<Edge, Integer> edges;
	
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new HashMap<Edge, Integer>();
	}

	public Graph(List<Vertex> vertices, HashMap<Edge, Integer> edges) {
		vertices = vertices;
		edges = edges;
	}

	public HashMap<Edge, Integer> getEdges() {
		return edges;
	}

	public void setEdges(HashMap<Edge, Integer> edges) {
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public Boolean addEdge(Edge edge) {
		if (isExist(edge)) {
			return false;
		}

		// int key = 0;
		//
		// try {
		// key = KeyGenerator.getInstance("BlowFish").generateKey().hashCode();
		// } catch (NoSuchAlgorithmException e) {
		// // TODO Auto-generated catch block
		// System.out.println("No Such Algorithm Exception");
		// return false;
		// }

		getEdges().put(edge, edge.getDistance());

		// populate the neighbors

		edge.getFirstVertex().addNeighbor(edge);
		edge.getSecondVertex().addNeighbor(edge);

		return true;
	}

	public void printEdges() {

		System.out.println("THE EDGES ARE:");
		for (Map.Entry<Edge, Integer> entry : edges.entrySet()) {
			Edge key = entry.getKey();

			System.out.println(key.toString());

		}

	}

	public void addVertex(Vertex vertex) {

		if (!vertices.contains(vertex.getVertexNumber())) {
			getVertices().add(vertex);
		}

	}

	// TODO avoid using this inefficient code
	public void removeDuplicateVertices() {
		for (Vertex vertex : getVertices()) {

			for (Vertex vertex2 : getVertices()) {

				if (vertex2.getVertexNumber().equals(vertex.getVertexNumber())) {
					getVertices().remove(vertex);
				}

			}

		}

	}

	public void printVertices() {
		System.out.println("THE VERTICES ARE:");
		for (Vertex vertex : getVertices()) {
			System.out.println(vertex.toString());

		}
	}

	public void findShortestPath(Vertex originVertex, Vertex destinationVertex) {

		findValidPaths(originVertex, destinationVertex);

		// TODO determineShortestPath();

	}

	public void findValidPaths(Vertex originVertex, Vertex destinationVertex) {

		Path path = new Path(originVertex, destinationVertex);

		for (Map.Entry<Edge, Integer> entry : getEdges().entrySet()) {

			Edge edge = entry.getKey();

			// for each each and its neighbors

			System.out.println("findValidPaths method");
			for (Vertex vertex : getVertices()) {
				System.out.println(vertex.toString());
			}

			Integer originVertexNumber = originVertex.getVertexNumber();
			Integer destinationVertexNumber = destinationVertex.getVertexNumber();

			Integer firstVertexNumber = edge.getFirstVertex().getVertexNumber();
			Integer secondVertexNumber = edge.getSecondVertex().getVertexNumber();

			if (originVertexNumber.equals(firstVertexNumber) && destinationVertexNumber.equals(secondVertexNumber)) {
				path.getEdges().add(edge);
			}
		}

	}

	private Boolean isExist(Edge lane) {

		for (Map.Entry<Edge, Integer> entry : edges.entrySet()) {
			Edge key = entry.getKey();
			Integer value = entry.getValue();

			if ((key.getFirstVertex().getVertexNumber().equals(lane.getFirstVertex().getVertexNumber()))
					&& (key.getSecondVertex().getVertexNumber().equals(lane.getSecondVertex().getVertexNumber()))) {
				return true;
			}

			continue;

		}

		return false;
	}

	public void populateNeighborEdges() {

		for (Vertex vertex : getVertices()) {

			Integer current = vertex.getVertexNumber();

			for (Map.Entry<Edge, Integer> entry : getEdges().entrySet()) {
				Edge edge = entry.getKey();

				Integer source = edge.getFirstVertex().getVertexNumber();

				if (current.intValue() == source.intValue()) {

					vertex.neighborEdges.add(edge);
					continue;
				}

			}

		}

	}

	public void printNeighborEdges() {
		System.out.println("THE NEIGHBOR EDGES ARE (NOTE TWO-WAY CAN BE SEEN):");
		for (Vertex vertex : getVertices()) {
			System.out.println(vertex.getVertexNumber() + " " + vertex.getNeighborEdges().toString());
		}
	}

}
