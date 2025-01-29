package models;

public class Client extends User {
    private int amountOfMoney;
    private boolean gender;
    private int size;

    public Client() {
        super();
    }

    public Client(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        super(name, email, password);
        setSize(size);
        setGender(gender);
        setAmountOfMoney(amountOfMoney);
    }

    public Client(String name, String email, String password, boolean gender, int size) {
        super(name, email, password);
        setSize(size);
        setGender(gender);
        this.amountOfMoney = 0;
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
