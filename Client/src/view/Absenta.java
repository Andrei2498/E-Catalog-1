package view;

import java.time.LocalDate;

public class Absenta {

    private LocalDate data;
    private String profesor;
    private String materie;

    public Absenta(LocalDate data, String materie, String profesor){
        setData(data);
        setMaterie(materie);
        setProfesor(profesor);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }
}
