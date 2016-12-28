package au.com.amaysim.main;

import java.math.BigDecimal;

/**
 * @author FSantos1
 * 
 *         This class persists the products. The products should be a database
 *         of some sort.
 * 
 *         The spreadsheet products.csv is the DS or data source in this
 *         exercise.
 * 
 */
public class Product {

	String productCode;
	String productName;
	BigDecimal price;

	public Product() {
		super();
	}

	public Product(String productCode, String productName, BigDecimal price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String toString() {
		return getProductCode() + ":" + getProductName() + ":" + getPrice();
	}

}
