package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderParamTest {

    private List<String> color;

    public OrderParamTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] dataGen() {
        return new Object[][] {
                {List.of("GREY", "YELLOW")},
                {List.of("YELLOW")},
                {null},
        };
    }

    @Test public void checkEm() {
        System.out.println(color);
    }
}
