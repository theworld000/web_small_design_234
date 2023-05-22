<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "app",
  mounted() {
    this.$router.push("/login")
  },
}
</script>

<style>
#app{
  min-height: 100vh;
}
</style>
