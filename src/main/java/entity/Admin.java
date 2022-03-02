package entity;

public class Admin extends User{

    private String nationalCode;

    public Admin(String username, String password, String nationalCode) {
        super(username, password);
        this.nationalCode = nationalCode;
    }

  //  public Admin(String nationalCode) {
  //      this.nationalCode = nationalCode;
  //  }

    public Admin(int id, String username, String password, String nationalCode) {
        super(id, username, password);
        this.nationalCode = nationalCode;
    }


    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nationalCode='" + nationalCode + '\'' +
                "username='" + super.getUsername() +'\''+
                "password='" + super.getPassword() +'\''+
                '}';
    }
}
