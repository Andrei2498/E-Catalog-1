package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Nota {
//    private int idElev;
//    private int idMaterie;
//    private int idProfesor;
    private Elev elev;
    private Materie materie;
    private Profesor profesor;
    private int nota;
    private LocalDate dataNotare;

    public Nota(Elev elev, Materie materie, Profesor profesor, int nota, LocalDate dataNotare) {
        this.elev = elev;
        this.materie = materie;
        this.profesor = profesor;
        this.nota = nota;
        this.dataNotare = dataNotare;
    }

    public Elev getElev() {
        return elev;
    }

    public void setElev(Elev elev) {
        this.elev = elev;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public LocalDate getDataNotare() {
        return dataNotare;
    }

    public void setDataNotare(LocalDate dataNotare) {
        this.dataNotare = dataNotare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nota)) return false;
        Nota nota1 = (Nota) o;
        return nota == nota1.nota &&
                elev.equals(nota1.elev) &&
                materie.equals(nota1.materie) &&
                profesor.equals(nota1.profesor) &&
                dataNotare.equals(nota1.dataNotare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elev, materie, profesor, nota, dataNotare);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "elev=" + elev +
                ", materie=" + materie +
                ", profesor=" + profesor +
                ", nota=" + nota +
                ", dataNotare=" + dataNotare +
                '}';
    }
}
