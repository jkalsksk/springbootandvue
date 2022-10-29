import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home' ,
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/HomeView'),
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '/registered',
    name: 'Registered',
    component: () => import('@/views/Registered')
  } ,
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
