package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class AfisareNote {
    private StringProperty nume;
    private StringProperty prenume;
    private Integer valoare;
    private LocalDate data;

    AfisareNote(String nume,String prenume,Integer valoare,LocalDate data){
        setNume(nume);
        setPrenume(prenume);
        setValoare(valoare);
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

    public Integer getValoare() {
        return valoare;
    }

    public void setValoare(Integer valoare) {
        this.valoare = valoare;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
