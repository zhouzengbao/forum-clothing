<template>
	<view class="app">
		
			<view class="bg-gradual-purple radius shadow margin-lg " style="height: 400rpx; position: relative;">
				
				<view class="cu-tag bg-green radius" style="transform: rotate(-45deg); position: relative; top: 20rpx; left: -10rpx;" v-if="user.auth == 1">已审核</view>
				<view class="cu-tag bg-red radius" style="transform: rotate(-45deg); position: relative; top: 20rpx; left: -10rpx;" v-else>待审核</view>
				<view class="cu-item flex justify-between align-center padding-lg" >
					<view>
						<view class="text-xxl">{{user.userName}}</view>
						<text class="text text-red margin-top-sm">请联系管理员</text>
						<view class="text-lg">{{managerTelephone}}</view>
					</view>
					
					<view ><image src="../../static/headimg-2.jpg" class="cu-avatar radius xxl"></image></view>
				</view>
				<view class="text-right padding-sm text-xl" style="position: absolute; bottom: 0px; width: 100%;">会员编号：{{user.userCode}}</view>
			</view>
	</view>
</template>

<script>
	export default{
		data(){
			return {
				user:{
					auth:0,
					userCode:'',
					userName:'',
				},
				managerTelephone:'',
			}
		},
		onLoad(){
			let that = this;
			that.user = uni.getStorageSync('userInfo');
			if(!that.user){
				uni.redirectTo({
					url:'/pages/register/index'
				})
			}
			that.getUserInfoByOpenId(that.user.openId);
			that.getConfigFn();
		},
		onPullDownRefresh() {
			let that = this;
			that.getUserInfoByOpenId(that.user.openId)
		},
		methods:{
			async getUserInfoByOpenId(openid){
				let that = this;
				let res = await this.$http('v1/user/getUserByOpenId', 'get', {openid:openid}, {showLoading: false});
				if(res.code == 0){
					that.user = res.data;
					uni.setStorageSync('userInfo',res.data)
				} else {
					that.$util.msg(res.msg)
				}
			},
			getConfigFn(){
				let that = this;
				that.$request.get('v1/common/config', {
					data: {}
				}).then(res => {
					console.log(res)
					that.managerTelephone = res.data.managerTelephone;
				})
			},
			
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #ffffff;
	}
</style>