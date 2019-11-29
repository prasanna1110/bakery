package com.bakery.invoice.generation.model;

public class ProductPrice {
  private String code;
  private Integer quantity;
  private Float price;

  public ProductPrice(Integer quantity, Float price, String code) {
    this.quantity = quantity;
    this.price = price;
    this.code = code;
  }

  public int getQuantity() {
    return quantity;
  }

  public Float getPrice() {
    return price;
  }

  public String getCode() {
    return code;
  }
}
