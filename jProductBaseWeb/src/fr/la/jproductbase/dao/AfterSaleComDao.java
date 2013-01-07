package fr.la.jproductbase.dao;

import java.io.IOException;
//import java.util.Date;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
//import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.AfterSaleCom;
//import fr.la.jproductbase.metier.ApparentCause;
//import fr.la.jproductbase.metier.Product;

public interface AfterSaleComDao {

	public AfterSaleCom getAfterSaleCom(String idaftersalecom)
			throws SQLException, ConfigFileReaderException, IOException;

	public List<AfterSaleCom> listAfterSaleCom() throws SQLException,
			ConfigFileReaderException, IOException;

	public void addDevisPrea(AfterSaleCom AfterSaleCom) throws SQLException,
			AfterSaleComDaoException;

	public List<AfterSaleCom> rechercheNumCmd(String rechercher)
			throws SQLException, ConfigFileReaderException, IOException;

	public void addCmd(AfterSaleCom AfterSaleCom) throws SQLException,
			AfterSaleComDaoException;

	public void updateCmd(AfterSaleCom _aftersalecom) throws SQLException,
			AfterSaleComDaoException;

	public List<AfterSaleCom> ListDevisPrea() throws SQLException,
			ConfigFileReaderException, IOException;

	public List<AfterSaleCom> getAfterSaleComExpedSAV() throws SQLException,
			ConfigFileReaderException, IOException;

	public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage)
			throws SQLException;

	public int countRecapCom() throws SQLException;

	public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin)
			throws SQLException, ConfigFileReaderException, IOException;

}