<template>
	<view class="top" > 
		<view class="sort-bar flex align-center justify-center">
			<view class="item flex align-center justify-center" :class="{active: item.current, last: index === sortList.length-1}" v-for="(item,index) in sortList" :key="index" @click="changeSort(item,index)">				
				<text>{{ item.name|title(item) }}</text>				
				<view v-if="item.type=='sort'" class="icon-wrap">
					<text class="cuIcon-triangleupfill"  v-if="item.current && item.value =='desc'" ></text>
					<text class="cuIcon-triangledownfill"  v-if="item.current && item.value == 'asc'" ></text>
				</view>
			</view>
			<view class="padding-lr-sm" v-if="menuData" @click="showDrawer">
				<text class="cuIcon-filter"></text>
			</view>
		</view> 
		
		<uni-popup ref="popup" type="left">
			<!-- 第一级菜单 -->
			<scroll-view class="pop-content" :scroll-y="true" >
				
				<block v-for="(data,index) in menuData.data" :key="index">
					
					<view class="padding"> {{data.name}} </view>
					<view class="">
						
					</view>					
				</block>
				
				
			</scroll-view> 
		</uni-popup>
	</view>
</template>

<script>
	export default {
		name: 'HM-filterDropdown',
		data() {
			return {
				menuData:{},
				sortList:[],
				subMenu:[]
			}
		},
		props: { 
			dataValue: {
				value: Array,
				default: []
			},
		},
		filters:{
			title(name,item){
				if(item.type === "dropdown"){
					let info =  typeof item.data === 'Array' ? item.data.find(i => i.value === item.value) : undefined;
					if(typeof info === 'undefined') return item.name;
					return info.name;
				}else{
					return name;
				}			
			}
		},
		watch:{
			dataValue: {
				handler(newVal) {
					 // 数据分离
					this.sortList = JSON.parse(JSON.stringify( newVal.filter(item => !['filter'].includes(item.type)) )) ; //获取排序的数据
					this.menuData = newVal.find(item => ['filter'].includes(item.type)) 
				},
				immediate: true,
				deep: true
			},
		},
		computed:{
			list(){
				// let data = this.filterData.filter(item => !['filter'].includes(item.type));
				// return data;
			}
		},
		methods: { 
			showDrawer(){
				this.$refs.popup.open();
			},
			//排序
			changeSort(item){ 
				if(!item.current){
					this.sortList.forEach(v=> { v.current = false }); 
					this.$set(item,'current',true); 
				}else{
					// 排序 多次点击进行排序转换
					if(item.type == 'sort'){
						this.$set(item,'value', typeof item.value === 'undefined' || item.value == 'desc' ? 'asc' : 'desc' );  
					}
				}
				// 如果是下拉测进行显示菜单并结束			
				if(item.type == 'dropdown'){
					
					
					return false;					
				}
				
			},
		}
	}
	
</script>

<style scoped lang="scss">
	.pop-content{
		background-color: #ffffff;
		height: 100vh;
		width: 80vw;
	}
	.sort-bar{
		justify-content: space-around;
		height: 76rpx;
		padding: 4rpx 0 4rpx 4rpx;
		/* #ifdef MP */
		padding-left: 10rpx;
		/* #endif */
		background-color: #fff;
		position: relative;
		z-index: 1;
		
		.item{
			flex: 1;
			height: 100%;
			font-size: 28rpx;
			color: #333;
			position: relative;
			overflow: hidden;
			
			&.active{
				color: #ff536f;
				font-weight: 700;				
				// &:after{
				// 	position: absolute;
				// 	left: 50%;
				// 	bottom: 0;
				// 	transform: translateX(-28rpx);
				// 	content: '';
				// 	width: 56rpx;
				// 	height: 4rpx;
				// 	background-color: $base-color;
				// 	border-radius: 10px;
				// } 
			}
			
			&.last:before{
				content: '';
				position: absolute;
				right: 0;
				top: 50%;
				transform: translateY(-50%);
				width: 2rpx;
				height: 40rpx;
				box-shadow: 0 0 16rpx rgba(0,0,0,.6);
			}			 
		}
		 
		[class*=cuIcon]{
			font-size: 40rpx !important;
			width: 20rpx;
			color: #bbb;
		}		 
	}
	
	.sub-menu-class {
		width: 100%;
		position: absolute;
		left: 0;
		transform: translate3d(0, - 100%, 0);
		max-height: 345px;
		background-color: #ffffff;
		z-index: 11;
		box-shadow: 0 5px 5px rgba(0, 0, 0, .1);
		overflow: hidden;
		flex-direction: row;
		transition: transform .15s linear;
		&.hide {
			display: none;
		}
	
		&.show {
			transform: translate3d(0, calc(44px + 0rpx), 0);
		}
	}
</style>