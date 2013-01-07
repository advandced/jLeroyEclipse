package fr.la.jproductbase.metier;

import java.io.Serializable;

public class Datamatrix implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String reference = "";
	private String majorIndex = "";
	private String minorIndex = "";
	private String datecode = "";
	private String serialNumber = "";
	private String providerCode = "";

	public Datamatrix(String data) throws DatamatrixException {
		if (null != data) {
			// Supp space after and before
			String _data = data.trim();
			
			if (_data.contains("-")) {
				// Product datamatrix
				this.type = "Product";
				
				String[] _datas = _data.split("-");
				
				if (5 == _datas.length) {
					int _dataLenght = _datas[0].length();
					this.reference = _datas[0].substring(0, (_dataLenght -1));
					this.majorIndex = _datas[0].substring((_dataLenght -1));
					
					this.minorIndex = "";
					
					this.serialNumber = _datas[1];
					
					this.datecode = _datas[2];
					
					this.providerCode = _datas[3];
				} else {
					throw new DatamatrixException("Datamatrix produit (" + _data + ") non reconnu.");
				}
			} else {
				this.type = "Card";
				if (_data.contains(" ")) {
					// FEDD card datamatrix
					String[] _datas = _data.split("(\\s+)");
					
					int _dataLenght = _datas[0].length();
					
					// Référence
					int _refBegin = 0;
					int _refEnd = 6;
					// Indice majeur
					int _majorIndexBegin = _refEnd;
					int _majorIndexEnd = _majorIndexBegin +1;
					// Provider code
					int _provideCodeBegin = _dataLenght -3;
					int _provideCodeEnd = _dataLenght;
					// Indice mineur
					int _minorIndexBegin = _majorIndexEnd;
					int _minorIndexEnd = _provideCodeBegin;
					
					// Premiers caractères
					this.reference = "FCIE" + _datas[0].substring(_refBegin, _refEnd);
					// 1 caractère après le n° de série
					this.majorIndex = _datas[0].substring(_majorIndexBegin, _majorIndexEnd);
					// 1 caractère après l'indice majeur
					this.minorIndex = _datas[0].substring(_minorIndexBegin, _minorIndexEnd);
					// 3 derniers caractères
					this.providerCode = _datas[0].substring(_provideCodeBegin, _provideCodeEnd);
					
					_dataLenght = _datas[1].length();
					this.datecode = _datas[1].substring(0, 4);
					this.serialNumber = _datas[1].substring(4);
				} else {
					if (17 == _data.length()) {
						// DUAGON card datamatrix
						this.reference = "D113 L.T.-P4B2";
						this.majorIndex = "5496";
						this.minorIndex = "";
						this.providerCode = "507";
						
						this.serialNumber = _data.substring(0, 9);
					} else {
						throw new DatamatrixException("Datamatrix carte (" + _data + ") non reconnu.");
					}
				}
			}
		} else {
			throw new DatamatrixException("Datamatrix vide.");
		}
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the majorIndex
	 */
	public String getMajorIndex() {
		return majorIndex;
	}

	/**
	 * @param majorIndex the majorIndex to set
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
	 * @param minorIndex the minorIndex to set
	 */
	public void setMinorIndex(String minorIndex) {
		this.minorIndex = minorIndex;
	}

	/**
	 * @return the datecode
	 */
	public String getDatecode() {
		return datecode;
	}

	/**
	 * @param datecode the datecode to set
	 */
	public void setDatecode(String datecode) {
		this.datecode = datecode;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the providerCode
	 */
	public String getProviderCode() {
		return providerCode;
	}

	/**
	 * @param providerCode the providerCode to set
	 */
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
}
