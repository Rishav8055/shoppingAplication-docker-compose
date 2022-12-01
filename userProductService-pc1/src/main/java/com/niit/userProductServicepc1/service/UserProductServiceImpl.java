package com.niit.userProductServicepc1.service;

import com.niit.userProductServicepc1.domain.Product;
import com.niit.userProductServicepc1.domain.UserModel;
import com.niit.userProductServicepc1.exception.ProductNotFoundException;
import com.niit.userProductServicepc1.exception.UserAlreadyExistsException;
import com.niit.userProductServicepc1.exception.UserNotFoundException;
import com.niit.userProductServicepc1.proxy.UserProxy;
import com.niit.userProductServicepc1.repository.UserProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {
    private UserProductRepo userProductRepo;
    private UserProxy userProxy;
    @Autowired
    public UserProductServiceImpl(UserProductRepo userProductRepo, UserProxy userProxy) {
        this.userProductRepo = userProductRepo;
        this.userProxy = userProxy;
    }

    @Override
    public UserModel registerUser(UserModel userModel) throws UserAlreadyExistsException {
        if (userProductRepo.findById(userModel.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserModel savedUser =userProductRepo.save(userModel);
        if (!savedUser.getEmail().isEmpty()){
            ResponseEntity res = userProxy.saveUser(userModel);
            System.out.println(res.getBody());
        }
        return savedUser;
    }

    @Override
    public UserModel saveUserProductToList(Product product, String email) throws UserNotFoundException {
        if (userProductRepo.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        UserModel user = userProductRepo.findByEmail(email);
        if (user.getProductList() == null) {
            user.setProductList(Arrays.asList(product));
        } else {
            List<Product> products = user.getProductList();
            products.add(product);
            user.setProductList(products);
        }
        return userProductRepo.save(user);
    }

    @Override
    public UserModel deleteUserProductFromList(String email, int productId) throws UserNotFoundException, ProductNotFoundException {
        boolean productIdIsPresent = false;
        if (userProductRepo.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        UserModel user = userProductRepo.findById(email).get();
        List<Product> products = user.getProductList();
        productIdIsPresent = products.removeIf(x -> x.getProductId() == productId);
        if (!productIdIsPresent) {
            throw new ProductNotFoundException();
        }
        user.setProductList(products);
        return userProductRepo.save(user);

    }

    @Override
    public List<UserModel> getAllProduct() {
        return userProductRepo.findAll();
    }
}
