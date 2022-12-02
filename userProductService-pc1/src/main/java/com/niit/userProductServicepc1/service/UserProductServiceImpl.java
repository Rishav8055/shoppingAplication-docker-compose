package com.niit.userProductServicepc1.service;

import com.niit.userProductServicepc1.domain.Product;
import com.niit.userProductServicepc1.domain.UserModel;
import com.niit.userProductServicepc1.exception.ProductNotFoundException;
import com.niit.userProductServicepc1.exception.UserAlreadyExistsException;
import com.niit.userProductServicepc1.exception.UserNotFoundException;

import com.niit.userProductServicepc1.rabbitMq.CommonUser;
import com.niit.userProductServicepc1.rabbitMq.Producer;
import com.niit.userProductServicepc1.rabbitMq.UserDTO;
import com.niit.userProductServicepc1.repository.UserProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {

    private UserProductRepo userProductRepo;
    @Autowired
    private Producer producer;

    @Autowired
    public UserProductServiceImpl(UserProductRepo userProductRepo) {
        this.userProductRepo = userProductRepo;

    }

    @Override
    public UserModel addUser1(CommonUser commonUser) {
        UserDTO userDTO = new UserDTO(commonUser.getUserId(),commonUser.getPassword());
        producer.sendDtoToQueue(userDTO);
        UserModel userModel=new UserModel(commonUser.getUserId(),commonUser.getUserName(),commonUser.getEmail());
        return userProductRepo.insert(userModel);
    }

    @Override
    public UserModel registerUser(UserModel userModel) throws UserAlreadyExistsException {
        if (userProductRepo.findById(userModel.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }


        return userProductRepo.insert(userModel);
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
