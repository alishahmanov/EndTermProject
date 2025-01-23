package models;

public class Client extends User {
    int amountOfMoney;
    boolean gender;
    int size;

    public Client(String name, String email,boolean gender, int size, int amountOfMoney) {
        super(name, email);
        this.size = size;
        this.amountOfMoney = amountOfMoney;
        this.gender = gender;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public boolean getGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        if (gender==true){
            return "Client:\n" + "Name: " + name + "\nGender: Male"+ "\nSize of leg: "+ size + "\nAmount of money: " + amountOfMoney+ "\n";}
        else{
            return "Client:\n" + "Name: " + name + "\nHis email:" + email  + "\nGender: Female "+ "\nSize of leg: "+ size + "\nAmount of money: " + amountOfMoney+ "\n";}
    }

}
