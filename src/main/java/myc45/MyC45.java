package myc45;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class MyC45 {

	public static void main(String[] args) throws IOException {
		// .csv data sets
		String files[] = {"C:\\Users\\Jesús Barajas\\Desktop\\workspace\\c45\\C4.5\\data_sets\\datos.csv"};
		Scanner scan;
		
		// start loop for all files HERE
		scan = new Scanner(new File(files[0]));
		String headerLine = scan.nextLine();
		String headers[]  = headerLine.split(",");
		
		// class index is assumed to be the last column
		int classIndex    = headers.length - 1;
		int numAttributes = headers.length - 1;
		
		// store data set attributes
		Attribute attributes[] = new Attribute[numAttributes];
		for(int x = 0; x < numAttributes; x++) {
			attributes[x] = new Attribute(headers[x]);
		}
		
		// for storing classes and class count
		List<String>  classes      = new ArrayList<String>();
		List<Integer> classesCount = new ArrayList<Integer>();
		
	
		while(scan.hasNextLine()){
			Val data = null;
			String inLine = scan.nextLine();
			String lineData[] = inLine.split(",");
			
			//  instertar la clase dentro de la lista de clases
			if(classes.isEmpty()){
				classes.add(lineData[classIndex]);
				classesCount.add(classes.indexOf(lineData[classIndex]), 1);
			}
			else{
				if(!classes.contains(lineData[classIndex])){
					classes.add(lineData[classIndex]);
					classesCount.add(classes.indexOf(lineData[classIndex]), 1);
				}
				else {
					classesCount.set(classes.indexOf(lineData[classIndex]),
							classesCount.get(classes.indexOf(lineData[classIndex])) + 1);
				}
			}
			
			// Insertar datos en los atributos
			for(int x = 0; x < numAttributes; x++){
				data = new Val(lineData[x], lineData[classIndex]);
				attributes[x].insertVal(data);
			}
		}
		int totalNumClasses = 0;
		for(int i : classesCount){
			totalNumClasses += i;
		}
		double Entro = calcEntro(classesCount); 
		
		/*
		Attribute outlock = new Attribute("outlock");
		
		Val inV = new Val("sunny","no"); outlock.insertVal(inV);
		inV = new Val("sunny","yes"); outlock.insertVal(inV);
		inV = new Val("overcast","yes"); outlock.insertVal(inV);
		inV = new Val("rainy","yes"); outlock.insertVal(inV);
		inV = new Val("rainy","yes"); outlock.insertVal(inV);
		inV = new Val("rainy","yes"); outlock.insertVal(inV);
		inV = new Val("overcast","yes"); outlock.insertVal(inV);
		inV = new Val("sunny","no"); outlock.insertVal(inV);
		inV = new Val("sunny","no"); outlock.insertVal(inV);
		inV = new Val("rainy","no"); outlock.insertVal(inV);
		inV = new Val("sunny","yes"); outlock.insertVal(inV);
		inV = new Val("overcast","yes"); outlock.insertVal(inV);
		inV = new Val("overcast","no"); outlock.insertVal(inV);
		inV = new Val("rainy","no"); outlock.insertVal(inV);
		
		System.out.println(outlock.toString());
		

		List<Integer> testCount = new ArrayList<Integer>();
		testCount.add(9);
		testCount.add(5);

		double testEntro = calcEntro(testCount);
		outlock.setGain(testEntro,14);

		System.out.println("I of D: " + testEntro);
		System.out.println("outlock: " + outlock.gain);
		
		*/
		
		for(Attribute a : attributes){
			System.out.println(a.toString());
			
			List<Integer> testCount = new ArrayList<Integer>();
			testCount.add(9);
			testCount.add(5);

			double testEntro = calcEntro(testCount);
			a.setGain(testEntro,14);

			System.out.println("Entropia  " + testEntro);
			System.out.println(a.name +" "+ a.gain);
			
		}
		
		
	}
	
	
	
	
	public static double calcEntro(List<Integer> classesCount){
		double Entro = 0.0;
		double temp = 0.0;
		
		int totalNumClasses = 0;
		for(int i : classesCount){
			totalNumClasses += i;
		}
		
		for(double d : classesCount){
			temp = (-1 * (d/totalNumClasses)) * (Math.log((d/totalNumClasses)) / Math.log(2));
			Entro += temp;
		}
		return Entro;
	}
}
