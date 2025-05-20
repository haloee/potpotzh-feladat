package hu.pte.mik.prog4.entity;
import java.util.Objects;
public class RoleEntity {
    private long id;
    private String kod;
    private String leiras;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id && Objects.equals(kod, that.kod) && Objects.equals(leiras, that.leiras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kod, leiras);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }
}
