import Vue from 'vue'
import store from './store'
import App from './App'
import requests from '@/common/js/request.js'

 // main.js
 import uView from '@/uni_modules/uview-ui'
 Vue.use(uView) 
 
// const apiUrl = 'http://dev.wb.com';  

const apiUrl = 'https://www.ccmtoys.cn/api/app'; 
 // const apiUrl = 'http://192.168.3.7:8080';
const imgUploadUrl = {
	tixian_img_url: apiUrl + '/v1/common/uploadImage', //正式
}
// 全局配置+
requests.setConfig({ 
	baseUrl: apiUrl + '/',
	header: {
		'content-type': 'application/x-www-form-urlencoded'
	}
});
//https://ucenter.zhenshihuishop.net/kmserver/api.store.kemai.vip/index

// 设置响应拦截器
requests.interceptors.response(res => {
	const code = res.data && res.data.code||'';
	if ([999,401].includes(code)) {
		msg(res.data.info),
		store.commit('logout'), 
		setTimeout(() => {
			if(code == 401) msg('未登录')//未登录
			if(code == 999) msg('站点关闭')//站点关闭
		}, 1500)
	}
	return res.data; // 原样返回
});

// 请求头设置
requests.interceptors.request(config => {
	let userInfo = uni.getStorageSync('userInfo') || {}
	config.header['Api-Type'] = 'miniprograme'; // 应用类型
	config.header['Api-Token'] =  uni.getStorageSync('userToken') || '' ;  // 登录用户的信息
	config.header['Api-AppId'] = store.state.appid;  //应用 ID		
	config.header['Api-Version'] = store.state.versionNum; // 应用版本
	return config;
})
 
 import cache from '@/common/js/cache'  
 import filter from '@/common/filter/filter'
 import mixin from '@/common/mixin/mixin'
 Vue.mixin(mixin) 

 import {msg,isLogin,debounce,throttle,prePage,date,listSortBy,compareVersion,getMpCode} from '@/common/js/util.js';  
 Vue.prototype.$util = {msg,isLogin,debounce,throttle,prePage,date,listSortBy,compareVersion,cache,getMpCode}  
 Vue.prototype.$api = {msg,prePage};
 
Vue.config.productionTip = false 
Vue.prototype.$store = store; 
Vue.prototype.$request = requests
//Vue.prototype.$url = imgUploadUrl.tixian_img_url;
App.mpType = 'app'
const app = new Vue({
	...App
})
app.$mount()
