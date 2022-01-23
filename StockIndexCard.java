/**
 * Creates the obj StockIndexCard, toString, 5 primitives, getters and setters for each primitive 
 * @author andy phan
 *
 */
public class StockIndexCard {
	private int sicID;				//ID of the card
	private String title;			//title of the book
	private String author;			//author of the book
	private double price;			//price of a book
	private int quantity;			//quantity of books
	
	/**
	 * Constructor for a StockIndexCard
	 * @param id takes in the unique ID
	 * @param tit takes in the title
	 * @param auth takes in the author
	 * @param pr takes in the price
	 * @param quan takesi nthe quantity
	 */
	public StockIndexCard(int id, String tit, String auth, double pr, int quan) {
		sicID = id;
		title = tit;
		author = auth;
		price = pr;
		quantity = quan;
	}//end constructor

	/**
	 * getter for the ID
	 * @return the ID
	 */
	public int getSicID() {
		return sicID;
	}
	/**
	 * setter for the ID
	 * @param newID takes in the new ID and assigns it to the current ID
	 */
	public void setSicID(int newID) {
		this.sicID = newID;
	}
	/**
	 * getter for the title
	 * @return the name of the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setter for the title
	 * @param newTitle takes in the new title and assigns it to the current title
	 */
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	/**
	 * getter for the author
	 * @return the name of the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * setter for the author
	 * @param newAuthor takes in the new author and assigns it to the current author
	 */
	public void setAuthor(String newAuthor) {
		this.author = newAuthor;
	}
	/**
	 * getter for the price
	 * @return the price of the book
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * setter for the price
	 * @param newPrice takes in the new cost of a book and assigns it to the current price
	 */
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	/**
	 * getter for the quantity
	 * @return the number of books in stock
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * setter for the quantity
	 * @param newQuantity takes in the new quantity and assigns it to the current quantity
	 */
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	/**
	 * toString returns all the current values of a StockCard
	 */
	@Override
	public String toString() {
		return "\nStockIndexCard [\nsicID=" + sicID + ", \ntitle=" + title + ",\nauthor=" + author + ", \nprice=$" + price
				+ ", \nquantity=" + quantity + "]\n";
	}
}//end class
