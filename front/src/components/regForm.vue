<template>
  <div>
    <div class="g-form">
    	<div class="g-form-line">
    		<span class="g-form-label">用户名：</span>
    		<div class="g-form-input">
    			<input type="text" v-model="username" placeholder="请输入用户名" >
    		</div>
    		<span class="g-form-error"> {{ userErrors.errText }}</span>
    	</div>
    	<div class="g-form-line">
    		<div class="g-form-label">密码：</div>
    		<div class="g-form-input">
    			<input type="password" v-model="password" placeholder="请输入密码">
    		</div>
    		<span class="g-form-error"> {{ passErrors.errText }}</span>
    	</div>
    	<div class="g-form-line">
    		<div class="g-form-label">邮箱：</div>
    		<div class="g-form-input">
    			<input type="text" v-model="email" placeholder="请输入邮箱">
    		</div>
    		<span class="g-form-error"> {{ emailErrors.errText }}</span>
    	</div>
    	<div class="g-form-line">
    		<div class="g-form-label">手机：</div>
    		<div class="g-form-input">
    			<input type="type" v-model="phone" placeholder="请输入正确的手机号码">
    		</div>
    		<div class="btn-code">
            <a class="button" @click="onCode">获取验证码</a>
    		</div>
    		<span class="g-form-error"> {{ phoneErrors.errText }}</span>
    	</div>
      <div class="g-form-line">
        <div class="g-form-label">验证码：</div>
        <div class="g-form-input">
          <input type="text" v-model="verificationCode" placeholder="请输入验证码" >
        </div>
        <span class="g-form-error">{{ codeErrors.errText }}</span>
      </div>
    	<div class="g-form-line">
    		<div calss="g-form-btn">
    			<a class="button" @click="onReg">注册</a>
    		</div>
    	</div>
    	{{ errText }}
    </div>
  </div>
</template>

<script>
import md5 from 'js-md5'
import Qs from 'qs'

export default {
  methods: {
    onReg () {
      if( !this.userErrors.status || !this.passErrors.status || !this.emailErrors.status || !this.phoneErrors.status || !this.codeErrors.status) {
        this.errText = '输入错误，请检查'
      }else if( this.code !== this.verificationCode ){
        console.log(this.code+" "+this.verificationCode)
        this.errText = '请输入正确的验证码'
      }else{
        this.errText = ''
        let data = Qs.stringify({
          username: this.username,
          password: this.password,
          email: this.email,
          verCode: this.verificationCode
        })
        this.$http.post('/api/register.do', 
          data, 
          {headers:{'Content-Type':'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': '*'}}
          ).then(function (res) {
            console.log(res)
          }).catch(function (err) {
            console.log(err)
          })
      }
    },
    onCode () {
      var that = this;
      this.errText = ''
      if( !this.phoneErrors.status ){
        this.phoneErrors.errText = '号码错误'
      }else{
        this.$http.post('/api/verification.do',
          Qs.stringify({mobile: this.phone}),
          {headers:{'Content-Type':'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': '*'}}
        ).then(function(res){
          if(res.data.code === 208){
            that.errText = '短信发送失败'
          }else if(res.data.code === 0){
            that.errText = '短信发送成功，请注意查收'
            that.code = res.data.data
          }
        }).catch(function(err){
          console.log(err)
        })
      }
    }
  },
  data () {
    return {
    	username: '',
    	password: '',
    	errText: '',
      email: '',
      phone: '',
      //用户输入的验证码
      verificationCode: '',
      code: 0
    }
  },
  computed: {
  	userErrors () {
  		let errText, status
      if(this.username.length < 6) {
        errText = 'too short'
      }else if( !/^[a-zA-Z]/g.test(this.username)){
        errText = '开头必须为字母'
      }else{
        errText = ''
        status = true
      }
  		if(!this.userFlag) {
        errText = ''
        this.userFlag = true
      }
  		return { errText, status }
  	},
    passErrors () {
      let errText, status
      if(this.password.length < 6){
        errText = 'too short'
      }else{
        errText = ''
        status = true
      }
      if(!this.passFlag){
        errText = ''
        this.passFlag = true
      }
      return { errText, status }
    },
    emailErrors () {
      let errText, status
      if( !/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/g.test(this.email) ) {
        errText = '错误的邮箱'
      }else {
        errText = ''
        status = true
      }
      if(!this.emailFlag){
        errText = ''
        this.emailFlag = true
      }
      return { errText, status }
    },
    phoneErrors () {
      let errText, status
      if( !/^1\d{10}$/.test(this.phone) ) {
        errText = '格式错误'
      }else{
        errText = ''
        status = true
      }
      if( !this.phoneFlag ){
        errText = ''
        this.phoneFlag = true
      }
      return { errText, status }
    },
    codeErrors () {
      let errText, status
      if( this.verificationCode.length !== 6 ) {
        errText = 'wrong!'
      }else{
        errText = ''
        status = true
      }
      if( !this.codeFlag ){
        errText = ''
        this.codeFlag = true
      }
      return { errText, status }
    }
  } 
}
</script>

<style scoped>
.btn-code{
	display: inline-block;
	padding-left: 20px;
}
</style>