package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.jproductbase.dao.TesterDaoException;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.TesterException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.TesterForm;
import java.io.Serializable;

@ManagedBean(name = "gestTesterBean")
@ApplicationScoped
public class GestTesterBean extends GestFormAbstract<Tester> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameTester;
    private int stateTester;

    public GestTesterBean() {
        super();
        try {
            this.objectList = this.moduleGolbal.getTesters();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @Override
    public void selectedModify() {
        // TODO Auto-generated method stub
        super.selectedModify();
        System.out.println("selected Modify");
        this.nameTester = getSelectedObject().getName();
        this.stateTester = getSelectedObject().getState();
    }

    @Override
    public void selectedCreate() {
        // TODO Auto-generated method stub
        super.selectedCreate();

        System.out.println("selected create");
        this.nameTester = null;
        this.stateTester = 1;
    }

    @Override
    protected void create() throws TesterException, NamingException {

        TesterForm _testerForm = new TesterForm(this.nameTester, this.stateTester);
        // On crée un nouveau testeur avec les infos recup dans la
        // requete
        try {
            this.moduleGolbal.addTester(_testerForm.getDescription(), _testerForm.getState());
            this.objectList = this.moduleGolbal.getTesters();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TesterDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @Override
    protected void update() throws TesterException {
        ServiceInterface _serviceInterface = new ServiceInterface();
        TesterForm _testerForm = new TesterForm(this.nameTester, this.stateTester);
        // On recup le testeur, on le modifie avec les infos recup
        // dans la requete
        getSelectedObject().setName(_testerForm.getDescription());
        getSelectedObject().setState(_testerForm.getState());
        try {
            _serviceInterface.updateTester(getSelectedObject());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TesterDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getNameTester() {
        return nameTester;
    }

    public void setNameTester(String nameTester) {
        this.nameTester = nameTester;
    }

    public int getStateTester() {
        return stateTester;
    }

    public void setStateTester(int stateTester) {
        this.stateTester = stateTester;
    }
}
