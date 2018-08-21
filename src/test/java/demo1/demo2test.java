package demo1;

import org.junit.Assert;
import org.junit.Test;

public class demo2test {
    //针对已存在源码写单元测试用例
    @Test
    public void test111(){
        //实例化
        demo2 demo222;
        demo222 = new demo2();
        //断言结果对比
        Assert.assertEquals(3,demo222.sum(1,2));
        ;
    }
}
