<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
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
                  <span>{{ picture.productName }}</span>
                </p>
                <p>
                  Rent Number: <span>{{ picture.rentNum }}</span>
                </p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        v-model:currentPage="currentPage"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next"
        :total="total"
        style="display: flex; justify-content: center"
      >
      </el-pagination>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios'
import commonHeader from '../components/commonHeader.vue'

export default {
  components: {
    commonHeader
  },
  data() {
    return {
      Picture: [],
      currentPage: 1,
      pageSize: 40,
      total: 400
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios
        .post(
          '/goodPic/category/' +
            this.$route.params.categoryId +
            '/' +
            this.currentPage +
            '/' +
            this.pageSize
        )
        .then(res => {
          console.log(res)
          this.Picture = res.data.data
          console.log(this.Picture)
        })
    },
    handleSizeChange(val) {
      console.log(` ${val} per page `)
    },
    handleCurrentChange(val) {
      console.log(`now page: ${val}`)
      axios
        .get(
          '/category/getAllCategory/' + this.currentPage + '/' + this.pageSize
        )
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    }
  }
}
</script>

<style scoped>
.el-card {
  margin: 10px;
  height: 240px;
  display: flex;
  flex-direction: column;
  align-items: center;
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
  display: block;
}

p {
  font-size: 8px;
}
.el-row {
  padding: 20px;
}
</style>
