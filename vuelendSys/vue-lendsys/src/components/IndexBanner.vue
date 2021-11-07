<template>
  <div class="home-banner">
    <el-carousel :interval="5000" height="450px" arrow="always" type="card">
      <el-carousel-item v-for="item in 4" :key="item">
        <img src="@/assets/logo.png" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  
}
</script>


<style lang="less" scoped>


.home-banner {
  width: 1240px;
  height: 450px !important;
  left: 0;
  right: 0;
  top: 0;
  z-index: 98;
}

.el-carousel__item h3 {
  color: #d3dce6;
  font-size: 18px;
  opacity: 0.75;
  line-height: 450px;
  margin: 0;
  height: 450px !important;
}

.el-carousel__item:nth-child(2n) {
  background-color: #d3dce6;
   height: 450px !important;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
   height: 450px !important;
}

</style>
