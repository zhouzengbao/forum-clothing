<template>
	<view class="app">
		<view class="padding-top-sm padding-left-sm" >
			<image src="../../static/logo.png" class="xxl cu-avatar" mode="aspectFill" >
		</view>
		<view class="text-center text-xxl margin-tb-xs">
			注 册
		</view>
		<view class="padding-sm bg-white">
			<view class="cu-item padding-bottom-sm">
				<view class="action margin-bottom-xs">姓名：</view>
				<view class="border solid padding-left-sm padding-tb-xs">
					<input class="text-left" v-model="form.userName"  placeholder="请输入姓名" />
				</view>
			</view>
			<view class="cu-item padding-bottom-lg">
				<view class="action margin-bottom-xs">身份证号：</view>
				<view class="border solid padding-left-sm padding-tb-xs">
					<input class="text-left" v-model="form.idNum"  placeholder="请输身份证号" />
				</view>
			</view>	
			<view class="cu-item padding-bottom-lg">
				<view class="action margin-bottom-xs">身份类型：</view>
				<view class="border solid padding-left-sm padding-tb-xs">
					<view class="justify-between flex align-center" @click="openSupply">
						<view class="">{{supplyTitle||'请选择供应商'}}</view> <text class="cuIcon-unfold margin-left-xs"></text>
					</view> 
				</view>
			</view>	
			<view class="cu-item padding-bottom-lg">
				<view class="action margin-bottom-xs">手机号：</view>
				<view class="border solid padding-left-sm align-center padding-tb-xs flex justify-between">
					<input class="text-left" disabled="" v-model="form.telephone"  placeholder="请授权获取手机号" />
					
					<button class="cu-btn bg-blue radius padding-lr-xs padding-tb-xs" open-type="getPhoneNumber" @getphonenumber="getPhoneNumber" style="height: auto;">获取手机号</button>
				</view>
			</view>	
			
			<view class="cu-item padding-bottom-lg">
				<view class="action margin-bottom-xs">邀请码：</view>
				<view class="border solid padding-left-sm padding-tb-xs">
					<input class="text-left" v-model="form.inviteCode"  placeholder="请输入邀请码" />
				</view>
			</view>	
			<view class="cu-item">
				<view class="action margin-bottom-xs">资质图片：<text class="text-red text-sm">(至少上传三张)</text></view>
				<view class="padding-left-xs padding-tb-xs">
					<mix-upload-image ref="Img" @onChange="chooseImg" :length="6" :count="6"></mix-upload-image>
				</view>
			</view>	
			<checkbox-group class="block" @change="serviceClick">
				<view class="margin-tb-sm flex justify-left align-center">
					<checkbox class="round"></checkbox> 
					<text class="padding-left-sm">请勾选<text class="text-blue">用户服务协议</text>和<text class="text-blue">用户隐私协议</text></text>
				</view>
			</checkbox-group>
			
			<mix-button @onConfirm="submitFn" text="提交申请"></mix-button>
		</view>
		<lb-picker ref="storePicker" :list="supplyList"  v-model="form.userType" :value="form.userType"  @confirm="setSupply" mode='selector'></lb-picker>
	</view>
</template>

<script>
	import LbPicker from '@/components/lb-picker'; 
	import { isLogin } from '../../common/js/util';
	export default{
		components: {LbPicker},
		data(){
			return {
				form:{
					userId:'',
					userName:'',
					idNum:'',
					userType:'',
					telephone:'',
					inviteCode:'',
					sitePic:'',
					
				},
				supplyList:[],
				checkService:0,
			}
		},
		onLoad(){
			let that = this;
			let {form} = this;
			let user = uni.getStorageSync('userInfo');
			if(user){
				// if(user.auth == 1){
				// 	uni.redirectTo({
				// 		url:'/pages/my/index'
				// 	});
				// }
				form.userId = user.id;
				form.userName = user.userName;
				form.idNum = user.idNum;
				form.inviteCode = user.inviteUserId;
				form.userType = user.userType;
				form.telephone = user.telephone;	
			}
			else {
				uni.login({
					success(loginRes) {
						that.$request.get('v1/user/getUserByWechatCode', {
							data: {
								code:loginRes.code
							}
						}).then(res => {
							if(res.code == 0){
								if(res.data.auth == 1){
									uni.setStorageSync('userInfo',res.data);
									uni.redirectTo({
										url:'/pages/my/index'
									});
								}
							}
							let userInfo = res.data;
							form.userId = userInfo.id;
							form.userName = userInfo.userName;
							form.idNum = userInfo.idNum;
							form.inviteCode = userInfo.inviteUserId;
							form.userType = userInfo.userType;
							form.telephone = userInfo.telephone;	
						})
					}
				})
			}
			that.$request.get('v1/common/config', {
				data: {}
			}).then(confRes => {
				let userType = confRes.data.userType;
				userType.forEach(function(value,index){
					for(let item in value){
						that.supplyList.push({'label':value[item],'value':item})
					}
				})
			})
			
		},
		computed:{
			supplyTitle(){
				let that = this;
				let {supplyList,form} = this;
				let data = supplyList.find(i => i.value == form.userType||'')||{};
				return data && data.label||''
			},
		},
		methods:{
			serviceClick(e){
				let that = this;
				that.checkService = that.checkService == 0 ? 1 : 0;
			},
			//打开选择器
			openSupply(){
				this.$refs.storePicker.show()
			},
			//设置选择标题
			setSupply(e){
				let that = this;
				let {form} = this;
				that.$set(form,'userType',e.value)
			},
			//选择图片
			chooseImg(bool) {
				this.form.sitePic = bool.list.map(i => i.url).join(',');
			},
			//设置图片
			setImage() {
				let images = [], {form} = this
				if(form && form.sitePic && form.sitePic.length > 0) {
					form.sitePic.split(',').forEach(img => images.push({url:img,filePath:img,progress:100})  );		
				}				
				this.$refs.Img.imageList = images;
			},
			//提交
			async submitFn(){
				let that = this;
				let {form} = this;
				if(that.checkService !=1 ){
					that.$util.msg('请勾选用户服务协议与隐私协议');
					return false;
				}
				let res = await this.$http('v1/user/initUserInfo', 'post', form, {showLoading: false});
				if(res.code == 0){
					uni.setStorageSync('userInfo',res.data);
					uni.redirectTo({
						url:'/pages/my/index'
					})
				} else {
					that.$util.msg(res.msg)
				}
				
			},
			async getPhoneNumber(e){
				let that = this;
				let {form} = this;
				if(e.detail.errno){
					that.$util.msg('获取手机号错误，请重试');
					return false;
				}
				let res = await this.$http('v1/user/getUserPhoneByWechatCode', 'get', {'code':e.detail.code}, {showLoading: false});
				if(res.code==0){
					form.telephone = res.data;
				} else {
					that.$util.msg(res.msg);
				}
				
			}
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #ffffff;
	}
</style>