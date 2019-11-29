package com.bakery.invoice.generation.businesslogic;

import com.bakery.invoice.generation.model.ProductPrice;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ProductDetailTest {

  @Test
  public void getProductPriceDetailsTest() {
    String input = "VS5";
    ProductDetail productDetail = new ProductDetail();
    List<ProductPrice> output = productDetail.getProductPriceDetails(input);
    Assert.assertEquals(3, output.get(0).getQuantity());
    Assert.assertEquals("VS5", output.get(0).getCode());
  }
}
