package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.CartMapper;
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
    @Override
    public ResultVo getAllCart(int current, int size , String username) {
        PageHelper.startPage(current, size);
        List<Cart> carts = cartMapper.selectByName(username);
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) carts;
        return new ResultVo(1000, "success", carts.size(), carts);
    }

    @Override
    public ResultVo removeCart(String id) {
        Integer cartId = Integer.parseInt(id);
        int num = cartMapper.deleteByPrimaryKey(cartId);
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);
    }

    @Transactional
    public ResultVo addCart(Cart cart,String username) {
        synchronized (this) {
            int productId = cart.getProductid();
            Example example = new Example(Cart.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("productid", productId);
            List<Cart> carts = cartMapper.selectByExample(example);
            Example exampleUser = new Example(Users.class);
            Example.Criteria criteriaUser = exampleUser.createCriteria();
            criteriaUser.andEqualTo("username", username);
            Users users = usersMapper.selectByExample(exampleUser).get(0);
            cart.setUserId(users.getUserid());
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

    @Override
    public ResultVo editCart(Cart cart) {
        return null;
    }
}
