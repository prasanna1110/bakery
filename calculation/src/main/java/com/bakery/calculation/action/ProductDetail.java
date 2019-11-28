package com.bakery.calculation.action;

import com.bakery.calculation.model.ProductPrice;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDetail {

  private static Map<String, String> productDetail = new HashMap<>();
  private static Map<String, List<ProductPrice>> productPriceMap = new HashMap<>();

  public ProductDetail() {
    List<String> productData = readFile("product.csv");

    productData.forEach(
        line -> {
          String[] product = line.split(",");
          productDetail.put(product[0], product[1]);
        });

    List<String> productPriceInfo = readFile("productPrice.csv");

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

  public String getProductDescription(String productCode) {
    return productDetail.get(productCode);
  }

  public List<ProductPrice> getProductPrice(String productCode) {
    return productPriceMap.get(productCode);
  }
}
