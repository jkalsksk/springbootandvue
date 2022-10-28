<template>
  <div>
    <!--页头  -->
    <Top/>

    <div style="display: flex">
      <!-- 侧边导航 -->
      <Sidebar/>

      <!-- view 的数据展示-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Top from "@/components/Top";
import Sidebar from "@/components/Sidebar";

export default {
  name: "Layout",
  components: {
    Top,
    Sidebar
  }
}
</script>

<style scoped>

</style>
