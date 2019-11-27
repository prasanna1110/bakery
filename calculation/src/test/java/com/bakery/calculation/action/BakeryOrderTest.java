package com.bakery.calculation.action;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BakeryOrderTest {

  @Test
  public void testAnOrder(){
    String input = "21 MB11";
    String output = BakeryOrder.processRequest(input);
    Assert.assertNotEquals(
        "19 MB11-Blueberry Muffin $61.8\n\t2 X 2$9.95\n\t1 X 5$16.95\n\t1 X 8$24.95",
        output);
  }
}
