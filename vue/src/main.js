import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//引入自己的css样式
import './assets/gloable.css'
import request from "@/utils/request";

// Vue.prototype.request = request

Vue.config.productionTip = false

Vue.use(ElementUI, {size: "mini"})

Vue.prototype.request = request

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
