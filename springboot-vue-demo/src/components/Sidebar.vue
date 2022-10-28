<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
            style="width: 200px; min-height: calc(100vh - 50px)"
            default-active="user"
            :default-openeds="[1]"
            class="el-menu-vertical-demo">
          <el-sub-menu index="1">
            <template #title>
              系统导航
            </template>
              <el-menu-item index="user" :route="{path:'/'}" >用户管理</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="data"  >数据管理</el-menu-item>

        </el-menu>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "Sidebar"
}

</script>

<style scoped>

</style>
