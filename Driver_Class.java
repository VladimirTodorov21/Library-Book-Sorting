/*This program imports a series of objects. It uses these objects to read the book files and write the data in the respective genre category text files while putting 
 *all syntatically invalid books in a seaparate file (part1). The program then verifies those newly created text files and writes data in binary files while verifying
 *for semantic errors and if there are any, the books are put in a separate file (part2). The program finally executes an interactive program that prompts the user to 
 *check in the library which files they wish to see and view the books contained in that file (that contains all books of the same genre).*/

package comp249_assignment3;

public class driverAllParts {
	
	public static void main(String[] args) {
		
		a3.do_part1();
		a3.do_part2();
		a3.do_part3();
	}
}
