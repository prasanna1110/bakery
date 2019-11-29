package com.bakery.invoice.generation.businesslogic;

import static com.bakery.invoice.generation.constants.Constants.INVALID_INPUT;
import static com.bakery.invoice.generation.constants.Constants.SPACE;

import com.bakery.invoice.generation.model.ProductPrice;
import java.util.List;
import java.util.Map;

public class BakeryOrder {

  ProductDetail productDetail;

  public BakeryOrder() {
    productDetail = new ProductDetail();
  }

  /*
   * @input Quantity+Code
   * @Output Bill with product price details
   */
  public String ProcessBillGenerationRequest(String input) {
    String bill;
    try {
      String[] orderDetails = input.split(SPACE);

      List<ProductPrice> productPackPriceDetails =
          productDetail.getProductPriceDetails(orderDetails[1]);

      int quantity = Integer.parseInt(orderDetails[0]);

      Map<Integer, Integer> result =
          BillGeneration.getProductPackQuantity(
              BillGeneration.getSortedQuantityList(productPackPriceDetails), quantity);
       bill = BillGeneration.printBill(input, productPackPriceDetails, result);

    } catch (Exception e) {
      bill = INVALID_INPUT;
    }
    return bill;
  }
}
