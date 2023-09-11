<template>
	
		<scroll-view scroll-y class="DrawerWindow" :class="modalName=='viewModal'?'show':''">
			<view class="cu-list menu card-menu margin-top-xl margin-bottom-xl shadow-lg">
				<view class="cu-item arrow" @click="navTo(item.url)" v-for="item in navData" :key="item.url">
					<view>
						<text class="cuIcon-rank text-grey margin-right-xs"></text>
						<text>{{item.title}}</text>
					</view>
				</view>
				<view class="cu-item arrow" @click="UserLogout">
					<view>
						<text class="cuIcon-rank text-grey margin-right-xs"></text>
						<text>重新登录</text>
					</view>
				</view>
				<view class="text-center text-sm text-gray padding-tb-sm">
					{{'版本 :'+versionNum}}
				</view>
			</view>
		</scroll-view>
	
</template>

<script>
	export default {
		data(){
			return {
				
			}
		},
		props:{
			modalName:{
				type:String,
				default:null
			},
			navData:{
				type:Array,
				default:[
					{url:'/pages/tabbar/index',title:'首页'},
					{url:'/pages/performanceRanking/performanceRanking',title:'门店业绩排行'},
					{url:'/pages/profitAnalysis/profitAnalysis',title:'门店利润排名'},
					{url:'/pages/storedValue/storedValue',title:'门店储值汇总'},
					{url:'/pages/cardCount/cardCount',title:'门店会员分析'},
					{url:'/pages/storeVipValue/storeVipValue',title:'门店储值卡'},
					{url:'/pages/storeLogin/storeLogin',title:'会员领取查询'},
					{url:'/pages/storeTimeSlot/storeTimeSlot',title:'门店时段消费排行'}
				]
			}
		},
		methods:{
			...mapMutations(['logout']),
			navTo(url){
				// this.modalName = null;
				// this.position = 'static';
				this.$emit('position')
				uni.navigateTo({
					url:url
				})
			},
			
			UserLogout(){
				this.logout();
				uni.redirectTo({
					url:"/pages/login/index"
				})
			},
		}
	}
</script>

<style scoped>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}
	
	.text-area {
		display: flex;
		justify-content: center;
	}
	
	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	.masking_layer{
		background-color: rgba(0,0,0,.4);
		display: flex;
		height: calc(100vh);
		flex-direction: column;
		justify-content: flex-end;
		position: fixed;
		top: 0;
		left: 0;
		width: 100vw;
		z-index: 99;
	}
	.DrawerPage {
		/* position: fixed;
		width: 100vw;
		height: 100vh;
		left: 0vw; */
		width: 100vw;
		height: 100%;
		left: 0vw;
		background-color: #f1f1f1;
		transition: all 0.4s;
	}
	
	.DrawerPage.show {
		transform: scale(0.9, 0.9);
		left: 85vw;
		box-shadow: 0 0 60upx rgba(0, 0, 0, 0.2);
		transform-origin: 0;
	}
	
	.DrawerWindow {
		position: absolute;
		width: 85vw;
		height: 100vh;
		left: 0;
		top: 0;
		transform: scale(0.9, 0.9) translateX(-100%);
		opacity: 0;
		pointer-events: none;
		transition: all 0.4s;
		/* padding: 100upx 0; */
	}
	
	.DrawerWindow.show {
		transform: scale(1, 1) translateX(0%);
		opacity: 1;
		pointer-events: all;
	}
	
	.DrawerClose {
		position: absolute;
		width: 40vw;
		height: 100vh;
		right: 0;
		top: 0;
		color: transparent;
		padding-bottom: 30upx;
		display: flex;
		align-items: flex-end;
		justify-content: center;
		background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.01), rgba(0, 0, 0, 0.6));
		letter-spacing: 5px;
		font-size: 50upx;
		opacity: 0;
		pointer-events: none;
		transition: all 0.4s;
	}
	
	.DrawerClose.show {
		opacity: 1;
		pointer-events: all;
		width: 15vw;
		color: #fff;
	}
	
	.DrawerPage .cu-bar.tabbar .action button.cuIcon {
		width: 64upx;
		height: 64upx;
		line-height: 64upx;
		margin: 0;
		display: inline-block;
	}
	
	.DrawerPage .cu-bar.tabbar .action .cu-avatar {
		margin: 0;
	}
	
	.DrawerPage .nav {
		flex: 1;
	}
	
	.DrawerPage .nav .cu-item.cur {
		border-bottom: 0;
		position: relative;
	}
	
	.DrawerPage .nav .cu-item.cur::after {
		content: "";
		width: 10upx;
		height: 10upx;
		background-color: currentColor;
		position: absolute;
		bottom: 10upx;
		border-radius: 10upx;
		left: 0;
		right: 0;
		margin: auto;
	}
	
	.DrawerPage .cu-bar.tabbar .action {
		flex: initial;
	}
</style>
