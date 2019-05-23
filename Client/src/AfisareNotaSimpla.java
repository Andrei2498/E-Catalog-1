import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AfisareNotaSimpla {
    private int idElev;
    private StringProperty nume;
    private StringProperty prenume;

    AfisareNotaSimpla(int idElev,String nume,String prenume){
        setIdElev(idElev);
        setNume(nume);
        setPrenume(prenume);
    }

    public int getIdElev() {
        return idElev;
    }

    public void setIdElev(int idElev) {
        this.idElev = idElev;
    }

    public StringProperty getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = new SimpleStringProperty(nume);
    }

    public StringProperty getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = new SimpleStringProperty(prenume);
    }
}
