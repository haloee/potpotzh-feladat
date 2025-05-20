package hu.pte.mik.prog4.repository;
import hu.pte.mik.prog4.entity.UserEntity;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserRepository extends Repository {
    public UserEntity findByUsername(String username) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "select ID,felhasznalonev,jelszo from user where username = ?;");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserEntity user = new UserEntity();
                user.setId(rs.getLong(1));
                user.setFelhasznalonev(rs.getString(2));
                user.setJelszo(rs.getString(3));
                return user;
            }
            return null;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
