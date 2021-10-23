import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import './assets/css/global.css';
import $ from "jquery" ;


const app = createApp(App);
app.provide('$axios', axios);
axios.defaults.baseURL = '/api'
app.config.globalProperties.$http = axios
axios.interceptors.request.use(config =>{
    console.log(config)
    config.headers.Authorization = window.sessionStorage.getItem('token')
    return config
})

app.use(store).use(router).use(ElementPlus).mount('#app')

