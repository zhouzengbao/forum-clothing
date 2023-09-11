<template>
	<view class="app">
		<view class="padding-sm bg-white">
			<button open-type="getUserInfo" @getuserinfo="getUserInfo">微信授权登录</button>
		</view>

	</view>
</template>

<script>

	export default{
		data(){
			return {}
		},
		onLoad(){
			
		},
		methods:{
			//提交
			getUserInfo(){
				let that = this;
				uni.getSetting({
					success(res) {
						if (res.authSetting['scope.userInfo']) {
							uni.getUserInfo({
								provider:'weixin',
								withCredentials:true,
								success(profile) {
									console.log(profile)
								}
							})
						}
					}
				})
				
			}
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #ffffff;
	}
</style>