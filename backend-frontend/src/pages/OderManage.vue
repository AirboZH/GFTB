<template>
  <el-table :data="tableData.slice((page-1)*7,page*7)" style="width: 100%">
    <el-table-column label="Id" prop="commodityId" sortable width="180">
    </el-table-column>
    <el-table-column label="商品名">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.commodityName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="数量" prop="oderNumber" width="180">
    </el-table-column>
    <el-table-column label="地址" prop="oderAddress" width="280">
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope" >
        <el-dropdown size="small" split-button type="primary">
          {{ scope.row.oderStatus === 0 ? '已下单' : scope.row.oderStatus === 1 ? '已发货' : scope.row.oderStatus === 2 ? '已收货' : '已完成' }}
          <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="oderOperation(scope.row,0)">已下单</el-dropdown-item>
            <el-dropdown-item @click="oderOperation(scope.row,1)">已发货</el-dropdown-item>
            <el-dropdown-item @click="oderOperation(scope.row,2)">已收货</el-dropdown-item>
            <el-dropdown-item @click="oderOperation(scope.row,3)">已完成</el-dropdown-item>
          </el-dropdown-menu>
          </template>
        </el-dropdown>
      </template>
    </el-table-column>
  </el-table>

  <!--  分页-->
  <div style="display: flex;justify-content: center;margin-top: 20px">
    <el-pagination v-model:current-page="page" :page-size="7"
                   :total="tableData.length"
                   background
                   layout="prev, pager, next"/>

  </div>
</template>
<script setup>
import {Plus} from '@element-plus/icons-vue'</script>
<script>
import {ElMessage} from "element-plus";

export default {
  name: "ShoppingManage",
  data() {
    return {
      page: 1,
      tableData: [],
      dialogVisible: false,
      addDialogVisible: false,
      formLabelAlign: null,
    }
  },
  methods: {
    oderOperation(oder,status){
      this.$http.post(`http://localhost:8080/GFTB/api/oder/${oder.oderId}/${status}`)
          .then(res=>{
            console.log(res.data)
            if (res.data.code) {
              ElMessage({message: '操作成功！', type: 'success',})
              location.reload()
            }
          })
    },
    handleSave() {
      this.$http.patch(`http://localhost:8080/GFTB/api/commodity/${this.formLabelAlign.commodityId}`, this.formLabelAlign)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '更新成功！', type: 'success',})
              location.reload()
            }
          })
    },
    handleAvatarSuccess(response) {
      this.formLabelAlign.commodityPic = "/image/" + response.data
    },
    addHandleAvatarSuccess(response) {
      this.addCommodity.commodityPic = "/image/" + response.data
    },
    beforeAvatarUpload(rawFile) {
      if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
      }
      return true
    }
  },
  mounted() {
    if (!localStorage.getItem('token')) {
      this.$router.push('/account')
      return;
    }
    this.$http.get('http://localhost:8080/GFTB/api/oder').then((res) => {
      if (res.data.code !== 1)
        ElMessage.error('Oops, Can`t get the commodities.')
      for(let one of res.data.data){
        this.$http.get(`http://localhost:8080/GFTB/api/commodity/${one.commodityId}`)
            .then(res => {
              if (res.data.code !== 1)
                ElMessage.error('Oops, Can`t get the commodities.')
              one.commodityName = res.data.data.commodityName
              this.tableData.push(one)
            })
      }
    }).catch((e) => {
      ElMessage.error('Oops, Can`t connection to the server.')
      console.log(e);
    })
  }
}
</script>

<style scoped>
span {
  font-size: medium;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>