<template>
	<view class="nav-bar solid-bottom">
		<view
			class="nav-item" 
			v-for="(item, index) in navs" 
			:key="index"
			@click="navChange(index)"
		>  
			<view class="tit-wrap">
				<text class="tit" :class="{'active': current == index}">{{ item.name }}</text>
				<text v-if="counts.length > index && counts[index] > 0" class="number">{{ counts[index] }}</text>
			</view>
			<view class="line" :class="{'line--show': current === index}"></view>
		</view>
	</view>
</template>

<script>
	/**
	 * 顶部tab栏
	 */
	export default {
		data(){
			return {
				countList: [],
			}
		},
		props: {
			navs: {
				type: Array,
				default(){
					return [];
				}
			},
			current: {
				type: Number,
				default: 0
			},
			counts: {
				type: Array,
				default(){
					return [];
				}
			}
		},
		watch: {
			
		},
		methods: {
			navChange(index){
				this.$emit('onChange', index);
			}
		}
	}
</script>

<style scoped lang='scss'>
	/* #ifndef APP-NVUE */
	view{
		display: flex;
		flex-direction: column;
	}
	/* #endif */
	.fill-view{
		height: 84rpx;
		width: 100%;
	}
	.nav-bar{ 
		display: flex; 
		flex-direction: row;
		align-items: center;
		justify-content: space-around;
		width: 750rpx;
		height: 84rpx;
		background-color: #fff;
		z-index: 90;
		/* position: fixed;
		left: 0;
		top: 0; */
		/* #ifdef H5 */
		/* top: var(--window-top); */
		/* #endif */
		
		&:after{
			border-color: #f7f7f7;
		}
	}
	.nav-item{
		flex: 1;
		align-items: center;
		justify-content: center;
		height: 84rpx;
		padding-top: 4rpx;
		position: relative;
	}
	.tit-wrap{
		flex: 1;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		position: relative;
	}
	.number{
		position: absolute;
		right: -10px;
		top: -1px;
		min-width: 14px;
		height: 14px;
		padding: 0 2px;
		text-align: center;
		line-height: 14px;
		border: 2px solid #fff;
		background-color: rgba(255,83,111, .8);
		border-radius: 100rpx;
		font-size: 9px;
		color: #fff;
	}
	.tit{
		/* margin-top: 8px; */
		font-size: 14px;
		color: #333;
	}
	.active{
		color: #ff4443;
		font-weight: 700;
	}
	.line{
		width: 70%;
		height: 4rpx;
		border-radius: 100rpx;
		background-color: #ff4443;
		opacity: 0;
		
		&--show{
			opacity: 1;
		}
	}
</style>
