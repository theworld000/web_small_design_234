<template>
<div>
  <h1>这是一个div</h1>
  <img decoding="async" alt="Vue logo" src="../assets/logo.png" />
  <p>{{ msg }}</p>
  <HelloWorld msg="欢迎来到快乐老家！" text-align:center></HelloWorld>
  <input v-model="msg">
  <ol>
    <li v-for="(web, index) in webs">
      {{index+1}} 名称： {{web.name}} 链接 {{web.url}}
    </li>
  </ol>
  <button @click="showtime()" type="button">xxxxx</button>


</div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: "GameView",
  components: {
    HelloWorld
  },
  data() {
    return {
      msg: "好无聊 我是垃圾",
      webs: [
        {name: "百度", url: "www.baidu.com"},
        {name: "腾讯", url: "www.tenxun.com"},
        {name: "Acwing", url: "www.acwing.com"}
      ],
      time: Date.parse(new Date()),
      startTime,
      endTime,
      yy_mm_dd
    }
  },
  methods: {
    showtime(){
      this.startTime = this.getNowDate() + ' ' + '00:00:00' //结果： 当前日期0点
      this.endTime = this.getNowDate() + ' ' + '23:59:59'    //结果： 当前日期最后一秒
      const timeone = new Date();
      const year = timeone.getFullYear()
      alert(`当前时间为： ${this.getNowDate()}`)
    },
    getNowDate() {
      const timeOne = new Date()
      const year = timeOne.getFullYear()
      let month = timeOne.getMonth() + 1
      let day = timeOne.getDate()
      month = month < 10 ? '0' + month : month
      day = day < 10 ? '0' + day : day
      const NOW_MONTHS_AGO = `${year}-${month}-${day}`
      return NOW_MONTHS_AGO
    }
  }
}
</script>

<style scoped>

</style>
