<template>
	<uni-popup ref="uniPopup" type="bottom" >
		<view class="content bg-gray padding-bottom">
			<view class="flex align-center justify-between padding-sm bg-white ">
				<text>提取申请单</text>
				<text class="text-gray cuIcon-close lg" @click="close"></text>
			</view>
			<view  class="bg-white margin-sm radius">
				<view class="padding-lr-xs"> 
					<view class="cu-form-group">
						<view class="title">提款方式</view>
						<view class="flex justify-between">
							<picker @change="PickerChange" :value="data.type" :range="picker">
								<view class="picker" :class="{'text-gray':data.type<0}">
									{{data.type>-1?picker[data.type]:'=点击请选择='}}
								</view>
							</picker>
						</view>
					</view>
					<view class="cu-form-group" v-if="data.type==1" style="min-height: 2.4rem;">					 	
					 	<view class="text-red">
							<view class="margin-bottom-sm">1、财务不会给用户实际打款</view>
							<view>2、操作人员进行前台进行充值大于 {{total}} 金额</view>		
						</view>				
					</view>
					<view class="cu-form-group" v-if="data.type==0">
						<view class="title">收款人</view>
						<input placeholder="收款人" class="text-right text-blue" placeholder-class="text-gray text-sm" style="padding-right:0" v-model="data.bank_name"></input>
					</view>
					<view class="cu-form-group" v-if="data.type==0">
						<view class="title">银行卡号</view>
						<input placeholder="银行卡号" class="text-right text-blue" placeholder-class="text-gray text-sm" style="padding-right:0" v-model="data.bank_number"></input>
					</view> 
					<view class="cu-form-group" v-if="data.type==0">
						<view class="title">开户银行</view>
						<input placeholder="开户银行" class="text-right text-blue" placeholder-class="text-gray text-sm" style="padding-right:0" v-model="data.bank_company"></input>
					</view>
				</view> 
			</view>
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-red margin-tb-sm lg" @click="confirms()">保存资料</button>
			</view>
			 
		</view>
	</uni-popup>
</template>
<script> 
	export default {		 
		data() { 
			return { 
				data:{
					type:0,
					bank_name:'',
					bank_number:'',
					bank_company:''
				},
				picker: ['银行卡提现', '余额续存提现'],
			};
		},
		computed: {
			express(){
				return {};
			}
		},
		props: {
			express_company:[Array],
			total:[Number],
		},
		 
		methods: {
			open(){
				this.$refs.uniPopup.open();
			},
			close(){
				this.$refs.uniPopup.close();
			},
			confirms(){
				// this.$refs.mixModal.open();
				// const {code,number,express,item} = this;
				// let data =  {code,number,express,item};
				this.$emit('onConfirm',this.data)
			},
			PickerChange(e){
				this.data.type = e.detail.value
			}
		}
	}
</script>

<style scoped lang="scss">
	 
	.content{
		border-radius: 20rpx 20rpx 0 0;
	}
	.text-blue{
		color: #0081ff !important;
	}
	.cu-form-group{
		min-height: 80rpx;
	}
</style>
