<template>
	<uni-popup ref="popup" type="center" :isMaskClick="maskClick">
		<view class="mix-landscape">
			
			<slot></slot>
			<view v-if="closeBtn" class="cuIcon-close" :class="closePosition" @click="close" ></view>
		</view>
	</uni-popup>
</template>

<script> 
	export default {		
		props: {
			// 显示关闭图标
			closeBtn: {
			  type: Boolean,
			  default: true
			},
			// leftTop -> 左上角 rightTop -> 右上角 bottom -> 底部
			title: {
			  type: String,
			  default: '标题'
			},
			// 关闭图标位置
			// leftTop -> 左上角 rightTop -> 右上角 bottom -> 底部
			closePosition: {
			  type: String,
			  default: 'rightTop'
			},
			maskClick: {
				type: Boolean,
				default: true
			}
		},
		methods: { 
			open(){
				this.$refs.popup.open();
			},
			close(){
				this.$emit('onClose');
				this.$refs.popup.close();
			}
		}
	}
</script>

<style scoped lang="scss">
	.mix-landscape{
		 position: relative;
	}
	.cuIcon-close{
		position: absolute;
		text-align: center;
		font-size: 40rpx;
		line-height: 50rpx;
		width: 55rpx;
		height: 55rpx;
		border: 1rpx solid #ffffff;
		border-radius: 50%;
		color: #FFFFFF;
		transform: translateX(-50%);
		
		&.leftTop {
		  top: -40rpx;
		  left: 20rpx;
		}
		
		&.rightTop {
		  top: -40rpx;
		  right: 40rpx;
		}
		
		&.bottom {
		  left: 50%;
		  bottom: -100rpx;
		  
		}
	}
</style>