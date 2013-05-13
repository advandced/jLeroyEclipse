package fr.la.jproductbaseweb.beanmanaged.modeltable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.service.ServiceInterface;

public class ProductLazyList extends LazyDataModel<Product> {

    private int type = 0;
    private static final long serialVersionUID = 1L;
    private List<Product> products = new ArrayList<Product>();
    private ServiceInterface serviceInterface = ServiceInterface.getInstance();

    public ProductLazyList(int type) {
        this.type = type;
    }

    @Override
    public List<Product> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        if (this.type != 1) {
            this.products = this.serviceInterface.getProductsSearch(startingAt, maxPerPage, filters, this.type);
        } else {
            this.products = this.serviceInterface.getProductWithMother(startingAt, maxPerPage, filters);
        }

        int countSizeResult = 0;
        try {
            countSizeResult = this.serviceInterface.getCountQuery(filters, type);
        } catch (SQLException e) {
        }
        setRowCount(countSizeResult);
        setPageSize(maxPerPage);
        return this.products;
    }
}