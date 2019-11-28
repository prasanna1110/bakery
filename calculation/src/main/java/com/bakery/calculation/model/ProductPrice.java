package com.bakery.calculation.model;

public class ProductPrice {
  private Integer quantity;
  private Float price;

  public ProductPrice(Integer quantity, Float price) {
    this.quantity = quantity;
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public Float getPrice() {
    return price;
  }
}
