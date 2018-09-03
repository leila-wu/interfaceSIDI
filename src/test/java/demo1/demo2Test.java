package demo1;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class demo2Test {
    //实例化
    demo2 demo222;

    @Test
    public void sum() {
        demo222 = new demo2();
        //断言结果对比
        Assert.assertEquals(3,demo222.sum(1,2));
    }

    @Test
    public void subtract() {
        demo222 = new demo2();
        //断言结果对比
        Assert.assertEquals(1,demo222.subtract(2,1));
    }

//    @Test
//    public void multiply() {
//        demo222 = new demo2();
//        //断言结果对比
//        Assert.assertEquals(2,demo222.multiply(1,2));
//    }

//    @Test
//    public void divide() {
//        demo222 = new demo2();
//        //断言结果对比
//        Assert.assertEquals(2,demo222.divide(4,2));
//    }
}