<template>
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#f5f5f5"
      text-color="#5F9EA0"
      active-text-color="#516891"
    >
      <div
        style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc;display: flex"
      >
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
          icon: 's-home',
          label: 'User Management',
          url: '/AdminHome'
        },
        {
          path: '/item',
          name: 'Item',
          icon: 'menu',
          label: 'Item Management',
          url: '/'
        },
        {
          path: '/address',
          name: 'Address',
          label: 'User Address',
          icon: 'document',
          url: '/'
        },
        {
          path: '/option',
          name: 'Option',
          label: 'Option',
          icon: 'setting',
          url: '/'
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
    }
  },
  methods: {
    clickMenu(item){
        this.$router.push({name: item.name})
    }
  }
}
</script>
