import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: { 	
		userInfo: {},
		sysconf:{},
		versionNum:'0.0.1',
		// 以下要足部删除
		openid:'',
		storeHasLogin:false,		
		timerIdent: false,//全局1s定时器，只在全局开启一个，所有需要定时执行的任务监听该值即可，无需额外开启
	},
	getters: {
		hasLogin(state){
			return !!state.token;
		}
	},
	mutations: { 
		//更新state数据
		setStateAttr(state, param){
			if(param instanceof Array){
				for(let item of param){
					Vue.set(state,item.key,item.val); 
				}
			}else{
				Vue.set(state,param.key,param.val); 
			}
		},
		//更新token
		setToken(state, data){ 
			const {token,userInfo,expire} = data;
			Vue.set(state,'token',token); 
			uni.setStorageSync('userInfo', userInfo);
			uni.setStorageSync('userToken', token);
			//uni.setStorageSync('tokenExpired', expire);
			//this.dispatch('getUserInfo'); //更新用户信息  
		},
		//退出登录
		logout(state){ 
			Vue.set(state,'token','');
			uni.removeStorageSync('userToken'); 
			uni.removeStorageSync('userInfo'); 
			setTimeout(()=>{
				state.userInfo = {};
			}, 1100)
		},
	},
	actions: {
		//更新用户信息
		async getUserInfo({state, commit}){			
			// await  Vue.prototype.$request.post("storeapp/api.auth.center/get",{data:{}}).then(res=>{
			// 	if(res.code === 1){
			// 		const userInfo = res.data;  
			// 		if(!userInfo.operator_id){
			// 			Vue.prototype.$util.msg('您所绑定的本店失效，请重新绑定');
			// 			commit('logout')
			// 			return false;
			// 		} 
			// 		uni.setStorageSync('userInfo', userInfo);
			// 		commit('setStateAttr', {
			// 			key: 'userInfo',
			// 			val: userInfo
			// 		})					 
			// 	}else if(res.code === 401){
			// 		Vue.prototype.$util.msg(res.info);
			// 		commit('logout')
			// 	}
			// })
		}, 
	}
})

export default store
