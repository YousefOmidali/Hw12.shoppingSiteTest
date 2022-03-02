package entity;

public class Customer extends User{

    private String address;

    public Customer(int id) {
        super(id);
    }


   // public Customer(String address) {
   //     this.address = address;
   // }

    public Customer(int id, String username, String password, String address) {
        super(id, username, password);
        this.address = address;
    }

    public Customer(String username, String password, String address) {
        super(username, password);
        this.address = address;
    }

    public Customer(String username, String password,String address, int id) {
        super(username, password, id);
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                "username='" + getUsername() + '\'' +
                "password='" + getPassword() + '\'' +
                "id='" + getId() + '\'' +
                '}';
    }
}
