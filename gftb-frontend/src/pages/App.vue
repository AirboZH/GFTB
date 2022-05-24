<template>
  <MyHeader style="z-index: 1;background-color: white"></MyHeader>
  <router-view style="padding-top: 60px;width: 1200px"/>
</template>
<script>
export default {
  name:"App"
}
</script>
<script setup>
import MyHeader from "../components/MyHeader.vue";
</script>
<style>

</style>