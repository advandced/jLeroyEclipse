package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.TestTypeException;
import java.io.Serializable;

public class TestTypeForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int state;
    private boolean needTester;

    public TestTypeForm(String name, int state, boolean needTester) throws TestTypeException {
        super();
        this.name = name;
        this.state = state;
        this.needTester = needTester;

        if (name.isEmpty()) {

            throw new TestTypeException();


        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isNeedTester() {
        return needTester;
    }

    public void setNeedTester(boolean needTester) {
        this.needTester = needTester;
    }
}
