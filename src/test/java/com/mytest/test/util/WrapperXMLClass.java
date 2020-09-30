package com.mytest.test.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MapWrapper")
public class WrapperXMLClass {
    MyTestClass myTestClass;

    public WrapperXMLClass(){}
    public WrapperXMLClass(MyTestClass myTestClass) {
        this.myTestClass = myTestClass;
    }

    public void setMyTestClass(MyTestClass myTestClass) {
        this.myTestClass = myTestClass;
    }

    public MyTestClass getMyTestClass() {
        return myTestClass;
    }
}
