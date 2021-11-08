<template>
  <el-row>
    <el-col
      :span="4"
      v-for="picture in Picture"
      :key="picture"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card :body-style="{ padding: '4px' }" shadow="always">
        <img :src="picture.url" class="image" />
        <div style="padding: 14px;">
          <div class="bottom">
            <p>
              <span>{{ picture.productname }}</span>
            </p>
            <p>
              Rent Number: <span>{{ picture.rent }}</span>
            </p>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      currentDate: new Date(),
      Picture: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios.get('/goodPic/getAdvanceGoodPic').then(res => {
        console.log(res)
        this.Picture = res.data.data
        console.log(this.Picture)
      })
    }
  }
}
</script>

<style>
.el-card {
  margin-left: 10px;
  height: 240px;
}

.bottom {
  margin-top: 13px;
  line-height: 30px;
  display: flex;
  flex-direction: column;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
  height: 130px;
}

p{
  font-size: 8px;
}
</style>
