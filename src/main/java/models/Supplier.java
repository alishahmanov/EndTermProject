package models;
public class Supplier extends User {
    private String brandofshoes;
    private String countryOfOrigin;
    private int deliveryCost;

    public Supplier() {
        super();
    }

    public Supplier(String brandofshoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        super(name, email, password);
       setBrandofshoes(brandofshoes);
       setCountryOfOrigin(countryOfOrigin);
       setDeliveryCost(deliveryCost);
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