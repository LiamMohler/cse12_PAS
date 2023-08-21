/*
 * Name: Hannah Hui
 * Email: hahui@ucsd.edu
 * PID: A00000000
 * Sources Used: JDK 17 Doc
 *
 * This is an example of the public tester for CSE 12 PA5 in Winter 2023.
 * It contains sanity checks on all 3 classes.
 */

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * The public tester for PA5, which covers some basic test cases.
 */
public class PublicTester {

    // ----------------Student class----------------

    /**
     * Test Student constructor with valid argument
     */
    @Test
    public void testStudentConstructor() {
        Student student = new Student("Test", "Student", "A12345678");
        assertEquals("Test", student.getFirstName());
        assertEquals("Student", student.getLastName());
        assertEquals("A12345678", student.getPID());
    }

    /**
     * Test equals with two equivalent Student objects
     */
    @Test
    public void testEqualsSame() {
        Student student1 = new Student("Test", "Student",
                "A12345678");
        Student student2 = new Student("Test", "Student",
                "A12345678");
        assertEquals(student1, student2);
    }

    /**
     * Test hashCode from Student
     */
    @Test
    public void testHashValueSame() {
        Student student = new Student("Test", "Student",
                "A12345678");
        assertEquals(student.hashCode(), Objects.hash("Test",
                "Student", "A12345678"));
    }

    // ----------------Course class----------------

    /**
     * Test Course constructor with valid argument
     */
    @Test
    public void testCourseConstructor() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        assertEquals("CSE", course.getDepartment());
        assertEquals("12", course.getNumber());
        assertEquals("Data Structure", course.getDescription());
        assertEquals(100, course.getCapacity());
    }

    /**
     * Enroll a new non-null student
     */
    @Test
    public void testEnrollNewStudent() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        course.enrolled = new HashSet<>();
        Student stu = new Student("Whales", "Ocean", "A123");
        assertTrue(course.enroll(stu));
        assertTrue(course.enrolled.contains(stu));
        assertEquals(1, course.enrolled.size());
    }

    /**
     * Drop an existing student
     */
    @Test
    public void testDropExistingStudent() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        Student stu = new Student("Whales", "Ocean", "A123");

        course.enrolled = new HashSet<>();
        course.enrolled.add(stu);

        assertTrue(course.drop(stu));
        assertFalse(course.enrolled.contains(stu));
        assertEquals(0, course.enrolled.size());
    }

    /**
     * Test isFull with a full roster
     */
    @Test
    public void testIsFull() {
        Course course = new Course("CSE", "12", "Data Structure", 3);
        course.enrolled = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            course.enrolled.add(new Student("Whales" + i, "Ocean", "A123"));
        }

        assertTrue(course.isFull());
    }

    /**
     * Get a descriptive string from a normal course
     */
    @Test
    public void testToString() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        assertEquals("CSE 12 [100] Data Structure", course.toString());
    }

    // ----------------Sanctuary class----------------

    /**
     * Test Sanctuary constructor with valid argument
     */
    @Test
    public void testSanctuaryConstructor() {
        Sanctuary sanct = new Sanctuary(500, 50);
        assertEquals(500, sanct.getMaxAnimals());
        assertEquals(50, sanct.getMaxSpecies());
        assertEquals(0, sanct.sanctuary.size());
    }

    /**
     * Test getNum with valid argument
     */
    @Test
    public void testCountForSpecies() {
        Sanctuary sanct = new Sanctuary(500, 50);
        sanct.sanctuary.put("Giraffe", 20);
        sanct.sanctuary.put("Meerkat", 65);

        assertEquals(20, sanct.countForSpecies("Giraffe"));
        assertEquals(65, sanct.countForSpecies("Meerkat"));
    }

    /**
     * Get the total number of animals at the sanctuary
     */
    @Test
    public void TestGetTotalAnimals() {
        Sanctuary sanct = new Sanctuary(100, 4);
        sanct.sanctuary.put("Koala", 55);
        assertEquals(55, sanct.getTotalAnimals());
    }

    /**
     * Get total number of species at a sanctuary
     */
    public void TestGetTotalSpecies() {
        Sanctuary sanct = new Sanctuary(1000, 4);
        sanct.sanctuary.put("Koala", 55);
        sanct.sanctuary.put("Capybara", 70);
        sanct.sanctuary.put("Groundhog", 22);
        sanct.sanctuary.put("Vulture", 3);
        sanct.sanctuary.put("Zebra", 14);
        assertEquals(5, sanct.getTotalSpecies());
    }

    /**
     * Add a new species of animals to not-full sanctuary
     */
    @Test
    public void testRescue() {
        Sanctuary sanct = new Sanctuary(100, 20);

        assertEquals(0, sanct.rescue("Panda", 3));
        assertTrue(sanct.sanctuary.containsKey("Panda"));
        assertEquals(3, (int) sanct.sanctuary.get("Panda"));

        assertEquals(0, sanct.rescue("Frog", 32));
        assertTrue(sanct.sanctuary.containsKey("Frog"));
        assertEquals(32, (int) sanct.sanctuary.get("Frog"));

        assertEquals(2, sanct.sanctuary.size());
    }

    /**
     * Remove animals from a larger group of them at the sanctuary
     */
    @Test
    public void testRelease() {
        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Capybara", 40);
        sanct.sanctuary.put("Horse", 5);

        sanct.release("Capybara", 10);
        assertTrue(sanct.sanctuary.containsKey("Capybara"));
        assertEquals(30, (int) sanct.sanctuary.get("Capybara"));

        assertTrue(sanct.sanctuary.containsKey("Horse"));
        assertEquals(5, (int) sanct.sanctuary.get("Horse"));

        assertEquals(2, sanct.sanctuary.size());
    }
}
