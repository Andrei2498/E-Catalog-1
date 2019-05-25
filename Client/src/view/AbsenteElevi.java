package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AbsenteElevi {

    private int ID;
    private StringProperty nume;
    private StringProperty prenume;

    AbsenteElevi(int ID,String nume,String prenume){
        setID(ID);
        setNume(nume);
        setPrenume(prenume);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public StringProperty getNume() {
        return nume;
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume=new SimpleStringProperty(nume);
    }

    public StringProperty getPrenume() {
        return prenume;
    }

    public StringProperty prenumeProperty() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume=new SimpleStringProperty(prenume);
    }
}
