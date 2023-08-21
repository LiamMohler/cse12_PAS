/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources Used: JDK 17 Doc
 *
 * This is the custom tester for pa5.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * The private tester for PA5.
 */
public class CustomTester {
    /**
     * Test Student equals when non student obj.
     */
    @Test
    public void testStudentEquals() {
        Student student = new Student("Test", "Student", "A12345678");
        Course cor = new Course("null", "null", "null", 2);
        assertFalse("Test", student.equals(cor));
    }

    /**
     * Test Student equals when non student obj.
     */
    @Test
    public void testStudentCompareTo() {
        Student student = new Student("Test", "Student", "A12345678");
        Student student2 = new Student("Test", "Student", "A1234567");
        assertEquals(1, student.compareTo(student2));
        assertFalse(student.equals(student2));
    }

    /**
     * Test Course drop function
     */
    @Test
    public void testCourseDrop() {
        Student student = new Student("Test", "Student", "A12345678");
        Student student2 = new Student("Test", "Student", "A1234567");
        Course cor = new Course("null", "null", "null", 2);
        cor.enroll(student2);
        assertFalse(cor.drop(student));
        assertFalse(null, cor.enrolled.contains(student));
    }  

    /**
     * Test Course drop function
     */
    @Test
    public void testCourseEnroll() {
        Student student = new Student("Test", "Student", "A12345678");
        Student student2 = new Student("Test", "Student", "A1234567");
        Course cor = new Course("null", "null", "null", 1);
        cor.enroll(student2);
        assertFalse(cor.enroll(student));
        assertFalse(null, cor.enrolled.contains(student));
    }  
    /**
     * Test Course get roster function
     */
    @Test
    public void testCourseGetRoster() {
        ArrayList<Student> testRos = new ArrayList<>();
        Course cor = new Course("null", "null", "null", 150);
        for(int i = 0; i< 100;i++){
            String p = "A" + i;
            Student student = new Student("Test", "Student", p);
            cor.enroll(student);
            testRos.add(student);
        }
        Collections.sort(testRos);
        assertEquals(testRos, cor.getRoster());
    }  
    /**
     * Test Sanct constr.
     */
    @Test
    public void testSanctConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sanctuary sanc = new Sanctuary(-1, 0);
        });
    }
    /**
     * Test Sanct rescue function.
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary sanct = new Sanctuary(5, 2);
        assertEquals(3, sanct.rescue("null", 8));
        assertTrue(sanct.sanctuary.containsKey("null"));
        assertEquals(5, (int) sanct.sanctuary.get("null"));
    }

    /**
     * Test Sanct rescue function.
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(20, 1);
        sanct.rescue("null", 8);
        assertEquals(4,sanct.rescue("not-null", 4));
        assertFalse(null, sanct.sanctuary.containsKey("not-null"));
    }

    /**
     * Test Sanct release function.
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanct = new Sanctuary(20, 1);
        sanct.rescue("null", 8);
        sanct.release("null", 4);
        assertEquals(4, sanct.countForSpecies("null"));
    }

    /**
     * Test Sanct constr.
     */
    @Test
    public void testSanctReleaseTooMany() {
        Sanctuary sanc = new Sanctuary(5, 1);
        sanc.rescue("null", 3);

        
        assertThrows(IllegalArgumentException.class, () -> {
            sanc.release("null", 5);
        });
    }
}
