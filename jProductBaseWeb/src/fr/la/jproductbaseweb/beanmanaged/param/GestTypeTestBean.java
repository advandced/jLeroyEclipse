package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbaseweb.beanmanaged.exception.TestTypeException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.TestTypeForm;
import java.io.Serializable;

@ManagedBean(name = "gestTypeTestBean")
@SessionScoped
public class GestTypeTestBean extends GestFormAbstract<TestType> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameTypeTest;
    private int stateTypeTest;
    private boolean needTesterTypeTest;

    public GestTypeTestBean() {
        super();
        this.objectList = this.moduleGolbal.getTestTypes();
    }

    @Override
    public void selectedModify() {
        // TODO Auto-generated method stub
        super.selectedModify();
        System.out.println("modify");
        this.nameTypeTest = getSelectedObject().getName();
        this.stateTypeTest = getSelectedObject().getState();
        this.needTesterTypeTest = getSelectedObject().isNeedTester();


    }

    @Override
    public void selectedCreate() {
        // TODO Auto-generated method stub
        super.selectedCreate();
        this.nameTypeTest = null;
        this.stateTypeTest = 1;
        this.needTesterTypeTest = false;

    }

    @Override
    protected void create() throws TestTypeException {
        TestTypeForm _testTypeForm = new TestTypeForm(this.nameTypeTest, this.stateTypeTest, this.needTesterTypeTest);
        this.moduleGolbal.addTestType(_testTypeForm.getName(), _testTypeForm.getState(), _testTypeForm.isNeedTester());
        this.objectList = this.moduleGolbal.getTestTypes();
    }

    @Override
    protected void update() throws TestTypeException {
        System.out.println("update");
        TestTypeForm _testTypeForm = new TestTypeForm(this.nameTypeTest, this.stateTypeTest, this.needTesterTypeTest);
        getSelectedObject().setName(_testTypeForm.getName());
        getSelectedObject().setState(_testTypeForm.getState());
        getSelectedObject().setNeedTester(_testTypeForm.isNeedTester());

        this.moduleGolbal.updateTestType(getSelectedObject());
        this.objectList = this.moduleGolbal.getTestTypes();
    }

    public String getNameTypeTest() {
        return nameTypeTest;
    }

    public void setNameTypeTest(String nameTypeTest) {
        this.nameTypeTest = nameTypeTest;
    }

    public int getStateTypeTest() {
        return stateTypeTest;
    }

    public void setStateTypeTest(int stateTypeTest) {
        this.stateTypeTest = stateTypeTest;
    }

    public boolean isNeedTesterTypeTest() {
        return needTesterTypeTest;
    }

    public void setNeedTesterTypeTest(boolean needTesterTypeTest) {
        this.needTesterTypeTest = needTesterTypeTest;
    }
}
