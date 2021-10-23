import { createRouter, createWebHistory } from 'vue-router'
import AdminApp from '../components/AdminApp.vue'
import Login from '../components/login.vue'
import AdminHome from '../components/AdminHome.vue';
import Home from '../components/home.vue';

const routes = [
  {
    path: '/',
    name: 'AdminApp',
    component: AdminApp
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/AdminHome',
    name: 'AdminHome',
    component: AdminHome
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
