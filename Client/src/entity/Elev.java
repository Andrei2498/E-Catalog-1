package entity;

import java.util.Objects;

public class Elev {
    private int id;
    private String nume;
    private String prenume;
    private String gen;
    private int varsta;
    private String adresa;
    private String nr_telefon;
    private String email;
    private Liceu liceu;
    private String clasa;
    private String profil;

    public  Elev(){
    }

    public Elev(int id, String nume, String prenume, String gen, int varsta, String adresa, String nr_telefon, String email, Liceu liceu, String clasa, String profil) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.varsta = varsta;
        this.adresa = adresa;
        this.nr_telefon = nr_telefon;
        this.email = email;
        this.liceu = liceu;
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

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
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
        if (!(o instanceof Elev)) return false;
        Elev elev = (Elev) o;
        return id == elev.id &&
                varsta == elev.varsta &&
                Objects.equals(nume, elev.nume) &&
                Objects.equals(prenume, elev.prenume) &&
                Objects.equals(gen, elev.gen) &&
                Objects.equals(adresa, elev.adresa) &&
                Objects.equals(nr_telefon, elev.nr_telefon) &&
                Objects.equals(email, elev.email) &&
                Objects.equals(liceu, elev.liceu) &&
                Objects.equals(clasa, elev.clasa) &&
                Objects.equals(profil, elev.profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, gen, varsta, adresa, nr_telefon, email, liceu, clasa, profil);
    }

    @Override
    public String toString() {
        return "Elev{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", gen='" + gen + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", email='" + email + '\'' +
                ", liceu=" + liceu +
                ", clasa='" + clasa + '\'' +
                ", profil='" + profil + '\'' +
                "}\n";
    }
}
