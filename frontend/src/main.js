import Vue from 'vue'
import VueGoogleCharts from 'vue-google-charts'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'

Vue.config.productionTip = false
Vue.use(VueGoogleCharts)

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
