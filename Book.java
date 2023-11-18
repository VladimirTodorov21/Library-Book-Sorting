/*This class acts as an object for part2 of the assignment and is required to input the data from the .csv.txt files to be written
 *into the .csv.ser binary files. It has a default and parametrized constructor, a set of accessor and mutator methods, and lastly
 *an overridden toString and equals methods.*/

package comp249_assignment3;

import java.io.Serializable;

public class Book implements Serializable {
	//Attributes
	private String Title;
	private String Author;
	private double Price;
	private String ISBN;
	private String Genre;
	private int Year;
	
	//Default Constructor
	public Book() {
		this.Title = "";
		this.Author = "";
		this.Price = 0.0;
		this.ISBN = "";
		this.Genre = "";
		this.Year = 0;
	}
	
	//Parametrized Constructor
	public Book(String title, String author, double price, String isbn, String genre, int year) {
		this.Title = title;
		this.Author = author;
		this.Price = price;
		this.ISBN = isbn;
		this.Genre = genre;
		this.Year = year;
	}
	
	/*Mutator methods for attributes*/
	public void setTitle(String title) {
		this.Title = title;
	}
	
	public void setAuthor(String author) {
		this.Author = author;
	}
	
	public void setTitle(double price) {
		this.Price = price;
	}
	
	public void setISBN(String isbn) {
		this.ISBN = isbn;
	}
	
	public void setGenre(String genre) {
		this.Genre = genre;
	}
	
	public void setyear(int year) {
		this.Year = year;
	}
	
	/*Accessor methods for attributes*/
	public String getTitle() {
		return Title;
	}
	
	public String getAuthor() {
		return Author;
	}
	
	public double getPrice() {
		return Price;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public String getGenre() {
		return Genre;
	}
	
	public int getYear() {
		return Year;
	}
	
	//Overridden toString method
	public String toString() {
		return new StringBuffer("\nBook Title: ").append(this.Title).append(" Author: ").append(this.Author).append(" Price: ").append(this.Price)
				.append(" ISBN: ").append(this.ISBN).append(" Genre: ").append(this.Genre).append(" Year: ").append(this.Year).toString();
	}
	
	//Overridden equals method
	public boolean equals(Object b) {
		return (this == b);
	}
}
