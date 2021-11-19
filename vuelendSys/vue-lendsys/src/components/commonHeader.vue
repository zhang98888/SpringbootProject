<template>
  <header class="commonHeader">
    <div class="container">
      <ul class="navs">
        <a href="/product">
          <img class="logo" src="@/assets/logo.png" />
        </a>
        <template v-for="category in categoryName" :key="category.categoryName">
          <el-link
            :underline="false"
            :href="'/category/' + category.categoryId"
            >{{ category.categoryName }}</el-link
          >
        </template>
        <el-badge :value="cartValue" class="item">
          <el-link
            class="el-icon-shopping-cart-full"
            href="/basket"
            style="margin-right:5px"
          ></el-link>
        </el-badge>
      </ul>
    </div>
  </header>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'
export default {
  name: 'commonHeader',
  data() {
    return {
      categoryName: [],
      cartValue: 0
    }
  },
  created() {
    this.load()
  },

  methods: {
    load() {
      axios.get('/category/getAllCategory/' + 1 + '/' + 20).then(res => {
        console.log(res)
        this.categoryName = res.data.data
        axios
          .get('/cart/', {
            params: { username: window.sessionStorage.getItem('token') }
          })
          .then(resp => {
            this.cartValue = resp.data.total
          })
      })
    }
  }
}
</script>

<style scoped>
.navs {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  align-content: center;
  font-family: Arial;
}

.el-link {
  margin-right: 20px;
  list-style-type: none;
  font-size: 13px;
}

.container {
  width: 1240px;
  margin: 0 auto;
  position: relative;
  height: 60px;
}

.el-icon-shopping-cart-full {
  margin-right: 40px;
  font-size: 25px;
}

.logo {
  vertical-align: middle;
  padding: 0px 10px 0px 0px;
  width: 80px;
  height: 80px;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
