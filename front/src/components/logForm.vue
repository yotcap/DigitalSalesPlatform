<template>
  <div>
    <div class="login-form">
      <div class="g-form">
        <div class="g-form-line">
          <span class="g-form-label">用户名：</span>
          <div class="g-form-input">
            <input type="text" v-model="username" placeholder="请输入用户名">  
          </div>
          <span class="g-form-error"> {{ userErrors.errText }}</span>
        </div>
        <div class="g-form-line">
          <span class="g-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
          <div class="g-form-input">
            <input type="password" v-model="userpassword" placeholder="这里是密码">
          </div>
          <span class="g-form-error">{{ passwordErrors.errText }}</span>
        </div>
        <div class="g-form-line">
          <div class="g-form-btn">
            <a class="button" @click="onLogin">登录</a>
          </div>
        </div>
        {{ errText }}
      </div>
    </div>
  </div>
</template>

<script>
import md5 from 'js-md5'
import Qs from 'qs'

export default {
  data () {
    return {
      username: '',
      userpassword: '',
      errText: ''
    }
  },
  computed: {
    userErrors () {
      let errText, status

      /*if( !/@/g.test(this.username) ) {
        errText = 'no @'
      }else {
        errText = ''
        status = true
      }*/

      status = true
      //第一次进入则不检测输入格式
      if(!this.userFlag) {
        errText = ''
        this.userFlag = true
      }
      return { errText, status }
    },
    passwordErrors () {
      let errText, status
      if( this.userpassword.length < 6) {
        errText = 'too short'
      }else{
        errText = ''
        status = true
      }
      status = true
      if(!this.passwordFlag) {
        errText = ''
        this.passwordFlag = true
      }
      return { errText, status }
    }
  },
  methods: {
    onLogin () {
      var pass = md5(this.userpassword);
      var that = this;
      //检查输入格式
      if( !this.userErrors.status || !this.passwordErrors.status ) {
        this.errText = '输入错误，请检查'
      }else {
        this.errText = ''
        //将传过去的key字符串转换为query参数。Qs这个库是axios包含的，直接引入
        var data = Qs.stringify({username: this.username, password: pass})
        this.$http.post('/api/login.do',
          data,
          //不设置header，则参数以Request Payload形式传递，并不是我们所熟悉的form-data形式
          {headers:{'Content-Type':'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': '*'}}
        ).then(function (res) {
          //201 为用户名错误； 202 为密码错误
          if(res.data.code === 201 || res.data.code === 202) {
            that.errText = '用户名或密码错误'
          }else{
            that.errText = '登录成功'
            // sessionStorage.setItem("name", res.data.username)
            //返回数据到 layout.vue
            setTimeout(() => {
              that.$emit('login-success', res.data)
            }, 1000)
          }
        }).catch(function (err) {
          console.log(err)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>