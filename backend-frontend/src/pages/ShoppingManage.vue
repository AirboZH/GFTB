<template>
  <div class="add-commodity">
    <el-button type="primary" @click="add">新增商品</el-button>
  </div>
  <el-table :data="tableData.slice((page-1)*7,page*7)" style="width: 100%">
    <el-table-column label="Id" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.commodityId }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="图片" width="280">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-image :src="scope.row.commodityPic" fit="cover" style="width: 180px;height: 180px"/>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="商品名">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.commodityName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="标签" width="180">
      <template #default="scope">
        <el-tag>{{ scope.row.commodityTag }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="价格" width="280">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.commodityPrice }}</span>
        </div>
      </template>
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
            @click="handleDelete(scope.row.commodityId,)"
        >Delete
        </el-button
        >
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

  <!--  对话框-->
  <el-dialog
      v-model="dialogVisible"
      title="编辑商品"
      width="30%"
  >
    <el-form
        :label-position="'top'"
        :model="formLabelAlign"
        label-width="100px"
        style="max-width: 460px"
    >
      <el-form-item label="商品名">
        <el-input v-model="formLabelAlign.commodityName"/>
      </el-form-item>
      <el-form-item label="图片">
        <div class="img-upload" style="padding-left: 30px">
          <el-upload
              :before-upload="beforeAvatarUpload"
              :on-success="handleAvatarSuccess"
              :show-file-list="false"
              action="http://localhost:8080/GFTB/api/commodity/upload"
              class="avatar-uploader"
          >
            <img v-if="formLabelAlign.commodityPic" :src="formLabelAlign.commodityPic" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item label="标签">
        <el-select v-model="formLabelAlign.commodityTag" placeholder="please select your zone">
          <el-option label="服装" value="服装" />
          <el-option label="玩具" value="玩具" />
          <el-option label="人物" value="人物" />
          <el-option label="饮料" value="饮料" />
          <el-option label="家具" value="家具" />
        </el-select>
      </el-form-item>
      <el-form-item label="价格">
        <el-input v-model="formLabelAlign.commodityPrice"/>
      </el-form-item>
      <el-form-item label="详情">
        <el-input v-model="formLabelAlign.commodityDetail"/>
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

  <el-dialog
      v-model="addDialogVisible"
      title="新增商品"
      width="30%"
  >
    <el-form
        :label-position="'top'"
        :model="addCommodity"
        label-width="100px"
        style="max-width: 460px"
    >
      <el-form-item label="商品名">
        <el-input v-model="addCommodity.commodityName"/>
      </el-form-item>
      <el-form-item label="图片">
        <div class="img-upload" style="padding-left: 30px">
          <el-upload
              :before-upload="beforeAvatarUpload"
              :on-success="addHandleAvatarSuccess"
              :show-file-list="false"
              action="http://localhost:8080/GFTB/api/commodity/upload"
              class="avatar-uploader"
          >
            <img v-if="addCommodity.commodityPic" :src="addCommodity.commodityPic" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item label="标签">
        <el-select v-model="addCommodity.commodityTag" placeholder="please select your zone">
          <el-option label="服装" value="服装" />
          <el-option label="玩具" value="玩具" />
          <el-option label="人物" value="人物" />
          <el-option label="饮料" value="饮料" />
          <el-option label="家具" value="家具" />
        </el-select>
      </el-form-item>
      <el-form-item label="价格">
        <el-input v-model="addCommodity.commodityPrice"/>
      </el-form-item>
      <el-form-item label="详情">
        <el-input v-model="addCommodity.commodityDetail"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="saveAdd"
        >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
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
      addCommodity: {},
    }
  },
  methods: {
    add() {
      this.addCommodity = {}
      this.addDialogVisible = true
    },
    saveAdd() {
      this.$http.post(`http://localhost:8080/GFTB/api/commodity`, this.addCommodity)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '新增成功！', type: 'success'})
              location.reload()
            }
          })
    },
    handleEdit(a) {
      this.formLabelAlign = this.tableData[(this.page-1)*7 + a]
      this.dialogVisible = true
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
    handleDelete(commodityId) {
      this.$http.delete(`http://localhost:8080/GFTB/api/commodity/${commodityId}`)
          .then((res) => {
            if (res.data.code) {
              ElMessage({message: '删除成功！', type: 'success',})
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
    this.$http.get('http://localhost:8080/GFTB/api/commodity').then((res) => {
      if (res.data.code !== 1)
        ElMessage.error('Oops, Can`t get the commodities.')
      this.tableData = res.data.data
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