package fr.la.jproductbase.dao;

import java.util.Date;
import java.util.List;

import fr.la.jproductbase.metier.AfterSaleCom;

public interface AfterSaleComDao {

	public AfterSaleCom getAfterSaleCom(String idaftersalecom);

	public List<AfterSaleCom> listAfterSaleCom();

	public void addDevisPrea(AfterSaleCom AfterSaleCom);

	public List<AfterSaleCom> rechercheNumCmd(String rechercher);

	public void addCmd(AfterSaleCom AfterSaleCom);
	
	public void updateCmd(AfterSaleCom _aftersalecom);

	public List<AfterSaleCom> ListDevisPrea();

	public List<AfterSaleCom> getAfterSaleComExpedSAV();

	public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage);

	public int countRecapCom();

	public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin);

}