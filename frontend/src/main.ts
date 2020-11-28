import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//Bootstap styling
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//Axios for HTTP
import axios from 'axios'
import VueAxios from 'vue-axios'

//Global Component
import HeaderComponent from '@/components/Global/Header.vue'
Vue.component('my-header', HeaderComponent)

Vue.use(VueAxios, axios)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
