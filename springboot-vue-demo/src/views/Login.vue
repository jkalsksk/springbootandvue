<template>
  <div style="width: 100%; height: 100vh; background-color: aliceblue; overflow: hidden">
    <div style="width: 460px; margin: 150px auto;">

      <div style="color: dodgerblue; font-weight: bold; font-size: 30px; text-align: center">Login</div>

      <!-- 登入表单 -->
      <div style="margin-top: 15px">
        <el-form
            ref="form"
            :model="form"
            size="large"
            :label-position="'right'"
            label-width="100px"
            style="max-width: 400px">

          <el-form-item label="用户名">
            <el-input v-model="form.username" />
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" show-password />
          </el-form-item>
          <el-form-item >
            <el-button style="width: 100%;" type="primary" @click="login">登入</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/rqeust";
import {ElMessage} from "element-plus";

export default {
  name: "Login",
  data() {
    return {
      form: {}
    }
  },
  methods:{
    login() {
      request.post("/user/login", this.form).then(res => {
        if (res.code === "0") {
          ElMessage.success(res.msg)
        } else {
          ElMessage.error(res.msg)
        }
        this.$router.push('/') // 登入成功 页面跳转
      })

    }
  }
}
</script>

<style scoped>

</style>
