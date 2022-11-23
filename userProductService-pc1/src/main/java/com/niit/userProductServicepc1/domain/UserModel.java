package com.niit.userProductServicepc1.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class UserModel {
    List<Product> productList;
    private int userId;
    @Id
    private String email;
    private String userName;

    public UserModel() {
    }

    public UserModel(List<Product> productList, int userId, String email, String userName) {
        this.productList = productList;
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "productList=" + productList +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
