package fr.la.jproductbase.metier;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;

/**
 * Classe m&eacute;tier de produit.
 * 
 * @author stc
 * 
 */
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int idProduct;
	protected Timestamp timestamp;
	protected int state; // 0: Disable, 1: Enable
	protected String serialNumber;
	protected String datecode;
	protected String macAddress;
	protected String providerCode;
	protected ProductConf productConf;
	protected List<Product> productComponents;


	protected List<Software> productSoftwares = new ArrayList<Software>();
    private Product mother;

	/**
	 * Creer un produit.
	 * 
	 * @param idProduct
	 *            Identifiant du produit.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param serialNumber
	 *            Numero de serie du produit.
	 * @param datecode
	 *            Datecode du produit.
	 * @param macAddress
	 *            Adresse MAC du produit.
	 * @param providerCode
	 *            Code du fournisseurs.
	 * @param productConf
	 *            Configuration produit auquel appartient le produit.
	 */
	public Product(int idProduct, Timestamp timestamp, int state,
			String serialNumber, String datecode, String macAddress,
			String providerCode, ProductConf productConf) {
		this(productConf, datecode, serialNumber, providerCode);
		this.idProduct = idProduct;
		this.timestamp = timestamp;
		this.state = state;
		this.macAddress = macAddress;
	}
	
	public Product(){}

	/**
	 * Cr&eacute;er un produit.
	 * 
	 * @param productConf
	 *            Configuration produit auquel appartient le produit.
	 * @param datecode
	 *            Datecode du produit.
	 * @param serialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param providerCode
	 *            Code du fournisseurs.
	 */
	public Product(ProductConf productConf, String datecode,
			String serialNumber, String providerCode) {
		this.productConf = productConf;
		this.datecode = datecode;
		this.setSerialNumber(serialNumber);
		this.setProviderCode(providerCode);
	}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idProduct
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct
	 *            the idProduct to set
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		String _serialNumber;
		if (null != serialNumber) {
			// Valid serialNumber
			_serialNumber = ToolsProduct.deleteAhead("0", serialNumber);
		} else {
			// Invalid serialNumber
			_serialNumber = "";
		}

		this.serialNumber = _serialNumber;
	}

	/**
	 * @return the datecode
	 */
	public String getDatecode() {
		return datecode;
	}

	/**
	 * @param datecode
	 *            the datecode to set
	 */
	public void setDatecode(String datecode) {
		this.datecode = datecode;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @param macAddress
	 *            the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * @return the productConf
	 */
	public ProductConf getProductConf() {
		return productConf;
	}

	/**
	 * @return the productConf from FEDDproductBase
	 */
	public ProductConf getFEDDProductConf() {
		return productConf;
	}

	/**
	 * @param productConf
	 *            the productConf to set
	 */
	public void setProductConf(ProductConf productConf) {
		this.productConf = productConf;
	}

	/**
	 * @return the productComponents
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	/*
	public List<Product> getProductComponents() {
		if (null == this.productComponents) {
			// Retreive productComponents
			ServiceInterface _serviceInterface = new ServiceInterface();
			this.productComponents = _serviceInterface.getProductComponents(this);
		} else {
			// data already
		}

		return productComponents;
	}
	*/
	/**
	 * @param productComponents
	 *            the productComponents to set
	 */
	public void setProductComponents(List<Product> productComponents) {
		this.productComponents = productComponents;
	}

	/**
	 * @return the productSoftwares
	 */
	public List<Software> getProductSoftwares() {
		return productSoftwares;
	}

	/**
	 * @param productSoftwares
	 *            the productSoftwares to set
	 */
	public void setProductSoftwares(List<Software> productSoftwares) {
		this.productSoftwares = productSoftwares;
	}

	/**
	 * @return the providerCode
	 */
	public String getProviderCode() {
		return providerCode;
	}

	/**
	 * @param providerCode
	 *            the providerCode to set
	 */
	public void setProviderCode(String providerCode) {
		String _providerCode;
		if (null != providerCode) {
			// Valid providerCode
			_providerCode = ToolsProduct.deleteAhead("0", providerCode);
		} else {
			// Invalid providerCode
			_providerCode = "";
		}

		this.providerCode = _providerCode;
	}

	/**
	 * PUBLIC METHODS
	 */

	/**
	 * Ajout d'un composant au produit.
	 * 
	 * @param productComponent
	 *            Composant &agrave; ajouter.
	 */
	public void addProductComponent(Product productComponent) {
		if (null != productComponent) {
			this.productComponents.add(productComponent);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un composant du produit
	 * 
	 * @param productComponent
	 *            Composant &agrave; supprimer.
	 */
	public void removeProductComponent(Product productComponent) {
		if (null != productComponent) {
			System.out.println("suppression de product Component" + productComponent);
			this.productComponents.remove(productComponent);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Ajout d'un logiciel au produit.
	 * 
	 * @param software
	 *            Logiciel &agrave; ajouter.
	 */
	public void addSoftware(Software software) {
		if (null != software) {
			this.productSoftwares.add(software);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un logiciel du produit
	 * 
	 * @param software
	 *            Logiciel &agrave; supprimer.
	 */
	public void removeSoftware(Software software) {
		if (null != software) {
			this.productSoftwares.remove(software);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Cr&eacute;ation de la liste des composants du produit &agrave; partir
	 * d'un tableau de composants.
	 * 
	 * @param productComponents
	 *            Tableau des composants.
	 * 
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws NamingException 
	 */


	/**
	 * Cr&eacute;ation de la liste des logiciels du produit &agrave; partir d'un
	 * tableau de logiciels.
	 * 
	 * @param productSoftwares
	 *            Tableau des logiciels.
	 * 
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws NamingException 
	 */


	@Override
	public String toString() {
		return this.productConf.getReference();
	}

    public Product getMother() {
        return mother;
    }

    public void setMother(Product mother) {
        this.mother = mother;
    }
	
	public List<Product> getProductComponents() {
		return productComponents;
	}
}
