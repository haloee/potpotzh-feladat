package hu.pte.mik.prog4.entity;
import java.util.Objects;
public class CompanyEntity {
   private long id;
   private String nev;
   private String alapitasiNev;
   private String orszag;
   private String ismertTermek;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return id == that.id && Objects.equals(nev, that.nev) && Objects.equals(alapitasiNev, that.alapitasiNev) && Objects.equals(orszag, that.orszag) && Objects.equals(ismertTermek, that.ismertTermek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, alapitasiNev, orszag, ismertTermek);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getAlapitasiNev() {
        return alapitasiNev;
    }

    public void setAlapitasiNev(String alapitasiNev) {
        this.alapitasiNev = alapitasiNev;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public String getIsmertTermek() {
        return ismertTermek;
    }

    public void setIsmertTermek(String ismertTermek) {
        this.ismertTermek = ismertTermek;
    }
}
