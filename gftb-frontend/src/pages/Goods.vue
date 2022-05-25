<template>
  <div v-if="commodityInfo!=null" class="goods-container">
    <el-row style="padding: 60px 0">
      <el-col :span="8" style="display: flex;justify-content: center">
        <img :src="commodityInfo.commodityPic" style="width: 320px"/>
      </el-col>
      <el-col :span="16" style="padding:10px 80px">
        <h1>{{ commodityInfo.commodityName }}</h1>
        <br>
        <h1 style="color: #FF0036">￥{{ commodityInfo.commodityPrice }}</h1>
        <br>
        <el-tag>{{ commodityInfo.commodityTag }}</el-tag>
        <br> <br> <br> <br>
        <el-button class="account-button" round type="danger" @click="shopping()">加入购物车</el-button>
      </el-col>
    </el-row>
    <el-divider/>
    <el-row style="padding: 60px 70px">
      <h3>{{ commodityInfo.commodityDetail }}</h3>
    </el-row>
  </div>
</template>

<script>

import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      commodityInfo: null
    }
  }, methods: {
    shopping() {
      let shoppingInfo = {
        "commodityId": this.$route.params.goodsId,
        "userId": localStorage.userId
      }
      this.$http.post('http://localhost:8080/GFTB/api/shopping', shoppingInfo)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '加入成功！', type: 'success',})
            }
          })
    }
  },
  mounted() {
    this.$http.get(`http://localhost:8080/GFTB/api/commodity/${this.$route.params.goodsId}`)
        .then((res) => {
          if (res.data.code) {
            this.commodityInfo = res.data.data
          } else {
            console.log("404");
            this.commodityInfo = '404'
          }

        })
  }
}
</script>

<style scoped>
</style>