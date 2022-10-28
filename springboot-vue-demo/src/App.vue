<template>
  <nav >
    <Top/>

    <div style="display: flex">
      <Sidebar/>

      <router-view style="flex: 1"/>
    </div>
  </nav>
 
</template>

<script>
  import Top from "@/components/Top";
  import Sidebar from "@/components/Sidebar";

  export default {
    name: "App",
    components: {
      Top,
      Sidebar
    }
  }
</script>

<style>

</style>
