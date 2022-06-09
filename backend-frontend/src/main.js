import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import router from "./router";
import 'element-plus/dist/index.css'
import App from './pages/App.vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import axios from 'axios'
import jwtDecode from 'jwt-decode'
import moment from 'moment'

const app = createApp(App)

// http request 拦截器
axios.interceptors.request.use(
    config => {
        if (localStorage.token) {
            config.headers.Authorization = `Bearer ${localStorage.token}`;
        }
        return config;
    },
    err => {
        console.log(err);
    }
);
app.config.globalProperties.$jwt = jwtDecode
app.config.globalProperties.$moment = moment
app.config.globalProperties.$http = axios
app.use(ElementPlus, {
    locale: zhCn,
})
app.use(router);
app.mount('#app')