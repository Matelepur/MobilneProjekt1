package lepur;

import java.io.Serializable;

public class Fruit implements Serializable {

    private int id;
    private String genus;
    private String name;
    private String family;
    private String order;
    private Nutriotion nutritions;

    public Fruit() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Nutriotion getNutritions() {
        return nutritions;
    }

    public void setNutritions(Nutriotion nutritions) {
        this.nutritions = nutritions;
    }
}
