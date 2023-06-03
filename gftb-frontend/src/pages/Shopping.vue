<template>
  <div class="shopping-container">
    <el-table :data="tableData" style="width: 100%;">
      <el-table-column label="CommodityId" prop="commodityId"/>
      <el-table-column label="Pic">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-image :src="scope.row.commodityPic" fit="cover"
                      style="width: 80px;height: 80px;overflow: hidden;"/>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Name">
        <template #default="scope">
          <a :href="`/goods/${scope.row.commodityId}`">{{ scope.row.commodityName }}</a>
        </template>
      </el-table-column>
      <el-table-column label="Price" prop="commodityPrice"/>
      <el-table-column label="Number">
        <template #default="scope">
          <el-input-number v-model="scope.row.commodityNum" :max="100" :min="1"
                           @change="handleChange(scope.row.commodityId)"/>
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
      <span style="font-size: 20px">已选购物品 </span>
      <span class="num">{{ sumNum }} </span>
      <span style="margin-right: 120px;font-size: 20px">件</span>
      <span style="font-size: 20px">总金额：</span>
      <span class="num" style="margin-right: 120px">{{ sumPrice }}</span>
      <el-button type="danger" round style="margin-right: 130px" @click="oder">购买</el-button>
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
  },
  computed: {
    sumPrice() {
      let sum = 0
      for (let oneData of this.tableData) {
        sum += oneData.commodityPrice * oneData.commodityNum
      }
      return this.sumPrice = sum.toFixed(2)
    },
    sumNum() {
      let sum = 0
      for (let oneData of this.tableData) {
        sum += oneData.commodityNum
      }
      return sum
    }
  },
  methods: {
    handleChange(id) {
      let shoppingInfo = {
        "commodityId": id,
        "userId": localStorage.userId
      }
      this.$http.post('http://localhost:8080/GFTB/api/shopping', shoppingInfo)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '数量修改成功！', type: 'success',})
            }
          })
    },
    handleDelete(a, b) {
      this.$http.delete(`http://localhost:8080/GFTB/api/shopping/${b.shoppingId}`)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '删除成功！', type: 'success',})
              this.tableData.splice(a, 1)
            }
          })
    },
    oder() {
      for (let oneData of this.tableData) {
        let oderInfo = {
          "commodityId": oneData.commodityId,
          "oderNumber": oneData.commodityNum
        }
        this.$http.post('http://localhost:8080/GFTB/api/oder', oderInfo)
            .then(res => {
              console.log(res.data)
            })
      }
      ElMessage({message: '下单成功！', type: 'success',})
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
<style scoped>
a, a:visited {
  color: var(--el-text-color-regular);
}

.shopping-container {
  width: 100%;
  padding-bottom: 60px;
}

.shopping-footer {
  position: fixed;
  bottom: 0;
  border-top: 1px solid var(--el-border-color);
  height: 60px;
  width: 1200px;
  background-color: white;
  z-index: 200;
  display: flex;
  justify-content: right;
  align-items: center;
}

.num {
  color: rgb(255, 0, 54);
  font-size: 30px;
}
</style>