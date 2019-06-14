/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa4.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import pro192xa4.entity.EDegree;
import pro192xa4.entity.EPosition;
import pro192xa4.entity.Employee;
import pro192xa4.entity.Staff;
import pro192xa4.entity.Teacher;

/**
 *
 * @author hp
 */
public class EmployeeManagement {

    //store all staff/teacher
    ArrayList<Employee> listE;

    public EmployeeManagement() {        
        listE = new ArrayList<>();
    }
    
    private void loadData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String numberOfEm = scanner.nextLine();
            
            for(int i=0; i<Integer.valueOf(numberOfEm);i++) {
            	String employeeDetails = scanner.nextLine();
            	String[] arrayDetails = employeeDetails.split(",");
            	//Test
            
            	if(arrayDetails[0].contentEquals("Staff")) {
            		Staff s = new Staff();
                    s.setFullName(arrayDetails[1]);
                    float salaryRatio =Float.parseFloat(arrayDetails[4]);
                    s.setSalaryRatio(salaryRatio);
                    String department = arrayDetails[2];
                    s.setDepartment(department);           
                    String position = arrayDetails[3];
                    switch(position) {
                    case "HEAD":s.setPosition(EPosition.HEAD);break;
                    case "VICE_HEAD":s.setPosition(EPosition.VICE_HEAD);break;
                    case "STAFF":s.setPosition(EPosition.STAFF);break;
                    }
                    float numberOfWD = Float.parseFloat(arrayDetails[6]);
                    s.setNoOfWorkingDay(numberOfWD);
                    this.addEmployee(s);
            	}else {
            		Teacher t = new Teacher();
            		t.setFullName(arrayDetails[1]);
                    float salaryRatio = Float.parseFloat(arrayDetails[4]);
                    t.setSalaryRatio(salaryRatio);
                    String faculty = arrayDetails[2];
                    t.setFaculty(faculty);
                    String position = arrayDetails[3];
                    switch(position) {
                    case "BACHELOR":t.setDegree(EDegree.BACHELOR);break;
                    case "MASTER":t.setDegree(EDegree.MASTER);break;
                    case "DOCTOR":t.setDegree(EDegree.DOCTOR);break;
                    }
                    int numberOfWD = (int) Float.parseFloat(arrayDetails[6]);
                    t.setTeachingHours(numberOfWD);
                    this.addEmployee(t);
                    }  		
            	}
            
         }catch(IOException e) {
        	 System.out.println(e);
         }   
        catch (Exception e) {
             System.out.println(e);
        }
    	
    }

    public void loadFileData() {
    	
        File file = new File("data.txt");
        if(!file.exists()) {
        	System.out.println("*****Load data: data.txt file not found");
        	Scanner scanner = new Scanner(System.in);
        	System.out.println("Do you want to change data.txt's path(y/n)");
        	String answer=scanner.nextLine();
        	if(answer.contentEquals("y")) {
        		System.out.println("Your path: ");
        		String path=scanner.nextLine();
        		File fileNew = new File(path);
        		loadData(fileNew);
        	}
        }else {
        	loadData(file);
        }
    }
    
    
    public void updateFileData() {
    	
    	PrintWriter writer;
		try {
			writer = new PrintWriter("data.txt", "UTF-8");
			writer.println(this.listE.size());
			for (Employee e : this.listE) {
				if(e.getClass()==Staff.class) {
					writer.print("Staff,");
					writer.print(e.getFullName()+",");
					Staff staff = (Staff) e;
					writer.print(staff.getDepartment()+",");
					writer.print(staff.getPosition()+",");
					writer.print(e.getSalaryRatio()+",");
					writer.print(e.getAllowance()+",");
					writer.print(staff.getNoOfWorkingDay()+",");
					writer.println(staff.getSalary());
				}else {
					writer.print("Teacher,");
					Teacher teacher = (Teacher) e;
					writer.print(e.getFullName()+",");
					writer.print(teacher.getFaculty()+",");
					writer.print(teacher.getDegree()+",");	
					writer.print(e.getSalaryRatio()+",");
					writer.print(e.getAllowance()+",");
					writer.print(teacher.getTeachingHours()+",");
					writer.println(teacher.getSalary());
				}
			}
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
    }
    
    
    public void addEmployee(Employee emp) {
        //add emp to listE
        //your code 	
    	this.listE.add(emp);
    	/*
    	// Save in file
		try {
		    File file = new File("data.txt");
			FileWriter fr = new FileWriter(file, true);
				if(emp.getClass()==Staff.class) {
					fr.write("Staff,");
					fr.write(emp.getFullName()+",");
					Staff staff = (Staff) emp;
					fr.write(staff.getDepartment()+",");
					fr.write(staff.getPosition()+",");
					fr.write(emp.getSalaryRatio()+",");
					fr.write(emp.getAllowance()+",");
					fr.write(staff.getNoOfWorkingDay()+",");
					fr.write(""+staff.getSalary());
					fr.write(System.lineSeparator());
				}else {
					fr.write("Teacher-");
					Teacher teacher = (Teacher) emp;
					fr.write(emp.getFullName()+",");
					fr.write(teacher.getFaculty()+",");
					fr.write(teacher.getDegree()+",");	
					fr.write(emp.getSalaryRatio()+",");
					fr.write(emp.getAllowance()+",");
					fr.write(teacher.getTeachingHours()+",");
					fr.write(""+teacher.getSalary());
					fr.write(System.lineSeparator());
				}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}*/
    	

    }
    //search by staff/teacher's name
    public ArrayList<Employee> searchByName(String name) {
    	String fixName = name.toLowerCase();
        //store all matching staff or teacher
        ArrayList<Employee> empFound = new ArrayList<>();
        //your code
        for(int i=0;i<this.listE.size();i++) {
        	if(this.listE.get(i).getFullName().toLowerCase().contains(fixName)) {
        		empFound.add(this.listE.get(i));
        	}
        }
        return empFound;
    }
    //search by staff/teacher's department/faculty
    public ArrayList<Employee> searchByDept(String dept) {
        ArrayList<Employee> empFound = new ArrayList<>();
        //your code
        String fixDept = dept.toLowerCase();
        for(int i=0;i<this.listE.size();i++) {
        	if(this.listE.get(i).getClass()==Staff.class) {
        		Staff staff = (Staff) this.listE.get(i);
        		if(staff.getDepartment().toLowerCase().contains(fixDept)) {
        			empFound.add(this.listE.get(i));
        		}
        	}else {
        		Teacher teacher = (Teacher) this.listE.get(i);
        		if(teacher.getFaculty().toLowerCase().contains(fixDept)) {
        			empFound.add(this.listE.get(i));
        		}
        	}
        }
        return empFound;
    }

    public ArrayList<Employee> listAll() {
        //sort the list of staff/teacher before return
        //your code
    	Collections.sort(this.listE);;
        return listE;
    }

}
