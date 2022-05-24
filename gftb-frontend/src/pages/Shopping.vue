<template>
<div class="shopping-container">
  <el-table :data="tableData" style="width: 100%;">
    <el-table-column label="Id" prop="commodityId" />
    <el-table-column label="Pic" prop="commodityPic">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-image :src="scope.row.commodityPic" style="width: 80px"/>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="Name" prop="commodityName" />
    <el-table-column label="Price" prop="commodityPrice" />
    <el-table-column label="Number" prop="number" >
      <template #default="scope">
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
        >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: "Shopping",
  data(){
    return{
      tableData:[],
      userId:localStorage.userId
    }
  },methods:{
    handleDelete(a,b){
      console.log(a, b);
    }
  },
  mounted() {
    if(!localStorage.userId)
      this.$router.push("/account/login")
    this.$http.get(`http://localhost:8080/GFTB/api/shopping/${this.userId}`)
        .then((res)=>{
          this.tableData= res.data.data
        })
  }
}
</script>

<style scoped>
.shopping-container{
  width: 100%;
}
</style>