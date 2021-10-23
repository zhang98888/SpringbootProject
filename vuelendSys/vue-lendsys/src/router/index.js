import { createRouter, createWebHistory } from 'vue-router'
import AdminApp from '../views/AdminApp.vue'
import Login from '../views/login.vue'
import Home from '../views/home.vue';
import header from '../components/Header.vue';

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    name: 'AdminApp',
    component: AdminApp
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由导航守卫
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
