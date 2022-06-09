<template>
  <MyHeader style="z-index: 20;background-color: white"></MyHeader>
  <router-view style="padding-top: 60px;width: 1200px"/>
</template>
<script>
export default {
  name:"App",
  mounted() {
    if(localStorage.getItem('token')){
      this.$http.get(`http://localhost:8080/GFTB/api/account/${localStorage.getItem('token')}`)
          .then(res=>{
            localStorage.setItem('userId',res.data.data.userId)
            localStorage.setItem('userName',res.data.data.userName)
          })
          .catch(e=>{
            localStorage.clear();
            this.$router.push('/')
          })
    }
  }
}
</script>
<script setup>
import MyHeader from '../components/MyHeader.vue'
</script>
<style>

</style>