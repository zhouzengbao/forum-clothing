<template>
	<view class="app">
		<mescroll-body
			ref="mescrollRef" 
			:sticky="true"
			@init="mescrollInit"
			top=5
			:down="downOption"  
			@down="downCallback"  
			:up="upOption" 
			@up="loadList" 
		>
			<view class="sticky-tabs">
				<scroll-view scroll-x class="bg-white nav">
					<view class="flex text-center">
						<view class="cu-item flex-sub" :class="item.value==navCut?'text-blue cur':''" v-for="item in navTab" :key="item.value" @tap="tabSelect" :data-id="item.value">
							{{item.name}}
						</view>
						
					</view>
				</scroll-view>
			</view>
			<view class="justify-between flex padding-lr-sm margin-top-sm">
				<view class="justify-between flex align-center" style="width: 50%;">
					<text>类别:</text>
					<view class="justify-between flex align-center border solid padding-xs" style="width: 75%;"  @click="openSearchCate">
						<view>{{searchCateTitle||'请选择类别'}}</view>
						<text class="cuIcon-unfold margin-left-xs"></text>
					</view>
				</view>
				<view class="padding-right-sm ">
					<text class="bg-black text-white cu-btn radius" @click="showFilter()">发布</text>
				</view>
			</view>
			<block v-if="navCut == 0">
				<view class="margin-sm">
						<view class="solid shadow shadow-blur cu-card margin-bottom-sm" v-for="(item,index) in list">
							<view class="padding-lr-xs padding-top-xs shadow shadow-lg" style="width: 100%; " :style="item.qualityTypeStr == '供'? 'border-top:2px solid green':'border-top:2px solid blue'">
								<text class="round  padding-xs" :class="item.qualityTypeStr == '供'?'bg-green':'bg-blue'" >{{item.qualityTypeStr}}</text>
							</view>
							<view class=" padding-sm">
								<view class="align-center padding-bottom-sm flex justify-between">
									<view class="">
										<checkbox class="round"></checkbox> <text class="padding-left-sm">{{item.userName}} [{{item.userTypeStr}}]</text>
									</view>
									<text :class="item.collect==0?'cuIcon-favor':'cuIcon-favorfill line-yellow'" @click="favorFn(index)"></text>
								</view>
								
								<view class="align-center padding-bottom-sm flex">
									<view class="padding-right-lg">类别：{{item.typeStr}}</view>
									<view class="margin-left-lg">月库存量：{{item.demand}}吨</view>
								</view>
								<view class="align-center padding-bottom-sm">指标：精：{{item.product.j}} A货：{{item.product.a}}% B货：{{item.product.b}}% C货：{{item.product.c}}%</view>
								<view class="align-center margin-bottom-sm text-cut-2">所在地：{{item.address}}</view>
							</view>
						</view>
				</view>
				
			</block>
			<block v-if="navCut == 1">
				<view class="margin-sm">
						<view class="solid shadow shadow-blur cu-card margin-bottom-xs" v-for="(item,index) in list">
							<view class="padding-lr-xs padding-top-xs" style="width: 100%; " :style="item.qualityTypeStr == '供'? 'border-top:2px solid green':'border-top:2px solid blue'">
								<text class="round  padding-xs" :class="item.qualityTypeStr == '供'?'bg-green':'bg-blue'" >{{item.qualityTypeStr}}</text>
							</view>
							<view class=" padding-sm">
								<view class="align-center padding-bottom-sm flex justify-between">
									<view class="">
										<checkbox class="round"></checkbox> <text class="padding-left-sm">{{item.userName}} [{{item.userTypeStr}}]</text>
									</view>
									<text :class="item.collect==0?'cuIcon-favor':'cuIcon-favorfill line-yellow'" @click="favorFn(index)"></text>
								</view>
								
								<view class="align-center padding-bottom-sm flex">
									<view class="padding-right-lg">类别：{{item.typeStr}}</view>
									<view class="margin-left-lg">库存量：{{item.demand}}吨</view>
								</view>
								<view class="align-center padding-bottom-sm">指标：精品：{{item.product.j}} A货：{{item.product.a}}% B货：{{item.product.b}}% C货：{{item.product.c}}%</view>
								<view class="align-center margin-bottom-sm text-cut-2">所在地：{{item.address}}</view>
							</view>
						</view>
				</view>
			</block>
			<block v-if="navCut == 2">
				<view class="margin-sm">
						<view class="solid shadow shadow-blur cu-card margin-bottom-xs" v-for="(item,index) in list">
							<view class="padding-lr-xs padding-top-xs" style="width: 100%; " :style="item.qualityTypeStr == '供'? 'border-top:2px solid green':'border-top:2px solid blue'">
								<text class="round  padding-xs" :class="item.qualityTypeStr == '供'?'bg-green':'bg-blue'" >{{item.qualityTypeStr}}</text>
							</view>
							<view class=" padding-sm">
								<view class="align-center padding-bottom-sm flex justify-between">
									<view class="">
										<checkbox class="round"></checkbox> <text class="padding-left-sm">{{item.userName}} [{{item.userTypeStr}}]</text>
									</view>
									<text :class="item.collect==0?'cuIcon-favor':'cuIcon-favorfill line-yellow'" @click="favorFn(index)"></text>
								</view>
								
								<view class="align-center padding-bottom-sm flex">
									<view class="padding-right-lg">类别：{{item.typeStr}}</view>
									<view class="margin-left-lg">库存量：{{item.demand}}吨</view>
								</view>
								<view class="align-center padding-bottom-sm">指标：精品：{{item.product.j}}% A货：{{item.product.a}}% B货：{{item.product.b}}% C货：{{item.product.c}}%</view>
								<view class="align-center margin-bottom-sm text-cut-2">所在地：{{item.address}}</view>
							</view>
						</view>
				</view>
			</block>
		</mescroll-body>
		<uni-popup  ref="filter" type="top">
			<view class="content bg-white">
				<view class="padding-sm">
					<view class="flex align-center margin-bottom-sm" >
						<text class="">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</text>
						<view class="justify-between flex align-center border solid padding-xs" style="width: 75%;"  @click="openCate">
							<view>{{cateTitle||'请选择类别'}}</view>
							<text class="cuIcon-unfold margin-left-xs"></text>
						</view>
					</view>
					<view class="flex align-center margin-bottom-sm" >
						<text class="">发布类型：</text>
						<view class="justify-between flex align-center border solid padding-xs" style="width: 75%;"  @click="openQualityQualityType">
							<view>{{qualityQualityTypeTitle||'请选择类别'}}</view>
							<text class="cuIcon-unfold margin-left-xs"></text>
						</view>
					</view>
					<view class="flex align-center margin-bottom-sm" >
						<text class="">库存量：</text>
						<view class="border solid padding-lr-sm padding-tb-xs flex justify-between align-center" style="width: 75%;">
							<input class="text-left"  v-model="form.demand"  placeholder="请输入库存量" />
							<text>吨</text>
						</view>
					</view>
					<view class="flex margin-bottom-sm" >
						<text class="padding-tb-xs">产品指标：</text>
						<view class="" style="width:75%">
							<view class="flex margin-bottom-xs">
								<view class="padding-tb-xs">精品：</view>
								<view class="border solid padding-lr-sm padding-tb-xs  flex justify-between align-center" style="width: 85%;">
									<input class="text-left" v-model="product.j"  placeholder="请输入产品指标" />
									<text>%</text>
								</view>
							</view>
							<view class="flex margin-bottom-xs">
								<view class="padding-tb-xs">A货：</view>
								<view class="border solid padding-lr-sm padding-tb-xs  flex justify-between align-center" style="width: 85%;">
									<input class="text-left" v-model="product.a"  placeholder="请输入产品指标" />
									<text>%</text>
								</view>
							</view>
							<view class="flex margin-bottom-xs">
								<view class="padding-tb-xs">B货：</view>
								<view class="border solid padding-lr-sm padding-tb-xs  flex justify-between align-center" style="width: 85%;">
									<input class="text-left" v-model="product.b"  placeholder="请输入产品指标" />
									<text>%</text>
								</view>
							</view>
							<view class="flex">
								<view class="padding-tb-xs">C货：</view>
								<view class="border solid padding-lr-sm padding-tb-xs flex justify-between align-center" style="width: 85%;">
									<input class="text-left"  v-model="product.c"  placeholder="请输入产品指标" />
									<text>%</text>
								</view>
							</view>
						</view>
					</view>
					
					<view class="flex align-center margin-bottom-sm" >
						<text class="">所&nbsp;&nbsp;在&nbsp;&nbsp;地：</text>
						<view @click="openArea" class="padding-right-sm flex align-center justify-between solid" style="width:75%">
							<view class="padding-tb-xs padding-left-xs">
								<text v-if="areaValue.length>0">{{areaValue.join('，')}}</text>
								<text v-else style="color:rgb(128, 128, 128 , .40);">请选择所在区域</text>
							</view>
							<text class="cuIcon-unfold margin-left-xs"></text>
						</view>
					</view>
					<view class="flex justify-around align-center margin-tb-lg" >
						<button class="cu-btn radius" @click="hidePopup('filter')">关闭</button>
						<button class="cu-btn radius bg-blue" @click="submitFn()">发布</button>
					</view>
				</view>
			</view>
		</uni-popup>
		 <mix-loading v-if="isLoading" :type="1"></mix-loading>
		 <lb-picker ref="CatePicker" :list="cateList"  v-model="form.type" :value="form.type"  @confirm="setCate" mode='selector'></lb-picker>
		 <lb-picker ref="SearchCatePicker" :list="searchCateList"  v-model="searchCate" :value="searchCate"  @confirm="setSearchCate" mode='selector'></lb-picker>
		  <lb-picker ref="qualityQualityTypePicker" :list="qualityQualityTypeList"  v-model="form.qualityQualityType" :value="form.qualityQualityType"  @confirm="setQualityQualityType" mode='selector'></lb-picker>
		 <lb-picker ref="areaPicker" :value="areaValue" mode="multiSelector" :list="cityList" :level="3" :props="{label:'label',value:'label',children:'children'}" @confirm="areaConfirm" ></lb-picker>
	</view>
</template>

<script>
	import LbPicker from '@/components/lb-picker'; 
	import areaData from '@/components/lb-picker/area-data-min' 
	import { isLogin } from '../../common/js/util';
	import MescrollMixin from "@/uni_modules/mescroll-uni/components/mescroll-uni/mescroll-mixins.js";
	
	export default{
		components: {LbPicker},
		mixins: [ MescrollMixin], 
		data(){
			return {
				list: [],
				form:{
					id:'',
					type:'',
					province:'',
					city:'',
					area:'',
					demand:'',
					qualityPer:{},
					openid:'',
					address:'',
					qualityQualityType:''
				},
				cateList:[],
				searchCateList:[],
				qualityQualityTypeList:[],
				favor:2,
				cityList: areaData,
				navCut:0,
				navTab:[
					{name:'推荐',value:0},
					{name:'收藏',value:1},
					{name:'我的发布',value:2}
				],
				
				upOption:{
					auto: false, // 是否自动加载
					page: {
					 	num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
					 	size: 5 // 每页数据的数量
					},
					noMoreSize: 6
				},
				user:'',
				searchCate:'',
				product:{
          'j':'',
					'a':'',
					'b':'',
					'c':''
				},
				productShow:{}
			}
		},
		onLoad(){
			let that = this;
			// 判断是否登录并且是否认证 
			that.user = uni.getStorageSync('userInfo');
			if(!that.user){
				uni.redirectTo({
					url:'/pages/register/index'
				})
			}
			that.form.openid = that.user.openId;
			that.getConfigFn();
		},
		computed:{
			cateTitle(){
				let that = this;
				let {cateList,form} = this;
				let data = cateList.find(i => i.value == form.type||'')||{};
				return data && data.label||''
			},
			searchCateTitle(){
				let that = this;
				let {searchCateList} = this;
				let data = searchCateList.find(i => i.value == that.searchCate||'')||{};
				return data && data.label||''
			},
			qualityQualityTypeTitle(){
				let that = this;
				let {form} = this;
				let {qualityQualityTypeList} = this;
				let data = qualityQualityTypeList.find(i => i.value == form.qualityQualityType||'')||{};
				return data && data.label||''
			},
			areaValue(){
				let {province,city,area} = this.form;
				return [province,city,area].filter(i=>i.length > 0)
			},
		},
		methods:{
			async favorFn(index){
				let that = this;
				
				let data = {
					'id':that.list[index].id,
					'userId':that.user.id,
				}
				let res = await this.$http('v1/quality/collect', 'post',data , {showLoading: false});
				if(res.code == 0){
					if(that.list[index].collect == 1){
						that.list[index].collect = 0;
						that.$util.msg('取消收藏');
					}
					else{
						that.list[index].collect = 1;
						that.$util.msg('收藏成功');
					}
				} else {
					that.$util.msg(res.msg)
				}
			},
			
			async submitFn(){
				let that = this;
				let {form} = this;
				form.address = form.province+form.city+form.area;
				form.qualityPer = JSON.stringify(that.product);
				let res = await this.$http('v1/quality/publish', 'post', form, {showLoading: false});
				if(res.code == 0){
					that.navCut = 2;
					this.hidePopup('filter');
					that.refreshList();
				} else {
					that.$util.msg(res.msg)
				}
			},
			showFilter(){
				if(this.user.auth==0){
					this.$util.msg('用户未审核通过，无法发布')
				} else {
					this.showPopup('filter');
				}
								
			},
			tabSelect(e){
				this.list = [];
				this.navCut = e.currentTarget.dataset.id;
				this.refreshList();
			},
			//打开选择器
			openCate(){
				this.$refs.CatePicker.show()
			},
			//设置选择标题
			setCate(e){
				let that = this;
				let {form} = this;
				that.$set(form,'type',e.value)
			},
			
			//打开选择器
			openSearchCate(){
				this.$refs.SearchCatePicker.show()
			},
			//设置选择标题
			setSearchCate(e){
				let that = this;
				that.$set(that,'searchCate',e.value)
			},
			
			//打开选择器
			openQualityQualityType(){
				this.$refs.qualityQualityTypePicker.show()
			},
			//设置选择标题
			setQualityQualityType(e){
				let that = this;
				let {form} = this;
				that.$set(form,'qualityQualityType',e.value)
			},
			
			openArea(){
				this.$refs.areaPicker.show()
			},
			areaConfirm(e){ 
				this.form.province = e.value[0]||'';
				this.form.city = e.value[1]||'';
				this.form.area = e.value[2]||'';
			},
			getConfigFn(){
				let that = this;
				that.$request.get('v1/common/config', {
					data: {}
				}).then(confRes => {
					let qualityQualityType = confRes.data.qualityQualityType; 
					let qualityType = confRes.data.qualityType; 
					qualityType.forEach(function(value,index){
						for(let item in value){
							that.cateList.push({'label':value[item],'value':item})
						}
						for(let item in value){
							that.searchCateList.push({'label':value[item],'value':item})
						}
					})
					qualityQualityType.forEach(function(value,index){
						for(let item in value){
							that.qualityQualityTypeList.push({'label':value[item],'value':item})
						}
					})
				})
			},
			async loadList(e){
				let that = this;
				const {navCut} = this;
				let data = {
					page: e.num,
					size: e.size,
					type:that.searchCate,
					pageType:navCut,
					openid:that.user.openId,
				}
				let url = 'v1/quality/list';
				const res = await this.$http(url, 'post', data, {showLoading: true});
				const curList = res.data.list||[];
				if (e.num === 1) {
					this.list = []; 
				}
				for(let item in curList){
					curList[item].product = JSON.parse(curList[item].qualityPer);
				}
				this.list = this.list.concat(curList); //追加新数据
				this.mescroll.endSuccess(curList.length); //结束加载状态
			},
			//刷新列表
			refreshList() {
				this.mescroll && this.mescroll.resetUpScroll(false);
			}, 
			mescrollInit(mescroll){ 
				this.mescroll = mescroll;  
			},
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #fff;
	}
	.sticky-tabs{
			z-index: 99;
			position: sticky;
			top: var(--window-top);
			background-color: #fff;
		}
		
		// 使用mescroll-uni,则top为0
		.mescroll-uni,
		/deep/.mescroll-uni{
			.sticky-tabs{
				top: 0;
			}
		}
</style>