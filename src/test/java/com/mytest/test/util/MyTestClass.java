package com.mytest.test.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MyTestClass")
public class MyTestClass {
    private String imie;
    private String nazwisko;

    public MyTestClass(){}

    public MyTestClass(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public String toString() {
        return "MyTestClass{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
