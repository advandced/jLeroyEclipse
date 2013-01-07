package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de document li&eacute; à un produit.
 * exemple : d&eacute;rogation
 * 
 * @author rmo
 * 
 */
public class ProductDocument implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProductDocument;
	private Timestamp timestamp;
	private int state;
	private String title;
	private String link;
	private ProductDocumentType documentType;
	private Product product;

	/**
	 * Cr&eacute;er un document.
	 * 
	 * @param idProductDocument
	 *            Identifiant du document.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param timestamp
	 * @param state
	 * @param title
	 * @param link
	 * @param documentType
	 * @param product
	 *            Produit concernn&eacute; par le rapport.
	 */
	public ProductDocument(int idProductDocument, Timestamp timestamp,
			int state, String title, String link, ProductDocumentType documentType, Product product) {
		this.idProductDocument = idProductDocument;
		this.timestamp = timestamp;
		this.state = state;
		this.title = title;
		this.link = link;
		this.documentType = documentType;
		this.product = product;
	}
	
	/**
	 * GETTERS AND SETTERS
	 */

	public int getIdProductDocument() {
		return idProductDocument;
	}

	public void setIdProductDocument(int idProductDocument) {
		this.idProductDocument = idProductDocument;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ProductDocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(ProductDocumentType documentType) {
		this.documentType = documentType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}	
	
}
