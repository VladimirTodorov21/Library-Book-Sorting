/*This program imports a series of objects. It uses these objects to read the book files and write the data in the respective genre category text files while putting 
 *all syntatically invalid books in a seaparate file (part1). The program then verifies those newly created text files and writes data in binary files while verifying
 *for semantic errors and if there are any, the books are put in a separate file (part2). The program finally executes an interactive program that prompts the user to 
 *check in the library which files they wish to see and view the books contained in that file (that contains all books of the same genre).*/

package comp249_assignment3;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;

public class a3 {
	
	public static void do_part1() {
		
		String LineInFile;
		
		PrintWriter pwCCB = null;
		PrintWriter pwHCB = null;
		PrintWriter pwMTV = null;
		PrintWriter pwMRB = null;
		PrintWriter pwNEB = null;
		PrintWriter pwOTR = null;
		PrintWriter pwSSM = null;
		PrintWriter pwTPA = null;
		PrintWriter pwSyntaxError = null;
		BufferedReader fileScan = null;
		
		try {
			
			pwCCB = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Cartoon_Comics_Books.csv.txt"));
			pwHCB = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Hobbies_Collectibles_Books.csv.txt"));
			pwMTV = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Movies_TV.csv.txt"));
			pwMRB = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Music_Radio_Books.csv.txt"));
			pwNEB = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Nostalgia_Eclectic_Books.csv.txt"));
			pwOTR = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Old_Time_Radio.csv.txt"));
			pwSSM = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Sports_Sports_Memorabilia.csv.txt"));
			pwTPA = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Trains_Planes_Automobiles.csv.txt"));
			pwSyntaxError = new PrintWriter(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\syntax_error_file.txt"));
			
			for (int i=0; i<=15;i++) {
				int bookyear = 1995 + i;
				fileScan = new BufferedReader(new FileReader("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\" + "books" + bookyear + ".csv.txt"));
				pwSyntaxError.print("\nSyntax error in file: books" + bookyear + ".csv\n====================\n");

				LineInFile = fileScan.readLine();
				
				while (LineInFile != null) {
					/*Source: https://stksgarcia.github.io/posts/split-a-comma-separated-string/*/
					String[] line = LineInFile.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					
					if (line.length < 6) {
						pwSyntaxError.print("\nError: Too few fields\nRecord: " + LineInFile + "\n");
					} else if (line.length > 6) {
						pwSyntaxError.print("\nError: Too many fields\nRecord: " + LineInFile + "\n");
					} else if (line[0] == "") {
						pwSyntaxError.print("\nError: Missing Title\nRecord: " + LineInFile + "\n");
					} else if (line[1] == "") {
						pwSyntaxError.print("\nError: Missing Authors\nRecord: " + LineInFile + "\n");
					} else if (line[2] == "") {
						pwSyntaxError.print("\nError: Missing Price\nRecord: " + LineInFile + "\n");
					} else if (line[3] == "") {
						pwSyntaxError.print("\nError: Missing ISBN\nRecord: " + LineInFile + "\n");
					} else if (line[4] == "") {
						pwSyntaxError.print("\nError: Missing Genre\nRecord: " + LineInFile + "\n");
					} else if (line[5] == "") {
						pwSyntaxError.print("\nError: Missing Year\nRecord: " + LineInFile + "\n");
					} else if ((!line[4].equals("CCB")) && (!line[4].equals("HCB")) && (!line[4].equals("MTV")) && (!line[4].equals("MRB")) && (!line[4].equals("NEB")) && (!line[4].equals("OTR")) && (!line[4].equals("SSM")) && (!line[4].equals("TPA"))) {
						pwSyntaxError.print("\nError: Invalid Genre\nRecord: " + LineInFile + "\n");
					} else {
						if (line[4].equals("CCB")) {
							pwCCB.println(LineInFile);
						} else if (line[4].equals("HCB")) {
							pwHCB.println(LineInFile);
						} else if (line[4].equals("MTV")) {
							pwMTV.println(LineInFile);
						} else if (line[4].equals("MRB")) {
							pwMRB.println(LineInFile);
						} else if (line[4].equals("NEB")) {
							pwNEB.println(LineInFile);
						} else if (line[4].equals("OTR")) {
							pwOTR.println(LineInFile);
						} else if (line[4].equals("SSM")) {
							pwSSM.println(LineInFile);
						} else if (line[4].equals("TPA")) {
							pwTPA.println(LineInFile);
						}
					}
					LineInFile = fileScan.readLine();
				}
			}
			
			fileScan.close();
			pwCCB.close();
			pwHCB.close();
			pwMTV.close();
			pwMRB.close();
			pwNEB.close();
			pwOTR.close();
			pwSSM.close();
			pwTPA.close();
			pwSyntaxError.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("\nAttention! Cannot proceed to copy the file because files do not exist.");
			System.out.println("Terminating the program.");
			System.exit(0);
		} catch(IOException e) {
			System.out.println("An error has occured while reading the file.");
		}
		
		System.out.println("Part1: Successfully created the .csv.txt files!");	
	}
	
	public static void do_part2() {
		
		String LineInSynValidFile;
		
		ObjectOutputStream oosCCB = null;
		ObjectOutputStream oosHCB = null;
		ObjectOutputStream oosMTV = null;
		ObjectOutputStream oosMRB = null;
		ObjectOutputStream oosNEB = null;
		ObjectOutputStream oosOTR = null;
		ObjectOutputStream oosSSM = null;
		ObjectOutputStream oosTPA = null;
		ObjectOutputStream oosSemanticError = null;
		BufferedReader oosFileScan = null;
		
		try {
			String[] files = {"Cartoon_Comics_Books.csv.txt","Hobbies_Collectibles_Books.csv.txt","Movies_TV.csv.txt","Music_Radio_Books.csv.txt",
							  "Nostalgia_Eclectic_Books.csv.txt","Old_Time_Radio.csv.txt","Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt"};
			
			oosCCB = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Cartoon_Comics_Books.csv.ser"));
			oosHCB = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Hobbies_Collectibles_Books.csv.ser"));
			oosMTV = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Movies_TV.csv.ser"));
			oosMRB = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Music_Radio_Books.csv.ser"));
			oosNEB = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Nostalgia_Eclectic_Books.csv.ser"));
			oosOTR = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Old_Time_Radio.csv.ser"));
			oosSSM = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Sports_Sports_Memorabilia.csv.ser"));
			oosTPA = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Trains_Planes_Automobiles.csv.ser"));
			oosSemanticError = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\semantic_error_file.txt"));
			
			for (int i=0;i<files.length;i++) {
				oosFileScan = new BufferedReader(new FileReader("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\" + files[i]));
				oosSemanticError.writeUTF("\nSemantic error in file: " + files[i] + "\n====================\n");
				
				LineInSynValidFile = oosFileScan.readLine();
				
				while(LineInSynValidFile != null) {
					/*Source: https://stksgarcia.github.io/posts/split-a-comma-separated-string/*/
					String[] line = LineInSynValidFile.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					
					Book b = new Book(line[0],line[1],Double.parseDouble(line[2]),line[3],line[4],Integer.parseInt(line[5]));
					
					if (Double.parseDouble(line[2]) < 0) {
						oosSemanticError.writeUTF("\nError: Invalid Price\nRecord: " + LineInSynValidFile + "\n");
					} else if (line[3] != "") {
						float isbn10=0;
						float isbn13=0;
						
						//Source: https://www.regular-expressions.info/floatingpoint.html
						if (line[3].length() == 10 && line[3].matches("[-+]?[0-9]*\\.?[0-9]+")) {
							for (int j=1; j<=line[3].length();j++) {
								isbn10 += (11-j)* Float.parseFloat(String.valueOf(line[3].charAt(j-1)));
							}
							if (isbn10 % 11 != 0) {
								oosSemanticError.writeUTF("\nError: Invalid ISBN-10\nRecord: " + LineInSynValidFile + "\n");
							} else if (Integer.parseInt(line[5]) < 1995 || Integer.parseInt(line[5]) > 2010) {
								oosSemanticError.writeUTF("\nError: Invalid Year\nRecord: " + LineInSynValidFile + "\n");
							} else {
								if (line[4].equals("CCB")) {
								    oosCCB.writeUTF(b.toString());
								} else if (line[4].equals("HCB")) {
									oosHCB.writeUTF(b.toString());
								} else if (line[4].equals("MTV")) {
									oosMTV.writeUTF(b.toString());
								} else if (line[4].equals("MRB")) {
									oosMRB.writeUTF(b.toString());
								} else if (line[4].equals("NEB")) {
									oosNEB.writeUTF(b.toString());
								} else if (line[4].equals("OTR")) {
									oosOTR.writeUTF(b.toString());
								} else if (line[4].equals("SSM")) {
									oosSSM.writeUTF(b.toString());
								} else if (line[4].equals("TPA")) {
									oosTPA.writeUTF(b.toString());
								} 
							}
						}
						
						if (line[3].length() == 13 && line[3].matches("[-+]?[0-9]*\\.?[0-9]+")) {
							for (int j=1; j<=line[3].length();j++) {
								if (j%2 == 0) {
									isbn13 += 3 * Float.parseFloat(String.valueOf(line[3].charAt(j-1)));
								} else {
									isbn13 += Float.parseFloat(String.valueOf(line[3].charAt(j-1)));
								}
							}
							if (isbn13 % 10 != 0) {
								oosSemanticError.writeUTF("\nError: Invalid ISBN-13\nRecord: " + LineInSynValidFile + "\n");
							} else if (Integer.parseInt(line[5]) < 1995 || Integer.parseInt(line[5]) > 2010) {
								oosSemanticError.writeUTF("\nError: Invalid Year\nRecord: " + LineInSynValidFile + "\n");
							} else {
								if (line[4].equals("CCB")) {
								    oosCCB.writeUTF(b.toString());
								} else if (line[4].equals("HCB")) {
									oosHCB.writeUTF(b.toString());
								} else if (line[4].equals("MTV")) {
									oosMTV.writeUTF(b.toString());
								} else if (line[4].equals("MRB")) {
									oosMRB.writeUTF(b.toString());
								} else if (line[4].equals("NEB")) {
									oosNEB.writeUTF(b.toString());
								} else if (line[4].equals("OTR")) {
									oosOTR.writeUTF(b.toString());
								} else if (line[4].equals("SSM")) {
									oosSSM.writeUTF(b.toString());
								} else if (line[4].equals("TPA")) {
									oosTPA.writeUTF(b.toString());
								} 
							}
						}
					}
					LineInSynValidFile = oosFileScan.readLine();
				}
			}
			
			oosFileScan.close();
			oosCCB.close();
			oosHCB.close();
			oosMTV.close();
			oosMRB.close();
			oosNEB.close();
			oosOTR.close();
			oosSSM.close();
			oosTPA.close();
			oosSemanticError.close();
			
		} catch(IOException e) {
			System.out.println("\nAttention! Cannot proceed to copy the file due to an I/O mismatch.");
			System.out.println("Terminating the program.");
			System.exit(0);
		}
		System.out.println("\nPart2: Successfully created the .csv.ser binary files!\n");
	}
	
	public static void do_part3() {
		
		boolean MainMenu = true;
		boolean returnToMenu = false;
		String uI, lineRead;
		int fC, arrStrIndex = 0;
		
		String[] fileStrings = {"Cartoon_Comics_Books.csv.ser","Hobbies_Collectibles_Books.csv.ser","Movies_TV.csv.ser","Music_Radio_Books.csv.ser",
								"Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio.csv.ser","Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};
		
		int[] records = {0,0,0,0,0,0,0,0};
		
		Scanner userInput = new Scanner(System.in);
		Scanner fileChoice = new Scanner(System.in);
		ObjectInputStream oisCCB = null;
		ObjectInputStream oisHCB = null;
		ObjectInputStream oisMTV = null;
		ObjectInputStream oisMRB = null;
		ObjectInputStream oisNEB = null;
		ObjectInputStream oisOTR = null;
		ObjectInputStream oisSSM = null;
		ObjectInputStream oisTPA = null;
		
		try {
			oisCCB = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Cartoon_Comics_Books.csv.ser"));
			oisHCB = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Hobbies_Collectibles_Books.csv.ser"));
			oisMTV = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Movies_TV.csv.ser"));
			oisMRB = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Music_Radio_Books.csv.ser"));
			oisNEB = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Nostalgia_Eclectic_Books.csv.ser"));
			oisOTR = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Old_Time_Radio.csv.ser"));
			oisSSM = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Sports_Sports_Memorabilia.csv.ser"));
			oisTPA = new ObjectInputStream(new FileInputStream("C:\\Users\\arnol\\eclipse-workspace\\comp248_assignments\\COMP249\\comp249_assignment3\\Trains_Planes_Automobiles.csv.ser"));
			
			ObjectInputStream[] ois = {oisCCB,oisHCB,oisMTV,oisMRB,oisNEB,oisOTR,oisSSM,oisTPA};
			
			System.out.println("------------------\n    Main Menu\n------------------");
			System.out.println("v View the selected file: " + fileStrings[arrStrIndex]);
			System.out.println("s Select a file to view");
			System.out.println("x Exit\n------------------\n");
			
			System.out.print("Enter your Choice: ");
			uI = userInput.next();
			
			while(MainMenu == true) {
				
				if (returnToMenu == true) {
					System.out.println("\n------------------\n    Main Menu\n------------------");
					System.out.println("v View the selected file: " + fileStrings[arrStrIndex]);
					System.out.println("s Select a file to view");
					System.out.println("x Exit\n------------------\n");
					
					System.out.print("Enter your Choice: ");
					uI = userInput.next();
				}
				
				returnToMenu = false;
				if (uI.equals("v") || uI.equals("V")) {
					try {
						System.out.println("Viewing the selected file: " + fileStrings[arrStrIndex] + "\n");
						lineRead = ois[arrStrIndex].readUTF();
						while (lineRead != null) {
							System.out.println(lineRead);
							lineRead = ois[arrStrIndex].readUTF();
						}
					} catch(EOFException e) {
						System.out.println("\nReading files has been completed.");
					} catch(IOException e) {
						System.out.println("\nError: there is a problem reading the files.\nTerminating Program.");
						System.exit(0);
					}
					finally {
						returnToMenu = true;
					}
				} else if (uI.equals("s") || uI.equals("S")) {
					System.out.println("\n------------------\n  File Sub-Menu\n------------------");
					try {
						for (int i=0; i<ois.length;i++) {
							lineRead = ois[i].readUTF();
							while (lineRead != null) {
								records[i]++;
								lineRead = ois[i].readUTF();
							}
						}
					} catch(EOFException e) {
						System.out.println("Reading files has been completed.");
					} catch(IOException e) {
						System.out.println("\nError: there is a problem reading the files.\nTerminating Program.");
						System.exit(0);
					}
					finally {	
						for (int k=0;k<records.length;k++) {
							System.out.printf("%1d%-38s%s",k+1," " + fileStrings[k],"(" + records[k] + " Records)\n");
						}
						
						System.out.printf("%1d%-38s\n",9," Exit",1);
						System.out.println("------------------");
						
						System.out.print("\nEnter your Choice: ");
						fC = fileChoice.nextInt();
						
						if (fC > 9 || fC < 0) {
							System.out.print("Wrong Input, please enter a valid input: ");
							fC = fileChoice.nextInt();
						} else if (fC == 9) {
							returnToMenu = true;
						} else {
							arrStrIndex = fC -1;
							returnToMenu = true;
						}
					}
				} else if (uI.equals("x") || uI.equals("X")) {
					System.out.println("\nExiting program.\nProgram has terminated.");
					MainMenu = false;	
				} else {
					System.out.print("Wrong Input, please enter a valid input: ");
					uI = userInput.next();
				}	
			}
			userInput.close();
			fileChoice.close();
			oisCCB.close();
			oisHCB.close();
			oisMTV.close();
			oisMRB.close();
			oisNEB.close();
			oisOTR.close();
			oisSSM.close();
			oisTPA.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nError: file not found.\nTerminating Program.");
			System.exit(0);
			
		} catch(IOException e) {
			System.out.println("\nError: there is a problem reading the files.\nTerminating Program.");
			System.exit(0);
		}
	}
}
