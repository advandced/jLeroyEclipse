package fr.la.jproductbase.metier;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.la.jproductbase.service.ServiceInterface;
import java.io.Serializable;

public class ProductIntegrationHandler extends DefaultHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	// Component
	private String reference;
	private String majorIndex;
	private String minorIndex;
	private String datecode;
	private String serialNumber;
	private String providerCode;
	// Software
	private String name;
	private String version;

	// buffer nous permettant de récupérer les données
	private StringBuffer buffer;

	// simple constructeur
	public ProductIntegrationHandler() {
		super();
	}

	// Détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("product")) {
			// Init product data
			this.product = null;
			this.datecode = "";
			this.serialNumber = "";
			this.minorIndex = "";
			this.providerCode = "";
		} else {
			if (qName.equals("component")) {
				if (null == this.product) {
					// Record product
					try {
						ServiceInterface _serviceInterface = new ServiceInterface();
						ProductConf _productConf = _serviceInterface
								.getProductConf(this.reference,
										this.majorIndex, this.minorIndex);
						this.product = new Product(_productConf, this.datecode,
								this.serialNumber, this.providerCode);
						this.product.setProductComponents(new ArrayList<Product>());
					} catch (Exception e) {
						// Propage l'exception
						throw new SAXException(e.getMessage());
					}
				} else {
					// Product already recorded
				}

				// Init component data
				this.datecode = "";
				this.serialNumber = "";
				this.minorIndex = "";
				this.providerCode = "";
			} else {
				this.buffer = new StringBuffer();
			}
		}
	}

	// Détection de caractères
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String _data = new String(ch, start, length);
		if (null != this.buffer) {
			this.buffer.append(_data.trim());
		}
	}

	// Détection de fermeture de balise
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("product")) {
			// Save product to bdd
			try {
				ServiceInterface _serviceInterface = new ServiceInterface();
				_serviceInterface.setProduct(this.product);
			} catch (Exception e) {
				// Propage l'exception
				throw new SAXException(e.getMessage());
			}
		} else {
			if (qName.equals("component")) {
				// Record component
				try {
					ServiceInterface _serviceInterface = new ServiceInterface();
					ProductConf _productConf = _serviceInterface
							.getProductConf(this.reference, this.majorIndex,
									this.minorIndex);
					Product _component = new Product(_productConf,
							this.datecode, this.serialNumber, this.providerCode);
					this.product.addProductComponent(_component);
				} catch (Exception e) {
					e.printStackTrace();

					// Propage l'exception
					throw new SAXException(e.getMessage());
				}
			} else {
				if (qName.equals("software")) {
					// Record software
					try {
						Software _software = new Software(this.name,
								this.version);
						this.product.addSoftware(_software);
					} catch (Exception e) {
						// Propage l'exception
						throw new SAXException(e.getMessage());
					}
				} else {
					// Update data
					if (qName.equals("reference")) {
						this.reference = this.buffer.toString();
					} else {
						if (qName.equals("major")) {
							this.majorIndex = this.buffer.toString();
						} else {
							if (qName.equals("minor")) {
								this.minorIndex = this.buffer.toString();
							} else {
								if (qName.equals("serialNumber")) {
									this.serialNumber = this.buffer.toString();
								} else {
									if (qName.equals("datecode")) {
										this.datecode = this.buffer.toString();
									} else {
										if (qName.equals("provider")) {
											this.providerCode = this.buffer
													.toString();
										} else {
											if (qName.equals("name")) {
												this.name = this.buffer
														.toString();
											} else {
												if (qName.equals("version")) {
													this.version = this.buffer
															.toString();
												} else {
													// Not pertinent data
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		this.buffer = null;
	}
}
