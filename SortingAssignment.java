
import java.util.Arrays;

public class SortingAssignment {

    public static void main(String[] args) {
        
        Integer[] integerList = {2, -9, 0, 5, 12, -25, 22, 9, 8, 12};
        String[] stringList = {"mohsin", "hamza", "akbar", "moiz", "talha"};
        Student[] studentList = {
            new Student("mohsin", "bscs15069", 2.7f), 
            new Student("hamza", "bscs15024", 3.37f), 
            new Student("hassan", "bscs15022", 3.0f), 
            new Student("zohair", "bscs15001", 3.9f),
            new Student("akasha", "bscs15026", 3.0f)
        };
        
        Employee[] employeeList = {
            new Employee("mohsin", 30000, "HR"), 
            new Employee("hamza", 40000, "DevOps"), 
            new Employee("hassan", 25680, "Backend"), 
            new Employee("zohair", 50000, "Ml"),
            new Employee("akasha", 60000, "Frontend")
        };
        
        SortingAlgorithm sortingAlgorithm = new SelectionSort();
        
        /* **************************************************************************** */
        System.out.println("Integer list before sorting:\n" + Arrays.toString(integerList));
        
        sortingAlgorithm.sort(integerList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return (Integer)obj1 - (Integer)obj2;  // swap if first element is greater
            }
        });
        
       System.out.println("Integer list after sorting descending:\n" + Arrays.toString(integerList));
        
        sortingAlgorithm.sort(integerList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return  (Integer)obj2 - (Integer)obj1;  // swap if second element is greater
            }
        });
        
        System.out.println("Integer list after sorting ascending:\n" + Arrays.toString(integerList));
        /* **************************************************************************** */
        System.out.println("");
        /* **************************************************************************** */
        System.out.println("String list before sorting:\n" + Arrays.toString(stringList));
        
        sortingAlgorithm.sort(stringList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return ((String)obj1).compareTo((String)obj2);  // swap if first element is greater
            }
        });
        
       System.out.println("String list after sorting descending:\n" + Arrays.toString(stringList));
        
        sortingAlgorithm.sort(stringList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                 return ((String)obj2).compareTo((String)obj1);  // swap if second element is greater
            }
        });
        
        System.out.println("String list after sorting ascending:\n" + Arrays.toString(stringList));
        /* **************************************************************************** */
        System.out.println("");
        /* **************************************************************************** */
        System.out.println("Student list before sorting:\n" + Arrays.toString(studentList));
        
        sortingAlgorithm.sort(studentList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return ((Student)obj1).getName().compareTo(((Student)obj2).getName());  // swap if first element is greater
            }
        });
        
       System.out.println("Student list after sorting descending on name:\n" + Arrays.toString(studentList));
        
        sortingAlgorithm.sort(studentList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return ((Student)obj2).getName().compareTo(((Student)obj1).getName());  // swap if second element is greater
            }
        });
        
        System.out.println("Student list after sorting ascending on name:\n" + Arrays.toString(studentList));
        /* **************************************************************************** */
         System.out.println("");
        /* **************************************************************************** */
        System.out.println("Employee list before sorting:\n" + Arrays.toString(employeeList));
        
        sortingAlgorithm.sort(employeeList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return ((Employee)obj1).getSalary() - ((Employee)obj2).getSalary();   // swap if first element is greater
            }
        });
        
       System.out.println("Employee List list after sorting descending on salary:\n" + Arrays.toString(employeeList));
        
        sortingAlgorithm.sort(employeeList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                return ((Employee)obj2).getSalary() - ((Employee)obj1).getSalary();  // swap if second element is greater
            }
        });
        
        System.out.println("Employee List list after sorting ascending on salary:\n" + Arrays.toString(employeeList));
        /* **************************************************************************** */
    }
    
}
