package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe m&eacute;tier de d&eacute;faut.
 * 
 * @author stc
 * 
 */
public class Failure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idFailure;
	private Timestamp timestamp;
	private int state;
	private Date diagnosisDate;
	private String topoRef;
	private String failureCause;
	private String cardFace;
	private String manufacturingTechnique;
	private String failureCode;
	private String imputationCode;
	private boolean beyondRepair;
	private Operator operator;
	private Product product;
	private Failure newFailureCardChanged;
	private List<ElementChanged> elementsChanged = new ArrayList<ElementChanged>();
	private boolean dismantleCard;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param diagnosisDate
	 *            Date de diagnostic.
	 * @param topoRef
	 *            Rep&eacute;re topographique.
	 * @param failureCause
	 *            Composant chang&eacute; &grave; l'origine de la panne.
	 * @param cardFace
	 *            Face sur laquelle se trouve le composant chang&eacute;.
	 * @param manufacturingTechnique
	 *            Type du composant chang&eacute;.
	 * @param failureCode
	 *            Code d&eacute;faut.
	 * @param operator
	 *            Op&eacute;rateur ayant effectu&eacute; la r&eacute;paration.
	 * @param product
	 *            Produit concern&eacute;.
	 */
	public Failure(Date diagnosisDate, String topoRef, String failureCause,
			String cardFace, String manufacturingTechnique, String failureCode,
			Operator operator, Product product) {
		this.diagnosisDate = diagnosisDate;
		this.topoRef = topoRef;
		this.failureCause = failureCause;
		this.cardFace = cardFace;
		this.manufacturingTechnique = manufacturingTechnique;
		this.failureCode = failureCode;
		this.operator = operator;
		this.product = product;
	}

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param diagnosisDate
	 *            Date de diagnostic.
	 * @param topoRef
	 *            Rep&eacute;re topographique.
	 * @param failureCause
	 *            Composant chang&eacute; &grave; l'origine de la panne.
	 * @param cardFace
	 *            Face sur laquelle se trouve le composant chang&eacute;.
	 * @param manufacturingTechnique
	 *            Type du composant chang&eacute;.
	 * @param failureCode
	 *            Code d&eacute;faut.
	 * @param operator
	 *            Op&eacute;rateur ayant effectu&eacute; la r&eacute;paration.
	 * @param product
	 *            Produit concern&eacute;.
	 * @param elementsChanged
	 *            El&eacute;ments chang&eacute;s.
	 */
	public Failure(Date diagnosisDate, String topoRef, String failureCause,
			String cardFace, String manufacturingTechnique, String failureCode,
			Operator operator, Product product,
			List<ElementChanged> elementsChanged, boolean dismantleCard) {
		this(diagnosisDate, topoRef, failureCause, cardFace,
				manufacturingTechnique, failureCode, operator, product);
		this.elementsChanged = elementsChanged;
		this.dismantleCard = dismantleCard;
	}
	
	
	

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param idFailure
	 *            Identifiant du d&eacute;faut.
	 * @param diagnosisDate
	 *            Date de diagnostic.
	 * @param topoRef
	 *            Rep&eacute;re topographique.
	 * @param failureCause
	 *            Composant chang&eacute; &grave; l'origine de la panne.
	 * @param cardFace
	 *            Face sur laquelle se trouve le composant chang&eacute;.
	 * @param manufacturingTechnique
	 *            Type du composant chang&eacute;.
	 * @param failureCode
	 *            Code d&eacute;faut.
	 * @param operator
	 *            Op&eacute;rateur ayant effectu&eacute; la r&eacute;paration.
	 * @param product
	 *            Produit concern&eacute;.
	 * @param elementsChanged
	 *            El&eacute;ments chang&eacute;s.
	 */
	public Failure(int idFailure, Date diagnosisDate, String topoRef,
			String failureCause, String cardFace,
			String manufacturingTechnique, String failureCode,
			Operator operator, Product product,
			List<ElementChanged> elementsChanged, boolean dismantleCard) {
		this(diagnosisDate, topoRef, failureCause, cardFace,
				manufacturingTechnique, failureCode, operator, product,
				elementsChanged, dismantleCard);
		this.idFailure = idFailure;
	}
	
	public Failure(int idFailure, Date diagnosisDate, String topoRef,
			String failureCause, String cardFace,
			String manufacturingTechnique, String failureCode,
			Operator operator, Product product,
			List<ElementChanged> elementsChanged, boolean dismantleCard, Failure newFailureCardChanged) {
		this(diagnosisDate, topoRef, failureCause, cardFace,
				manufacturingTechnique, failureCode, operator, product,
				elementsChanged, dismantleCard);
		this.idFailure = idFailure;
		this.newFailureCardChanged = newFailureCardChanged;
	}

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param idFailure
	 *            Identifiant du d&eacute;faut.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param diagnosisDate
	 *            Date de diagnostic.
	 * @param topoRef
	 *            Rep&eacute;re topographique.
	 * @param failureCause
	 *            Composant chang&eacute; &grave; l'origine de la panne.
	 * @param cardFace
	 *            Face sur laquelle se trouve le composant chang&eacute;.
	 * @param manufacturingTechnique
	 *            Type du composant chang&eacute;.
	 * @param failureCode
	 *            Code d&eacute;faut.
	 * @param imputationCode
	 *            Code d'imputation.
	 * @param beyondRepair
	 *            Produit r&eacute;parable.
	 * @param operator
	 *            Op&eacute;rateur ayant effectu&eacute; la r&eacute;paration.
	 * @param product
	 *            Produit concern&eacute;.
	 */
	public Failure(int idFailure, Timestamp timestamp, int state,
			Date diagnosisDate, String topoRef, String failureCause,
			String cardFace, String manufacturingTechnique, String failureCode,
			String imputationCode, boolean beyondRepair, Operator operator,
			Product product, boolean dismantleCard) {
		this(diagnosisDate, topoRef, failureCause, cardFace,
				manufacturingTechnique, failureCode, operator, product);
		this.idFailure = idFailure;
		this.timestamp = timestamp;
		this.state = state;
		this.imputationCode = imputationCode;
		this.beyondRepair = beyondRepair;
		this.dismantleCard = dismantleCard;
	}
	
	
	public Failure(int idFailure, Timestamp timestamp, int state,
			Date diagnosisDate, String topoRef, String failureCause,
			String cardFace, String manufacturingTechnique, String failureCode,
			String imputationCode, boolean beyondRepair, Operator operator,
			Product product, boolean dismantleCard, Failure failureDismantleCard) {
		this(diagnosisDate, topoRef, failureCause, cardFace,
				manufacturingTechnique, failureCode, operator, product);
		this.idFailure = idFailure;
		this.timestamp = timestamp;
		this.state = state;
		this.imputationCode = imputationCode;
		this.beyondRepair = beyondRepair;
		this.dismantleCard = dismantleCard;
		this.newFailureCardChanged = failureDismantleCard;
	}
	
	public Failure(){}
	
	
	
	
	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param failureCause
	 *            Composant chang&eacute; &grave; l'origine de la panne.
	 * @param failureCode
	 *            Code d&eacute;faut.
	 * @param imputationCode
	 *            Code d'imputation.
	 * @param dismantledCard
	 * 			  Carte d&eacute;mont&eacute;e ou non.
	 * @param elementsChanged
	 *            El&eacute;ments chang&eacute;s.
	 * @param product
	 *            Produit concern&eacute;.
	 */
	public Failure(String failureCause, String failureCode,	
			String imputationCode, boolean dismantledCard, 
			List<ElementChanged> elementsChanged, Product product) {
		this.failureCause = failureCause;
		this.failureCode = failureCode;
		this.imputationCode = imputationCode;
		this.dismantleCard = dismantledCard;
		this.elementsChanged = elementsChanged;
		this.product = product;
	}

	/**
	 * GETTERS AND SETTERS
	 */

	

	/**
	 * @return the idFailure
	 */
	public int getIdFailure() {
		return idFailure;
	}

	/**
	 * @param idFailure
	 *            the idFailure to set
	 */
	public void setIdFailure(int idFailure) {
		this.idFailure = idFailure;
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
	 * @return the diagnosisDate
	 */
	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	/**
	 * @param diagnosisDate
	 *            the diagnosisDate to set
	 */
	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	/**
	 * @return the topoRef
	 */
	public String getTopoRef() {
		return topoRef;
	}

	/**
	 * @param topoRef
	 *            the topoRef to set
	 */
	public void setTopoRef(String topoRef) {
		this.topoRef = topoRef;
	}

	/**
	 * @return the failureCause
	 */
	public String getFailureCause() {
		return failureCause;
	}

	/**
	 * @param failureCause
	 *            the failureCause to set
	 */
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

	/**
	 * @return the cardFace
	 */
	public String getCardFace() {
		return cardFace;
	}

	/**
	 * @param cardFace
	 *            the cardFace to set
	 */
	public void setCardFace(String cardFace) {
		this.cardFace = cardFace;
	}

	/**
	 * @return the manufacturingTechnique
	 */
	public String getManufacturingTechnique() {
		return manufacturingTechnique;
	}

	/**
	 * @param manufacturingTechnique
	 *            the manufacturingTechnique to set
	 */
	public void setManufacturingTechnique(String manufacturingTechnique) {
		this.manufacturingTechnique = manufacturingTechnique;
	}

	/**
	 * @return the failureCode
	 */
	public String getFailureCode() {
		return failureCode;
	}

	/**
	 * @param failureCode
	 *            the failureCode to set
	 */
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}

	/**
	 * @return the imputationCode
	 */
	public String getImputationCode() {
		return imputationCode;
	}

	/**
	 * @param imputationCode
	 *            the imputationCode to set
	 */
	public void setImputationCode(String imputationCode) {
		this.imputationCode = imputationCode;
	}

	/**
	 * @return the beyondRepair
	 */
	public boolean isBeyondRepair() {
		return beyondRepair;
	}

	/**
	 * @param beyondRepair
	 *            the beyondRepair to set
	 */
	public void setBeyondRepair(boolean beyondRepair) {
		this.beyondRepair = beyondRepair;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the elementsChanged
	 */
	public List<ElementChanged> getElementsChanged() {
		return elementsChanged;
	}

	/**
	 * @param elementsChanged
	 *            the elementsChanged to set
	 */
	public void setElementsChanged(List<ElementChanged> elementsChanged) {
		this.elementsChanged = elementsChanged;
	}

	/**
	 * @return the dismantleCard
	 */
	public boolean isDismantleCard() {
		return dismantleCard;
	}

	/**
	 * @param dismantleCard
	 *            the dismantleCard to set
	 */
	public void setDismantleCard(boolean dismantleCard) {
		this.dismantleCard = dismantleCard;
	}

	/**
	 * @return the dateFormat
	 */
	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	
	
	public Failure getNewFailureCardChanged() {
		return newFailureCardChanged;
	}

	
	
	
	

	/**
	 * PUBLIC METHODS
	 */


	public void setNewFailureCardChanged(Failure newFailureCardChanged) {
		this.newFailureCardChanged = newFailureCardChanged;
	}

	/**
	 * Ajout d'un &eacute;l&eacute;ment changé au d&eacute;faut.
	 * 
	 * @param elementChanged
	 *            El&eacute;ment changé &agrave; ajouter.
	 */
	public boolean addElementChanged(ElementChanged elementChanged) {
		boolean _addResult = false;
		if (!(null != this.elementsChanged)) {
			this.elementsChanged = new ArrayList<ElementChanged>();
		}
		if (null != elementChanged) {
			_addResult = this.elementsChanged.add(elementChanged);
		} else {
			// Don't add null value
		}

		return _addResult;
	}

	/**
	 * Supprime un &eacute;l&eacute;ment changé du d&eacute;faut.
	 * 
	 * @param elementChanged
	 *            El&eacute;ment changé &agrave; supprimer.
	 */
	public boolean removeElementChanged(ElementChanged elementChanged) {
		boolean _removeResult = false;
		if (null != elementChanged) {
			_removeResult = this.elementsChanged.remove(elementChanged);
		} else {
			// Don't remove null value
		}

		return _removeResult;
	}

	@Override
	public String toString() {
		return "Failure [idFailure=" + idFailure + ", timestamp=" + timestamp
				+ ", state=" + state + ", diagnosisDate=" + diagnosisDate
				+ ", topoRef=" + topoRef + ", failureCause=" + failureCause
				+ ", cardFace=" + cardFace + ", manufacturingTechnique="
				+ manufacturingTechnique + ", failureCode=" + failureCode
				+ ", imputationCode=" + imputationCode + ", beyondRepair="
				+ beyondRepair + ", operator=" + operator + ", product="
				+ product + ", newFailureCardChanged=" + newFailureCardChanged
				+ ", elementsChanged=" + elementsChanged + ", dismantleCard="
				+ dismantleCard + "]";
	}

	
	
	
	
	
}
