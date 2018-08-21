package testJunit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class One {
    @Test
    public void myFirstTest() {

        assertEquals(2, 2 + 1);
    }

    @Ignore("Not Ready to Run")
    @Test
    public void demo1(){
        String str1 = "abc";
        String str2 = "abc";

        Assert.assertEquals(str1,str2);
    }

}
