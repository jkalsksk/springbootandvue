<template>
  <div style="width: 100%; height: 100vh; background-color: aliceblue; overflow: hidden">
    <div style="width: 460px; margin: 150px auto;">

      <div style="color: dodgerblue; font-weight: bold; font-size: 30px; text-align: center">Registered</div>

      <!-- 注册表单表单 -->
      <div style="margin-top: 15px">
        <el-form
            ref="form"
            :model="form"
            size="large"
            :label-position="'right'"
            label-width="100px"
            style="max-width: 400px"
            :rules="rules" >

          <el-form-item label="用户名" prop="userName">
            <el-input v-model="form.userName" />
          </el-form-item>
          <el-form-item label="密码" prop="passWord">
            <el-input v-model="form.passWord" show-password />
          </el-form-item>
          <el-form-item label="确认密码" prop="confirm">
            <el-input v-model="form.confirm" show-password />
          </el-form-item>
          <el-form-item >
            <el-button style="width: 100%;" type="primary" @click="submitForm('form')"
                       @keyup.enter="submitForm('form')" >注册</el-button>
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
  name: "Registered",
  data() {
    return {
      form: {userName :"",passWord:""},
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
        ]
      }
    }
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.post("/account/registered", this.form).then(res => {
            if (res.code === "0") {
              ElMessage.success(res.msg)
              this.$router.push('/login') // 注册成功 页面跳转 登入页面
            } else {
              ElMessage.error(res.msg)
            }
          })

        } else {
          ElMessage.error('亲 您还有表单项没有填写哦')
          return false;
        }
      });
    },
    registered() {
      // 校验数据
  /*    if (this.form.userName.trim()) {
        ElMessage.error("请输入用户名")
        return
      }

      if (this.form.passWord !== this.form.confirm) {
        ElMessage.error("两次输入的密码不一致")
        return
      }*/

     /* request.post("/account/login", this.form).then(res => {
        if (res.code === "0") {
          ElMessage.success(res.msg)
          this.$router.push('/login') // 注册成功 页面跳转 登入页面
        } else {
          ElMessage.error(res.msg)
        }
      })*/
    },
  }
}
</script>

<style scoped>

</style>
