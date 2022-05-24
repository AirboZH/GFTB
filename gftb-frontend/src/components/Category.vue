<template>
  <el-card class="box-card" shadow="never">
    <div v-for="category in categories" :key="category" class="text item">
      <a href="#">{{category}}</a>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "Category",
  data(){
    return{
      categories:[
          "服装",
          "玩具",
          "人物",
          "饮料",
          "家具"

      ]
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 20px;
  font-weight: bold;
}
a{
  color: black;
  text-decoration: none;
}
a:hover{
  color: #FF0036;
}
.item {
  padding: 18px 20px;
}

.box-card {
  width: 480px;
  width: 95%;
  height: 95%;
}
</style>