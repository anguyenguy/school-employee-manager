package pro192xa4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import pro192xa4.business.EmployeeManagement;
import pro192xa4.entity.Employee;
import pro192xa4.entity.Staff;
import pro192xa4.entity.Teacher;

public class TestJUnit {

	
	// Test staff get salary
	@Test
	   public void testStaffGetSalary() {
		float salaryRatio=(float) 0.22;
		float allowance = (float) 500;
		float noOfWD = (float) 20;
		float expectSalary = salaryRatio*730 +allowance+noOfWD*30;
		Staff staff = new Staff(salaryRatio,allowance,noOfWD);
	      assertEquals(expectSalary,staff.getSalary());
	   }
	
	@Test
	   public void testTeacherGetSalary() {
		float salaryRatio=(float) 0.22;
		float allowance = (float) 500;
		int teachingHours =  20;
		float expectSalary =salaryRatio * 730 +allowance + teachingHours * 45;
		Teacher staff = new Teacher(salaryRatio,allowance,teachingHours);
	      assertEquals(expectSalary,staff.getSalary());
	   }
	
	@Test
	 public void testSearchByName() {
		EmployeeManagement empMan = new EmployeeManagement();
		empMan.loadFileData();
		ArrayList<Employee> foundByName = empMan.searchByName("Quan");
		assertEquals("Nguyen Hong Quan",foundByName.get(0).getFullName());
	}
	@Test
	public void testSearchByDept() {
		EmployeeManagement empMan = new EmployeeManagement();
		empMan.loadFileData();
		ArrayList<Employee> foundByDept = empMan.searchByDept("Physics");
		assertEquals("Nguyen Thi Thao",foundByDept.get(0).getFullName());
	}

}
