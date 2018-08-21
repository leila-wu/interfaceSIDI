package testJunit.suite;

import org.junit.*;

public class Base {

    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("beforeClassTest");
    }

    @AfterClass
    public static void afterClassTest(){
        System.out.println("afterClassTest");
    }

    @Before
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @After
    public void afterTest(){
        System.out.println("afterTest");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }
}
