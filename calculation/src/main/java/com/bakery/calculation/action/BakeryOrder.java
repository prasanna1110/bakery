package com.bakery.calculation.action;

import com.bakery.calculation.model.ProductPrice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BakeryOrder {

  public static String processRequest(String input) {
    String[] order = input.split(" ");
    ProductDetail x = new ProductDetail();
    String productDescription = x.getProductDescription(order[1]);
    List<ProductPrice> price = x.getProductPrice(order[1]);

    int quantity = Integer.parseInt(order[0]);

    Map<Integer, Integer> result = generateResult(getCalculation(price), quantity);
    StringBuffer outputBuffer = new StringBuffer();
    float totalOrderValue = 0f;
    for(Integer packSize :  result.keySet()) {
      totalOrderValue += result.get(packSize) * getPrice(price, result.get(packSize));

      outputBuffer.append("\n" + "\t" + result.get(packSize) + "*" + packSize + "$"
          + getPrice(price, packSize));
    }

    System.out.println(input+ " "+ "$" + totalOrderValue + outputBuffer.toString() );

    return productDescription;
  }

  private static Float getPrice(List<ProductPrice> price, int quantity) {
    AtomicReference<Float> x = new AtomicReference<>(0f);
    price.forEach(data -> {
      if(data.getQuantity() == quantity)
      x.set(data.getPrice()); });
    return x.get();
  }

  private static List<Integer> getCalculation(List<ProductPrice> price) {
    List<Integer> x = new ArrayList<Integer>();
    price.forEach(data -> x.add(data.getQuantity()));
    Collections.sort(x, Collections.reverseOrder());
    return x;
  }

  private static Map<Integer, Integer> generateResult(List<Integer> priceList, int quantity) {
    Map<Integer, Integer> output = new HashMap<>();
    int q = quantity;
    int start = 0;
    int size = 0;

    while (q > 0 && start < priceList.size()) {
      if (size > 0) {
        if (priceList.indexOf(size) + 1 == priceList.size()) {
          size = priceList.get(0);
        }

        if (output.containsKey(size)) {
          q = q + size;

          if (output.get(size) > 1) {
            output.put(size, output.get(size) - 1);
          } else {
            output.remove(size);
          }

          start = priceList.indexOf(size) + 1;
        }
      }

      for (int i = start; i < priceList.size(); i++) {
        if (q / priceList.get(i) > 0) {
          size = priceList.get(i);
          output.put(size, q / size);
          q = q % size;
        }
      }

      start++;
    }

    if (q > 0) {
      output.clear();
    }

    return output;
  }
}
