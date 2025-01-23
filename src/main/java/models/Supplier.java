package models;
public class Supplier extends User {
    String brandofshoes;
    String countryOfOrigin;
    int deliveryCost;


    public Supplier(String brandofshoes, String countryOfOrigin,  String name, String email, int deliveryCost) {
        super(name, email);
        this.deliveryCost = deliveryCost;
        this.brandofshoes = brandofshoes;
        this.countryOfOrigin = countryOfOrigin;

    }


    @Override
    public String toString() {
        return "Supplier:\n"+ "Brand of shoes: " + brandofshoes + "\nCountry of origin: " + countryOfOrigin + "\nBrand representative: "+name + "\nHis email:" + email + "\nSize: " + "\nAmount of money: " + deliveryCost +"\n";
    }
}