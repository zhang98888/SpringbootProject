<template>
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#222d32"
      text-color="#5F9EA0"
      active-text-color="#faf039"
    >
      <div style="height: 50px; line-height: 50px; display: flex">
        <div
          style="width: 200px; padding-left: 0px; font-weight: bold; color: cadetblue; flex;
          flex-direction: row;
          justify-content: center;"
        >
          Admin Management
        </div>
      </div>
      <el-menu-item
        :index="item.path"
        v-for="item in noChildren"
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
export default {
  data() {
    return {
      menu: [
        {
          path: '/',
          name: 'AdminHome',
          icon: 'menu',
          label: 'Home',
          url: '/admin'
        },
        {
          path: '/admin/users',
          name: 'Users',
          icon: 'user',
          label: 'Users Management',
          url: '/admin/users'
        },
        {
          path: '/admin/goods',
          name: 'Goods',
          label: 'Goods Management',
          icon: 'document',
          url: '/admin/goods'
        },
        {
          path: '/admin/address',
          name: 'Address',
          label: 'User Address',
          icon: 'location',
          url: '/admin/address'
        }
      ]
    }
  },
  computed: {
    noChildren() {
      return this.menu.filter(item => !item.children)
    },
    hasChildren() {
      return this.menu.filter(item => item.children)
    },
    isCollapse(){
      return this.$store.state.tabs.isCollapse
    },
  },
  methods: {
    clickMenu(item) {
      this.$router.push({ name: item.name })
      this.$store.commit('selectMenu', item)
    }
  }
}
</script>
