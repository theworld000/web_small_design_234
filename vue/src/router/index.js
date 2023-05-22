import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/AboutView.vue')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import("../components/Register.vue")
    },
    {
        path: '/login',
        name: 'login',
        component: () => import("@/components/Login.vue")
    },
    {
        path: '/game',
        name: 'game',
        component: () => import(/* webpackChunkName: "about" */ '../views/GameView.vue')
    },
    {
        path: '/myhome',
        name: 'myhome',
        component: () => import(/* webpackChunkName: "about" */ '../views/myhome.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
