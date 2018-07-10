// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Layout from './components/layout'
import VueRouter from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import IndexPage from './pages/index'
import VueResource from 'vue-resource'
import DetailPage from './pages/detail'
import DetailAnalysis from './pages/detail/analysis'
import DetailCount from './pages/detail/count'
import DetailForecast from './pages/detail/forecast'
import DetailPublish from './pages/detail/publish'


Vue.config.productionTip = false
// Vue.use(Vuex)
Vue.use(VueRouter)
//Vue.use(VueResource)
Vue.use(VueAxios, axios)


let router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			name: 'index',
			component: IndexPage
		},
		{
			path: '/detail',
			component: DetailPage,
			//不会访问 /detail 路径
			redirect: '/detail/analysis',
			children: [
				{
					path: 'count',
					component: DetailCount
				},
				{
					path: 'analysis',
					component: DetailAnalysis
				},
				{
					path: 'publish',
					component: DetailPublish
				},
				{
					path: 'forecast',
					component: DetailForecast
				}
			]	
		}
	]
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { Layout },
  template: '<Layout/>'
})
