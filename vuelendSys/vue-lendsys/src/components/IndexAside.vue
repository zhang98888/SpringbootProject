<template>
  <div class="index-aside">
    <ul class="menu">
      <li v-for="category in categoryName" :key="category.categoryName">
        <RouterLink :to="'/category/'+ category.categoryId">{{category.categoryName}}</RouterLink>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'IndexAside',
    data() {
    return {
      categoryName: []
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
      })
    }
  }
}
</script>

<style lang="less" scoped>
li {
  list-style-type: none;
}
.menu {
  li {
    padding-left: 40px;
    height: 50px;
    line-height: 50px;
    &:hover {
      background: teal;
    }
    a {
      margin-right: 4px;
      color: #fff;
      &cfirst-child {
        font-size: 16px;
      }
    }
  }
}
</style>
