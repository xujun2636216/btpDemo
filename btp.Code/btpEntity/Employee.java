package btpEntity;
import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private Date dateTime;

    public Employee() {}

    public Employee(int id, String name, int age, double salary, Date dateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dateTime = dateTime;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", dateTime=" + dateTime +
                '}';
    }
}
