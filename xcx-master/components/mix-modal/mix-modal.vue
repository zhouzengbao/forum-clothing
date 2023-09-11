<template>
	<uni-popup ref="popup" type="center">
		<view class="pop-content">
			<text class="title">{{ title }}</text>
			<view class="con flex justify-center align-center">
				<text class="text">{{ text }}</text>
			</view>
			<view class="btn-group flex justify-center align-center solid-top">
				<view class="btn justify-center align-center text-center" @click="close">
					<text>{{ cancelText }}</text>
				</view>
				<view class="btn justify-center align-center text-center solid-left" @click="confirm">
					<text>{{ confirmText }}</text>
				</view>
			</view>
		</view>
	</uni-popup>
</template>

<script>
	/**
	 * 确认对话框
	 * @prop title 标题
	 * @prop text 提示内容
	 * @prop cancelText 取消按钮文字
	 * @prop confirmText 确认按钮文字
	 * @event onConfirm 确认按钮点击时触发
	 */
	export default {		
		props: {
			title: {
				type:String,
				default: '提示信息'
			},
			text: {
				type:String,
				default: ''
			},
			cancelText: {
				type: String,
				default: '取消'
			},
			confirmText: {
				type: String,
				default: '确定'
			}
		},
		methods: {
			confirm(){
				this.$emit('onConfirm');
				this.$emit('onClose',true);
				this.$refs.popup.close();
			},
			open(){
				this.$refs.popup.open();
			},
			close(){
				this.$emit('onClose',false);
				this.$refs.popup.close();
			}
		}
	}
</script>

<style scoped lang='scss'>
	.pop-content{
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 540rpx;
		padding-top: 36rpx;
		background-color: #fff;
		border-radius: 24rpx;
		overflow: hidden;
	
		.title{
			font-size: 32rpx;
			color: #333;
			line-height: 48rpx;
			font-weight: 700;
		}
		.con{
			padding: 36rpx 40rpx 54rpx;
		}
		.text{
			width: 460rpx;
			font-size: 26rpx;
			color: #333;
			line-height: 40rpx;
			text-align: center;
		}
		.btn-group{
			width: 100%;
		}
		.btn{
			flex: 1;
			height: 88rpx;
			line-height: 88rpx;
			font-size: 32rpx;
			color: #777;
			
			&:last-child{
				color: #007aff;
			}
		}
	}
</style>
