package models;
import models.enums.Role;

public abstract class User {
    private String name;
    private String email;
    private String password;
    private Role role;

    public User() {
    }

    public User(String name, String email, String password, Role role) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
