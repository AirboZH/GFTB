<template>
  <div class="shopping-container">
    <el-table :data="tableData" style="width: 100%;">
      <el-table-column label="CommodityId" prop="commodityId"/>
      <el-table-column label="Pic" prop="commodityPic">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-image :src="scope.row.commodityPic" fit="cover" style="width: 80px;height: 80px;overflow: hidden;"/>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Name" prop="commodityName"/>
      <el-table-column label="Price" prop="commodityPrice"/>
      <el-table-column label="Number">
        <template #default="scope">
        <el-input-number v-model="scope.row.commodityNum" :max="10" :min="1" @change="handleChange"/>
        </template>
      </el-table-column>
      <el-table-column label="Delete">
        <template #default="scope">
          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
          >Delete
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
<div class="shopping-footer">

</div>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Shopping",
  data() {
    return {
      tableData: [],
      userId: localStorage.userId,
    }
  }, methods: {
    handleDelete(a, b) {
      this.$http.delete(`http://localhost:8080/GFTB/api/shopping/${b.shoppingId}`)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '删除成功！', type: 'success',})
              this.tableData.splice(a, 1)
            }

          })
    }
  },
  mounted() {
    if (!localStorage.userId)
      this.$router.push("/account/login")
    this.$http.get(`http://localhost:8080/GFTB/api/shopping/${this.userId}`)
        .then((res) => {
          if (!res.data.code)
            ElMessage({message: '先加入一些商品到购物车吧~', type: 'warning',})
          this.tableData = res.data.data
        }).catch((err) => {
      if (err.response.status !== 404)
        ElMessage({message: '查询数据失败', type: 'error',})
    })
  }
}
</script>
<script setup>

</script>
<style scoped>
.shopping-container {
  width: 100%;
}
.shopping-footer{

}
</style>