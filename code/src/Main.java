import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import graph.Graph;

public class Main {

	public static void main(String[] args) {

		if (args.length > 0) {
			args[0] += ".cav";		
			int[] data = loadTextFile(args[0]);

			Graph graph = new Graph(data);
			// System.out.println(graph); //prints full information about nodes and connections
			aStar.Algorithm aStar = new aStar.Algorithm();
			aStar.findShortestRoute(graph);

//			System.err.println("Result found:");
			writeTextFile(args[0].replaceAll(".cav", ".csn"), aStar.getResult());
		} else {
			// Running thru all .cav files in the current folder if no parameters are defined
			// runFolderData();
			System.err.println("Missing file argument");
		}

	}
	
	private static int[] loadTextFile(String filepath) {
//		System.err.println("Loading " + filepath);
		try {
			String csv = new String(Files.readAllBytes(Paths.get(filepath)));
//			if (csv.length() > 42)
//				System.out.println("\t" + csv.substring(0, 42) + " (...)");
//			else
//				System.out.println("\t" + csv);
			return convertToIntArray(csv.split(","));
		} catch (IOException e) {
			System.err.println(filepath + " could not be loaded.");
			return null;
		}
	}

	private static int[] convertToIntArray(String[] numberChars) {
		int[] values = new int[numberChars.length];
		for (int i = 0; i < numberChars.length; i++) {
			try {
				values[i] = Integer.parseInt("" + numberChars[i]);
			} catch (NumberFormatException nfe) {
				System.err.println("Error Parsing Integer Values at Position " + i);
				return values;
			}
		}
		return values;
	}
	
	private static void writeTextFile(String filename, String data) {
		try {
			PrintWriter writer = new PrintWriter(filename);
			writer.println(data);
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void runFolderData() {

		File folder = new File(".");
		for (File f : folder.listFiles()) {

			if (!f.getName().contains(".cav"))
				continue;

			int[] data = loadTextFile(f.getName());
			Graph graph = new Graph(data);
			// System.out.println(graph);
			aStar.Algorithm aStar = new aStar.Algorithm();
			aStar.findShortestRoute(graph);
			System.err.println("Result found:");
			aStar.getResult(); // will print result on console

			pause();
			System.out.print("\n");

		}
	}
	
	private static void pause() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
