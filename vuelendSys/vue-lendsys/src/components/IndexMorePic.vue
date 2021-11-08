<template>
  <el-row>
    <el-col
      :span="4"
      v-for="(o, index) in 2"
      :key="o"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card :body-style="{ padding: '0px' }" shadow="always">
        <img
          src="@/assets/logo.png"
          class="image"
        />
        <div style="padding: 14px;">
          <span>Description</span>
          <div class="bottom">
            <time class="time">{{ currentDate }}</time>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      currentDate: new Date()
    }
  }
}
</script>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}

</style>
