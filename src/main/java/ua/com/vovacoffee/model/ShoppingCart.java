package ua.com.vovacoffee.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SalePosition> salePositions = new ArrayList<>();

    public ShoppingCart() {
        super();
    }

    public ShoppingCart(List<SalePosition> salePositions) {
        super();
        this.salePositions = salePositions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Shoping Cart: ");
        if (salePositions != null && !salePositions.isEmpty()) {
            int count = 1;
            for (SalePosition salePosition : salePositions) {
                sb.append("\n").append(count++).append(") ").append(salePosition.getProduct().getTitle())
                        .append("\n№ ").append(salePosition.getProduct().getId())
                        .append(", ").append(salePosition.getPrice()).append(" UAH");
            }
            sb.append("\nPrice: ").append(getPrice()).append(" UAH");
        } else {
            sb.append("is empty!");
        }
        return sb.toString();
    }

    public void addSalePosition(SalePosition salePosition) {
        if (salePosition != null) {
            if (!salePositions.contains(salePosition)) {
                salePositions.add(salePosition);
            } else {
                int index = salePositions.indexOf(salePosition);
                salePositions.get(index).numberIncr();
            }
        }
    }

    public void addSalePositions(List<SalePosition> salePositions) {
        for (SalePosition salePosition : salePositions) {
            addSalePosition(salePosition);
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
        return salePositions == null || salePositions.isEmpty() ? Collections.EMPTY_LIST : Collections.unmodifiableList(salePositions);
    }

    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
    }

    public double getPrice() {
        double sum = 0;
        for (SalePosition salePosition : salePositions) {
            sum += salePosition.getPrice();
        }
        return sum;
    }

    public int getSize() {
        int size = 0;
        for (SalePosition salePosition : salePositions) {
            size += salePosition.getNumber();
        }
        return size;
    }
}
