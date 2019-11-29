package com.bakery.invoice.generation;

import com.bakery.invoice.generation.userinput.UserInputRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BakeryInvoiceGenerationApplication {
  public static void main(String[] args) {
    UserInputRouter userInputRouter = new UserInputRouter();
    userInputRouter.userEntry();
  }
}
