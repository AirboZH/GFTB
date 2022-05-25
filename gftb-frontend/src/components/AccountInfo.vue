<template>
  <el-card class="box-card" shadow="never" style="display: flex;flex-direction: column;justify-content: center">
    <div v-if="accountStatus" class="account-info">
      <el-icon :size="90" color="#409EFC">
        <User/>
      </el-icon>
      <div class="account-info-text">欢迎来到FGTB</div>
      <el-button class="account-button" plain type="primary" @click="goto('account/login')" >登录</el-button>
      <br>
      <el-button class="account-button" plain type="primary" @click="goto('account/register')">注册</el-button>
    </div>
    <div v-else class="account-info">
      <img src="https://www.airbozh.cn/images/myavatar.jpg" style="border-radius: 50%;width: 50%" >
      <div class="username account-info-text">欢迎回来：{{userName}}</div>
      <el-button class="account-button" plain type="primary" @click="goto('shopping')">购物车</el-button>
      <br>
      <el-button class="account-button" plain type="primary" @click="logOut()">退出</el-button>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "Account",
  data(){
    return{
      userName:"Airbo",
      accountStatus:false
    }
  },
  methods:{
    goto(url){
      this.$router.push("/"+url)
    },
    logOut(){
      localStorage.clear()
      location.reload()
    }
  },mounted() {
    if(!localStorage.getItem('userName'))
      this.accountStatus = true
  }
}
</script>
<script setup>
import { User } from '@element-plus/icons-vue'
</script>
<style scoped>
.box-card {
  width: 95%;
  height: 95%;
}
.account-info{
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
.account-info-text{
  margin: 20px 20px;
  font-family: "Microsoft YaHei UI",serif;
  font-weight: bolder;
  font-size: 20px;
}
.account-button{
  height: 40px;
  width: 120px;
}
</style>