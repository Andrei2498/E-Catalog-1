package entity;

import java.util.Objects;

public class Materie {
    private int id;
    private String nume;
    private int clasa;
    private String profil;

    public Materie() {
    }

    public Materie(int id, String nume, int clasa, String profil) {
        this.id = id;
        this.nume = nume;
        this.clasa = clasa;
        this.profil = profil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getClasa() {
        return clasa;
    }

    public void setClasa(int clasa) {
        this.clasa = clasa;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materie)) return false;
        Materie materie = (Materie) o;
        return id == materie.id &&
                clasa == materie.clasa &&
                Objects.equals(nume, materie.nume) &&
                Objects.equals(profil, materie.profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, clasa, profil);
    }

    @Override
    public String toString() {
        return "Materie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", clasa=" + clasa +
                ", profil='" + profil + '\'' +
                '}';
    }
}
