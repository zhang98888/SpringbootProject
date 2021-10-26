<template>
  <el-row class="home" :getter="20">
    <el-col :span="6" class="user">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="user">
          <img :src="userImg" alt="" />
          <div class="login-info">
            <div class="name"><p>User name: <span>Admin</span></p></div>
            <div class="access"><p>User Level: <span>Supervisor</span></p></div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="16"> </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      userImg: require('../../assets/logo2.png')
    }
  }
}
</script>

<style>
@import '../../assets/css/adminhome.css';
</style>
