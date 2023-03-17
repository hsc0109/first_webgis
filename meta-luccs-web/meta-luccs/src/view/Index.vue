<template>
  <div>
    <el-container>
      <el-container>
        <el-aside v-if="true"  >
          <Aside />
        </el-aside>
        <el-main><Main /> </el-main>
      </el-container>

    </el-container>
  </div>
</template>
<script>
import Header from './commons/Header'
import Aside from './commons/Aside'
import Main from './commons/Main'
import Footer from './commons/Footer'

export default {
  name: 'index',
  components: {
    Header,
    Main,
    Aside,
    Footer
  },
  props: {
    msg: String
  }
}
</script>

<style>
.el-container {
  height: 100%;
}
.el-header {
  background-color: #001529;
  color: #fff;
  text-align: center;
  line-height: 60px;
}
.el-footer {
  background-color: #001529;
  color:#FFFFFF;
  text-align: center;
  height: 20px;
  line-height: 20px;
  font-size: 12px;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  padding: 0px;
}
.el-aside {
  height: 100%;
  width: 400px!important;
  background-color: #304156;
  transition: width .28s;
  text-align: center;
  line-height: 20px;
  text-align: left;
  overflow: hidden;
  position: fixed;
  z-index: 1001;
}
.el-select {
 width: 100px;
}
body > .el-container {
  margin-bottom: 30px;
}

.el-submenu__icon-arrow{
  right: 60px;
}

</style>
