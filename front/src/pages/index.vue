<template>
  <div class="index-wrap">
    <div class="index-left">
      <div class="index-left-block">
        <h2>全部产品</h2>
        <template v-for="product in productList">
          <h3>{{ product.title }}</h3>
          <ul>
            <li v-for="item in product.list">
              <a :href="item.url">{{ item.name }}</a>
              <span v-if="item.hot" class="tag-hot">HOT!</span>
            </li>
          </ul>
          <div v-if="!product.last" class="hr"></div>
        </template>
        <h3>最新消息</h3>
        <ul>
          <li v-for="news in newsList">
            <!-- a 的 new-item 样式的作用：文字过长时省略，并且不换行 -->
            <a :href="news.url" class="new-item" :title="news.name">{{ news.name }}</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="index-right">
      <!-- 图片轮播：slides为内容；invTime为时间；onChange为自定义事件，监听子组件 -->
      <slider-show :slides="slides" :invTime="invTime" @onChange="slideOnChange"></slider-show>
      <div v-for="(item, index) in boardList" class="index-board-item" :class="[{ 'line-last': index % 2 !== 0},  'index-board-' + item.id ]">
        <div class="index-board-item-inner">
          <h2>{{ item.title }}</h2>
          <p>{{ item.description }}</p>
          <div class="index-board-button">
            <a :href="item.href" class="button">查看</a>
          </div>
        </div>

      </div>      
    </div>

  </div>
</template>

<script>
import sliderShow from '../components/slidershow'

export default {
  components: {
    sliderShow
  },
  methods: {
    slideOnChange (index) {
      console.log("slide index: " + index)
    }
  },
  data () {
    return {
      //图片轮播时间间隔
      invTime: 3000,
      slides: [
        {
          src: require('../assets/slideShow/pic1.jpg'),
          title: 'image01',
          href: 'detail/analysis'
        },
        {
          src: require('../assets/slideShow/pic2.jpg'),
          title: 'image02',
          href: 'detail/count'
        },
        {
          src: require('../assets/slideShow/pic3.jpg'),
          title: 'image03',
          href: 'detail/forecast'
        },
        {
          src: require('../assets/slideShow/pic4.jpg'),
          title: 'image04',
          href: 'detail/publish'
        }
      ],
      boardList: [
        {
          title: '展板01',
          description: '这是展板01的描述',
          id: 'car',
          href: 'detail/analysis'
        },
        {
          title: '展板02',
          description: '这是展板02的描述',
          id: 'loud',
          href: 'detail/count'
        },
        {
          title: '展板03',
          description: '这是展板03的描述',
          id: 'earth',
          href: 'detail/forecast'
        },
        {
          title: '展板04',
          description: '这是展板04的描述',
          id: 'hill',
          href: 'detail/publish'
        },
        {
          title: '展板05',
          description: '这是展板05的描述',
          id: 'car',
          href: 'detail/analysis'
        }
      ],
      newsList: [
        {
          name: 'news1news1news1news1news1news1',
          url: 'http://www.yotcap.top'
        },
        {
          name: 'news2',
          url: 'http://www.yotcap.top'
        }
      ],
      productList: {
        pc: {
          title: 'PC产品',
          list: [
            {
              name: '数据统计',
              url: 'detail/count'
            },
            {
              name: '数据预测',
              url: 'detail/forecast',
              hot: true
            },
            {
              name: '流量分析',
              url: 'detail/analysis'
            },
            {
              name: '广告发布',
              url: 'detail/publish'
            }
          ]
        },
        app: {
          title: '手机应用类',
          last: true,
          list: [
            {
              name: 'app01',
              url: 'http://www.yotcap.top'
            },
            {
              name: 'app02',
              url: 'http://www.yotcap.top'
            },
            {
              name: 'app03',
              url: 'http://www.yotcap.top'
            },
            {
              name: 'app04',
              url: 'http://www.yotcap.top'
            }
          ]
        }
      }
    }
  }
}
</script>

<style scoped>
.index-wrap {
  width: 1200px;
  margin: 0 auto;
  overflow: hidden;
}
.index-left {
  float: left;
  width: 300px;
  text-align: left;
}
.index-right {
  float: left;
  width: 900px;
}
.index-left-block {
  margin: 15px;
  background: #fff;
  box-shadow: 0 0 1px #ddd;
}
.index-left-block .hr {
  margin-bottom: 20px;
}
.index-left-block h2 {
  background: #4fc08d;
  color: #fff;
  padding: 10px 15px;
  margin-bottom: 20px;
}
.index-left-block h3 {
  padding: 0 15px 5px 15px;
  font-weight: bold;
  color: #222;
}
.index-left-block ul {
  padding: 10px 15px;
}
.index-left-block li {
  padding: 5px;
}
.index-board-list {
  overflow: hidden;
}
.index-board-item {
  float: left;
  width: 400px;
  background: #fff;
  box-shadow: 0 0 1px #ddd;
  padding: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
}
.index-board-item-inner {
  min-height: 125px;
  padding-left: 120px;
}
.index-board-car .index-board-item-inner{
  background: url(../assets/images/1.png) no-repeat;
}
.index-board-loud .index-board-item-inner{
  background: url(../assets/images/2.png) no-repeat;
}
.index-board-earth .index-board-item-inner{
  background: url(../assets/images/3.png) no-repeat;
}
.index-board-hill .index-board-item-inner{
  background: url(../assets/images/4.png) no-repeat;
}
.index-board-item h2 {
  font-size: 18px;
  font-weight: bold;
  color: #000;
  margin-bottom: 15px;
}
.line-last {
  margin-right: 0;
}
.index-board-button {
  margin-top: 20px;
}
.lastest-news {
  min-height: 512px;
}
.tag-hot{
  background: red;
  color: #fff;
  font-size: 10px;
}
.new-item {
  display: inline-block;
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
