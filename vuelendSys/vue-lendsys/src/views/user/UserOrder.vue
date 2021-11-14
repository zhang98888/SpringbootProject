<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
      <el-table :data="tableData" tooltip-effect="dark" style="width: 80%">
        <el-table-column prop="productId" label="product Id" width="130">
        </el-table-column>
        <el-table-column prop="productName" label="product Name" width="200">
        </el-table-column>
        <el-table-column prop="orderStatus" label="Rent Status" width="130">
        </el-table-column>
        <el-table-column prop="cartNum" label="Quantity" width="130">
        </el-table-column>
        <el-table-column
          prop="endDate"
          label="Return Date"
          show-overflow-tooltip
        >
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px;margin-bottom:20px">
        <el-button @click="borrow">Borrow</el-button>
        <el-button @click="toggleSelection()">Clear Selection</el-button>
      </div>
      <div class="pagination">
        <el-pagination
          @current-change="handleCurrentChange"
          v-model:currentPage="currentPage"
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="total"
          style="display: flex; justify-content: center"
        >
        </el-pagination>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios'
import commonHeader from '../../components/commonHeader.vue'

export default {
  components: {
    commonHeader
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 400
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios
        .get('/order/' + this.currentPage + '/' + this.pageSize, {
          params: { username: window.sessionStorage.getItem('token') }
        })
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    handleCurrentChange(val) {
      console.log(`now page: ${val}`)
      axios
        .get('/goodPic/getAllGoodPic/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    }
  }
}
</script>

<style scoped>
.el-main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0;
}
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
}
</style>
