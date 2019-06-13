/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author hamzashafique
 */
public class Employee {

    private String name;
    private int salary;
    private String deptCode;
    
    public Employee(String name, int salary, String deptCode) {
        this.name = name;
        this.salary = salary;
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    
    public String toString() {
        return name + " " + salary + " " + deptCode;
    }
}
