import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/login.vue'
import Admin from '../views/Admin.vue';
import Index from '../views/index.vue'
import Order from '../views/user/UserOrder.vue'
import Basket from '../views/user/Basket.vue'
import categotyPro from '../views/categoryPro.vue'
import product from '../views/product.vue'
import productDetail from '../views/productDetail.vue'


const routes = [
  {
    path: '',
    name: 'Index',
    component: Index
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/order',
    name: 'order',
    component: Order
  },
  {
    path: '/basket',
    name: 'basket',
    component: Basket
  },
  {
    path: '/category/:categoryId',
    name: 'categotyPro',
    component: categotyPro,
  },
  {
    path: '/product',
    name: 'product',
    component: product,
  },
  {
    path: '/product/:productId',
    name: 'productDetail',
    component: productDetail,
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    children:[
      {
        path: '/admin',
        name: 'Home',
        component: ()=>import("@/views/admin/AdminHome.vue")
      },
      {
        path: '/admin/users',
        name: 'Users',
        component: ()=>import("@/views/admin/users.vue")
      },
      {
        path: '/admin/goods',
        name: 'Goods',
        component: ()=>import("@/views/admin/goods.vue")
      },
      {
        path: '/admin/goodsPic',
        name: 'GoodsPic',
        component: ()=>import("@/views/admin/goodsPicTure.vue")
      },
      {
        path: '/admin/category',
        name: 'category',
        component: ()=>import("@/views/admin/category.vue")
      },
      {
        path: '/user/userInfo',
        name: 'userInfo',
        component: ()=>import("@/views/user/userInfo.vue")
      },
      {
        path: '/admin/borrow',
        name: 'borrowGoods',
        component: ()=>import("@/views/admin/borrowApprove.vue")
      }
    ]
  },


]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



router.beforeEach((to, from, next) => {
  // to : the router will visit 
  // from : the router need to change
  // next: method 
  //   next() visit directly  next('/login') must visit login
  
  if(to.path === '/login'){
      return next(); 
  }

  const tokenStr = window.sessionStorage.getItem('token');

  if(!tokenStr) return next('/login')

  next();
   
})

export default router
