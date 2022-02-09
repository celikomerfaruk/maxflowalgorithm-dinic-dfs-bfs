import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class project4main {
public static void main(String[] args)throws FileNotFoundException {
		
		
		Scanner reader = new Scanner(new File(args[0]));
		reader.useLocale(Locale.US);
		PrintStream writer = new PrintStream(new File(args[1]));
		int totalGift = 0;
	//reading
		
		int trainToGreen = reader.nextInt();
		int[]greenTrains = new int[trainToGreen];
		reader.nextLine();
		for (int i = 0; i < trainToGreen; i++) {
			greenTrains[i] = reader.nextInt();
		}
		reader.nextLine();
		int trainToRed = reader.nextInt();
		int[]redTrains = new int[trainToRed];
		reader.nextLine();
		for (int i = 0; i < trainToRed; i++) {
			redTrains[i] = reader.nextInt();
		}
		reader.nextLine();
		
		int reindeerToGreen = reader.nextInt();
		int[]greenReindeer = new int[reindeerToGreen];
		reader.nextLine();
		for (int i = 0; i < reindeerToGreen; i++) {
			greenReindeer[i] = reader.nextInt();
		}
		reader.nextLine();
		int reindeerToRed = reader.nextInt();
		int[]redReindeer = new int[reindeerToRed];
		reader.nextLine();
		for (int i = 0; i < reindeerToRed; i++) {
			redReindeer[i] = reader.nextInt();
		}
		reader.nextLine();
		
		int numOfBags = reader.nextInt();
		ArrayList<CustomPair>bags = new ArrayList<CustomPair>();
		
		
		reader.nextLine();
		
		for (int i = 0; i < numOfBags; i++) {
			bags.add(new CustomPair(reader.next(), reader.nextInt()));
		}
		
		 int numOfVertices = numOfBags + reindeerToGreen + reindeerToRed + trainToGreen + trainToRed;
		 int sink = numOfVertices + 1;
		 int source = 0;
		 
		 Operations operations = new Operations(numOfVertices);
		
		//construct graph
		 int firstGreenReindeer = numOfBags+1;
		 //green reindeers
		for(int i = firstGreenReindeer ; i < firstGreenReindeer+reindeerToGreen ; i++) {
			operations.addEdge(i,sink,greenReindeer[i-firstGreenReindeer]);
		} 
		int firstGreenTrain = firstGreenReindeer+reindeerToGreen;
		// green trains
		for (int i = firstGreenTrain; i < trainToGreen+firstGreenTrain; i++) {
			operations.addEdge(i, sink, greenTrains[i-firstGreenTrain]);
		}
		
		// red trains
		int firstRedTrain = firstGreenTrain +trainToGreen;
		for (int i = firstRedTrain; i < firstRedTrain + trainToRed; i++) {
			operations.addEdge(i, sink, redTrains[i-firstRedTrain]);
		}
		
		//red reindeer 
		int firstRedReindeer = firstRedTrain+ trainToRed;
		for (int i = firstRedReindeer; i < sink; i++) {
			operations.addEdge(i, sink,redReindeer[i-firstRedReindeer] );
		}
		
		for (int i = 1;i<=bags.size();i++) {
			String keyString = bags.get(i-1).getKey();
			
			operations.addEdge(source, i, bags.get(i-1).getValue());
			totalGift += bags.get(i-1).getValue();
			switch (keyString) {
			case "a": 
				for (int j = firstGreenReindeer; j < sink; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "b":	
				for (int j = firstGreenReindeer; j < firstRedTrain; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
				
			case "c":	
				for (int j = firstRedTrain; j < sink; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "d":
				for (int j = firstGreenTrain; j < firstRedReindeer; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "e":
				for (int j = firstGreenReindeer; j < firstGreenTrain; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				for (int j = firstRedReindeer; j < sink; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "ab":	
				for (int j = firstGreenReindeer; j < firstRedTrain; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
				
			case "ac":	
				for (int j = firstRedTrain; j < sink; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "ad":	
				for (int j = firstGreenTrain; j < firstRedReindeer; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "ae":	
				for (int j = firstGreenReindeer; j < firstGreenTrain; j++) {
					operations.addEdge(i, j, 1);
				}
				for (int j = firstRedReindeer; j < sink; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "bd":
				for (int j = firstGreenTrain; j < firstRedTrain; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "be":	
				for (int j = firstGreenReindeer; j < firstGreenTrain; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "cd":
				for (int j = firstRedTrain; j < firstRedReindeer; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "ce":	
				for (int j = firstRedReindeer; j < sink; j++) {
					operations.addEdge(i, j, bags.get(i-1).getValue());
				}
				break;
			case "abd":	
				for (int j = firstGreenTrain; j < firstRedTrain; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "abe":	
				for (int j = firstGreenReindeer; j < firstGreenTrain; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "acd":	
				for (int j = firstRedTrain; j < firstRedReindeer; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			case "ace":	
				for (int j = firstRedReindeer; j < sink; j++) {
					operations.addEdge(i, j, 1);
				}
				break;
			default:
				break;
				
			}
				
			
			
		}
		
		
		int maxflow = operations.operate();
		writer.print(totalGift-maxflow);
		}
}
