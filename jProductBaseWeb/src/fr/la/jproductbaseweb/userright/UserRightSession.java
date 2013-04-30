package fr.la.jproductbaseweb.userright;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.la.jproductbaseweb.beanmanaged.LoginBean;
import fr.la.jproductbaseweb.view.bar.MenuWeb;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.service.ServiceUserRight;

@ManagedBean(name = "userRightSession")
@SessionScoped
public class UserRightSession implements Serializable {

	private static final long serialVersionUID = 1L;
	ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();
	private String login;
	private List<Autorisation> autorisationList;
	private MenuWeb menuWeb;

	public UserRightSession() {			
		FacesContext fcontext = FacesContext.getCurrentInstance();

		LoginBean logBean = (LoginBean) fcontext
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(fcontext.getELContext(), "#{loginBean}",
					LoginBean.class).getValue(fcontext.getELContext());
		
		List<Autorisation> _autorisationList = new ArrayList<Autorisation>();

		List<String> paths = new ArrayList<String>();
		List<Autorisation> permList = moduleGlobal.getAutorisationByLogin(logBean.getUserlogin());
		for (Autorisation r : permList) {	
			if (r.getRessource() != null && r.getPermission().getIdpermission() == 1) {
				paths.add(r.getRessource().getPath().trim());
			}
		}

		Ressource _resource = new Ressource(1, "/param/params.jsf", "Parametrage", "parametrageBean", "ressource pour le parametrage", new Ressource(null));
		Ressource _resourceOp = new Ressource(2, "/param/operator.jsf", "Operateur", "gestOperatorBean", "ressource pour gestion operateur", _resource);
		Ressource _resourceLog = new Ressource(3, "/param/software.jsf", "Logiciels",	"gestOperatorBean", "ressource pour gestion logiciel", _resource);
		Ressource _resourceAppCause = new Ressource(4, "/param/apparentCause.jsf", "Causes Probables", "gestApparentCauseBean", "ressource pour gestion apparent cause", _resource);
		Ressource _resourceAppCustomerCause = new Ressource(5, "/param/apparentCauseCustomer.jsf", "Cause Probables Client", "gestApparentCauseCustomerBean",	"ressource pour gestion apparent cause client", _resource);
		Ressource _resourceTester = new Ressource(6, "/param/tester.jsf", "Tester", "gestTesterBean", "ressource pour gestion Tester", _resource);
		Ressource _resourceTypeTest = new Ressource(7, "/param/typeTest.jsf", "Types de Test", "gestTypeTestBean", "ressource pour gestion Type Test", _resource);
		Ressource _resourceConfProd = new Ressource(8,	"/param/productConf.jsf",	"Configuration des produits", "gestProductConfBean", "ressource pour Configuration des Produits", _resource);
		Ressource _resourceProdFamily = new Ressource(9, "/param/productFamily.jsf",	"Familles de produits", "gestProductFamilyBean", "ressource pour Configuration des Familles de Produit",_resource);
		Ressource _resourceProdType = new Ressource(10, "/param/productType.jsf",	"Types de Produits", "gestProductTypeBean",	"ressource pour Configuration des types de produits", _resource);
		Ressource _resourcePodSupply = new Ressource(11, "/param/productSupply.jsf",	"Alimentations", "gestProductSupplyBean", "ressource pour Configuration des Alimentations", _resource);
		Ressource _ressourceFollowing = new Ressource(25, "/param/followingForm.jsf", "Fiches Suiveuses", "FollowingFormBean", _resource);
		Ressource _ressourceProductConfModel = new Ressource(26, "/param/productConfModel.jsf",	"Modèle Configuration produit", "ProductConfModelBean", _resource);		

		Ressource _resourceSaiseProd = new Ressource(12, "/entryPROD/entryPROD.jsf", "Saisie Prod", "entryPRODBean", "ressource pour le parametrage", new Ressource(null));
		Ressource _resourceSearchProd = new Ressource(13, "/entryPROD/searchProducts.jsf", "Rechercher Produits", "GestSearchProductsBean",	"rechercher un produit", _resourceSaiseProd);
		Ressource _resourceSearchCards = new Ressource(14, "/entryPROD/searchCards.jsf","Rechercher Cartes", "GestSearchCardsBean",	"rechercher une Carte", _resourceSaiseProd);
		Ressource _resourceSaveProd = new Ressource(15,	"/entryPROD/saveProduct.jsf", "Enregistrement", "GestSaveProdBean", "Enregistrement Produit", _resourceSaiseProd);
		Ressource _resourceDefaultRapp = new Ressource(16, "/entryPROD/entryDefaultRapport.jsf", "Saisie Rapport Defaut", "GestDefaultRapportBean",	"Enregistrement Defaut", _resourceSaiseProd);
		Ressource _ressourceSearchByDate = new Ressource(25, "/entryPROD/cailloux.jsf",	"Recherche Date Reparation", "CaillouxBean", "Recherche par Date de Reparation", _resourceSaiseProd);
		
		Ressource _resourceEntrySAV = new Ressource(17,	"/entrySAV/entrySAV.jsf", "Saisie SAV", "entrySAVBean",	"ressource pour SAV", new Ressource(null));
		Ressource _resourceEntryIntervention = new Ressource(18, "/entrySAV/entryIntervention.jsf",	"Saisir Intervention", "GestEntryInterventionBean",	"Enregistrement Intervention", _resourceEntrySAV);
		Ressource _ressourceRecapCom = new Ressource(19, "/entrySAV/recapCom.jsf", "Recap Commerce", "recapCOMBean", "Recapitulatif de la partie Commerce",	_resourceEntrySAV);
		Ressource _ressourceExpedSav = new Ressource(20, "/entrySAV/expedSAV.jsf", "Expedition SAV", "expedSAVBean", "Gestion des expeditions du SAV",	_resourceEntrySAV);
		Ressource _ressourceNumCmd = new Ressource(21,	"/entrySAV/numCommande.jsf", "Numero de Commande", "NumCommandeBean", "Attribution du numÃ©ro de commande", _resourceEntrySAV);
		Ressource _ressourceValidControl = new Ressource(22, "/entrySAV/validControl.jsf", "Controle Qualite", "ValidControlBean", "Attribution d'une date de Controle Qualite", _resourceEntrySAV);
		Ressource _ressourceDevisPrealable = new Ressource(23, "/entrySAV/devisPrealable.jsf", "Devis Prealable", "DevisPrealableBean", "", _resourceEntrySAV);
		Ressource _ressourceDevisRepa = new Ressource(24, "/entrySAV/devisReparation.jsf", "Devis Reparation", "DevisRepaBean", "", _resourceEntrySAV);
		
		Ressource _resourceAdmin = new Ressource(26, "/admin/",	"Administration", "Administration", "page admin", new Ressource(null));  

		
		
		Role _role = new Role(logBean.getUserlogin());
		Permission _permission = new Permission();

		Autorisation _autorisation = new Autorisation(_permission, _resource, _role);
		Autorisation _autorisationOP = new Autorisation(_permission, _resourceOp, _role);
		Autorisation _autorisationLO = new Autorisation(_permission, _resourceLog, _role);
		Autorisation _autorisationAppCause = new Autorisation(_permission, _resourceAppCause, _role);
		Autorisation _autorisationAppCauseCustomer = new Autorisation(_permission, _resourceAppCustomerCause, _role);
		Autorisation _autorisationTester = new Autorisation(_permission, _resourceTester, _role);
		Autorisation _autorisationTypeTest = new Autorisation(_permission, _resourceTypeTest, _role);
		Autorisation _autorisationConProd = new Autorisation(_permission, _resourceConfProd, _role);
		Autorisation _autorisationFamProd = new Autorisation(_permission, _resourceProdFamily, _role);
		Autorisation _autorisationProdtype = new Autorisation(_permission, _resourceProdType, _role);
		Autorisation _autorisationProdSupply = new Autorisation(_permission, _resourcePodSupply, _role);
		Autorisation _autorisationWriteProd = new Autorisation(_permission,	_resourceSaiseProd, _role);
		Autorisation _autorisationSearchProd = new Autorisation(_permission, _resourceSearchProd, _role);
		Autorisation _autorisationSearchCard = new Autorisation(_permission, _resourceSearchCards, _role);
		Autorisation _autorisationSaveProd = new Autorisation(_permission, _resourceSaveProd, _role);
		Autorisation _autorisationEntryDefaultRapport = new Autorisation(_permission, _resourceDefaultRapp, _role);
		Autorisation _autorisationSearchByDate = new Autorisation(_permission, _ressourceSearchByDate, _role);
		Autorisation _autorisationEntrySAV = new Autorisation(_permission, _resourceEntrySAV, _role);
		Autorisation _autorisationAdmin = new Autorisation(_permission,	_resourceAdmin, _role);
		Autorisation _autorisationEntryIntervention = new Autorisation(_permission, _resourceEntryIntervention, _role);
		Autorisation _autorisationRecapCom = new Autorisation(_permission,_ressourceRecapCom, _role);
		Autorisation _autorisationExpedSav = new Autorisation(_permission,_ressourceExpedSav, _role);
		Autorisation _autorisationNumCmd = new Autorisation(_permission,_ressourceNumCmd, _role);
		Autorisation _autorisationValidControl = new Autorisation(_permission,_ressourceValidControl, _role);
		Autorisation _autorisationDevisPrealable = new Autorisation(_permission, _ressourceDevisPrealable, _role);
		Autorisation _autorisationDevisRepa = new Autorisation(_permission,_ressourceDevisRepa, _role);
		Autorisation _autorisationFollowing = new Autorisation(_permission,_ressourceFollowing, _role);
		Autorisation _autorisationProductConfModel = new Autorisation(_permission, _ressourceProductConfModel, _role);

		if (paths.contains(_autorisation.getRessource().getPath()))
			_autorisationList.add(_autorisation);
		if (paths.contains(_autorisationOP.getRessource().getPath()))
			_autorisationList.add(_autorisationOP);
		if (paths.contains(_autorisationLO.getRessource().getPath()))
			_autorisationList.add(_autorisationLO);
		if (paths.contains(_autorisationAppCause.getRessource().getPath()))
			_autorisationList.add(_autorisationAppCause);
		if (paths.contains(_autorisationAppCauseCustomer.getRessource().getPath()))
			_autorisationList.add(_autorisationAppCauseCustomer);
		if (paths.contains(_autorisationTester.getRessource().getPath()))
			_autorisationList.add(_autorisationTester);
		if (paths.contains(_autorisationTypeTest.getRessource().getPath()))
			_autorisationList.add(_autorisationTypeTest);
		if (paths.contains(_autorisationConProd.getRessource().getPath()))
			_autorisationList.add(_autorisationConProd);
		if (paths.contains(_autorisationFamProd.getRessource().getPath()))
			_autorisationList.add(_autorisationFamProd);
		if (paths.contains(_autorisationProdtype.getRessource().getPath()))
			_autorisationList.add(_autorisationProdtype);
		if (paths.contains(_autorisationProdSupply.getRessource().getPath()))
			_autorisationList.add(_autorisationProdSupply);

		if (paths.contains(_autorisationWriteProd.getRessource().getPath()))
			_autorisationList.add(_autorisationWriteProd);
		if (paths.contains(_autorisationSearchProd.getRessource().getPath()))
			_autorisationList.add(_autorisationSearchProd);
		if (paths.contains(_autorisationSearchProd.getRessource().getPath()))
			_autorisationList.add(_autorisationSearchCard);
		if (paths.contains(_autorisationSaveProd.getRessource().getPath()))
			_autorisationList.add(_autorisationSaveProd);
		if (paths.contains(_autorisationEntryDefaultRapport.getRessource().getPath()))
			_autorisationList.add(_autorisationEntryDefaultRapport);
		if (paths.contains(_autorisationSearchByDate.getRessource().getPath()))
			_autorisationList.add(_autorisationSearchByDate);
		if (paths.contains(_autorisationEntrySAV.getRessource().getPath()))
			_autorisationList.add(_autorisationEntrySAV);

		if (paths.contains(_autorisationEntryIntervention.getRessource().getPath()))
			_autorisationList.add(_autorisationEntryIntervention);
		if (paths.contains(_autorisationValidControl.getRessource().getPath()))
			_autorisationList.add(_autorisationValidControl);
		if (paths.contains(_autorisationDevisPrealable.getRessource().getPath()))
			_autorisationList.add(_autorisationDevisPrealable);
		if (paths.contains(_autorisationDevisRepa.getRessource().getPath()))
			_autorisationList.add(_autorisationDevisRepa);
		if (paths.contains(_autorisationRecapCom.getRessource().getPath()))
			_autorisationList.add(_autorisationRecapCom);		
		if (paths.contains(_autorisationNumCmd.getRessource().getPath()))
			_autorisationList.add(_autorisationNumCmd);		
		if (paths.contains(_autorisationExpedSav.getRessource().getPath()))
			_autorisationList.add(_autorisationExpedSav);
		if (paths.contains(_autorisationFollowing.getRessource().getPath()))
			_autorisationList.add(_autorisationFollowing);
		if (paths.contains(_autorisationProductConfModel.getRessource().getPath()))
			_autorisationList.add(_autorisationProductConfModel);

		if (logBean.getUseradmin() == 1){
			_autorisationList.add(_autorisationAdmin);
		}
		
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