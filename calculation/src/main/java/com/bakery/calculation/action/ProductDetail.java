package com.bakery.calculation.action;

import com.bakery.calculation.model.ProductPrice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetail {

  private static Map<String, String> productDetail = new HashMap<>();
  private static Map<String, List<ProductPrice>> productPriceMap = new HashMap<>();

  public ProductDetail() {
    productDetail.put("VS5", "Vegemite Scroll");
    productDetail.put("MB11", "Blueberry Muffin");
    productDetail.put("CF", "Croissant");

    List<ProductPrice> vs = new ArrayList<>();
    vs.add(new ProductPrice(3, 6.99f));
    vs.add(new ProductPrice(5, 8.99f));
    productPriceMap.put("VS5", vs);

    List<ProductPrice> mb = new ArrayList<>();
    mb.add(new ProductPrice(2, 9.95f));
    mb.add(new ProductPrice(5, 16.95f));
    mb.add(new ProductPrice(8, 24.95f));
    productPriceMap.put("MB11", mb);

    List<ProductPrice> cf = new ArrayList<>();
    cf.add(new ProductPrice(3, 5.95f));
    cf.add(new ProductPrice(5, 9.95f));
    cf.add(new ProductPrice(9, 16.99f));
    productPriceMap.put("CF", cf);
  }

  public String getProductDescription(String s) {
    return productDetail.get(s);
  }

  public List<ProductPrice> getProductPrice(String s) {
    return productPriceMap.get(s);
  }
}
