<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        tooltip-effect="dark"
        style="width: 80%"
        fit="true"
      >
        <el-table-column align="center" prop="productId" label="product Id">
        </el-table-column>
        <el-table-column
          align="center"
          prop="orderStatus"
          label="Rent Status"
          sortable="true"
        >
          <template v-slot="scope">
            <span v-if="scope.row.orderStatus === 1">Need Approval</span>
            <span v-if="scope.row.orderStatus === 2">Collect/Delivery</span>
            <span v-if="scope.row.orderStatus === 3">Renting</span>
            <span v-if="scope.row.orderStatus === 4">Return</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="cartNum" label="Quantity">
        </el-table-column>
        <el-table-column align="center" prop="endDate" label="Return Date">
          <template v-slot="scope">
            {{ dateCal(scope.row.endDate) }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="Return">
          <template v-slot="scope">
            <el-button
              v-if="scope.row.orderStatus === 1"
              @click.prevent="returnOrder(scope.$index, tableData)"
              type="text"
              disabled
              >Return</el-button
            >
            <el-button
              v-if="scope.row.orderStatus === 2"
              @click.prevent="returnOrder(scope.$index, tableData)"
              disabled
              >Return</el-button
            >
            <el-button
              v-if="scope.row.orderStatus === 3"
              @click.prevent="returnOrder(scope.$index, tableData)"
              >Return</el-button
            >
            <el-button
              v-if="scope.row.orderStatus === 4"
              @click.prevent="returnOrder(scope.$index, tableData)"
              disabled
              >Return</el-button
            >
          </template>
        </el-table-column>
      </el-table>
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
import { dateFormat } from '../../utils/dateFormate'

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
    },
    returnOrder(index, rows) {
      this.$confirm(
        'Please Confirm Order Id: ' + this.tableData[index].orderId,
        'Remind',
        {
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
          type: 'warning'
        }
      )
        .then(() => {
          axios.post('/order/returnOrder/', this.tableData[index]).then(res => {
            if (res.data.status === 1000) {
              rows.splice(index, 1)
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'success'
              })
            } else {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'error'
              })
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: 'cancel'
          })
        })

      console.log(index)
      console.log(rows)
    },
    dateCal(date) {
      return dateFormat(date, 'yyyy-MM-dd hh:mm:ss')
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
