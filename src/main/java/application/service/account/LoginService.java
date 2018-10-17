package application.service.account;

public interface LoginService {
    Integer getRole(String login);

    boolean checkAuth(String login, String password);
}
