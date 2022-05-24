<template>
  <div class="account-container"></div>

      <el-card class="box-card" shadow="hover" style="width: 600px">
        <el-tabs v-model="activeName" class="demo-tabs">
          <el-tab-pane label="Login" name="login">
            <div class="tab-pane-box">
              <el-input
                  v-model="loginEmail"
                  placeholder="Please input username"
                  type="text"
              />
              <el-input
                  v-model="loginPasswd"
                  placeholder="Please input password"
                  show-password
                  type="password"
              />
              <el-button :icon="CircleCheck" class="submit-button" type="primary" @click="loginRequest">Login
              </el-button>
            </div>

          </el-tab-pane>
          <el-tab-pane label="Register" name="register">
            <div class="tab-pane-box">
              <el-input
                  v-model="registerUsername"
                  placeholder="Please input Username"
                  type="text"
              />

              <el-input
                  v-model="registerPasswd"
                  placeholder="Please input password"
                  show-password
                  type="password"
              />
              <el-button :disabled="registerDisable" :icon="CirclePlusFilled" class="submit-button"
                         type="primary" @click="requestRequest">Register
              </el-button>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
</template>

<script>
import {ElMessage} from 'element-plus'

export default {
  name: "Account",
  data() {
    return {
      activeName: "login",
      loginEmail: "",
      loginPasswd: "",
      registerUsername: "",
      registerPasswd: "",
      captcha: "",
      registerDisable: false,
    }
  },
  methods: {
    loginRequest() {
      if (this.loginEmail === "" || this.loginPasswd === "")
        ElMessage({
          message: '请输入账号密码',
          type: 'error',
        })
      else {
        let loginMsg = {
          userName: this.loginEmail,
          password: this.loginPasswd,
        }
        this.$http.post('http://localhost:8080/GFTB/api/account',
            loginMsg)
            .then((res) => {
              let token = res.data.data
              console.log(res.data);
              if (res.data.code === 1) {
                localStorage.setItem("token",token)
                const userInfo = JSON.parse(this.$jwt(token).sub);
                localStorage.setItem("userId",userInfo.userId)
                localStorage.setItem("userName",userInfo.userName)
                ElMessage({
                  message: '登录成功',
                  type: 'success',
                })
                this.$router.push('/')
              } else {
                ElMessage({
                  message: '账户或密码错误',
                  type: 'error',
                })
              }
            })
      }
    },
    requestRequest() {
      if (this.registerPasswd === "" || this.registerUsername === "")
        ElMessage({
          message: '请输入注册信息',
          type: 'error',
        })
      else {
        let registerMsg = {
          userName: this.registerUsername,
          password: this.registerPasswd,
        }
        this.$http.post('http://localhost:8080/GFTB/api/user',
            registerMsg)
            .then((res) => {
              if (res.data.code === 1) {
                ElMessage({
                  message: '注册成功',
                  type: 'success',
                })
                this.$router.push('/account')
              } else {
                ElMessage({
                  message: '注册失败',
                  type: 'error',
                })
              }
            })
        this.registerDisable = true
        this.activeName = 'login'
      }
    }
  },mounted() {
    if(this.activeName)
      this.activeName = this.$route.params.status
  }
}
</script>
<script setup>import {CirclePlusFilled, CircleCheck} from '@element-plus/icons-vue'</script>
<style scoped>
.account-container{
  margin: 100px;
}
.el-input{
  margin: 10px 0;
}
.tab-pane-box {
  margin: 25px 0 15px 0;
}

.tab-pane-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.submit-button {
  margin: 30px 10px;
}
</style>