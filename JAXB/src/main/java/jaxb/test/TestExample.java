package jaxb.test;

import jaxb.model.Department;
import jaxb.model.Employee;
import jaxb.model.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {

    private static final String XML_FILE = "dept-info.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        List<Employee> employeesDept1 = new ArrayList<>();
        employeesDept1.add(emp1);
        employeesDept1.add(emp2);

        List<Employee> employeesDept2 = new ArrayList<>();
        employeesDept2.add(emp3);

        // Создание департаментов
        Department dept1 = new Department("D01", "ACCOUNTING", "NEW YORK");
        dept1.setEmployees(employeesDept1);

        Department dept2 = new Department("D02", "SALES", "LOS ANGELES");
        dept2.setEmployees(employeesDept2);

        List<Department> departments = new ArrayList<>();
        departments.add(dept1);
        departments.add(dept2);

        Organization organization = new Organization(departments);

        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            // (1) Marshaller : Java Object to XML content.
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(organization, System.out);

            // Write to File
            File outFile = new File(XML_FILE);
            m.marshal(organization, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());
            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.
            Organization orgFromFile = (Organization) um.unmarshal(new FileReader(XML_FILE));
            for (Department dept : orgFromFile.getDepartments()) {
                System.out.println("Department: " + dept.getDeptName());
                for (Employee emp : dept.getEmployees()) {
                    System.out.println("Employee: " + emp.getEmpName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
