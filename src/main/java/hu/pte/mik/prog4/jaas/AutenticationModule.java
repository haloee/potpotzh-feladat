package hu.pte.mik.prog4.jaas;
import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.pte.mik.prog4.entity.UserEntity;
import hu.pte.mik.prog4.entity.RoleEntity;
import hu.pte.mik.prog4.repository.UserRepository;
import hu.pte.mik.prog4.repository.RoleRepository;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class AutenticationModule implements LoginModule {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private CallbackHandler callbackHandler;
    private Subject subject;
    private String login;
    private List<String> users;

    public AutenticationModule() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }
    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        try {
            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("login");
            callbacks[1] = new PasswordCallback("password", true);

            this.callbackHandler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            if(name !=  null) {
                UserEntity user = this.userRepository.findByUsername(name);
                BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), user.getJelszo());

                if(verify.verified) {
                    this.login = name;
                    this.users = this.roleRepository.findRolesByUser(user)
                            .stream()
                            .map(RoleEntity::getKod)
                            .collect(Collectors.toList());
                    return true;
                }
            }

            throw new LoginException("Hibás Authentikáció");
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Hiba: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(new UserPrincipal(this.login));
        this.users.stream().map(RolePrincipal::new)
                .forEach(this.subject.getPrincipals()::add);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().clear();
        return true;
    }
}
