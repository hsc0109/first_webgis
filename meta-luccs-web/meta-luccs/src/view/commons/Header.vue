<template>
  <div class="header" style="display:flex;">
    <div class="logo-title">Gis System</div>
    <div style="flex:1"></div>
    <div >
      <el-menu :default-active="activeIndex" mode="horizontal" class="el-menu-top"  text-color="#ffffff" active-text-color="#ffffff">
        <el-menu-item index="1">二维地图</el-menu-item>
        <el-menu-item index="2">三维地图</el-menu-item>
        <el-menu-item index="3">数据表单</el-menu-item>
      </el-menu>
    </div>

  </div>

</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      activeIndex: '1'
    }
  }
}
</script>

<style scoped>

.header {
  height: 60px;
  line-height: 60px;
  background: #001529;
}
.logo-title{
  width: 120px;
  line-height: 64px;
  font-size: 20px;
  color: #ffffff;
  float: left;
}

.el-menu-top {
  border-bottom: none;
}

.el-menu--horizontal > .el-menu-item {
  border-bottom: none;
  text-decoration: none;
  background-color: #001529;
}
.el-menu--horizontal  > .is-active {
  background-color: #409EFF !important;
}
.el-menu-item:hover {
  background-color: #409EFF !important;
}

</style>
