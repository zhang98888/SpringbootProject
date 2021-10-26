<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <el-menu-item index="1">index1</el-menu-item>
    <el-menu-item index="2" disabled>index2</el-menu-item>
    <el-menu-item index="3">index3</el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <img :src="userImg" class="user" />
      </template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
    </el-sub-menu>
    <el-button type="info" @click="logout">logout</el-button>
  </el-menu>
</template>

<script>
import router from '../router'
export default {
  name: 'Header',
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      userImg: require('../assets/logo2.png')
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    logout() {
      window.sessionStorage.clear()
      router.push('/login')
    }
  }
}
</script>

<style scoped>
.header {
  align-items: center;
  display: flex;

  height: 100%;
  justify-content: space-between;
}
.left {
  display: flex;
  align-items: center;
}

.user {
  width: 40px;
  height: 40px;
}
</style>
