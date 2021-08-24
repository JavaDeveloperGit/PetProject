package ua.com.vovacoffee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_details")
    private String shippingDetails;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<SalePosition> salePositions = new ArrayList<SalePosition>();

    public Order() {
        super();
        shippingAddress = "";
        shippingDetails = "";
        description = "";
        number = createRandomString();
        date = dateToString(new Date());
    }

    public Order(Status status, User client, List<SalePosition> salePositions) {
        super();

        this.status = status;
        this.client = client;

        addSalePositions(salePositions);

        shippingAddress = "";
        shippingDetails = "";
        description = "";
        number = createRandomString();
        date = dateToString(new Date());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(", ")
                .append(status.getDescription()).append(",\n").append(date);

        if (client != null) {
            sb.append("\n\nClient: ").append(client.getName())
                    .append("\ne-mail: ").append(client.getEmail())
                    .append("\nphone: ").append(client.getPhone()).append("\n");
        }

        if (manager != null) {
            sb.append("\n").append(manager.getRole().getDescription())
                    .append(" ").append(manager.getName()).append("\n");
        }

        if (!shippingAddress.isEmpty()) {
            sb.append("\nShipping address: ").append(shippingAddress);
        }
        if (!shippingDetails.isEmpty()) {
            sb.append("\nShipping details: ").append(shippingDetails);
        }
        if (!description.isEmpty()) {
            sb.append("\nDescription: ").append(description);
        }

        if (salePositions != null && !salePositions.isEmpty()) {
            sb.append("\nSale Positions: ");
            int count = 1;
            for (SalePosition salePosition : salePositions) {
                sb.append("\n").append(count++).append(") ").append(salePosition.getProduct().getTitle())
                        .append(", № ").append(salePosition.getProduct().getId()).append(",\n")
                        .append(salePosition.getNumber()).append(" x ")
                        .append(salePosition.getProduct().getPrice()).append(" = ")
                        .append(salePosition.getPrice()).append(" UAH;");
            }
            sb.append("\n\nPRICE = ").append(getPrice()).append(" UAH");
        }
        return sb.toString();
    }

    @Override
    public String toEquals() {
        return getNumber();
    }

    public void initialize(String number, Date date, String shippingAddress, String shippingDetails,
                           String description, Status status, User client, User manager) {
        setNumber(number);
        setDate(date);
        setShippingAddress(shippingAddress);
        setShippingDetails(shippingDetails);
        setDescription(description);
        setStatus(status);
        setClient(client);
        setManager(manager);
    }

    public void addSalePosition(SalePosition salePosition) {
        salePositions.add(salePosition);
        if (salePosition.getOrder() != this) {
            salePosition.setOrder(this);
        }
    }

    public void addSalePositions(List<SalePosition> salePositions) {
        this.salePositions.addAll(salePositions);
        for (SalePosition salePosition : salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    public void removeSalePosition(SalePosition salePosition) {
        salePositions.remove(salePosition);
    }

    public void removeSalePositions(List<SalePosition> salePositions) {
        this.salePositions.removeAll(salePositions);
    }

    public void clearSalePositions() {
        salePositions.clear();
    }

    public List<SalePosition> getSalePositions() {
        return getUnmodifiableList(salePositions);
    }

    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
        for (SalePosition salePosition : this.salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? "" : number;
    }

    public void newNumber() {
        number = createRandomString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date == null ? "" : dateToString(date);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? "" : shippingAddress;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails == null ? "" : shippingDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }
}
