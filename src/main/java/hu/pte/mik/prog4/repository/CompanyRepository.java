package hu.pte.mik.prog4.repository;
import hu.pte.mik.prog4.entity.CompanyEntity;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CompanyRepository extends Repository {
    public CompanyEntity save(CompanyEntity company) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "Insert into technologiai_ceg(nev,alapitasi_ev,orszag,ismert_termek) values (?,?,?);"+
                             "SELECT LAST_INSERT_ID();");) {
            stmt.setString(1, company.getNev());
            stmt.setString(2, company.getAlapitasiEv());
            stmt.setString(3, company.getOrszag());
            stmt.setString(4, company.getIsmertTermek());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                company.setId(rs.getLong(1));
            }
            return company;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
    public CompanyEntity update(CompanyEntity company) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "update technologiai_ceg where id = ? set nev = ?, alapitasi_ev = ?, orszag = ?, ismert_termek = ?");) {
            stmt.setLong(1, company.getId());
            stmt.setString(2, company.getNev());
            stmt.setString(3, company.getAlapitasiEv());
            stmt.setString(4, company.getOrszag());
            stmt.setString(5, company.getIsmertTermek());
            int result= stmt.executeUpdate();
            return company;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CompanyEntity findById(Long id) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "select ID,nev,alapitasi_ev,orszag,ismert_termek from technologiai_ceg where ID = ?;");) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CompanyEntity company = new CompanyEntity();
                company.setId(rs.getLong(1));
                company.setAlapitasiEv(rs.getString(2));
                company.setOrszag(rs.getString(3));
                company.setIsmertTermek(rs.getString(4));
                return company;
            }
            return null;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<CompanyEntity> listAll() {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "select ID,nev,alapitasi_ev,orszag,ismert_termek from technologiai_ceg;");) {
            ResultSet rs = stmt.executeQuery();
            List<CompanyEntity> movies = new ArrayList<CompanyEntity>();
            while (rs.next()) {
                CompanyEntity company = new CompanyEntity();
                company.setId(rs.getLong(1));
                company.setNev(rs.getString(2));
                company.setAlapitasiEv(rs.getString(3));
                company.setOrszag(rs.getString(4));
                company.setIsmertTermek(rs.getString(5));
                movies.add(company);
            }
            return movies;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
