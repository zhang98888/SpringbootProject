<template>
  <el-container>
    <el-header>
      <commonHeader />
    </el-header>

    <el-main>
      <div class="container">
        <div class="home">
          <IndexAside /> 
        </div>
        <div>
          <IndexBanner />
        </div>
      </div>
      <IndexName title="Recommend" subTitle="Polular rented">
        <template #right><index-more path="/" /></template>
      </IndexName>
      <IndexMorePic />
    </el-main>
  </el-container>
</template>

<script>
import IndexAside from '../components/IndexAside.vue'
import IndexBanner from '../components/IndexBanner.vue'
import commonHeader from '../components/commonHeader.vue'
import IndexName from '../components/IndexButtomName.vue'
import IndexMore from '../components/IndexMore.vue'
import IndexMorePic from '../components/IndexMorePic.vue'



export default {
  components: { IndexAside, IndexBanner, commonHeader,IndexName, IndexMore,IndexMorePic }
}
</script>

<style scoped lang="less">
.container {
  display: flex;
  justify-content: center;
  background-color:whitesmoke;
}


.el-header {
  height: 80px;
}
.home {
  width: 250px;
  height: 500px;
  background: hsla(0, 0%, 0%, 0.5);
  position: relative;
  z-index: 99;
}
</style>
