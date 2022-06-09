<template>
  <div class="common-layout">
    <el-container style="height: 100%">
      <el-header>
        <AdminHeader/>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Aside/>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "../components/Aside.vue";
import AdminHeader from "../components/AdminHeader.vue";

export default {
  name: "App",
  components: {AdminHeader, Aside},
  mounted() {
    this.$http.get(`http://localhost:8080/GFTB/api/account/${localStorage.getItem('token')}`)
        .then(res => {
          localStorage.setItem('userId', res.data.data.userId)
          localStorage.setItem('userName', res.data.data.userName)
        })
        .catch(e => {
          localStorage.clear();
          this.$router.push('/account')
        })
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0
}

.el-header {
  background-color: var(--el-color-primary);
  border-bottom: 1px solid var(--el-border-color);
  height: 55px;
}

.common-layout {
  height: 100vh;
}
</style>