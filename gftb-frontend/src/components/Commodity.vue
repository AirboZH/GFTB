<template>
      <el-card :body-style="{ padding: '0px' }" shadow="hover" style="margin: 20px 20px">
        <img
            :src="img"
            class="image"
            style="width:240px;height: 240px;object-fit: cover;"
        />
        <div style="padding: 14px;">
          <span>{{name}}</span>
          <span style="color: #FF0036;font-size: 24px;margin-left: 5px">￥{{price}}</span>
        </div>

      </el-card>
</template>

<script>
export default {
  name: "Commodity",
  props:{
    img:String,
    name:String,
    price:Number
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  display: block;
}
</style>