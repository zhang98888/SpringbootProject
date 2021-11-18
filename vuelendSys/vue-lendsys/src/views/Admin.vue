<template>
  <el-container style="height: 100%">
    <el-slide width="200px" style="background-color: #222d32">
      <common-aside></common-aside>
    </el-slide>
    <el-container style="background-color:#f5f5f5">
      <el-header>
        <common-tag></common-tag>
      </el-header>
      <div></div>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from '../components/commonAside.vue'
import CommonTag from '../components/commonTag.vue'
export default {
  name: 'AdminApp',

  components: { CommonAside, CommonTag },

  data() {
    return {}
  }
}
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 100px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-header {
  line-height: 0px;
  text-align: left;
}
.el-aside {
  line-height: 200px;
}

.el-main,
body > .el-container {
  margin-bottom: 40px;
}

.el-main {
  --el-main-padding: 0px !important;
  padding-left: 20px !important;
  padding-right: 20px !important;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-header {
  --el-header-height: 30px !important;
}
</style>
