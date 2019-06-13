/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamzashafique
 */
public class Student {

    private String name;
    private String rollnumber;
    private float cgpa;
    
    public Student(String name, String rollnumber, float cgpa) {
        this.name = name;
        this.rollnumber = rollnumber;
        this.cgpa = cgpa;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
    
    @Override
    public String toString() {
       return name + " " + rollnumber + " " + cgpa; 
    }
}
