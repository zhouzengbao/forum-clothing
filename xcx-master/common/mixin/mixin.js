import {request} from '@/common/js/request' 
import { mapState,mapGetters } from 'vuex'; 
export default{ 
	data(){
		return {
			page: 0, //页码
			pageNum: 6, //每页加载数据量
			loadingType: 1, //0加载前 1加载中 2没有更多
			isLoading: false, //刷新数据
			loaded: false, //加载完毕
		}
	},
	computed: {
		...mapState(['sysconf','userInfo','versionNum']),
		...mapGetters(['hasLogin']),
	},
	
	onLoad(){
		//this.$store.dispatch('getSalesmanList'); //更新系统参数配置
		//this.$store.dispatch('updateSystem'); //更新系统参数配置
		//this.updateApp();  //每次进入页面进行检查 更新 
	},
	onShow(){
		
	},
	methods: {
		/**
		 * navigatorTo跳转页面
		 * @param {String} url
		 * @param {Object} options
		 * @param {Boolean} options.login 是否检测登录  
		 */
		navTo(url, options={}){ 
			let that = this;
			// const pages = getCurrentPages();  
			this.$util.throttle(()=>{
				if(!url || url=='#'){
					return;
				}
				if((~url.indexOf('login=1') || options.login) && !this.$store.getters.hasLogin){
					if(options.errmsg){
						this.$util.msg(options.errmsg);
						return false;
					} 
					url = '/pages/tabbar/user'  
				}
				
				if(url.indexOf('/pages/tabbar')>=0){
					uni.switchTab({url})
				}else if(options.redirect){
					console.log(options);
					uni.redirectTo({url})
				}else{
					console.log(url);
					uni.navigateTo({url})
				} 
			}, 300)
		},
		$upload(api, file, data, ext = {}) {
			let userToken = uni.getStorageSync('userToken') || '',that = this, config = this.$request.getConfig();
			if(ext.login && !this.$util.isLogin()){
				return;
			}
			if (ext.showLoading) {
				this.isLoading = true;
			} 
		 
			return new Promise((resolve, reject) => { 
				const uploadTask = uni.uploadFile({
					url: config.baseUrl + api,
					fileType: 'image',
					filePath: file,
					formData: {
						dir: data.dir || ''
					},
					name: 'file',
					header: {
						'Api-Token':userToken,
						'Api-Type':'storeapp'
					},
					success(result) {
						if (ext.hideLoading !== false) {
							that.isLoading = false;
						}
						setTimeout(() => {
							if (ext.setLoaded !== false) {
								that.loaded = true;
							}
						}, 100) 
						that.$refs.confirmBtn && that.$refs.confirmBtn.stop();
						if (result.statusCode == 200) {
							resolve(JSON.parse(result.data));
						} else {
							reject(result.errMsg);
						}
					},
					fail(err) {
						reject(err);
					}
				}) 
				if (typeof ext.onProgressUpdate !== 'undefined' && typeof ext.onProgressUpdate == "function") {
					uploadTask.onProgressUpdate(ext.onProgressUpdate);
				}
			})
		},
		 /**
		  *  $request云函数请求
		  */
		 $http(api, method, data = {}, ext = {}){
		 	if(ext.login && !this.$util.isLogin()){
		 		return;
		 	}
		 	if(ext.showLoading){
		 		this.isLoading = true;
		 	}
		 	return new Promise((resolve, reject)=> {
		 		this.$request[method || 'post'](api, {data: data}).then(result => {
		 			if(ext.hideLoading !== false){
		 				this.isLoading = false;
		 			}
		 			setTimeout(()=>{
		 				if(this.setLoaded !== false){
		 					this.loaded = true;
		 				}
		 			}, 100)
		 			this.$refs.confirmBtn && this.$refs.confirmBtn.stop();
		 			resolve(result);
		 		}).catch((err) => {
		 			reject(err);
		 		})
		 	})
		 },
		 updateApp(){
		 	// 版本更新检测
		 	const updateManager = dd.getUpdateManager();
		 	updateManager.onCheckForUpdate(function(res) {
		 		// 请求完新版本信息的回调
		 		console.log('请求完新版本信息的回调', res.hasUpdate);
		 	}),
		 	updateManager.onUpdateReady(function(res) {
		 		uni.showModal({
		 			title: '更新提示',
		 			content: '新版本已经准备好，是否重启应用？',
		 			success(res) {
		 				if (res.confirm) {
		 					updateManager.applyUpdate();
		 				}
		 			}
		 		});
		 	}),updateManager.onUpdateFailed(function(res) {
		 		console.log('新的版本下载失败', res);
		 	}); 
		 },
		 backTo(delta=1){
			 this.$util.throttle(()=>{
				uni.navigateBack({delta});
			 }, 200)
		 },
		 showPopup(key){
		 	this.$util.throttle(()=>{
		 		this.$refs[key] && this.$refs[key].open();
		 	}, 200)
		 },
		 hidePopup(key){
		 	this.$refs[key].close();
		 },
		 stopPrevent(){},
	},
}