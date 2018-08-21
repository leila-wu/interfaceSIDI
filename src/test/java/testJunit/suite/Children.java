package testJunit.suite;

import org.junit.*;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Children extends Base {
    @BeforeClass
    public static void beforeClassTestChildren(){
        System.out.println(" Children beforeClassTest");
    }

    @AfterClass
    public static void afterClassTestChildren(){
        System.out.println("Children afterClassTest");
    }

    @Before
    public void beforeTestChildren(){
        System.out.println("Children beforeTest");
    }

    @After
    public void afterTestChildren(){
        System.out.println("Children afterTest");
    }

    @Test
    public void test1Children(){
        System.out.println("Children test1");
    }

    @Test
    public void test2Children(){
        System.out.println("Children test2");
    }
}
