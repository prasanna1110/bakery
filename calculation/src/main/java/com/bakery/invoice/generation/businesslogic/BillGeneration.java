package com.bakery.invoice.generation.businesslogic;

import static com.bakery.invoice.generation.constants.Constants.CURRENCY;
import static com.bakery.invoice.generation.constants.Constants.MULTIPLY;
import static com.bakery.invoice.generation.constants.Constants.NEWLINE;
import static com.bakery.invoice.generation.constants.Constants.SPACE;
import static com.bakery.invoice.generation.constants.Constants.TABSPACE;

import com.bakery.invoice.generation.model.ProductPrice;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BillGeneration {

  /**
   * @param productPrices
   * @param quantity
   * @return price of quantity
   */
  public static Float getPrice(List<ProductPrice> productPrices, int quantity) {
    AtomicReference<Float> price = new AtomicReference<>(0f);
    productPrices.forEach(
        data -> {
          if (data.getQuantity() == quantity) {
            price.set(data.getPrice());
          }
        });
    return price.get();
  }

  /**
   * @param ProductPriceList
   * @return sorted
   */
  public static List<Integer> getSortedQuantityList(List<ProductPrice> ProductPriceList) {
    List<Integer> sortedList = new ArrayList<Integer>();
    ProductPriceList.forEach(data -> sortedList.add(data.getQuantity()));
    Collections.sort(sortedList, Collections.reverseOrder());
    return sortedList;
  }

  /**
   * @param priceList
   * @param quantity
   * @return pack quantity
   */
  public static Map<Integer, Integer> getProductPackQuantity(
      List<Integer> priceList, int quantity) {
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
      System.out.println("Invalid Order Quantity");
    }

    return output;
  }

  public static String printBill(
      String input, List<ProductPrice> price, Map<Integer, Integer> result) {
    StringBuffer outputBuffer = new StringBuffer();
    float totalOrderValue = 0.00f;
    for (Integer packSize : result.keySet()) {
      totalOrderValue += result.get(packSize) * BillGeneration.getPrice(price, packSize);

      outputBuffer.append(
          NEWLINE
              + TABSPACE
              + result.get(packSize)
              + MULTIPLY
              + packSize
              + SPACE
              + CURRENCY
              + BillGeneration.getPrice(price, packSize));
    }

    String billPrint =
        input
            + SPACE
            + CURRENCY
            + BigDecimal.valueOf(totalOrderValue).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()
            + outputBuffer.toString();
    System.out.println(billPrint);
    return billPrint;
  }
}
