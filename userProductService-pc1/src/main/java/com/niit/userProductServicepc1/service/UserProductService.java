package com.niit.userProductServicepc1.service;

import com.niit.userProductServicepc1.domain.Product;
import com.niit.userProductServicepc1.domain.UserModel;
import com.niit.userProductServicepc1.exception.ProductNotFoundException;
import com.niit.userProductServicepc1.exception.UserAlreadyExistsException;
import com.niit.userProductServicepc1.exception.UserNotFoundException;

import java.util.List;

public interface UserProductService {
   UserModel registerUser(UserModel userModel) throws UserAlreadyExistsException;
   UserModel saveUserProductToList(Product product,String email)throws UserNotFoundException;
   UserModel deleteUserProductFromList(String email,int productId)throws UserNotFoundException, ProductNotFoundException;
   List<UserModel> getAllProduct();
}

