package com.ecommerce.EcommerceBackend.service;

import com.ecommerce.EcommerceBackend.Dto.CartDto;
import com.ecommerce.EcommerceBackend.Dto.UserDto;
import com.ecommerce.EcommerceBackend.model.Cart;
import com.ecommerce.EcommerceBackend.model.CartItem;
import com.ecommerce.EcommerceBackend.model.Product;
import com.ecommerce.EcommerceBackend.model.User;
import com.ecommerce.EcommerceBackend.repository.CartRepository;
import com.ecommerce.EcommerceBackend.repository.ProductRepository;
import com.ecommerce.EcommerceBackend.repository.UserRepository;
import com.ecommerce.EcommerceBackend.view.ItemRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    public CartDto addTocart(ItemRequest req , String username){
        int productId = req.getProductId();
        int quantity  = req.getQuantity();
        User user = userRepository.findByEmail(username);
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not Found"));
        /*if(!product.isStock()){
            System.out.println("Product Not Found");
            return null;
        }*/
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(product.getPrice() * quantity);

        Set<CartItem> itemSet;
        Cart cart = user.getCart();

        if(cart == null){
            cart = new Cart();
            itemSet = new HashSet<>();
            itemSet.add(cartItem);
            cart.setCartItem(itemSet);
            cart.setUser(user);
        }
        else{
            itemSet = cart.getCartItem();
            if(itemSet == null){
                itemSet.add(cartItem);
                cart.setCartItem(itemSet);
            }
            else{
                Boolean flag=true;
               for(CartItem item : itemSet){
                   if(item.getProduct().getProduct_id() == productId){
                       item.setQuantity(quantity);
                       item.setTotalPrice(product.getPrice()*quantity);
                       flag = false;
                       break;
                   }
               }
               if(flag) {
                   itemSet.add(cartItem);
               }
            }
        }
        cartItem.setCart(cart);
        Cart saveCart = this.cartRepository.save(cart);
        CartDto cartDto = new CartDto();
        cartDto.setCartId(saveCart.getCartId());
        cartDto.setCartItem(saveCart.getCartItem());
        UserDto userDto = this.mapper.map(user,UserDto.class);
        cartDto.setUser(userDto);
        return cartDto;
    }

    public CartDto getCart(User user){
        Cart cart = cartRepository.findByUser(user);
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setCartItem(cart.getCartItem());
        UserDto dto = this.mapper.map(user, UserDto.class);
        cartDto.setUser(dto);
        return cartDto;
    }

    public void removeItemFromCart(int id, User user) {
        Cart cart = user.getCart();
        Set<CartItem> itemSet = cart.getCartItem();
        if(itemSet == null){
            System.out.println("No item in cart");
           return ;
        }
        for(CartItem item : itemSet){
            if(item.getCartItemId() == id ){
                itemSet.remove(item);
                break;
            }
        }
        cart.setCartItem(itemSet);
        cartRepository.save(cart);
    }
}
