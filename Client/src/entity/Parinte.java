package entity;

import java.util.Objects;

public class Parinte {
    private int id;
    private String nume;
    private String prenume;
    private String gen;
    private int varsta;
    private String adresa;
    private String nr_telefon;
    private String email;
    private Elev elev;

    public Parinte(int id, String nume, String prenume, String gen, int varsta, String adresa, String nr_telefon, String email, Elev elev) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.varsta = varsta;
        this.adresa = adresa;
        this.nr_telefon = nr_telefon;
        this.email = email;
        this.elev = elev;
    }

    public Parinte() {
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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Elev getElev() {
        return elev;
    }

    public void setElev(Elev elev) {
        this.elev = elev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parinte)) return false;
        Parinte parinte = (Parinte) o;
        return id == parinte.id &&
                varsta == parinte.varsta &&
                Objects.equals(nume, parinte.nume) &&
                Objects.equals(prenume, parinte.prenume) &&
                Objects.equals(gen, parinte.gen) &&
                Objects.equals(adresa, parinte.adresa) &&
                Objects.equals(nr_telefon, parinte.nr_telefon) &&
                Objects.equals(email, parinte.email) &&
                Objects.equals(elev, parinte.elev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, gen, varsta, adresa, nr_telefon, email, elev);
    }

    @Override
    public String toString() {
        return "Parinte{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", gen='" + gen + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", email='" + email + '\'' +
                ", elev=" + elev +
                '}';
    }
}
