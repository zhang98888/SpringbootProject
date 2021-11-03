<template>
  <div class="header">
    <a href="#/">
      <!-- 点击进入首页 -->
      <img class="logo" src="@/assets/logo2.png" />
      <span class="company">LendSys</span>
    </a>
    <el-dropdown>
      <span class="el-dropdown-link">
        <img :src="userImg" class="user-img" />
        
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item icon="el-icon-edit" command="edit"
            >user information</el-dropdown-item
          >

          <el-dropdown-item icon="el-icon-s-operation" @click="logout"
            >log out</el-dropdown-item
          >
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
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
  background-color: #545c64;
  height: 60px;
  justify-content: space-between;
}

.user-img {
  width: 40px;
  height: 40px;
}
.logo {
  vertical-align: middle; 
  padding: 0px 10px 0px 40px; 
  width: 40px;
  height: 40px;
}

.company {
  position: absolute;
  color: white;
}

/* 下拉菜单 */
.el-dropdown {
  float: right; 
  margin-right: 40px; 
}
/* 系统管理 */
.el-dropdown-link {
  color: white;
  cursor: pointer; 
}
</style>
