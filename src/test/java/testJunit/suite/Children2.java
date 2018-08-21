package testJunit.suite;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Children2 extends Base {
//    @BeforeClass
//    public static void beforeClassTestChildren(){
//        System.out.println(" Children beforeClassTest");
//    }
//
//    @AfterClass
//    public static void afterClassTestChildren(){
//        System.out.println("Children afterClassTest");
//    }

    @Before
    public void beforeTestChildren2(){
        System.out.println("Children222 beforeTest");
    }

    @After
    public void afterTestChildren2(){
        System.out.println("Children222 afterTest");
    }

    @Test
    public void test1Children2(){
        System.out.println("Children222 test1");
    }

    @Test
    public void test2Children2(){
        System.out.println("Children222 test2");
    }
}
