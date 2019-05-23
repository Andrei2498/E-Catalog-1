package view;

import java.time.LocalDate;
import java.util.Date;

public class Nota {
    private int nota;
    private LocalDate data;
    private String profesor;
    private String isTeza;


    public Nota(int nota, LocalDate data, String profesor, String isTeza){

        setNota(nota);
        setData(data);
        setProfesor(profesor);
        setIsTeza(isTeza);
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
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

    public String getIsTeza() {
        return isTeza;
    }

    public void setIsTeza(String isTeza) {
        this.isTeza = isTeza;
    }


}
