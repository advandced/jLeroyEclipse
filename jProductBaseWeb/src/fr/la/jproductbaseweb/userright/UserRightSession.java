package fr.la.jproductbaseweb.userright;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.la.jproductbaseweb.view.bar.MenuWeb;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;

@ManagedBean(name = "userRightSession")
@SessionScoped
public class UserRightSession implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login;
	private List<Autorisation> autorisationList;
	private MenuWeb menuWeb;

	public UserRightSession() throws SQLException {

		this.login = "Halcens";
		
		//RequestContext rcontext = RequestContext.getCurrentInstance(); 
		
		/*FacesContext fcontext = FacesContext.getCurrentInstance();

		LoginBean logBean = (LoginBean) fcontext
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(fcontext.getELContext(), "#{loginBean}",
						LoginBean.class).getValue(fcontext.getELContext());
		
		if (logBean.isUserconnected() == true){
			pageLog = "/logout.jsf";
			nameLog = "Se déconnecter";
			//rcontext.update("layoutNorth:menuForm:masterMenuBar");
			System.out.println("logout");
		} else {
			pageLog = "/login.jsf";
			nameLog = "Se connecter";
			//rcontext.update("layoutNorth:menuForm:masterMenuBar");
			System.out.println("login");
		}*/

		List<Autorisation> _autorisationList = new ArrayList<Autorisation>();
		Ressource _resource = new Ressource(1, "/param/params.jsf",
				"Parametrage", "parametrageBean",
				"ressource pour le parametrage", new Ressource(null));
		Ressource _resourceOp = new Ressource(2,
				"/param/operator.xhtml?faces-redirect=true", "Operateur",
				"gestOperatorBean", "ressource pour gestion operateur",
				_resource);
		Ressource _resourceLog = new Ressource(3,
				"/param/software.xhtml?faces-redirect=true", "Logiciels",
				"gestOperatorBean", "ressource pour gestion logiciel",
				_resource);
		Ressource _resourceAppCause = new Ressource(4,
				"/param/apparentCause.xhtml?faces-redirect=true",
				"Causes Probables", "gestApparentCauseBean",
				"ressource pour gestion apparent cause", _resource);
		Ressource _resourceAppCustomerCause = new Ressource(5,
				"/param/apparentCauseCustomer.xhtml?faces-redirect=true",
				"Cause Probables Client", "gestApparentCauseCustomerBean",
				"ressource pour gestion apparent cause client", _resource);
		Ressource _resourceTester = new Ressource(6,
				"/param/tester.xhtml?faces-redirect=true", "Tester",
				"gestTesterBean", "ressource pour gestion Tester", _resource);
		Ressource _resourceTypeTest = new Ressource(7,
				"/param/typeTest.xhtml?faces-redirect=true", "Types de Test",
				"gestTypeTestBean", "ressource pour gestion Type Test",
				_resource);
		Ressource _resourceConfProd = new Ressource(8,
				"/param/productConf.xhtml?faces-redirect=true",
				"Configuration des produits", "gestProductConfBean",
				"ressource pour Configuration des Produits", _resource);
		Ressource _resourceProdFamily = new Ressource(9,
				"/param/productFamily.xhtml?faces-redirect=true",
				"Familles de produits", "gestProductFamilyBean",
				"ressource pour Configuration des Familles de Produit",
				_resource);
		Ressource _resourceProdType = new Ressource(10,
				"/param/productType.xhtml?faces-redirect=true",
				"Types de Produits", "gestProductTypeBean",
				"ressource pour Configuration des types de produits", _resource);
		Ressource _resourcePodSupply = new Ressource(11,
				"/param/productSupply.xhtml?faces-redirect=true",
				"Alimentations", "gestProductSupplyBean",
				"ressource pour Configuration des Alimentations", _resource);

		Ressource _resourceSaiseProd = new Ressource(12,
				"/entryPROD/entryPROD.jsf", "Saisie Prod", "entryPRODBean",
				"ressource pour le parametrage", new Ressource(null));
		Ressource _resourceSearchProd = new Ressource(13,
				"/entryPROD/searchProducts.jsf?faces-redirect=true",
				"Rechercher Produits", "GestSearchProductsBean",
				"rechercher un produit", _resourceSaiseProd);
		Ressource _resourceSearchCards = new Ressource(14,
				"/entryPROD/searchCards.jsf?faces-redirect=true",
				"Rechercher Cartes", "GestSearchCardsBean",
				"rechercher une Carte", _resourceSaiseProd);
		Ressource _resourceSaveProd = new Ressource(15,
				"/entryPROD/saveProduct.jsf?faces-redirect=true",
				"Enregistrement", "GestSaveProdBean", "Enregistrement Produit",
				_resourceSaiseProd);
		Ressource _resourceDefaultRapp = new Ressource(16,
				"/entryPROD/entryDefaultRapport.jsf?faces-redirect=true",
				"Saisie Rapport Defaut", "GestDefaultRapportBean",
				"Enregistrement Defaut", _resourceSaiseProd);
		Ressource _ressourceSearchByDate = new Ressource(25,
				"/entryPROD/cailloux.jsf?faces-redirect=true",
				"Recherche Date Reparation", "CaillouxBean",
				"Recherche par Date de Reparation", _resourceSaiseProd);

		Ressource _resourceEntrySAV = new Ressource(17,
				"/entrySAV/entrySAV.jsf", "Saisie SAV", "entrySAVBean",
				"ressource pour SAV", new Ressource(null));

		Ressource _resourceAdmin = new Ressource(27,
				"/utilisateur/utilisateur.jsf", "Administration",
				"Administration", "panneau d'administration", new Ressource(
						null));

		/*Ressource _resourceLogin = new Ressource(27, "/logout.jsf",
				"Se déconnecter", "Se déconnecter", "page de déconnection",
				new Ressource(null)); */

		Ressource _resourceEntryIntervention = new Ressource(18,
				"/entrySAV/entryIntervention.jsf?faces-redirect=true",
				"Saisir Intervention", "GestEntryInterventionBean",
				"Enregistrement Intervention", _resourceEntrySAV);
		Ressource _ressourceRecapCom = new Ressource(19,
				"/entrySAV/recapCom.jsf?faces-redirect=true", "Recap Commerce",
				"recapCOMBean", "Recapitulatif de la partie Commerce",
				_resourceEntrySAV);
		Ressource _ressourceExpedSav = new Ressource(20,
				"/entrySAV/expedSAV.jsf?faces-redirect=true", "Expedition SAV",
				"expedSAVBean", "Gestion des expeditions du SAV",
				_resourceEntrySAV);
		Ressource _ressourceNumCmd = new Ressource(21,
				"/entrySAV/numCommande.jsf?faces-redirect=true",
				"Numero de Commande", "NumCommandeBean",
				"Attribution du numÃ©ro de commande", _resourceEntrySAV);
		Ressource _ressourceValidControl = new Ressource(22,
				"/entrySAV/validControl.jsf?faces-redirect=true",
				"Controle Qualite", "ValidControlBean",
				"Attribution d'une date de Controle Qualite", _resourceEntrySAV);
		Ressource _ressourceDevisPrealable = new Ressource(23,
				"/entrySAV/devisPrealable.jsf?faces-redirect=true",
				"Devis Prealable", "DevisPrealableBean", "", _resourceEntrySAV);
		Ressource _ressourceDevisRepa = new Ressource(24,
				"/entrySAV/devisReparation.jsf?faces-redirect=true",
				"Devis Reparation", "DevisRepaBean", "", _resourceEntrySAV);
		Ressource _ressourceFollowing = new Ressource(25,
				"/param/followingForm.jsf?faces-redirect=true",
				"Following Form", "FollowingFormBean", _resource);
		Ressource _ressourceProductConfModel = new Ressource(26,
				"/param/productConfModel.jsf?faces-redirect=true",
				"Product Conf Model", "ProductConfModelBean", _resource);

		Role _role = new Role("Admin");

		Permission _permission = new Permission();

		Autorisation _autorisation = new Autorisation(_permission, _resource,
				_role);
		Autorisation _autorisationOP = new Autorisation(_permission,
				_resourceOp, _role);
		Autorisation _autorisationLO = new Autorisation(_permission,
				_resourceLog, _role);
		Autorisation _autorisationAppCause = new Autorisation(_permission,
				_resourceAppCause, _role);
		Autorisation _autorisationAppCauseCustomer = new Autorisation(
				_permission, _resourceAppCustomerCause, _role);
		Autorisation _autorisationTester = new Autorisation(_permission,
				_resourceTester, _role);
		Autorisation _autorisationTypeTest = new Autorisation(_permission,
				_resourceTypeTest, _role);
		Autorisation _autorisationConProd = new Autorisation(_permission,
				_resourceConfProd, _role);
		Autorisation _autorisationFamProd = new Autorisation(_permission,
				_resourceProdFamily, _role);
		Autorisation _autorisationProdtype = new Autorisation(_permission,
				_resourceProdType, _role);
		Autorisation _autorisationProdSupply = new Autorisation(_permission,
				_resourcePodSupply, _role);

		Autorisation _autorisationWriteProd = new Autorisation(_permission,
				_resourceSaiseProd, _role);
		Autorisation _autorisationSearchProd = new Autorisation(_permission,
				_resourceSearchProd, _role);
		Autorisation _autorisationSearchCard = new Autorisation(_permission,
				_resourceSearchCards, _role);
		Autorisation _autorisationSaveProd = new Autorisation(_permission,
				_resourceSaveProd, _role);
		Autorisation _autorisationEntryDefaultRapport = new Autorisation(
				_permission, _resourceDefaultRapp, _role);
		Autorisation _autorisationSearchByDate = new Autorisation(_permission,
				_ressourceSearchByDate, _role);

		Autorisation _autorisationEntrySAV = new Autorisation(_permission,
				_resourceEntrySAV, _role);
		/*Autorisation _autorisationAdmin = new Autorisation(_permission,
				_resourceAdmin, _role);*/
		/*Autorisation _autorisationLogin = new Autorisation(_permission,
				_resourceLogin, _role);*/
		Autorisation _autorisationEntryIntervention = new Autorisation(
				_permission, _resourceEntryIntervention, _role);
		Autorisation _autorisationRecapCom = new Autorisation(_permission,
				_ressourceRecapCom, _role);
		Autorisation _autorisationExpedSav = new Autorisation(_permission,
				_ressourceExpedSav, _role);
		Autorisation _autorisationNumCmd = new Autorisation(_permission,
				_ressourceNumCmd, _role);
		Autorisation _autorisationValidControl = new Autorisation(_permission,
				_ressourceValidControl, _role);
		Autorisation _autorisationDevisPrealable = new Autorisation(
				_permission, _ressourceDevisPrealable, _role);
		Autorisation _autorisationDevisRepa = new Autorisation(_permission,
				_ressourceDevisRepa, _role);
		Autorisation _autorisationFollowing = new Autorisation(_permission,
				_ressourceFollowing, _role);
		Autorisation _autorisationProductConfModel = new Autorisation(
				_permission, _ressourceProductConfModel, _role);

		_autorisationList.add(_autorisation);
		_autorisationList.add(_autorisationOP);
		_autorisationList.add(_autorisationLO);
		_autorisationList.add(_autorisationAppCause);
		_autorisationList.add(_autorisationAppCauseCustomer);
		_autorisationList.add(_autorisationTester);
		_autorisationList.add(_autorisationTypeTest);
		_autorisationList.add(_autorisationConProd);
		_autorisationList.add(_autorisationFamProd);
		_autorisationList.add(_autorisationProdtype);
		_autorisationList.add(_autorisationProdSupply);

		_autorisationList.add(_autorisationWriteProd);
		_autorisationList.add(_autorisationSearchProd);
		_autorisationList.add(_autorisationSearchCard);
		_autorisationList.add(_autorisationSaveProd);
		_autorisationList.add(_autorisationEntryDefaultRapport);
		_autorisationList.add(_autorisationSearchByDate);

		_autorisationList.add(_autorisationEntrySAV);
		//_autorisationList.add(_autorisationLogin);

		_autorisationList.add(_autorisationEntryIntervention);
		_autorisationList.add(_autorisationRecapCom);
		_autorisationList.add(_autorisationExpedSav);
		_autorisationList.add(_autorisationNumCmd);
		_autorisationList.add(_autorisationValidControl);
		_autorisationList.add(_autorisationDevisPrealable);
		_autorisationList.add(_autorisationDevisRepa);
		_autorisationList.add(_autorisationFollowing);
		_autorisationList.add(_autorisationProductConfModel);

		/*if (logBean.getUseradmin() == 1){
			_autorisationList.add(_autorisationAdmin);
			System.out.println("isAdmin");
		}*/

		this.autorisationList = _autorisationList;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public MenuWeb getMenuWeb() {
		return menuWeb;
	}

	public void setMenuWeb(MenuWeb menuWeb) {
		this.menuWeb = menuWeb;
	}

	public List<Autorisation> getAutorisationList() {
		return autorisationList;
	}

	public void setAutorisationList(List<Autorisation> autorisationList) {
		this.autorisationList = autorisationList;
	}
}
