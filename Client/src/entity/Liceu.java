package entity;

import java.util.Objects;

public class Liceu {
    private int id;
    private String titlu;
    private String nume;
    private String judet;

    public Liceu() {
    }

    public Liceu(int id, String titlu, String nume, String judet) {
        this.id = id;
        this.titlu = titlu;
        this.nume = nume;
        this.judet = judet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    @Override
    public String toString() {
        return "Liceu{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", nume='" + nume + '\'' +
                ", judet='" + judet + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Liceu)) return false;
        Liceu liceu = (Liceu) o;
        return id == liceu.id &&
                Objects.equals(titlu, liceu.titlu) &&
                Objects.equals(nume, liceu.nume) &&
                Objects.equals(judet, liceu.judet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titlu, nume, judet);
    }
}
