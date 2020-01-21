package main;
import java.util.function.*;
public class Main {
	public static void main(String[] args) {
		
		//Creates an empty matrix with 50 elements
		Matrix <Integer> table = new Matrix <Integer> (10000,10000);
		
		//fills the matrix
		BiConsumer <java.awt.Point, Integer> fillArr = (point,a) -> {
			table.setValue(point.x, point.y, (int)(Math.random() * 100) + 1);
		};
		table.forEachIndex(fillArr);
		
		table.printArr();
		System.out.println("-------");
		
		//initialize the array of amounts to be a bunch of zeros rather than a bunch of nulls
		Series <Integer> amounts = new Series <Integer> (Series.zeroArray(10));
		
		//figures out how many of each category are in the array and sets that value in the appropriate index of amounts
		Consumer<Integer> action = (n -> {
			for(int x = 0; x < 10; x++) {
				if( (n/10) == x) {
					amounts.setValue(amounts.getValue(x)+1, x);
				}
			}
			if(n == 100) {
				amounts.setValue(amounts.getValue(9) + 1, 9);
			}
			
		});
		table.forEach(action); //does action for each element in table
		
		amounts.printArr();
		System.out.println("-------------");
		
		//Makes a singular string with all the data, and puts stars in as needed. Prints final table.
		String end = "";
		for(int x = 1; x <= amounts.length; x++) {
			String out = ((x*10)-9) + "-" + (x*10) + " | " ;
			for(int y = 0; y < amounts.getValue(x-1); y++) out+= "*";
			out += "\n";
			end += out;
		}
		
		System.out.println(end);
	
		System.out.println("----------");
		
		//figures out the percents of how many numbers take up which category.
		int values = table.columns * table.rows;
		String percents = "";
		for(int x = 1; x <= amounts.length; x++) {
			String out = ((x*10)-9) + "-" + (x*10) + " | " ;
			out += (double)(amounts.getValue(x-1) * 100) / values;
			out += "%\n";
			percents += out;
		}
		
		System.out.println(percents);
		
		
	}
	
	

}
