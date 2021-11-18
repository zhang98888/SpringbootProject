<template>
  <div class="home-banner">
    <el-carousel :interval="5000" height="450px" arrow="always" type="card" v-if="HomeImg.length > 0">
      <el-carousel-item v-for="item in HomeImg" :key="item">
        <RouterLink :to="'/product/' + item.prodId">
          <img :src="item.imgUrl" style="height:450px; width:450px" />
        </RouterLink>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      HomeImg: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios.get('/homeImg').then(res => {
        console.log(res)
        this.HomeImg = res.data.data
      })
    }
  }
}
</script>

<style lang="less" scoped>
.home-banner {
  width: 900px;
  height: 450px !important;
  left: 0;
  right: 0;
  top: 0;
  z-index: 98;
}

.el-carousel__item h3 {
  background-color: white;
  font-size: 18px;
  opacity: 0.75;
  line-height: 450px;
  margin: 0;
  height: 450px !important;
}

.el-carousel__item:nth-child(2n) {
  background-color: white;
  height: 450px !important;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: white;
  height: 450px !important;
}
</style>
