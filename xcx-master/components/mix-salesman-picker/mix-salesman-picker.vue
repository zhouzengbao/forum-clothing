<template>
	<lb-picker ref="salePicker" :list="salesmanList" v-model="SalesId" @confirm="setSalesmanPicker" mode='selector'></lb-picker>
</template>

<script> 
	import LbPicker from '@/components/lb-picker'
	export default {
		components: {
			LbPicker
		},
		data() { 
			return {  
				 SalesId:this.value,
			};
		},
		computed: {
			salesmanList(){
				let list = []
				this.$store.state.salesmanList.forEach(item => {
					if(item.Status != 0 ){
						list.push({label:item.Name,value:item.SalesId});
					}					
				});
				return  list
			},
			salesmanPickerTitle(){
				let {SalesId,salesmanList} = this;
				if(salesmanList.length <= 0) return false;
				let info = salesmanList.find(item =>  item.value === SalesId);	
				let title = info&&info.label||''; 
				this.$emit('onConfirm',{title,value:SalesId});
			}
		},
		props: {
			value:{
				type:[String,Number],
				default: '' 
			}
		},
		watch: {
		  value(newVal){ 
				this.SalesId = newVal; 
		  },
		  SalesId(newVal){ 
				let {salesmanList} = this;
				let salesmanTotal = salesmanList.filter(item =>  item.value == newVal).length||0;	 	
				console.log(salesmanTotal)
				// if(newVal && salesmanTotal<=0) newVal = '';
				// this.SalesId = newVal;   
			  	this.$emit('input', newVal); 
			}
		},
		created() {
			// await this.getSalesmanList(); 
		},
		methods: { 
			setSalesmanPicker(e){
				let that = this, {salesmanPickerTitle} = this;
				that.SalesId = e.value;
			},
			async getSalesmanList(){
				let that = this,{SalesId} = that;
				const res = await this.$http('storeapp/api.auth.operator/gets', 'post'); 
				this.salesmanList = [];
				res.data.forEach(item => {
					if(item.Status != 0 ){
						this.salesmanList.push({label:item.Name,value:item.SalesId});
					}					
				});
			},
			open(){
				this.$refs.salePicker.show();
			}
		}
	}
</script>


<style scoped lang="scss">
</style>