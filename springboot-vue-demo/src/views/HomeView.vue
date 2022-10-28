<template>
  <div style="padding: 10px">

    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index" label="id" sortable="custom" width="180"/>
      <el-table-column prop="id" label="ID" width="190" v-if="false"/>
      <el-table-column prop="userName" label="名称" width="180" />
      <el-table-column prop="nickName" label="昵称" width="180" />
      <el-table-column prop="age" label="年龄" width="180" />
      <el-table-column prop="sex" label="性别" width="180" />
      <el-table-column prop="address" label="地址" width="180" />

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mimi" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mimi" type="danger" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div class="demo-pagination-block" style="margin-top: 10px">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />


      <!-- 新增 或者 添加 用户的提示 -->
      <el-dialog
          title="用户编辑"
          v-model="dialogVisible"
          width="30%">

        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>

          <el-form-item label="昵称">
            <el-input v-model="form.nickName"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="性别" >
           <el-radio v-model="form.sex" label="男">男</el-radio>
           <el-radio v-model="form.sex" label="女">女</el-radio>
           <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address"></el-input>
          </el-form-item>

        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveUser">确定</el-button>

          </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/rqeust";
import {ElMessage} from "element-plus";

export default {
  name: 'HomeView',
  components: {

  },
  created() {
    this.load()
  },
  data(){
    return {
      dialogVisible: false,
      dialogVisibleUpdate: false,
      form:{
        userName: '',
        nickName: '',
        age:null,
        sex: '未知',
        address: ''
      },
      currentPage: 1,
      pageSize: 5,
      total: 0,
      search: '',
      tableData: [

      ]
    }
  },
  methods:{
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
      console.log(this.form.id)
    },
    handleDelete(id) {
      request.delete("/user/delete/" + id).then(res => {
        if (res.code === "0") {
          this.load()
          ElMessage.success(res.msg)
        } else {
          ElMessage.error(res.msg)
        }

      })
    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange() {
      this.load()
    },
    load() {
      request.get("/user", {
        params: {
          pageNum: parseInt(this.currentPage),
          pageSize: parseInt(this.pageSize),
          search: this.search
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true;
      this.a()
    },
    saveUser() {
      if (this.form.id) { // 更新
        // 点确定 发送 请求
        request.put("/user/update", this.form).then(res => {
          if (res.code === "0") {
            // 成功
            // 等于 0 代表成功
            this.dialogVisible = false;
            this.load()
            ElMessage.success("更新成功!")
          } else {
            // 失败
            ElMessage.error(res.msg)
          }
        })

      } else  {
        // 新增
        request.post("/user/save", this.form).then(res => {
          if (res.code === "0") {
            // 等于 0 代表成功
            this.dialogVisible = false;
            this.a()
            this.load()
            ElMessage.success("添加成功!")
          } else {
            // 不等于0 提示信息
            ElMessage.error(res.msg)
          }
        })
      }


    },
    a() {
      this.form.id = null
      this.form.userName = ""
      this.form.nickName = ""
      this.form.age = null
      this.form.address = ""
    },

  }
}
</script>
