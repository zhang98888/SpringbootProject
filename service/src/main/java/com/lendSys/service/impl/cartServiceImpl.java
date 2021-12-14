package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.CartMapper;
import com.lendSys.dao.ProductMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Product;
import com.lendSys.entity.Users;
import com.lendSys.service.cartService;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Scope("singleton")
public class cartServiceImpl implements cartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ProductMapper productMapper;
    /*
    get all cart information by username and it will shown by different pages
     */

    @Override
    public ResultVo getAllCart(int current, int size , String username) {
        PageHelper.startPage(current, size);
        List<Cart> carts = cartMapper.selectByName(username);
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) carts;
        return new ResultVo(1000, "success", carts.size(), carts);
    }
    /*
    delete the cart by all ids
     */
    @Override
    public ResultVo removeCart(List<String> ids) {
        int num = 0;
        for(String id : ids){
            Integer cartId = Integer.parseInt(id);
            num = cartMapper.deleteByPrimaryKey(cartId);
        }
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);
    }
    /*
    add information to cart
    if the resources have been exist, it will add the quantity
    or it will add the lines to the table
     */
    @Transactional
    public ResultVo addCart(Cart cart,String username) {
        synchronized (this) {
            int productId = cart.getProductid();
            Example exampleUser = new Example(Users.class);
            Example.Criteria criteriaUser = exampleUser.createCriteria();
            criteriaUser.andEqualTo("username", username);
            Users users = usersMapper.selectByExample(exampleUser).get(0);
            cart.setUserId(users.getUserid());
            List<Cart> carts = cartMapper.selectByProductAndName(productId,username);
            // if not, need to save
            if (carts.size() == 0) {
                int check = cartMapper.insert(cart);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", check, cart);
                } else {
                    return new ResultVo(1001, "Fail to register", 0, null);
                }
            } else{
                int preCartNum = carts.get(0).getCartNum();
                carts.get(0).setCartNum(cart.getCartNum() + preCartNum);
                cartMapper.updateByPrimaryKey(carts.get(0));
                return new ResultVo(1000, "Success!", 1, carts);
            }

        }
    }
    /*
     the quantity could be changed based on the cart information.
     */
    @Override
    public ResultVo editCart(Cart cart) {
        Product product = productMapper.selectByPrimaryKey(cart.getProductid());
        int count = product.getRentNum();
        if(count < cart.getCartNum()){
            return new ResultVo(1001,"The quantity is more than allowed",0,null);
        }else{
            int num = cartMapper.updateByPrimaryKey(cart);
            return new ResultVo(1000,"Success",num,cart);
        }
    }
    /*
    get all cart by username
     */
    @Override
    public ResultVo getAll(String username){
        List list = cartMapper.selectByName(username);
        return new ResultVo(1000,"Success",list.size(),list);
    }
}
