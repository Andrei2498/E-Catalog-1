package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class AfisareAbsente {

    private StringProperty nume;
    private StringProperty prenume;
    private LocalDate data;

    public AfisareAbsente(String nume,String prenume,LocalDate data){
        setNume(nume);
        setPrenume(prenume);
        setData(data);
    }

    public String getNume() {
        return nume.get();
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume=new SimpleStringProperty(nume);
    }

    public String getPrenume() {
        return prenume.get();
    }

    public StringProperty prenumeProperty() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume=new SimpleStringProperty(prenume);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
