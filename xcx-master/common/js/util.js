let _debounceTimeout = null,
	_throttleRunning = false

/**
 * 防抖
 * @param {Function} 执行函数
 * @param {Number} delay 延时ms   
 */
export const debounce = (fn, delay = 500) => {
	clearTimeout(_debounceTimeout);
	_debounceTimeout = setTimeout(() => {
		fn();
	}, delay);
}
/**
 * 节流
 * @param {Function} 执行函数
 * @param {Number} delay 延时ms  
 */
export const throttle = (fn, delay = 500) => {
	if (_throttleRunning) {
		return;
	}
	_throttleRunning = true;
	fn();
	setTimeout(() => {
		_throttleRunning = false;
	}, delay);
}

//获取小程序code
export const getMpCode = (provider) => {
	return new Promise((resolve, reject) => {
		uni.login({
			provider,
			success: res=> {
				resolve(res.code);
			}
		})
	})
}

export const listSortBy = (myArray,field,order) => {
//冒泡排序
    if(myArray.length>0){
        for(var i=0; i<myArray.length; i++){
            //在这要注意myArray.length-i-1，意思是第一次从数组第一个值开始，第二次从第二个值开始.....
            for(var j=0; j<myArray.length-i-1; j++){
                var str_i = myArray[j][field];
                var str_j = myArray[j+1][field];
                //判断值是否大于后面值，如果大于进行换位处理
                if(order=='asc'){
                    if(parseFloat(str_i) > parseFloat(str_j)){
                        var tmp = myArray[j];
                        myArray[j] = myArray[j+1];
                        myArray[j+1] = tmp;
                    }
                }else if(order=='desc'){
                    if(parseFloat(str_i) < parseFloat(str_j)){
                        var tmp = myArray[j];
                        myArray[j] = myArray[j+1];
                        myArray[j+1] = tmp;
                    }
                }
            }
        }
    }
    return myArray;
}

/**
 * toast
 */
export const msg = (title = '', param={}) => {
	if(!title) return;
	uni.showToast({
		title,
		duration: param.duration || 1500,
		mask: param.mask || false,
		icon: param.icon || 'none'
	});
}
/**
 * 检查登录
 * @return {Boolean}
 */
export const isLogin = (options={}) => { 
	const userInfo = uni.getStorageSync('userToken'); 
	if(userInfo){ 
		return true;
	}
	// if(options.nav !== false){
	// 	uni.switchTab({
	// 		url: '/pages/tabbar/user'
	// 	})
	// }
	return false;
}
/**
 * 获取页面栈
 * @param {Number} preIndex为1时获取上一页
 * @return {Object} 
 */
export const prePage = (preIndex = 1) => {
	const pages = getCurrentPages();
	const prePage = pages[pages.length - (preIndex + 1)]; 
	return prePage.$vm;
}
 


/**
 * 格式化时间戳 Y-m-d H:i:s
 * @param {String} format Y-m-d H:i:s
 * @param {Number} timestamp 时间戳   
 * @return {String}
 */
export const date = (format, timeStamp) => { 
	if (typeof timeStamp == "number" && '' + timeStamp.length <= 10) {
		timeStamp = +timeStamp * 1000;
	} else {
		timeStamp = ''+timeStamp;
	}  
	let _date = new Date(timeStamp),
		Y = _date.getFullYear(),
		m = _date.getMonth() + 1,
		d = _date.getDate(),
		H = _date.getHours(),
		i = _date.getMinutes(),
		s = _date.getSeconds();
	
	m = m < 10 ? '0' + m : m;
	d = d < 10 ? '0' + d : d;
	H = H < 10 ? '0' + H : H;
	i = i < 10 ? '0' + i : i;
	s = s < 10 ? '0' + s : s;

	return format.replace(/[YmdHis]/g, key => {
		return {
			Y,
			m,
			d,
			H,
			i,
			s
		} [key];
	});
}

/**
 * 时间验证，不能比今天的日期小，yyyy-MM-dd 
 * date =“20118-07-06"
 */
export const  checkEndDate = (date,type='<=') => {
    var d1 = new Date(date.replace(/\-/g, "\/"));
    var now = new Date();
    var date =  now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
    var toDay =new Date(date.replace(/\-/g, "\/"));
 
    if(type =='<=' && d1 <= toDay){
    	// alert("填写的日期不能比今天的日期小！");
    	return true;
    }
    if(type =='<' && d1 < toDay){
    	// alert("填写的日期不能比今天的日期小！");
    	return true;
    }
	if(type =='>=' && d1 >= toDay){
		// alert("填写的日期不能比今天的日期小！");
		return true;
	}
	if(type =='>' && d1 > toDay){
		// alert("填写的日期不能比今天的日期小！");
		return true;
	}
	return false;
}

//二维数组去重
export const getUnique = array => {
	let obj = {}
	return array.filter((item, index) => {
		let newItem = item + JSON.stringify(item)
		return obj.hasOwnProperty(newItem) ? false : obj[newItem] = true
	})
}

/**
 * b版本号对比
 */
export const  compareVersion = (v1, v2='0.0.0') => {
  v1 = v1.split('.')
  v2 = v2.split('.')
  const len = Math.max(v1.length, v2.length) 
  while (v1.length < len) {
    v1.push('0')
  }
  while (v2.length < len) {
    v2.push('0')
  } 
  for (let i = 0; i < len; i++) {
    const num1 = parseInt(v1[i])
    const num2 = parseInt(v2[i])

    if (num1 > num2) {
      return 1
    } else if (num1 < num2) {
      return -1
    }
  } 
  return 0
}


export const validateIdCard = (idCard) => {
	var flag = false;
	//15位和18位身份证号码的正则表达式
	var regIdCard =
		/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	if (regIdCard.test(idCard)) {
		if (idCard.length == 18) {
			var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
			var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
			var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
			for (var i = 0; i < 17; i++) {
				idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
			}

			var idCardMod = idCardWiSum % 11; //计算出校验码所在数组的位置
			var idCardLast = idCard.substring(17); //得到最后一位身份证号码

			//如果等于2，则说明校验码是10，身份证号码最后一位应该是X
			if (idCardMod == 2) {
				if (idCardLast == "X" || idCardLast == "x") {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
				if (idCardLast == idCardY[idCardMod]) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} else if (idCard.length == 15) {
			var id17 = idCard.substring(0, 6) + '19' + idCard.substring(6);
			var parityBit = getParityBit(id17);
			var tempIdCard = id17 + parityBit;
			flag = validateIdCard(tempIdCard);
		}
	} else {
		flag = false;
	}
	return flag;
}


// 判断类型集合
export const checkStr = (str, type) => {
	switch (type) {
		case 'mobile': //手机号码
			return /^1[3|4|5|6|7|8|9][0-9]{9}$/.test(str);
		case 'tel': //座机
			return /^(0\d{2,3}-\d{7,8})(-\d{1,4})?$/.test(str);
		case 'card': //身份证
			// return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(str);
			return validateIdCard(str);
		case 'mobileCode': //4位数字验证码
			return /^[0-9]{4}$/.test(str)
		case 'pwd': //密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线
			return /^([a-zA-Z0-9_]){6,18}$/.test(str)
		case 'payPwd': //支付密码 6位纯数字
			return /^[0-9]{6}$/.test(str)
		case 'postal': //邮政编码
			return /[1-9]\d{5}(?!\d)/.test(str);
		case 'QQ': //QQ号
			return /^[1-9][0-9]{4,9}$/.test(str);
		case 'erpCode': //ERP账号
			return /^[0-9]{4,6}$/.test(str); 
		case 'email': //邮箱
			return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
		case 'money': //金额(小数点2位)
			return /^\d*(?:\.\d{0,2})?$/.test(str);
		case 'URL': //网址
			return /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/.test(str)
		case 'IP': //IP
			return /((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))/.test(str);
		case 'date': //日期时间
			return /^(\d{4})\-(\d{2})\-(\d{2}) (\d{2})(?:\:\d{2}|:(\d{2}):(\d{2}))$/.test(str) || /^(\d{4})\-(\d{2})\-(\d{2})$/
				.test(str)
		case 'number': //数字 
			return /^[0-9]*$/.test(str);
		case 'enNum': //英文+数字
			return /^[A-Za-z0-9]+$/.test(str);
		case 'sex': //性别盘点
			return /^[0-2]+$/.test(str);
		case 'english': //英文
			return /^[a-zA-Z]+$/.test(str);
		case 'chinese': //中文
			return /^[\u4E00-\u9FA5]+$/.test(str);
		case 'lower': //小写
			return /^[a-z]+$/.test(str);
		case 'upper': //大写
			return /^[A-Z]+$/.test(str);
		case 'HTML': //HTML标记
			return /<("[^"]*"|'[^']*'|[^'">])*>/.test(str);
		default:
			return true;
	}
}
/**
 * 数组排序
 */
export const sortKey = (array, key) => {
	return array.sort(function(a, b) {
	    let x = a[key];
	    let y = b[key];
	    return x > y ? -1 : x < y ? 1 : 0;
	});
}

/**
 * 数组求和
 */
export const arraySum = (arr) => {
	if(arr.length <= 0){
		return 0
	}
	return arr.reduce(function(prev, cur){ return parseInt(prev) + parseInt(cur);  })
}

export const haflYear = (date = null,num=6)  => {
	
  // 先获取当前时间
  var curDate = (new Date(date)).getTime();  // 1676023556660
  
  // 将半年的时间单位换算成毫秒
  var halfYear = ( 365 / 12 * num) * 24 * 3600 * 1000
  
  // 半年前的时间（毫秒单位）
  var pastResult = curDate - halfYear
  // 日期函数，定义起点为半年前
  var pastDate = new Date(pastResult)
  var pastYear = ''+pastDate.getFullYear()
  var pastMonth = pastDate.getMonth() + 1
  var pastDay = pastDate.getDate()
	if (pastMonth >= 1 && pastMonth <= 9) {
	  pastMonth = '0' + pastMonth
	}
	if (pastDay >= 0 && pastDay <= 9) {
	  pastDay = '0' + pastDay
	}
  var endDate = pastYear + '-' + pastMonth + '-' + pastDay
  return [pastYear,pastMonth,pastDay]
} 