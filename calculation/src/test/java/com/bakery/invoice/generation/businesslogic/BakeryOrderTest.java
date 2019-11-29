package com.bakery.invoice.generation.businesslogic;

import static com.bakery.invoice.generation.constants.Constants.INVALID_INPUT;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BakeryOrderTest {

  @Test
  public void testAnOrder() {
    String input = "19 MB11";
    BakeryOrder bakeryOrder = new BakeryOrder();
    String output = bakeryOrder.ProcessBillGenerationRequest(input);
    Assert.assertEquals("19 MB11 $61.8\n\t2 X 2 $9.95\n\t1 X 5 $16.95\n\t1 X 8 $24.95", output);
    output = bakeryOrder.ProcessBillGenerationRequest("10 VS5");
    Assert.assertEquals("10 VS5 $17.98\n\t2 X 5 $8.99", output);
    output = bakeryOrder.ProcessBillGenerationRequest("14 MB11");
    Assert.assertEquals("14 MB11 $54.8\n\t3 X 2 $9.95\n\t1 X 8 $24.95", output);
    output = bakeryOrder.ProcessBillGenerationRequest("13 CF");
    Assert.assertEquals("13 CF $25.85\n\t1 X 3 $5.95\n\t2 X 5 $9.95", output);
  }

  @Test
  public void testAnInvalidOrder() {
    String input = "19 MB111";
    BakeryOrder bakeryOrder = new BakeryOrder();
    String output = bakeryOrder.ProcessBillGenerationRequest(input);
    Assert.assertEquals(INVALID_INPUT, output);
    }

  @Test
  public void testAnInvalidQuantity() {
    String input = "34 CF";
    BakeryOrder bakeryOrder = new BakeryOrder();
    String output = bakeryOrder.ProcessBillGenerationRequest(input);
    Assert.assertEquals("34 CF $0.0", output);
  }
}
