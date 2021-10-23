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
    path: '/Login',
    name: 'Login',
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

export default router
