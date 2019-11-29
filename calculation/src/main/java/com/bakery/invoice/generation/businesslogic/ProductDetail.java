package com.bakery.invoice.generation.businesslogic;

import static com.bakery.invoice.generation.constants.Constants.PRODUCT_PRICE_CSV_FILE;

import com.bakery.invoice.generation.model.ProductPrice;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDetail {

  private static Map<String, List<ProductPrice>> productPriceMap = new HashMap<>();

  public ProductDetail() {

    List<String> productPriceInfo = readFile(PRODUCT_PRICE_CSV_FILE);

    productPriceInfo.stream()
        .map(
            data -> {
              String[] values = data.split(",");
              return new ProductPrice(
                  Integer.parseInt(values[1].trim()),
                  Float.parseFloat(values[2].trim()),
                  values[0]);
            })
        .forEach(
            data -> {
              List<ProductPrice> temp = new ArrayList<>();
              if (!productPriceMap.containsKey(data.getCode())) {
                temp.add(data);
                productPriceMap.put(data.getCode(), temp);
              } else {
                temp = productPriceMap.get(data.getCode());
                temp.add(data);
                productPriceMap.put(data.getCode(), temp);
              }
            });
  }

  private List<String> readFile(String fileName) {
    InputStream fileResource = ProductDetail.class.getClassLoader().getResourceAsStream(fileName);
    return new BufferedReader(new InputStreamReader(fileResource))
        .lines()
        .collect(Collectors.toList());
  }

  public List<ProductPrice> getProductPriceDetails(String productCode) {
    return productPriceMap.get(productCode);
  }
}
