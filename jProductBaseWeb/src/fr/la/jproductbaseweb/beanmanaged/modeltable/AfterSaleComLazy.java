package fr.la.jproductbaseweb.beanmanaged.modeltable;

import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class AfterSaleComLazy extends LazyDataModel<AfterSaleCom> {

	private static final long serialVersionUID = 1L;

	private List<AfterSaleCom> afterSaleCom;

	private ServiceInterface moduleGlobal;

	public AfterSaleComLazy() {

	}

	@Override
	public List<AfterSaleCom> load(int startingAt, int maxPerPage,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.moduleGlobal = new ServiceInterface();
		int countSizeResult = 0;
		try {
			this.afterSaleCom = this.moduleGlobal.getLazyRecapCom(startingAt,
					maxPerPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			countSizeResult = this.moduleGlobal.countRecapCom();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRowCount(countSizeResult);
		setPageSize(maxPerPage);
		return this.afterSaleCom;
	}

	public List<AfterSaleCom> getAfterSaleCom() {
		return afterSaleCom;
	}

	public void setAfterSaleCom(List<AfterSaleCom> afterSaleCom) {
		this.afterSaleCom = afterSaleCom;
	}
}