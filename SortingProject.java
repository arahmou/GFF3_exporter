package sort;

import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class SortingProject {

	     // first we need to declare the variables needed to create the files and arrays that will be ordered

	     public static Scanner input;

	     public static PrintWriter myWriter;

	     public static String fileNames[] = { "Random", "Reverse", "Ascending" };

	     static int FileSize[] = {50, 200, 500, 1000, 2000};


	     // CreateRandomizedArray() will create randomly sorted arrays to be sorted by the sorting methods
	     // it takes the arraySize needed and uses the random method available in java.lang.math library to generate the numbers
	     // and returns the random array that can be reversed or put in ascending order
	     
	     private static int[] CreateRandomizedArray(int arraySize) {

	          int array[] = new int[arraySize];

	          for (int i = 0; i < arraySize; i++) {

	              array[i] = (int) (Math.random() * (FileSize[FileSize.length - 1]) - 1) + 1;

	          }

	          return array;

	     }
	     
	  // since we need reversed elements for the sort
	  //reverseArray() method reverses the elements in the array
	  // it will return the reversed array

	     private static int[] reverseArray(int array[], int size) {

	          int reversedArray[] = new int[size];

	          for (int i = 0; i < size; i++) {

	              reversedArray[i] = array[size - i - 1];

	          }

	          return reversedArray;

	     }
	     
	     // InputWriter method will write the array values to the files 
	     //it takes in filename, arraysize, array
	     //and order: which defines if the file will be random, reversed or ascending

	     private static void InputWriter(String fileName, int array[], int arraySize, int order) {

	          try {

	              // we need to create an object of PrintWriter for each file

	              myWriter = new PrintWriter(new File(fileName));

	              // this following statement will define the order of the array

	              switch (order)  {

	              // if 0, then we keep the values in the array randomized

	              case 0:

	                   break;

	              // if 1, then the array elements are reversed using reverseArray method

	              case 1:

	                   Arrays.sort(array);

	                   array = reverseArray(array, arraySize);

	                   break;

	              // if 2, the array elements are put in ascending order

	              case 2:

	                   Arrays.sort(array);

	                   break;

	              }

	              // for loop that implements myWriter to write the array values into the files

	              for (int i = 0; i < arraySize; i++)  {

	              myWriter.println(array[i]);

	              }

	              myWriter.close();

	          }

	          catch (Exception e) {

	              System.out.println(e.getMessage());

	          }

	     }

	     // this following method printArray() is to print the values of the array
	     public static void printArray(int array[], int size) throws Exception{	    	 	    	 
	    	  
	          for (int j = 0; j < 50; j++) {  // only printing the first 50 because my system crashed when it printed bigger lists
	             
	        	  System.out.printf("%5d \t", array[j]);
	        	  
	              if ((j + 1) % 10 == 0)

	                   System.out.println();	             

	          }
	          
	     }
	    
	     // createInputFiles()create input files with different sizes and their perspective values
	     public static void createInputFiles() {

	          // declare the required variables

	          int array[];

	          // loop through each size and create the files

	          for (int i = 0; i < FileSize.length; i++) {

	              // define the array with size present at

	              // FileSize[i]

	              array = new int[FileSize[i]];

	              // convert the ArrayList values into the array

	              array = CreateRandomizedArray(FileSize[i]);

	              // loop to create the files

	              for (int k = 0; k < fileNames.length; k++)   {

	                   // define the name

	                   String fileName = fileNames[k] + "_" + FileSize[i] + " file";

	                    // call the method InputWriter() by passing

	                   // name of the file, array, size and case

	                   // index(Random/Reverse/Ascending)

	                   InputWriter(fileName, array, FileSize[i], k);

	              }

	          }

	     }

	     // getArrayValues() method will retrieve (read) the values from the file and store them in array
	     //returns the array
	     private static int[] getArrayValues(String fileName, int array[]) {

	          try {

	               // Scanner reads through the files 

	              input = new Scanner(new File(fileName));

	              int i = 0;

	              // while loop to read each line which contains one element and then stores that element at each index into variable
	              
	              while (input.hasNext()) {
	                   
	                   array[i] = input.nextInt();

	                   i++;

	              }

	              input.close();

	          }

	          catch (Exception e) {

	              System.out.println(e.getMessage());

	          }

	          return array;

	     }

	     // declare the ArrayList variables that will be used to store the time for each sorting method 
	     
	     static ArrayList<Long> shellTimer = new ArrayList<Long>();

	     static ArrayList<Long> heapTimer = new ArrayList<Long>();

	     static ArrayList<Long> InsertionTimer = new ArrayList<Long>();
	     
	     
	     // shellVsHeap() method is to display the statistics of the different size of file sorted
	     // and the time comparison of the Shell and Heap Sort for all types of files
	     public static void shellVsHeap() {

	          // k variable is declared to access the FileSize array

	          int k = 0;

	          System.out.println("\n");

	          // these statements will display the header line
	          
	          System.out.printf("*%s %s %57s\n", " ", "Shell Sort Vs Heap Sort Comparison: ", "*");
	          
	          System.out.println("\n");

	          System.out.printf("*%12s %25s %8s %20s %15s %25s %10s\n", "*", "Shell Sort", "*", "Heap Sort", "*", "Time differences", "*");

	          System.out.printf("*%10s * %9s %10s %10s ", "Size", "Random", "Reverse", "Ascending");

	          System.out.printf(" %s %10s %10s %10s %3s", "*", "Random", "Reverse", "Ascending", "*");

	          System.out.printf(" %10s %10s %10s %3s\n", "Random", "Reverse", "Ascending", "*");

	          // loop through each sorts timer values for three different type of array values

	          for (int i = 0, j = 0; i < shellTimer.size() && j < heapTimer.size(); i = i + 3, j = j + 3) {

	              //the shell sort run time

	              System.out.printf("*%-10d * %10d %10d %10d ", FileSize[k], shellTimer.get(i), shellTimer.get(i + 1), shellTimer.get(i + 2));

	              //the heap sort run time

	              System.out.printf("%s %10d %10d %10d %3s", "*", heapTimer.get(j), heapTimer.get(j + 1), heapTimer.get(j + 2), "*");
	              	
	              	long RandomTimeDiff ;
	                long ReverseTimeDiff ;
	                long AscendingTimeDiff ;
	                
	              // put the calculations in an if statement to avoid getting negative values for the time difference
	              
	              if (shellTimer.get(i) > heapTimer.get(j)) {
	              
	                RandomTimeDiff = shellTimer.get(i) - heapTimer.get(j);
	               
	              }
	              else {
	            	  
	            	  RandomTimeDiff = heapTimer.get(j) - shellTimer.get(i);	
	              }
	              
	              if (shellTimer.get(i+1) > heapTimer.get(j+1)) {
	            	  
		                ReverseTimeDiff = shellTimer.get(i + 1) - heapTimer.get(j + 1);
		                
		              }
	              
		          else {
		            	  
		            	  ReverseTimeDiff =  heapTimer.get(j + 1) - shellTimer.get(i + 1);
			              
		              }
	              
	              if (shellTimer.get(i+2) > heapTimer.get(j+2)) {	            	
		               
	            	  AscendingTimeDiff = shellTimer.get(i + 2) - heapTimer.get(j + 2);
		              }
	              
		          else {
		            	 
		        	  AscendingTimeDiff =  heapTimer.get(j + 1) - shellTimer.get(i + 2);
		              }

	              

	              //the heap sort run time 

	              System.out.printf("%10d %10d %10d %4s\n", RandomTimeDiff, ReverseTimeDiff, AscendingTimeDiff, "*");

	              // next filesize

	              k++;

	          }

	     }

	     // shellVsInsertion() method which display the comparison of the different size of array 
	     // and the time comparison of the Shell Sort and Heap Sort for the three different files

	     public static void shellVsInsertion() {

	          // define the k'th variable to access the FileSize array

	          int k = 0;

	          System.out.println("\n");

	          // display the header lines   

	          System.out.printf("*%s %s %43s\n", " ", "Shell Sort Vs Straight Insertion Sort comparison:", "*");
	          
	          System.out.println("\n");

	          System.out.printf("*%12s %25s %8s %25s %10s %25s %10s\n", "*", "Shell Sort", "*", "Insertion Sort", "*", "Time difference", "*");

	          System.out.printf("*%10s * %10s %10s %9s ", "Size", "Random", "Reverse", "Ascending");

	          System.out.printf(" %s %10s %10s %10s %3s", "*", "Random", "Reverse", "Ascending", "*");

	          System.out.printf(" %10s %10s %10s %3s\n", "Random", "Reverse", "Ascending", "*");

	          // loop through timers each of different methods of sorting

	          for (int i = 0, j = 0; i < shellTimer.size() && j < InsertionTimer.size(); i = i + 3, j = j + 3) {

	              //the shell sort run time 

	              System.out.printf("*%-10d * %10d %10d %10d ", FileSize[k], shellTimer.get(i), shellTimer.get(i + 1), shellTimer.get(i + 2));

	              //the heap sort run time

	              System.out.printf("%s %10d %10d %10d %3s", "*", InsertionTimer.get(j), InsertionTimer.get(j + 1), InsertionTimer.get(j + 2), "*");

	              // declare the variable needed to store the time diff analysis 
	              long RandomTimeDiff ;

	              long ReverseTimeDiff ;

	              long AscendingTimeDiff ;
	              
	              // to avoid obtaining negative value for the time diff
	              
	              if (shellTimer.get(i) > InsertionTimer.get(j)) {
		              
		                RandomTimeDiff = shellTimer.get(i) - InsertionTimer.get(j);
		               
		              }
		              else {
		            	  
		            	  RandomTimeDiff = InsertionTimer.get(j) - shellTimer.get(i);	
		              }
		              
		              if (shellTimer.get(i+1) > InsertionTimer.get(j+1)) {
		            	  
			                ReverseTimeDiff = shellTimer.get(i + 1) - InsertionTimer.get(j + 1);
			                
			              }
		              
			          else {
			            	  
			            	  ReverseTimeDiff =  InsertionTimer.get(j + 1) - shellTimer.get(i + 1);
				              
			              }
		              
		              if (shellTimer.get(i+2) > InsertionTimer.get(j+2)) {	            	
			               
		            	  AscendingTimeDiff = shellTimer.get(i + 2) - InsertionTimer.get(j + 2);
			              }
		              
			          else {
			            	 
			        	  AscendingTimeDiff =  InsertionTimer.get(j + 1) - shellTimer.get(i + 2);
			              }


	              //the heap sort run time 

	              System.out.printf("%10d %10d %10d %4s\n", RandomTimeDiff, ReverseTimeDiff, AscendingTimeDiff, "*");

	              //next filesize
	              k++;

	          }
	       
	     }

	     // shellSorting() method to sort the elements in the array

	     private static int[] shellSorting(int[] array, int size) {

	          int interval = 1;

	          int temp;

	          // calculate the interval values

	          while (interval <= size / 3) {

	              interval = (interval * 3) + 1;

	          }

	          // loop through the interval values

	          while (interval > 0) {

	              // loop the elements from the given interval

	              // to the size of the array

	              for (int i = interval; i < size; i++) {

	                   // store the element at index i into temp

	                   temp = array[i];

	                   int j;

	                   // loop through i to the j-interval elements

	                   // to move the elements from position (j - interval)

	                   // to j'th position

	                   for (j = i; j > interval - 1 && array[j - interval] >= temp; j = j - interval) {

	                        array[j] = array[j - interval];

	                   }

	                   // store the value at j'th index with temp value

	                   array[j] = temp;

	              }

	              // compute the interval

	              interval = (interval - 1) / 3;

	          }

	          // return the array

	          return array;

	     }

	     // shellSortingDetails() method to find the run time for shell sort for
	     // 

	     public static void shellSortingDetails() throws Exception{

	          // declare the required variables

	          String fileName = "";

	          int array[];

	          long start, end;

	          // loop through FileSize array

	          for (int i = 0; i < FileSize.length; i++)

	          {

	              // going through the different file types

	              for (int k = 0; k < fileNames.length; k++)

	               {

	                   // create the file name

	                   fileName = fileNames[k] + "_" + FileSize[i] + " file";

	                   // define the array

	                   array = new int[FileSize[i]];

	                   // get the values from the file

	                   array = getArrayValues(fileName, array);

	                   // to find the time it takes for this method to run

	                   // get the start time in nano seconds

	                   start = System.nanoTime();

	                   // call the shellSorting() method

	                   array = shellSorting(array, FileSize[i]);

	                   // get the end time

	                   end = System.nanoTime();

	                   long totalTime = end - start;

	                   // find the difference of the end and start time

	                   // and store it in shellSortTimer ArrayList

	                   shellTimer.add(totalTime);       
	                  
	                   System.out.println("\n Time to sort these " + FileSize[i] + " elements using Shell sort is: " + totalTime);

	                   System.out.println(" Results of sorting " + fileName + " using Shell sort: ");
	                
	                   printArray(array, FileSize[i]);
	                  
	              }

	          }

	     }

	     // buildHeap() methods to build the heap
	     //it takes in arrayvals and size
	     static void buildHeap(int arrayVals[], int size) {

	          //

	          for (int i = 1; i < size; i++)

	          {

	              // if the parent is less than the child node

	              if (arrayVals[(i - 1) / 2] < arrayVals[i])

	              {

	                   int j = i;

	                  

	                   // while loop to make on the alignments in heap order

	                   while (arrayVals[(j - 1) / 2] < arrayVals[j])

	                   {

	                        swapValues(arrayVals, j, (j - 1) / 2);

	                        j = (j - 1) / 2;

	                   }

	              }

	          }

	     }
	   
	     // swapValues() method to swap the elements in an array

	     public static void swapValues(int[] arrayVal, int i, int j) {

	          int temp = arrayVal[i];

	          arrayVal[i] = arrayVal[j]; // swap elements at index i and index j

	          arrayVal[j] = temp;

	     }
	     
	     // heapSorting() method to sort the elements in the array using heap
         // returns the array values
	     public static int[] heapSorting(int arrayValues[], int size) {

	          // first build the heap

	          buildHeap(arrayValues, size);

	          int j = 0;

	         

	          // for loop to go through each values in the array

	          for (int i = size - 1; i > 0; i--) {

	              // swap the values at index 0 with i element

	              swapValues(arrayValues, 0, i);

	             

	              j = 0;

	              int index;

	              // to loop until the index < i

	              do

	              {

	                   index = (2 * j + 1);

	                  

	                   if (index < (i - 1) && arrayValues[index] < arrayValues[index + 1])

	                        index++;

	                  

	                   if (index < i && arrayValues[j] < arrayValues[index])

	                        swapValues(arrayValues, j, index);

	                   j = index;

	              } while (index < i);

	          }

	          return arrayValues;

	     }


	     // heapSortingDetails() method to find the run time for heap sort for all file types and sizes

	     public static void heapSortingDetails() throws Exception{

	          // declare the required variables

	          String fileName = "";

	          int array[];

	          long start, end;

	          System.out.println("\n");

	          // loop through FileSize array

	          for (int i = 0; i < FileSize.length; i++) {

	              // loop through file types

	              for (int k = 0; k < fileNames.length; k++) {

	                   // create the file name

	                   fileName = fileNames[k] + "_" + FileSize[i] + " file";

	                   // define the array

	                   array = new int[FileSize[i]];

	                   // get the values from the file

	                   array = getArrayValues(fileName, array);

	                   // to find the time it takes for the heap sort to run

	                   // get the start time in nano seconds

	                   start = System.nanoTime();

	                   // call the heapSorting() method

	                   array = heapSorting(array, FileSize[i]);

	                   // get the end time

	                   end = System.nanoTime();

	                   long totalTime = end - start;

	                   // find the difference of the end and start time and store it in heapTimer ArrayList

	                   heapTimer.add(totalTime);

	                   System.out.println("\n Time to sort these " + FileSize[i] + " elements using heap sort is: " + totalTime);
	                   
	                   System.out.println(" Results of sorting " + fileName + " Using heap sort: ");
       
	                   printArray(array, FileSize[i]);

	              }

	          }

	     }

	     // insertionSorting() to sort array using insertion sort method
	     // if element of the array are greater than key it moves to one index ahead of the current position

	     public static int[] insertionSorting(int array[], int size)  {

	          int n = size;

	          for (int i = 1; i < n; ++i) {

	              int key = array[i];

	              int j = i - 1;

	              while (j >= 0 && array[j] > key) {

	                    array[j + 1] = array[j];

	                   j = j - 1;

	              }

	              array[j + 1] = key;

	          }

	          return array;

	     }

	     // insertionSortingDetails() method to find the run time for insertion sort for for all file types and sizes

	     public static void insertionSortingDetails()  throws Exception{

	          String fileName = "";

	          int array[];

	          long start, end;

	          System.out.println("\n");

	          // loop through FileSize array

	          for (int i = 0; i < FileSize.length; i++) {

	              // for loop to go through file types

	              for (int k = 0; k < fileNames.length; k++)  {

	                   // create the file name

	                   fileName = fileNames[k] + "_" + FileSize[i] + " file";

	                   array = new int[FileSize[i]];

	                   // get the values from the file

	                   array = getArrayValues(fileName, array);

	                   // find the time it takes for insertion sort  in nano seconds

	                   start = System.nanoTime();

	                   // call the insertionSorting() method

	                   array = insertionSorting(array, FileSize[i]);

	                   // get the time

	                   end = System.nanoTime();

	                   long totalTime = end - start;

	                   // find the difference of the end and start time and store it in InsertionTimer ArrayList

	                   InsertionTimer.add(totalTime);

	                   System.out.println("\n Time to sort these " + FileSize[i] + " elements using Insertion sort is: " + totalTime);

	                   System.out.println(" Results of sorting " + fileName + " Using Insertion sort: ");

	                   printArray(array, FileSize[i]);

	              }

	          }

	     }

	     // to run all methods and display results

	     public static void main(String args[]) throws Exception {
	    	 
	          createInputFiles(); // create input files
	          
	          heapSortingDetails(); // displays the sorted data sets and provide calculation for run time

	          shellSortingDetails(); // displays the sorted data sets and provide calculation for run time

	          insertionSortingDetails(); // displays the sorted data sets and provide calculation for run time
	          
	          shellVsHeap();// provide comparison of the two methods

	          shellVsInsertion(); // provide comparison of the two methods

	     }

	}