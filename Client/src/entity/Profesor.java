package entity;

import java.util.Objects;

public class Profesor {
    private int id;
    private String nume;
    private String prenume;
    private String gen;
    private int varsta;
    private String adresa;
    private String nr_telefon;
    private String email;
    private Liceu liceu;
    private Materie materie;

    public Profesor(int id, String nume, String prenume, String gen, int varsta, String adresa, String nr_telefon, String email, Liceu liceu, Materie materie) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.varsta = varsta;
        this.adresa = adresa;
        this.nr_telefon = nr_telefon;
        this.email = email;
        this.liceu = liceu;
        this.materie = materie;
    }

    public Profesor(){}

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

    public Liceu getLiceu() {
        return liceu;
    }

    public void setLiceu(Liceu liceu) {
        this.liceu = liceu;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;
        Profesor profesor = (Profesor) o;
        return id == profesor.id &&
                varsta == profesor.varsta &&
                Objects.equals(nume, profesor.nume) &&
                Objects.equals(prenume, profesor.prenume) &&
                Objects.equals(gen, profesor.gen) &&
                Objects.equals(adresa, profesor.adresa) &&
                Objects.equals(nr_telefon, profesor.nr_telefon) &&
                Objects.equals(email, profesor.email) &&
                Objects.equals(liceu, profesor.liceu) &&
                Objects.equals(materie, profesor.materie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, gen, varsta, adresa, nr_telefon, email, liceu, materie);
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", gen='" + gen + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", email='" + email + '\'' +
                ", liceu=" + liceu +
                ", materie=" + materie +
                '}';
    }
}
