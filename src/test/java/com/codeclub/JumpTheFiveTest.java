package com.codeclub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple App.
 */
public class JumpTheFiveTest
{
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void runs() {
        JumpTheFive test_app = new JumpTheFive();
        Assertions.assertInstanceOf(JumpTheFive.class, test_app);
    }

    @Test
    public void helpOutput() {
        JumpTheFive.main(new String[]{"-h"});
        Assertions.assertTrue(outputStreamCaptor.toString().contains("Usage"));
    }

    @Test
    public void test01() {
        JumpTheFive.main(new String[]{"123-456-7890"});
        Assertions.assertEquals("987-604-3215", outputStreamCaptor.toString().trim());
    }

    @Test
    public void test02() {
        JumpTheFive.main(new String[]{"That number to call is 098-765-4321."});
        Assertions.assertEquals("That number to call is 512-340-6789.", outputStreamCaptor.toString().trim());
    }
}
