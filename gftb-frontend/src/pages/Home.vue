<template>
  <div class="first-container">
    <el-row>
      <el-col :span="5" class="col" style="justify-content: flex-start;">
        <Category/>
      </el-col>
      <el-col :span="14" style="height: 500px;margin-top:-50px">
        <div class="mt-4">
          <el-input
              v-model="searchInput"
              class="input-with-select"
              placeholder="Please input"
              size="large"
          >
            <template #prepend>
              <el-button :icon="Search" />
            </template>
          </el-input>
        </div>
        <Carousel style="margin-top: 35px"/>
      </el-col>
      <el-col :span="5" class="col" style="justify-content: flex-end">
        <Account/>
      </el-col>
    </el-row>
    <el-row style="display: flex;justify-content: center;padding-top: 30px">
      <div v-if="commodities==null">
        <el-empty description="description" />
      </div>
      <div v-for="commodity of commodities">
        <a :href="`/goods/${commodity.commodityId}`">
          <Commodity :img="commodity.commodityPic" :name="commodity.commodityName" :price="commodity.commodityPrice"/>
        </a>
      </div>
    </el-row>
  </div>
</template>
<script>
import {ElMessage} from "element-plus";

export default {
  data(){
    return{
      searchInput:"",
      commodities:[]
    }
  },mounted() {
    this.$http.get('http://localhost:8080/GFTB/api/commodity').
    then((res)=>{
      if(res.data.code !== 1)
        ElMessage.error('Oops, Can`t get the commodities.')
      this.commodities = res.data.data
    }).catch((e)=>{
      ElMessage.error('Oops, Can`t connection to the server.')
      console.log(e);
    })
  }
}
</script>
<script setup>
import Carousel from "../components/Carousel.vue";
import Category from "../components/Category.vue";
import Account from "../components/AccountInfo.vue";
import Commodity from "../components/Commodity.vue"
import { Search } from '@element-plus/icons-vue'
</script>

<style>
*{margin: 0;padding: 0}
body{
  display: flex;
  justify-content: center;
  min-width: 1200px;
}
a{
  text-decoration: none;
}
.first-container{
  width: 1200px;
  margin-top: 85px;
}
.col{
  display: flex;
  align-items: center;
}
</style>
