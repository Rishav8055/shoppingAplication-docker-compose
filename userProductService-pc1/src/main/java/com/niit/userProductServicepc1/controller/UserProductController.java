package com.niit.userProductServicepc1.controller;

import com.niit.userProductServicepc1.domain.Product;
import com.niit.userProductServicepc1.domain.UserModel;
import com.niit.userProductServicepc1.exception.ProductNotFoundException;
import com.niit.userProductServicepc1.exception.UserAlreadyExistsException;
import com.niit.userProductServicepc1.exception.UserNotFoundException;
import com.niit.userProductServicepc1.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userproduct/user")

public class UserProductController {
    private UserProductService userProductService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserProductController(UserProductService userProductService) {
        this.userProductService = userProductService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel userModel) throws UserAlreadyExistsException {
        try {
            userModel.setProductList(new ArrayList<>());
            return new ResponseEntity<>(userProductService.registerUser(userModel), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
    }

    @PostMapping("/addproduct/{email}")
    public ResponseEntity<?> addProductToUser(@PathVariable String email, @RequestBody Product product) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userProductService.saveUserProductToList(product, email), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/getproduct/")
    public ResponseEntity<?> getProductToUser() {
        return new ResponseEntity<>(userProductService.getAllProduct(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteproduct/{productId}/{userId}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable int productId, @PathVariable int userId) throws UserNotFoundException, ProductNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(userProductService.deleteUserProductFromList(String.valueOf(productId), userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
