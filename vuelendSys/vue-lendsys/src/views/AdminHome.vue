<template>
  <el-container style="height: 100%">
    <el-slide width="200px">
      <common-aside></common-aside>
    </el-slide>
    <el-container>
      <el-header>
        <common-header></common-header>
      </el-header>
        
      <el-main> </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from '../components/commonAside.vue'
import CommonHeader from '../components/commonHeader.vue'
export default {
  name: 'AdminApp',
  
  components: { CommonAside,CommonHeader },

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
.el-header,
.el-aside {
  line-height: 200px;
}

.el-main,
body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
