package com.monstarlab.JavaExercise_3;

import java.util.Scanner;

public class ShortestDistanceTest {

	private final static String LOG_INVALID_EDGE = "INVALID - Duplicate edge. Try again.";
	private final static String LOG_VALID_EDGE = "VALID - Edge added. Proceed.";
	private final static String LOG_PROGRAM_END = "FYI - Program End";
	private final static String LOG_SHORTEST_PATH = "The shortest path is ";

	private final static String QUERY_STOP = "N";
	private final static String QUERY_REPLY = "Add an edge. V V D format. (N) to exit : ";
	private final static String QUERY_REPLY_SEPARATOR = " ";
	private final static String QUERY_PATH = "What vertices to get shortest distance? V V format. (N) to exit: ";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String input = null;
		Boolean done = false;

		Graph graph = new Graph();

		while (!done) {
			System.out.println(QUERY_REPLY);
			input = sc.nextLine();
			switch (input) {
			case QUERY_STOP:
				done = true;
				break;

			default:

				// TODO validate the input
				String[] splitInput = input.split(QUERY_REPLY_SEPARATOR);
				Vertex vertex1 = new Vertex(Integer.parseInt(splitInput[0]));
				Vertex vertex2 = new Vertex(Integer.parseInt(splitInput[1]));
				Integer distance = new Integer(splitInput[2]);

				Edge edge = new Edge(vertex1, vertex2, distance);

				graph.addVertex(vertex1);
				graph.addVertex(vertex2);

				Boolean isGoodToAddEdge = false;

				while (!isGoodToAddEdge) {
					isGoodToAddEdge = graph.addEdge(edge);

					if (!isGoodToAddEdge) {
						System.out.println(LOG_INVALID_EDGE);
						break;
					} else {
						System.out.println(LOG_VALID_EDGE);
					}
				}

				done = false;
				continue;
			}

		}

		graph.printEdges();

		graph.printNeighborEdges();

		input = null;
		done = false;

		while (!done) {
			System.out.println(QUERY_PATH);
			input = sc.nextLine();
			switch (input) {
			case QUERY_STOP:
				System.out.println(LOG_PROGRAM_END);
				done = true;
				break;
			default:

				// TODO validate the input
				String[] splitInput = input.split(QUERY_REPLY_SEPARATOR);
				Vertex originVertex = new Vertex(Integer.parseInt(splitInput[0]));
				Vertex destinationVertex = new Vertex(Integer.parseInt(splitInput[1]));

				System.out.println(LOG_SHORTEST_PATH);

				graph.getShortestPath(originVertex, destinationVertex);

				sc.close();

				done = true;
				break;
			}
		}

		
	}

}
