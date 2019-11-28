package com.bakery.calculation.action;

import com.bakery.calculation.model.ProductPrice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BakeryOrder {

  public static String processRequest(String input) {
    String[] order = input.split(" ");
    ProductDetail x = new ProductDetail();
    String productDescription = x.getProductDescription(order[1]);
    List<ProductPrice> price = x.getProductPrice(order[1]);

    int quantity = Integer.parseInt(order[0]);

    // Map<Integer, Integer> result = generateResult(getCalculation(price), quantity);

    return productDescription;
  }

  private static List<Integer> getCalculation(List<ProductPrice> price) {
    List<Integer> x = new ArrayList<Integer>();
    price.forEach(data -> x.add(data.getQuantity()));
    Collections.sort(x);
    return x;
  }
}
