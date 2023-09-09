package br.com.dbc.vemser.checkout.dtos;

import lombok.Getter;

@Getter
public class CheckoutItemDto {

  private String productName;
  private int  quantity;
  private double price;
  private long productId;
  private int userId;

  public CheckoutItemDto() {}

  public CheckoutItemDto(String productName, int quantity, double price, long productId, int userId) {
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
    this.productId = productId;
    this.userId = userId;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setProductId(long id) {
    this.productId = id;
  }

}
