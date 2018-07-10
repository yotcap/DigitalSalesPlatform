<template>
  <div class="chooser-component">
        <ul class="chooser-list">
          <li
          v-for="(item, index) in mediumList"
          @click="toggleChoice(index)"
          :title="item.label"
          :class="{active: checkAction(index) }"
          >{{ item.label }}</li>
        </ul>
      </div>
    </div>
</template>

<script>
export default {
  props: {
    mediumList: {
      type: Array,
      default: [{
        label: 'test',
        value: 0
      }]
    }
  },
  data () {
    return {
      nowIndex: [0]
    }
  },
  methods: {
    toggleChoice (index) {
      //如果不存在，则添加
      if(this.nowIndex.indexOf(index) === -1) {
        this.nowIndex.push(index)
      }else {
        //如果存在，则找到位置并删除
        this.nowIndex.splice(this.nowIndex.indexOf(index), 1)
      }
      this.$emit('on-change', this.nowIndex)
    },
    checkAction (index) {
      return this.nowIndex.indexOf(index) !== -1
    }
  }
}
</script>

<style scoped>
.chooser-component {
  position: relative;
  display: inline-block;
}
.chooser-list li{
  display: inline-block;
  border: 1px solid #e3e3e3;
  height: 25px;
  line-height: 25px;
  padding: 0 8px;
  margin-right: 5px;
  border-radius: 3px;
  text-align: center;
  cursor: pointer;
}
.chooser-list li.active {
  border-color: #4fc08d;
  background: #4fc08d;
  color: #fff;
}
</style>