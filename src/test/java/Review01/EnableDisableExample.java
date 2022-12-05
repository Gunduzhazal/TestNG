package Review01;

import org.testng.annotations.Test;

public class EnableDisableExample {

    @Test
    public void testOne() {
        System.out.println("My first case");
    }

    @Test(enabled = false)
    public void secondTest() {
        System.out.println("My second test");
    }
}
