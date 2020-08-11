package javaSE.project.logic;

import javaSE.project.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentLogicTest {
    StudentLogicI studentLogicI;
    public StudentLogicTest() throws SQLException, ClassNotFoundException {
        studentLogicI = new StudentLogic();
    }
    @Test
    public void add() throws SQLException{
        Student student = new Student();
        student.setIdNumber("10101010");
        student.setCourse("FOOD SCIENCE");
        student.setRegistrationNo("FS04/22093/09");
        student.setName("GUSTEAU THE CHEF");
        if(!studentLogicI.add(student))
            Assert.assertFalse(false); // confirmation that it has failed
        Student search = studentLogicI.find(student.getRegistrationNo());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void update() throws SQLException {
        Student search = studentLogicI.find("com/0011/2015");
        search.setName("CHEF THE GUSTEAU");
        search.setCourse("SCIENCE FOOD");
        search.setIdNumber("01010101");
        if(!studentLogicI.update(search))
            Assert.assertFalse(false); // confirmation that it has failed
        // Means the update was successful. We need to test for correctness of the updated data
        Student student = studentLogicI.find(search.getId());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void delete()  throws SQLException {
        Student search = new Student();
        search.setRegistrationNo("com/0011/2015");
        if(!studentLogicI.delete(search));
            Assert.assertFalse(false);

        /*// Means the deletion was successful. We need to check if its available
        Assert.assertEquals(null, search.getRegistrationNo());*/
    }

    @Test
    public void findAll()  throws SQLException {
        List<Student> find=new ArrayList<>() {};
        find=studentLogicI.findAll();
        if(find.isEmpty());
            Assert.assertFalse(false);
    }

    @Test
    public void find()  throws SQLException {
        Student student=new Student();
        student.setId(35848896);
        //call find method
        Student search=studentLogicI.find(student.getId());
        //check results
        Assert.assertEquals(search.getId(),student.getId());

    }@Test
    public void find1()  throws SQLException {
        Student student=new Student();
        student.setRegistrationNo("com/0011/2015");
        //call find method
        Student search=studentLogicI.find(student.getRegistrationNo());
        //check results
        Assert.assertEquals(search.getRegistrationNo(),student.getRegistrationNo());

    }
}