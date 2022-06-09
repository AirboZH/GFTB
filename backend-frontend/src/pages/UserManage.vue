<template>
<!--  表格-->
  <el-table :data="tableData.slice((page-1)*pageSize,page*pageSize)" style="width: 100%">
    <el-table-column label="Id" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.userId }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="用户名" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.userName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="标签" width="180">
      <template #default="scope">
        <el-tag v-if="scope.row.role === 1">管理员</el-tag>
        <el-tag v-else>用户</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="余额" width="100">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.money }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="地址">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.address }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column  prop="updateDate"  :formatter="dateFormat" label="更新时间">
    </el-table-column>
    <el-table-column prop="createDate"  :formatter="dateFormat"   label="注册时间">
    </el-table-column>

    <el-table-column label="操作">
    <template #default="scope">
      <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
      >Edit
      </el-button
      >
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

<!--  分页-->
  <div style="display: flex;justify-content: center;margin-top: 20px">
    <el-pagination v-model:current-page="page" :page-size="pageSize"
                   :total="tableData.length"
                   background
                   layout="prev, pager, next"/>

  </div>

<!--  对话框-->
  <el-dialog
      v-model="dialogVisible"
      :before-close="handleClose"
      title="编辑用户"
      width="30%"
  >
    <el-form
        :label-position="'top'"
        :model="formLabelAlign"
        label-width="100px"
        style="max-width: 460px"
    >
      <el-form-item label="Id">
        <el-input v-model="formLabelAlign.userId" disabled/>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="formLabelAlign.userName"/>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="formLabelAlign.address"/>
      </el-form-item>
      <el-form-item label="余额">
        <el-input v-model="formLabelAlign.money"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleSave"
        >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "UserManage",
  data() {
    return {
      page: 1,
      pageSize:18,
      tableData: [],
      dialogVisible: false,
      formLabelAlign: null
    }
  },
  methods: {
    handleEdit(a) {
      this.formLabelAlign = this.tableData[(this.page-1)*7 + a]
      this.dialogVisible = true
    },
    handleSave() {
      this.$http.patch(`http://localhost:8080/GFTB/api/user/${this.formLabelAlign.userId}`, this.formLabelAlign)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '更新成功！', type: 'success',})
              location.reload()
            }
          })
    },
    handleDelete(a, b) {
      this.$http.delete(`http://localhost:8080/GFTB/api/user/${b.userId}`)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '删除成功！', type: 'success',})
              location.reload()
            }
          })
    },
    dateFormat(row, column) {
      const data = row[column.property]
      if(data === undefined) {return ''}
      return this.$moment(data).format("YYYY-MM-DD HH:mm:ss");
    }
  },
  mounted() {
    if(!localStorage.getItem('token')) {
      this.$router.push('/account')
      return;
    }
    this.$http.get('http://localhost:8080/GFTB/api/user').then((res) => {
      if (res.data.code !== 1)
        ElMessage.error('Oops, Can`t get the commodities.')
      this.tableData = res.data.data
    }).catch((e) => {
      ElMessage.error('Oops, Can`t connection to the server.')
      console.log(e);
    })
  },

}
</script>

<style scoped>
span {
  font-size: medium;
}
</style>