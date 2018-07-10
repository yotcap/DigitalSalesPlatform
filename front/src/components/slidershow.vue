<template>
  <div>
    <div class="slide-show" @mouseover="clearInv" @mouseout="runInterval">
      <div class="slide-img">
        <a :href="slides[nowIndex].href">
          <!-- <img :src="slides[nowIndex].src"> -->
          <transition name="slide-trans">
            <img v-if="isShow" :src="slides[nowIndex].src" >
          </transition>
          <transition name="slide-trans-old">
            <img v-if="!isShow" :src="slides[nowIndex].src" >
          </transition>
        </a>
      </div>
      <h2>{{ slides[nowIndex].title }}</h2>
      <ul class="slide-pages">
        <li @click="goto(preIndex)">&lt;</li>
        <li v-for="(item, index) in slides" @click="goto(index)" :class="{ on: index === nowIndex }">
          {{ index + 1 }}
        </li>
        <li @click="goto(nextIndex)">&gt;</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    slides: {
      type: Array,
      default: []
    },
    invTime: {
      type: Number,
      default: 1000
    }

  },
  data () {
    return {
      nowIndex: 0,
      isShow: true
    }
  },
  computed: {
    preIndex () {
      if(this.nowIndex === 0){
        return this.slides.length - 1
      }else {
        return this.nowIndex - 1
      }
    },
    nextIndex () {
      if(this.nowIndex === this.slides.length - 1){
        return 0
      }else {
        return this.nowIndex + 1
      }
    }
  },
  methods: {
    goto (index) {
      this.isShow = false
      setTimeout(() => {
        this.isShow = true
        this.nowIndex = index
        //回调给监听器
        //this.$emit('onChange', index)
      }, 10)
    },

    /****简单的方法实现
      goPrevious () {
      if(this.nowIndex === 0){
        this.nowIndex = this.slides.length - 1
      }else{
        this.nowIndex -= 1
      }
    },
    goNext () {
      if(this.nowIndex === this.slides.length - 1) {
        this.nowIndex = 0
      }else{
        this.nowIndex += 1
      }
    }*/
    runInterval () {
      this.invId = setInterval(() => {
        this.goto(this.nextIndex)
      }, this.invTime)
    },
    clearInv () {
      clearInterval(this.invId)
    }
  },
  mounted () {
    this.runInterval()
  }
}
</script>

<style scoped>

.slide-trans-enter-active {
  transition: all .5s;
}
.slide-trans-enter {
  transform: translateX(900px);
}
.slide-trans-old-leave-active {
  transition: all .5s;
  transform: translateX(-900px);
}
.slide-show {
  position: relative;
  margin: 15px 15px 15px 0;
  width: 900px;
  height: 500px;
  overflow: hidden;
}
.slide-show h2 {
  position: absolute;
  width: 100%;
  height: 100%;
  color: #fff;
  background: #000;
  opacity: .5;
  bottom: 0;
  height: 30px;
  line-height: 30px;
  text-align: left;
  padding-left: 15px;
}
.slide-img {
  width: 100%;
}
.slide-img img {
  width: 100%;
  position: absolute;
  top: 0;
}
.slide-pages {
  position: absolute;
  bottom: 5px;
  right: 25px;
}
.slide-pages li {
  display: inline-block;
  height: 25px;
  padding: 0 20px;
  cursor: pointer;
  color: #fff;
  line-height: 25px;
}
.on {
  background: #4fc08d;
  color: #000;
}
</style>
