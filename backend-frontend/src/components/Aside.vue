<template>
  <el-menu
      :router="true"
      class="el-menu-vertical"
      default-active="/"
      style="height: 100%"
  >
    <el-menu-item index="/">
      <el-icon>
        <location/>
      </el-icon>
      <span>商品管理</span>
    </el-menu-item>
    <el-menu-item index="/userManage">
      <el-icon>
        <Menu/>
      </el-icon>
      <span>用户管理</span>
    </el-menu-item>
    <el-menu-item index="/oderManage">
      <el-icon>
        <document/>
      </el-icon>
      <span>订单管理</span>
    </el-menu-item>
    <el-menu-item index="/account" :disabled="isLogin">
      <el-icon>
        <Avatar/>
      </el-icon>
      <span>账户登录</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return{
      isLogin:false
    }
  },
  mounted() {
    if(localStorage.getItem("token")){
      this.isLogin = true
    }
  }
}
</script>
<script setup>
import {Location, Document, Menu, Avatar} from '@element-plus/icons-vue'
</script>
<style scoped>
</style>