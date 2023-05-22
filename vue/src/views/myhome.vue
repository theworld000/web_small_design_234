<template>
  <div>
    {{msg}}
    <table>
      <tr>
        <td class="my-big" style="text-align: center">序号</td>
        <td class="my-big">名称</td>
      </tr>
      <tr v-for="it in books">
          <td class="my-big">{{it.id}}</td>
          <td class="my-big">{{it.context}}</td>
      </tr>
    </table>

    <div id="app">
      <div id="nav" >
        <router-link to="/game">Game</router-link>
        <router-link to="/about">About</router-link>
      </div>
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "myhome",
  data() {
    return {
      msg: "一个无聊的网页",
      books: [
        {
          id: 1,
          context: "西游记"
        },{
          id: 5,
          context: "水浒传"
        }
      ]
    }
  }
}
</script>

<style scoped>
.my-big{
  font-size: large
}
</style>
