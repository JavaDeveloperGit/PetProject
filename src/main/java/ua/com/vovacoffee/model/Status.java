package ua.com.vovacoffee.model;

import ua.com.vovacoffee.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "statuses")
public class Status extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<Order>();

    public Status() {
        super();
        description = "";
    }

    public Status(StatusEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title.name() + "\nDescription: " + description;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void removeOrders(List<Order> orders) {
        this.orders.removeAll(orders);
    }

    public void clearOrders() {
        orders.clear();
    }

    public List<Order> getOrders() {
        return getUnmodifiableList(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public StatusEnum getTitle() {
        return title;
    }

    public void setTitle(StatusEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }
}
