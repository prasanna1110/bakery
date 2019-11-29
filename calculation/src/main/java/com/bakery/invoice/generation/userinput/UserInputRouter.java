package com.bakery.invoice.generation.userinput;

import static com.bakery.invoice.generation.constants.Constants.ORDER_TEXT;

import com.bakery.invoice.generation.businesslogic.BakeryOrder;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class UserInputRouter {
  public void userEntry() {
    System.out.println(ORDER_TEXT);
    Scanner scanner = new Scanner(new BufferedInputStream(System.in));
    BakeryOrder bakeryOrder = new BakeryOrder();
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      if (!"exit".equals(input)) {
        bakeryOrder.ProcessBillGenerationRequest(input);
      } else return;
    }
  }
}
