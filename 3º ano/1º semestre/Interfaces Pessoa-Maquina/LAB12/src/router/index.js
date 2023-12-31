import { createRouter, createWebHistory } from 'vue-router'
import  Home from '@/views/Home.vue'
import  Products from '@/views/Products.vue'
import  Basket from '@/views/Basket.vue'
import  Register from '@/views/Register.vue'
import  Login from '@/views/Login.vue'
import  MyOrders from '@/views/MyOrders.vue'
import  Message from '@/views/Message.vue'



const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/products',
    component: Products
  }, 
  {
    path: '/basket',
    component: Basket
  }, 
  {
    path: '/register',
    component: Register
  },
  {
    path: '/login',
    component: Login
  }, 
  {
    path: '/myorders',
    component: MyOrders
  }, 
  {
    path: '/message',
    component: Message
  },        
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
