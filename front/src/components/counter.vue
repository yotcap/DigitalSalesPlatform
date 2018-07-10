<template>
  <div class="counter-component">
    <div class="counter-btn" @click="minus"> - </div>
    <div class="counter-show">
      <input type="text" v-model="number" @keyup="fixNumber">
    </div>
    <div class="counter-btn" @click="add"> + </div>
  </div>
</template>

<script>
export default {
  props: {
    max: {
      type: Number,
      default: 20
    },
    min: {
      type: Number,
      default: 1
    }
  },
  watch: {
    number () {
      this.$emit('on-change', this.number)
    }
  },
  methods: {
    fixNumber () {
      let temp = 0
      if(typeof this.number === 'string') {
        //如果输入的为字符，则将非数字去掉，最后将字符串转换为数字
        temp = Number(this.number.replace(/\D/g, ''))
      }else {
        temp = this.number
      }
      if( temp > this.max) {
        //如果数字过大，则取最大值
        temp = this.max
      }else if( temp < this.min ) {
        //如果过小，则取最小值
        temp = this.min
      }
      this.number = temp
    },
    minus () {
      if(this.number <= this.min) {
        return
      }
      this.number --
    },
    add () {
      if(this.number >= this.max) {
        return
      }
      this.number ++
    }
  },
  data () {
    return {
      number: this.min
    }
  }
}
</script>

<style scoped>
.counter-component {
  position: relative;
  display: inline-block;
  overflow: hidden;
  vertical-align: middle;
}
.counter-show {
  float: left;
}
.counter-show input {
  border: none;
  border-top: 1px solid #e3e3e3;
  border-bottom: 1px solid #e3e3e3;
  height: 23px;
  line-height: 23px;
  width: 30px;
  outline: none;
  text-indent: 4px;
}
.counter-btn {
  border: 1px solid #e3e3e3;
  float: left;
  height: 25px;
  line-height: 25px;
  width: 25px;
  text-align: center;
  cursor: pointer;
}
.counter-btn:hover {
  border-color: #4fc08d;
  background: #4fc08d;
  color: #fff;
}

</style>