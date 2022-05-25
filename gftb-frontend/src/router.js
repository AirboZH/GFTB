import {createRouter,createWebHistory} from "vue-router";

import Account from "./pages/Account.vue";
import Home from "./pages/Home.vue";
import Goods from "./pages/Goods.vue";
import Shopping from "./pages/Shopping.vue";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path:"/account/:status",
        name: "Account",
        component: Account
    },
    {
        path:"/goods/:goodsId",
        name: "Goods",
        component: Goods
    },
    {
        path:"/shopping",
        name: "Shopping",
        component: Shopping
    }
    ]

export default createRouter({
    history: createWebHistory(),
    routes,
});