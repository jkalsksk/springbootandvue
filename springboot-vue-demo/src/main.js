import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import locale from 'element-plus/lib/locale/lang/zh-cn'


import '@/assets/css/global.css'
import 'element-plus/dist/index.css'

createApp(App).use(store).use(router).use(ElementPlus, {locale}).mount('#app')


