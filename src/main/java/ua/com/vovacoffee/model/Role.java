package ua.com.vovacoffee.model;

import ua.com.vovacoffee.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<User>();

    public Role() {
        super();
        description = "";
    }

    public Role(RoleEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title.name() + "\nDescription: " + description;
    }

    public String toEquals() {
        return title.name();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeUsers(List<User> users) {
        this.users.removeAll(users);
    }

    public void clearUsers() {
        users.clear();
    }

    public List<User> getUsers() {
        return getUnmodifiableList(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public RoleEnum getTitle() {
        return title;
    }

    public void setTitle(RoleEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }
}
