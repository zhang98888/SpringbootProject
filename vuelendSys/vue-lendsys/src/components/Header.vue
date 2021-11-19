<template>
  <div class="header">
    <a href="/">
      <img class="logo" src="@/assets/header.png" />
    </a>
    <div class="back">
      <a href="/" class="nav-link">Home</a>
      <a href="/product" class="nav-link">Product</a>
      <a href="/admin" class="nav-link">Management</a>
      <a href="/user/userInfo" class="nav-link">Information</a>
      <a href="/order" class="nav-link">Order</a>
      <a href="" @click="logout" class="nav-link">logout</a>

      <!-- <el-dropdown>
        <span class="el-dropdown-link">
          <img :src="userImg" class="user-img" />
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item icon="el-icon-edit" command="edit" href="/admin"
              >user information</el-dropdown-item
            >
            <el-dropdown-item icon="el-icon-edit" command="edit" href="/order"
              >user order</el-dropdown-item
            >
            <el-dropdown-item icon="el-icon-edit" command="edit" href=""
              >user Address</el-dropdown-item
            >

            <el-dropdown-item icon="el-icon-s-operation" @click="logout"
              >log out</el-dropdown-item
            >
          </el-dropdown-menu>
        </template>
      </el-dropdown> -->

      
    </div>
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
  border-radius: 20px;
}
.logo {
  vertical-align: middle;
  padding: 0px 10px 0px 0px;
  width: 300px;
  height: 40px;
}

.company {
  position: absolute;
  color: white;
}

.el-dropdown {
  float: right;
  margin-right: 100px;
}

.el-dropdown-link {
  color: white;
  cursor: pointer;
  margin-right: 40px;
}
.nav-link {
  color: white;
  margin-right: 40px;
}

.back {
  display: flex;
  flex-direction: row;
  align-items: center;
}



</style>
