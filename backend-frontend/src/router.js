import {createRouter,createWebHistory} from "vue-router";

import App from "./pages/App.vue";
import ShoppingManage from "./pages/ShoppingManage.vue";
import UserManage from "./pages/UserManage.vue";
import OderManage from "./pages/OderManage.vue";
import Account from "./pages/Account.vue";

const routes = [
    {
        path:"/",
        name: "ShoppingManage",
        component: ShoppingManage
    },
    {
        path:"/userManage",
        name: "UserManage",
        component: UserManage
    },
    {
        path:"/oderManage",
        name: "OderManage",
        component: OderManage
    },
    {
        path:"/account",
        name: "Account",
        component: Account
    },
    ]

export default createRouter({
    history: createWebHistory(),
    routes,
});