package se.iths.stefan.labbdrift.validator;

import org.springframework.stereotype.Component;
import se.iths.stefan.labbdrift.model.Product;

@Component
public class ProductValidator {

    public void validate(Product p) {
        if (p == null) throw new IllegalArgumentException("Product får inte vara null");

        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Produktnamn får inte vara tomt");
        }

        if (p.getPrice() <= 0) {
            throw new IllegalArgumentException("Pris får inte vara negativt");
        }

        if (p.getStock() < 0) {
            throw new IllegalArgumentException("Lagersaldo får inte vara negativt");

        }
    }
}
