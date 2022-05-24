<template>
  <div>
    <el-carousel height="390px" trigger="click">
      <el-carousel-item v-for="link in links" :key="links">
        <el-image :src="link" fit="cover" style="width: 100%" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default{
  name: "carousel",
  data(){
    return{
      links:[
          "https://img.alicdn.com/imgextra/i3/6000000005682/O1CN01JcsUaP1rqPCe6tkKn_!!6000000005682-2-octopus.png",
          "https://img.alicdn.com/imgextra/i3/6000000001767/O1CN01bhatde1OvKbVoG4yV_!!6000000001767-0-octopus.jpg",
          "https://img.alicdn.com/imgextra/i4/6000000002220/O1CN01zF4rit1SGo1HNQ8r2_!!6000000002220-0-octopus.jpg",
          "https://img.alicdn.com/imgextra/i4/6000000006232/O1CN01j2UxU11vuJ2WhPuzX_!!6000000006232-2-octopus.png",
          "https://img.alicdn.com/imgextra/i3/6000000002280/O1CN01RR4L1s1SiHmcYknGP_!!6000000002280-0-octopus.jpg"
      ]
    }
  }
}
</script>

<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}
</style>