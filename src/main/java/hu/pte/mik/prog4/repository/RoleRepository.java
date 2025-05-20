package hu.pte.mik.prog4.repository;
import hu.pte.mik.prog4.entity.RoleEntity;
import hu.pte.mik.prog4.entity.UserEntity;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RoleRepository extends Repository {
    public List<RoleEntity> findRolesByUser(UserEntity userEntity) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "select r.ID,r.code,r.description from role r inner join user_role ur on r.ID = ur.role_id where ur.user_id = ?;");) {
            stmt.setLong(1, userEntity.getId());
            ArrayList<RoleEntity> roles = new ArrayList<RoleEntity>();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                RoleEntity role = new RoleEntity();
                role.setId(rs.getLong(1));
                role.setKod(rs.getString(2));
                role.setLeiras(rs.getString(3));
                roles.add(role);
            }
            return roles;
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
