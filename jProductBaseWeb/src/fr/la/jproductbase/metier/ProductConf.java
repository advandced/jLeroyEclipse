package fr.la.jproductbase.metier;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.service.ServiceInterface;

/**
 * Classe m&eacute;tier d'une configuration produit.
 * 
 * @author stc
 * 
 */
public class ProductConf implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int idProductConf;
	protected Timestamp timestamp;
	protected int state; // 0: Disable, 1: Enable
	protected String reference;
	protected String majorIndex;
	protected String minorIndex;
	protected boolean identifiable;
	protected ProductSupply productSupply;
	protected ProductFamily productFamily;
	protected List<ProductConf> productConfComponents;
	protected List<Software> productConfSoftwares = new ArrayList<Software>();
	protected FollowingFormModel followingForm;
	protected ProductLine productLine;
	protected ProductConfModel productConfModel;

	/**
	 * Cr&eacute;er une configuration produit.
	 * 
	 * @param idProductConf
	 *            Identifiant de la configuration produit.
	 * @param timeStamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param reference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param majorIndex
	 *            Indice majeur de la configuration produit.
	 * @param minorIndex
	 *            Indice mineur de la configuration produit.
	 * @param identifiable
	 *            Configuration produit identifiable.
	 * @param productSupply
	 *            Alimentation de la configuration produit.
	 * @param productFamily
	 *            Famille de la configuration produit.
	 * @param followingFormModel
	 *            Mod&eacute;le de fiche suiveuse de la configuration produit.
	 * @param productLine
	 *            Gamme produit.
	 * @param productConfModel
	 *            Mod&eacute;le produit.
	 */
	public ProductConf(int idProductConf, Timestamp timeStamp, int state,
			String reference, String majorIndex, String minorIndex,
			boolean identifiable, ProductSupply productSupply,
			ProductFamily productFamily, FollowingFormModel followingFormModel,
			ProductLine productLine, ProductConfModel productConfModel) {
		this.idProductConf = idProductConf;
		this.timestamp = timeStamp;
		this.state = state;
		this.reference = reference;
		this.majorIndex = majorIndex;
		this.minorIndex = minorIndex;
		this.identifiable = identifiable;
		this.productSupply = productSupply;
		this.productFamily = productFamily;
		this.followingForm = followingFormModel;
		this.productLine = productLine;
		this.productConfModel = productConfModel;
	}
	public ProductConf(){}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idProductConf
	 */
	public int getIdProductConf() {
		return idProductConf;
	}

	/**
	 * @param idProductConf
	 *            the idProductConf to set
	 */
	public void setIdProductConf(int idProductConf) {
		this.idProductConf = idProductConf;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the productSupply
	 */
	public ProductSupply getProductSupply() {
		return productSupply;
	}

	/**
	 * @param productSupply
	 *            the productSupply to set
	 */
	public void setProductSupply(ProductSupply productSupply) {
		this.productSupply = productSupply;
	}

	/**
	 * @return the productFamily
	 */
	public ProductFamily getProductFamily() {
		return productFamily;
	}

	/**
	 * @param productFamily
	 *            the productFamily to set
	 */
	public void setProductFamily(ProductFamily productFamily) {
		this.productFamily = productFamily;
	}

	/**
	 * @return the productConfComponents
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	/*
	public List<ProductConf> getProductConfComponents() {
		if (null == this.productConfComponents) {
			// Retreive productConfComponents
			ServiceInterface _serviceInterface = new ServiceInterface();
			this.productConfComponents = _serviceInterface.getProductConfComponents(this);
		} else {
			// data already
		}

		return productConfComponents;
	}
	*/
	
	/**
	 * @param productConfComponents
	 *            the productConfComponents to set
	 */
	public void setProductConfComponents(List<ProductConf> productConfComponents) {
		this.productConfComponents = productConfComponents;
	}

	/**
	 * @return the productConfSoftwares
	 */
	public List<Software> getProductConfSoftwares() {
		return productConfSoftwares;
	}

	/**
	 * @param productConfSoftwares
	 *            the productConfSoftwares to set
	 */
	public void setProductConfSoftwares(List<Software> productConfSoftwares) {
		this.productConfSoftwares = productConfSoftwares;
	}

	/**
	 * @return the majorIndex
	 */
	public String getMajorIndex() {
		return majorIndex;
	}

	/**
	 * @param majorIndex
	 *            the majorIndex to set
	 */
	public void setMajorIndex(String majorIndex) {
		this.majorIndex = majorIndex;
	}

	/**
	 * @return the minorIndex
	 */
	public String getMinorIndex() {
		return minorIndex;
	}

	/**
	 * @param minorIndex
	 *            the minorIndex to set
	 */
	public void setMinorIndex(String minorIndex) {
		this.minorIndex = minorIndex;
	}

	/**
	 * @return the identifiable
	 */
	public boolean isIdentifiable() {
		return identifiable;
	}

	/**
	 * @param identifiable
	 *            the identifiable to set
	 */
	public void setIdentifiable(boolean identifiable) {
		this.identifiable = identifiable;
	}

	/**
	 * @return the followingForm
	 */
	public FollowingFormModel getFollowingForm() {
		return followingForm;
	}

	/**
	 * @param followingForm
	 *            the followingForm to set
	 */
	public void setFollowingForm(FollowingFormModel followingForm) {
		this.followingForm = followingForm;
	}

	/**
	 * @return the productLine
	 */
	public ProductLine getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine
	 *            the productLine to set
	 */
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	/**
	 * @return the productConfModel
	 */
	public ProductConfModel getProductConfModel() {
		return productConfModel;
	}

	/**
	 * @param productConfModel
	 *            the productConfModel to set
	 */
	public void setProductConfModel(ProductConfModel productConfModel) {
		this.productConfModel = productConfModel;
	}

	/**
	 * PUBLIC METHODS
	 */

	/**
	 * Ajout d'un composant &grave; la configuration produit.
	 * 
	 * @param productConfComponent
	 *            Composant &agrave; ajouter.
	 */
	public void addProductConfComponent(ProductConf productConfComponent) {
		if (null != productConfComponent) {
			this.productConfComponents.add(productConfComponent);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un composant de la configuration produit
	 * 
	 * @param productConfComponent
	 *            Composant &agrave; supprimer.
	 */
	public void removeProductConfComponent(ProductConf productConfComponent) {
		if (null != productConfComponent) {
			this.productConfComponents.remove(productConfComponent);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Ajout d'un logiciel &grave; la configuration produit.
	 * 
	 * @param productConfSoftware
	 *            Logiciel &agrave; ajouter.
	 */
	public void addSoftware(Software productConfSoftware) {
		if (null != productConfSoftware) {
			this.productConfSoftwares.add(productConfSoftware);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un logiciel de la configuration produit
	 * 
	 * @param productConfSoftware
	 *            Logiciel &agrave; supprimer.
	 */
	public void removeSoftware(Software productConfSoftware) {
		if (null != productConfSoftware) {
			this.productConfSoftwares.remove(productConfSoftware);
		} else {
			// Don't add null value
		}
	}
	
	@Override
	public String toString() {
		return "ProductConf [idProductConf=" + idProductConf + ", timestamp="
				+ timestamp + ", state=" + state + ", reference=" + reference
				+ ", majorIndex=" + majorIndex + ", minorIndex=" + minorIndex
				+ ", identifiable=" + identifiable + ", productSupply="
				+ productSupply + ", productFamily=" + productFamily
				+ ", productConfComponents=" + productConfComponents
				+ ", productConfSoftwares=" + productConfSoftwares
				+ ", followingForm=" + followingForm + ", productLine="
				+ productLine + ", productConfModel=" + productConfModel + "]";
	}

	
	
	
	
	
	
}
