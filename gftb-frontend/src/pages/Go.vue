<template>
  <div v-if="commodityInfo!=null" class="goods-container">
    <p>{{commodityInfo.commodityName}}</p>
    <p>{{commodityInfo.commodityTag}}</p>
    <p>{{commodityInfo.commodityDetail}}</p>
    <p>{{commodityInfo.commodityPrice}}</p>
    <img :src="commodityInfo.commodityPic"/>
    <br>
  </div>

</template>

<script>

export default {
  data(){
    return{
      commodityInfo:null
    }
  },
  mounted() {
    this.$http.get(`http://localhost:8080/GFTB/api/commodity/${this.$route.params.goodsId}`)
        .then((res)=>{
          if(res.data.code){
            this.commodityInfo = res.data.data
          }else{
            console.log("404");
            this.commodityInfo = '404'
          }

        })
  }
}
</script>

<style scoped>
</style>