package fr.la.jproductbase.metier;

import java.io.Serializable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LabviewTesterReportHandler extends DefaultHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LabviewTesterReport labviewTesterReport;
	private Defect defect;

	// buffer nous permettant de récupérer les données
	private StringBuffer buffer;

	public LabviewTesterReportHandler() {
		super();
	}

	// Détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("Rapport")) {
			// Root element
			this.labviewTesterReport = new LabviewTesterReport();
		} else {
			if (qName.equals("Tests")) {
				this.defect = new Defect();
			} else {
			}

			this.buffer = new StringBuffer();
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
		if (qName.equals("Tests")) {
			this.labviewTesterReport.addDefect(this.defect);
		} else {
			// Update data
			if (qName.equals("Type_produit")) {
				this.labviewTesterReport.setTypeProduit(this.buffer.toString());
			} else {
				if (qName.equals("Date")) {
					this.labviewTesterReport.setDate(this.buffer.toString());
				} else {
					if (qName.equals("Heure")) {
						this.labviewTesterReport.setHeure(this.buffer
								.toString());
					} else {
						if (qName.equals("Version_test")) {
							this.labviewTesterReport.setVersionTest(this.buffer
									.toString());
						} else {
							if (qName.equals("Type_de_test")) {
								this.labviewTesterReport
										.setTypeTest(this.buffer.toString());
							} else {
								if (qName.equals("Station_ID")) {
									this.labviewTesterReport
											.setStationId(this.buffer
													.toString());
								} else {
									if (qName.equals("Serial_number")) {
										this.labviewTesterReport
												.setSerialNumber(this.buffer
														.toString());
									} else {
										if (qName.equals("Ref_materielle")) {
											this.labviewTesterReport
													.setRefMaterielle(this.buffer
															.toString());
										} else {
											if (qName.equals("Resultat")) {
												this.labviewTesterReport
														.setResultat(this.buffer
																.toString());
											} else {
												if (qName.equals("Mac_adresse")) {
													this.labviewTesterReport
															.setMacAdresse(this.buffer
																	.toString());
												} else {
													if (qName
															.equals("Conso_Umini")) {
														this.labviewTesterReport
																.setConsoUmini(this.buffer
																		.toString());
													} else {
														if (qName
																.equals("Conso_Unomi")) {
															this.labviewTesterReport
																	.setConsoUnomi(this.buffer
																			.toString());
														} else {
															if (qName
																	.equals("Conso_Umaxi")) {
																this.labviewTesterReport
																		.setConsoUmaxi(this.buffer
																				.toString());
															} else {
																if (qName
																		.equals("Sequence")) {
																	this.defect
																			.setSequence(this.buffer
																					.toString());
																} else {
																	if (qName
																			.equals("Test")) {
																		this.defect
																				.setTestName(this.buffer
																						.toString());
																	} else {
																		if (qName
																				.equals("Fonction")) {
																			this.defect
																					.setFunction(this.buffer
																							.toString());
																		} else {
																			if (qName
																					.equals("Valeur")) {
																				this.defect
																						.setValue(this.buffer
																								.toString());
																			} else {
																				// No
																				// pertinent
																				// data
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
								}
							}
						}
					}
				}
			}

			this.buffer = null;
		}
	}

	/**
	 * @return the labviewTesterReport
	 */
	public LabviewTesterReport getLabviewTesterReport() {
		return labviewTesterReport;
	}

	/**
	 * @param labviewTesterReport
	 *            the labviewTesterReport to set
	 */
	public void setLabviewTesterReport(LabviewTesterReport labviewTesterReport) {
		this.labviewTesterReport = labviewTesterReport;
	}
}
