/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.modeltable;

import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.service.ServiceInterface;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Joffrey
 */
public class ProductConfLazy extends LazyDataModel<ProductConf> {

    private static final long serialVersionUID = 1L;
    private List<ProductConf> listProductConf = new ArrayList<>();
    private ServiceInterface moduleGlobal = new ServiceInterface();

    @Override
    public List<ProductConf> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        System.out.println("FILTRE CONF" + filters.toString());
        System.out.println("start " + startingAt + " maxperpage " + maxPerPage);
        try {
            this.listProductConf = this.moduleGlobal.getProductConfLazy(filters, startingAt, maxPerPage);
        } catch (SQLException e) {
         System.out.println(e.getMessage());
        }
        int countSizeResult = 0;
        try {
            countSizeResult = this.moduleGlobal.countProductConf(filters);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        setRowCount(countSizeResult);
        setPageSize(maxPerPage);
        return this.listProductConf;
    }
}