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
      <div class="main">
        <IndexName title="Available" subTitle="Polular rented">
          <template #right><index-more path="/product"/></template>
        </IndexName>
        <IndexMorePic />
      </div>
      <div class="lines1"></div>
      <div class="main">
        <IndexName title="Working at Home" subTitle="Suitable for working at home">
          <template #right><index-more path="/product"/></template>
        </IndexName>
        <IndexHomeWork />
      </div>
      <div class="lines1"></div>
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
import IndexHomeWork from '../components/IndexHomeWork.vue'

export default {
  components: {
    IndexAside,
    IndexBanner,
    commonHeader,
    IndexName,
    IndexMore,
    IndexMorePic,
    IndexHomeWork
  }
}
</script>

<style scoped lang="less">
.container {
  display: flex;
  justify-content: center;
  background-color: whitesmoke;
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

.el-main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.main {
  width: 1150px;
}
.lines1 {
  height: 12px;
  background-color: whitesmoke;
  width: 100%;
  position: relative;
}
</style>
