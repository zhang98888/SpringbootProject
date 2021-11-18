<template>
  <el-container style="height:100%">
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
          <el-card :body-style="{ padding: '4px' }" shadow="hover">
            <RouterLink :to="'/product/' + picture.productId">
              <img :src="picture.url" class="image" />
            </RouterLink>
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
    </el-main>
    <div class="pagination">
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
    </div>
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
        .get('/goodPic/getProductPic/' + this.currentPage + '/' + this.pageSize)
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
          '/category/getProductPic/' + this.currentPage + '/' + this.pageSize
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

.el-header {
  height: 80px;
}
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
}
</style>
