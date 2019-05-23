package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Nota {
    private int idElev;
    private int idMaterie;
    private int idProfesor;
    private int nota;
    private LocalDate dataNotare;

    public Nota(int idElev, int idMaterie, int idProfesor, int nota, LocalDate dataNotare) {
        this.idElev = idElev;
        this.idMaterie = idMaterie;
        this.idProfesor = idProfesor;
        this.nota = nota;
        this.dataNotare = dataNotare;
    }

    public int getIdElev() {
        return idElev;
    }

    public void setIdElev(int idElev) {
        this.idElev = idElev;
    }

    public int getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(int idMaterie) {
        this.idMaterie = idMaterie;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
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
    public String toString() {
        return "Teze{" +
                "idElev=" + idElev +
                ", idMaterie=" + idMaterie +
                ", idProfesor=" + idProfesor +
                ", nota=" + nota +
                ", dataNotare=" + dataNotare +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nota)) return false;
        Nota nota1 = (Nota) o;
        return idElev == nota1.idElev &&
                idMaterie == nota1.idMaterie &&
                idProfesor == nota1.idProfesor &&
                nota == nota1.nota &&
                Objects.equals(dataNotare, nota1.dataNotare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idElev, idMaterie, idProfesor, nota, dataNotare);
    }
}