import { createRouter, createWebHistory } from 'vue-router'
import AdminApp from '../views/AdminApp.vue'
import Login from '../views/login.vue'
import Admin from '../views/Admin.vue';

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },

  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    children:[
      {
        path: '/admin',
        name: 'AdminHome',
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
        path: '/admin/address',
        name: 'Address',
        component: ()=>import("@/views/admin/adminAddress.vue")
      },
    ]
  },
  {
    path: '/adminApp',
    name: 'AdminApp',
    component: AdminApp,
  },

]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


// 路由导航守卫
// router.beforeEach((to, from, next) => {
//   // to : the router will visit 
//   // from : the router need to change
//   // next: method 
//   //   next() visit directly  next('/login') must visit login
  
//   if(to.path === '/login'){
//       return next(); 
//   }

//   const tokenStr = window.sessionStorage.getItem('token');
//   if(!tokenStr) return next('/login')

//   next();
   
// })

export default router
