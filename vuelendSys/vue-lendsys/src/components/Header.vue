<template>
  <div class="line"></div>
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
    <el-sub-menu index="2">
      <template #title>downlist</template>
      <el-menu-item index="2-1">option1</el-menu-item>
      <el-menu-item index="2-2">option2</el-menu-item>
      <el-menu-item index="2-3">option3</el-menu-item>
      <el-sub-menu index="2-4">
        <template #title>option4</template>
        <el-menu-item index="2-4-1">opt1</el-menu-item>
        <el-menu-item index="2-4-2">opt2</el-menu-item>
        <el-menu-item index="2-4-3">opt33</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="3" disabled>index2</el-menu-item>
    <el-menu-item index="4">index3</el-menu-item>
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
      activeIndex2: '1'
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
