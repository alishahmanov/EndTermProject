package models;
import models.enums.Role;
public class Supplier extends User {
    private Long id;
    private String brandofshoes;
    private String countryOfOrigin;
    private int deliveryCost;

    public Supplier() {
        super();
    }

    public Supplier(Long id, String name, String email, String password, String brandOfShoes, String countryOfOrigin, int deliveryCost, Role role) {
        super(name, email, password, Role.SUPPLIER);
        this.id = id;
        this.brandofshoes = brandOfShoes;
        this.countryOfOrigin = countryOfOrigin;
        this.deliveryCost = deliveryCost;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBrandofshoes() {
        return brandofshoes;
    }

    public void setBrandofshoes(String brandofshoes) {
        this.brandofshoes = brandofshoes;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public String toString() {
        return "Supplier:\n"+ "Brand of shoes: " + brandofshoes + "\nCountry of origin: " + countryOfOrigin + "\nBrand representative: "+ getName() + "\nHis email:" + getEmail() + "\nSize: " + "\nAmount of money: " + deliveryCost +"\n";
    }
}