<template>
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px); height: 100%"
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      background-color="#222d32"
      text-color="#5F9EA0"
      active-text-color="#faf039"
      
    >
      <div style="height: 50px; line-height: 50px; display: flex; height: 100%">
        <div
          style="width: 200px; padding-left: 0px; font-weight: bold; color: cadetblue; flex;
          flex-direction: row;
          justify-content: center; 
          height: 100%"
        >
          Admin Management
        </div>
      </div>
      <el-menu-item
        :index="item.path"
        v-for="item in form"
        :key="item.path"
        @click="clickMenu(item)"
        
      >
        <i :class="'el-icon-' + item.icon"></i>
        <span>{{ item.label }}</span></el-menu-item
      >
    </el-menu>
  </div>
</template>

<script>
import axios from 'axios'
import menu from '../utils/menu'
import userMenu from '../utils/userMenu'

export default {
  data() {
    return {
      menu,
      userMenu,
      form: [],
  
    }
  },
  computed: {
    isCollapse() {
      return this.$store.state.tabs.isCollapse
    }
  },
  created() {
   this.noChildren()
  },
  methods: {
      async noChildren() {
      let formdata = new FormData()
      const tokenStr = window.sessionStorage.getItem('token')
      formdata.append('username', tokenStr)
      await axios.post('/getUserInfo', formdata).then(res => {
        if (res.data.data.userLevel === '111') {
          this.form = this.menu
        } else 
          this.form = this.userMenu
      })
    },
    clickMenu(item) {
      this.$router.push({ name: item.name })
      this.$store.commit('selectMenu', item)
    }
  }
}
</script>
