package fr.la.jproductbaseweb.beanmanaged.modeltable;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.service.ServiceInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class ProductLazyList extends LazyDataModel<Product> {

    private int type = 0;
    private static final long serialVersionUID = 1L;
    private List<Product> products = new ArrayList<>();
    private ServiceInterface serviceInterface = new ServiceInterface();

    public ProductLazyList(int type) {
        this.type = type;
    }

    @Override
    public List<Product> load(int startingAt, int maxPerPage, String sortField,
            SortOrder sortOrder, Map<String, String> filters) {
        try {
            if (this.type != 1) {
                this.products = this.serviceInterface.getProductsSearch(startingAt, maxPerPage, filters, this.type);
            } else {
                this.products = this.serviceInterface.getProductWithMother(startingAt, maxPerPage, filters);
            }
        } catch (SQLException | ConfigFileReaderException | IOException ex) {
            Logger.getLogger(ProductLazyList.class.getName()).log(Level.SEVERE, null, ex);
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