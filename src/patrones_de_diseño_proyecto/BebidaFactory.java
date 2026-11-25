package patrones_de_diseño_proyecto;

import java.util.Arrays;
import java.util.List;

public class BebidaFactory {
    public static List<String> getCatalogoNombres() {
        return Arrays.asList(
            "Caramel Macchiato", "Caffe Latte", "Cappuccino", "Americano",
            "Mocha", "Frappuccino", "Matcha Latte", "Cold Brew"
        );
    }

    public static Bebida crearBebida(String nombre) {
        switch (nombre) {
            case "Caramel Macchiato":
                return new BebidaBase(nombre, "Espresso, leche y dulce caramelo", "☕", 89.00);
            case "Caffe Latte":
                return new BebidaBase(nombre, "Espresso suave con leche cremosa", "♨", 75.00);
            case "Cappuccino":
                return new BebidaBase(nombre, "Espresso, espuma y leche", "♨", 72.00);
            case "Americano":
                return new BebidaBase(nombre, "Espresso diluido en agua caliente", "♨", 55.00);
            case "Mocha":
                return new BebidaBase(nombre, "Chocolate, espresso y leche", "☕", 85.00);
            case "Frappuccino":
                return new BebidaBase(nombre, "Bebida fría y cremosa", "❄", 95.00);
            case "Matcha Latte":
                return new BebidaBase(nombre, "Matcha preparado con leche", "🍵", 90.00);
            case "Cold Brew":
                return new BebidaBase(nombre, "Café infusionado en frío", "❆", 79.00);
            default:
                return new BebidaBase(nombre, "Bebida preparada al momento", "♨", 60.00);
        }
    }
}