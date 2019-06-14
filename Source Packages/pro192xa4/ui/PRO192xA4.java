/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa4.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import pro192xa4.business.AllowanceCalulator;
import pro192xa4.business.EmployeeManagement;
import pro192xa4.entity.EDegree;
import pro192xa4.entity.EPosition;
import pro192xa4.entity.Employee;
import pro192xa4.entity.Staff;
import pro192xa4.entity.Teacher;

/**
 *
 * @author hp
 */
public class PRO192xA4 {

    //create an employee by inputing it's attribute values from keyboard
    static Employee createNewImployee()  {
        System.out.print("Do you want to create a Staff or a Teacher (enter S for Staff, otherwise for Teacher)?");
        //accept Staff or Teacher details from keyboard        
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("s")) {
                Staff s = new Staff();
                //input staff details
                //your code
                try {
                    System.out.print("Name:");
                    String fullName = scan.nextLine();
                    if(fullName.length()==0) {
                    	throw  new Exception("Missing fullname");
                    }
                    s.setFullName(fullName);
                    System.out.print("Salary ratio:");
                    float salaryRatio = Float.parseFloat(scan.nextLine());
                    s.setSalaryRatio(salaryRatio);
                    System.out.print("Department:");
                    String department = scan.nextLine();
                    if(department.length()==0) {
                    	throw  new Exception("Missing department");
                    }
                    s.setDepartment(department);
                    System.out.print("Position ( 1=HEAD;  2=VICE HEAD; 3=STAFF):");
                    int position = Integer.valueOf(scan.nextLine());
                    if(position>3||position<1) {
                    	throw  new Exception("Check position again");
                    }
                    switch(position) {
                    case 1:s.setPosition(EPosition.HEAD);break;
                    case 2:s.setPosition(EPosition.VICE_HEAD);break;
                    case 3:s.setPosition(EPosition.STAFF);break;
                    }
                    System.out.print("Number of working day:");
                    float numberOfWD = Float.parseFloat(scan.nextLine());
                    s.setNoOfWorkingDay(numberOfWD);
                }catch(NumberFormatException e) {
                	System.out.println("Your input was wrong type or be missing! Please check again");
                	System.out.println("Your error: "+e);
                } catch (Exception e) {
					// TODO Auto-generated catch block
                	System.out.println("Error :"+ e);
				}

                return s;

            } else {
                Teacher t = new Teacher();
                //inputs Teacher details
                //your code
                try {
	                System.out.print("Name:");
	                String fullName = scan.nextLine();
                    if(fullName.length()==0) {
                    	throw  new Exception("Missing fullname");
                    }
	                t.setFullName(fullName);
	                System.out.print("Salary ratio:");
	                float salaryRatio = Float.parseFloat(scan.nextLine());
	                t.setSalaryRatio(salaryRatio);
	                System.out.print("Faculty:");
	                String faculty = scan.nextLine();
                    if(faculty.length()==0) {
                    	throw  new Exception("Missing faculty");
                    }
	                t.setFaculty(faculty);
	                System.out.print("Degree ( 1=BACHELOR;  2=MASTER; 3=DOCTOR):");
	                int degree = Integer.valueOf(scan.nextLine());
                    if(degree>3||degree<1) {
                    	throw  new Exception("Check degree again");
                    }
	                switch(degree) {
	                case 1:t.setDegree(EDegree.BACHELOR);break;
	                case 2:t.setDegree(EDegree.MASTER);break;
	                case 3:t.setDegree(EDegree.DOCTOR);break;
	                }
	                System.out.print("Number of teaching hours:");
	                int teachingHours= Integer.valueOf(scan.nextLine());
	                t.setTeachingHours(teachingHours);
                
	            }catch(NumberFormatException e) {
                	System.out.println("Your input was wrong type or be missing! Please check again");
                	System.out.println("Your error: "+e);
	            }catch (Exception e) {
					// TODO Auto-generated catch block
                	System.out.println("Error :"+ e);
				}
	                return t;
	            }
        }

    

    //display a list of employee
    static void display(ArrayList<Employee> listE) {
        System.out.println("Results:");
        System.out.println("Name, Fac/Dept, Deg/Pos, Sal Ratio, Allowance, T.Hours/W.Days, Salary");
        for (Employee e : listE) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
    	
        // create employee management object
        EmployeeManagement empMan = new EmployeeManagement();
        //menu
        Scanner scan = new Scanner(System.in);
        boolean keepRunning = true;
        
        //Read file
        empMan.loadFileData();
        
        while (keepRunning) {
            System.out.println("University Staff Management 1.0");
            System.out.println("\t1.Add staff");
            System.out.println("\t2.Search staff by name");
            System.out.println("\t3.Search staff by department/faculty");
            System.out.println("\t4.Display all staff");
            System.out.println("\t5.Exit");
            System.out.print("Select function (1,2,3,4 or 5): ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1://add staff/teacher    
                	Employee emp = createNewImployee();
                    float allowance = AllowanceCalulator.calculateAllowance(emp);
                    emp.setAllowance(allowance);
                    empMan.addEmployee(emp);
                    empMan.updateFileData();
                    break;
                case 2://search by name                    
                    System.out.print("\tEnter name to search: ");
                    scan = new Scanner(System.in);
                    String name = scan.nextLine();
                    ArrayList<Employee> foundByName = empMan.searchByName(name);
                    display(foundByName);
                    break;
                case 3://search by dept
                    System.out.print("\tEnter dept/fac to search: ");
                    scan = new Scanner(System.in);
                    String dept = scan.nextLine();
                    ArrayList<Employee> foundByDept = empMan.searchByDept(dept);
                    display(foundByDept);
                    break;
                case 4://display all
                    ArrayList<Employee> listE = empMan.listAll();
                    display(listE);
                    break;
                case 5://exit
                    keepRunning = false;
            }
        }
    }
}
