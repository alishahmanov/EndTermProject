package models;
import models.enums.Role;

public class Client extends User {
    private Long id;
    private int amountOfMoney;
    private boolean gender;
    private int size;

    public Client() {
        super();
    }

    public Client(Long id, String name, String email, String password, Role role, boolean gender, int size, int amountOfMoney) {
        super(name, email, password, role);
        setId(id);
        setSize(size);
        setGender(gender);
        setAmountOfMoney(amountOfMoney);
    }

    public Client(String name, String email, String password, Role role, boolean gender, int size, int amountOfMoney) {
        super(name, email, password, role);
        setSize(size);
        setGender(gender);
        this.amountOfMoney = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public boolean isGender() {
        return gender;
    }

    public int getSize() {
        return size;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (gender){
            return "Client:\n" + "Name: " + getName() + "\nGender: Male"+ "\nSize of leg: "+ size + "\nAmount of money: " + amountOfMoney+ "\n";}
        else{
            return "Client:\n" + "Name: " + getName()+ "\nHis email:" + getEmail()  + "\nGender: Female "+ "\nSize of leg: "+ size + "\nAmount of money: " + amountOfMoney+ "\n";}
    }

}
