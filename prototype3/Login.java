package prototype3;

import java.io.Serializable;


//this class is a PLACEHOLDER CLASS
public class Login implements Serializable {

	private static final long serialVersionUID = 5541279089126047339L;
	private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login: Username - " + username + ", Password - " + password;
    }
}
