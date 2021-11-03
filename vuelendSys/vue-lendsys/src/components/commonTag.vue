<template>
  <div class="tabs">
    <el-tag
      v-for="(tag,index) in tags"
      :key="tag.name"
      size="small"
      :closable="tag.name !== 'Home'"
      :effect="$route.name === tag.name ? 'dark' : 'plain'"
      @click="changeMenu(tag)"
      @close="handleClose(tag,index)"
    >
      {{ tag.name }}
    </el-tag>
  </div>
</template>
<script>
import { mapMutations, mapState } from 'vuex'

export default {
  data() {
    return {}
  },
  computed: {
    ...mapState({
      tags: state => state.tabList
    })
  },
  methods: {
      ...mapMutations({
          close:'closeTag'
      }),
      changeMenu(item){
          this.$router.push({name:item.name})
          this.$store.commit('selectMenu',item)
      },
      handleClose(tag,index) {
        let len = this.tags.length - 1
        this.close(tag)
        
        if(tag.name !== this.$route.name){
            return;
        }

        if(index === len){
            this.$router.push({
                name: this.tags[index-1].name,
            })
        }else{
            this.$router.push({name: this.tags[index].name})
        }
        
      },
  }
}
</script>

<style scoped>
.tabs {
  padding: 10px;
  height: 5 px;
}
.el-tag {
  margin-right: 15px;
  cursor: pointer;
}
</style>
