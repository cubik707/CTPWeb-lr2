package jaxb.test;

import jaxb.model.Department;
import jaxb.model.Employee;

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

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
        List<Department> list1 = new ArrayList<Department>();
        list1.add(dept);

        dept.setEmployees(list);
        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Department.class);

            // (1) Marshaller : Java Object to XML content.
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(dept, System.out);

            // Write to File
            File outFile = new File(XML_FILE);
            m.marshal(dept, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());
            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.
            Department deptFromFile1 = (Department) um.unmarshal(new FileReader(
                    XML_FILE));
            List<Employee> emps = deptFromFile1.getEmployees();
            for (Employee emp : emps) {
                System.out.println("Employee: " + emp.getEmpName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
