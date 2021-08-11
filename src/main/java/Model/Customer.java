package Model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private int salary = 0;

    public Customer() {
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int id, String name, String phone, String email, int salary) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
    }

    public Customer(String name, String phone, String email, int salary) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
