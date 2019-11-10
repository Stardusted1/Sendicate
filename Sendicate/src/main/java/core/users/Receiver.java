package core.users;

import core.cargo.Supply;

public class Receiver extends Customer {

    protected String email;
    protected String phone;
    protected String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean ReceiveSupply(Supply supply){
        return false;
//        TODO
    }

    public boolean AcceptSupply(Supply supply){
        return false;
//        TODO
    }

    public boolean ChangeSupplyStatus(Supply supply){
        return false;
//        TODO
    }



    public Receiver(String name, String login, String password) {
        super(name, login, password);
    }


}
