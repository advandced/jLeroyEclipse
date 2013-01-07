package fr.la.jproductbase.metier;

import java.io.Serializable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PreTesterReportHandler extends DefaultHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PreTesterReport preTesterReport;
	private ProductTest productTest;

	// buffer nous permettant de récupérer les données
	private StringBuffer buffer;

	public PreTesterReportHandler() {
		super();
	}

	// Détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("PreTesterReport")) {
			this.preTesterReport = new PreTesterReport();
		} else {
			if (qName.equals("productTest")) {
				// Initialize ProductTest
				this.productTest = new ProductTest();
			} else {
				this.buffer = new StringBuffer();

				// testType
				if (qName.equals("testType")) {
					// Process each attribute
					for (int index = 0; index < attributes.getLength(); index++) {
						if (attributes.getQName(index).equals("flow")) {
							if (attributes.getValue(index).equals("true")) {
								this.productTest.setInFlow(true);
							} else {
								this.productTest.setInFlow(false);
							}
						} else {
							// Don't process attribute
						}
					}
				} else {
					// Don't process attributes
				}
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
		if (qName.equals("productTest")) {
			// Add PreTesterReport to productTests list
			this.preTesterReport.addProductTest(this.productTest);
		} else {
			if (qName.equals("reference")) {
				this.productTest
						.setProductConfReference(this.buffer.toString());
			} else {
				if (qName.equals("serialNumber")) {
					this.productTest.setProductSerialNumber(this.buffer
							.toString());
				} else {
					if (qName.equals("datecode")) {
						this.productTest.setProductDatecode(this.buffer
								.toString());
					} else {
						if (qName.equals("labview")) {
							this.productTest.setLabviewTestType(this.buffer
									.toString());
						} else {
							if (qName.equals("code")) {
								this.productTest.setOperatorCode(this.buffer
										.toString());
							} else {
								if (qName.equals("test")) {
									this.preTesterReport.setResult(this.buffer
											.toString());
								} else {
									if (qName.equals("confirm")) {
										if (this.buffer.toString().equals(
												"true")) {
											this.preTesterReport
													.setResultConfirmation(true);
										} else {
											this.preTesterReport
													.setResultConfirmation(false);
										}
									} else {
										if (qName.equals("customerComment")) {
											this.preTesterReport
													.setCustomerComment(this.buffer
															.toString());
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

		this.buffer = null;
	}

	/**
	 * @return the preTesterReport
	 */
	public PreTesterReport getPreTesterReport() {
		return preTesterReport;
	}
}
