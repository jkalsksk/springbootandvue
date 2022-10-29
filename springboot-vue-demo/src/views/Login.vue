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
            style="max-width: 400px"
            :rules="rules">

          <el-form-item label="用户名">
            <el-input v-model="form.userName" @blur="checkUserName"/>
          </el-form-item>
          <el-form-item label="密码" prop="passWord">
            <el-input v-model="form.passWord" show-password />
          </el-form-item>
          <el-form-item >
            <el-button style="width: 100%;" type="primary" @click="login" @keyup.enter="login">登入</el-button>
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
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.passWord !== '') {
          this.$refs.ruleForm.validateField('请输入密码');
        }
        callback();
      }
    };
    return {
      form: {userName :"",passWord:""},
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    login() {
      request.post("/account/login", this.form).then(res => {
        if (res.code === "0") {
          ElMessage.success(res.msg)
          this.$router.push('/') // 登入成功 页面跳转
        } else {
          ElMessage.error(res.msg)
        }
      })

    },
    checkUserName() {
      if (this.form.userName.trim() === '') {

      }
    }
  }
}
</script>

<style scoped>

</style>
