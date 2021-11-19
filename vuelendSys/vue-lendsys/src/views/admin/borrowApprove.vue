<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="User Id">
          <el-input
            v-model="searchForm.userId"
            placeholder="User Id"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            style="margin-left: 5px"
            @click="searchCategory"
            >Search</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column
        prop="orderId"
        label="order id"
        width="180"
        sortable="true"
      >
      </el-table-column>
      <el-table-column prop="userId" label="user id" width="180">
      </el-table-column>
      <el-table-column prop="productId" label="product id"> </el-table-column>
      <el-table-column prop="cartNum" label="Rent num"></el-table-column>
      <el-table-column prop="length" label="Rent length"></el-table-column>
      <el-table-column label="Operation" fixed="right">
        <template #default="scope">
          <el-button
            size="mini"
            @click.prevent="handleRemove(scope.$index, tableData)"
            type="text"
          >
            approve
          </el-button>
        </template>
      </el-table-column>
    </el-table>
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
  </div>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'

export default {
  data() {
    return {
      tableData: [],
      searchForm: {},
      search: '',
      currentPage: 1,
      pageSize: 20,
      total: 400
    }
  },
  created() {
    this.load()
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (this.tableData[rowIndex].rentNum <= 50) {
        return 'warning-row'
      } else if (this.tableData[rowIndex].rentNum >= 500) {
        return 'success-row'
      }
      return ''
    },
    load() {
      axios
        .get('/order/approvalOrder/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    handleSizeChange(val) {
      console.log(` ${val} per page `)
    },
    handleCurrentChange(val) {
      console.log(`now page: ${val}`)
      axios
        .get('/order/approvalOrder/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    handleRemove(index, rows) {
      this.$confirm('Please Confirm UserId: '+this.tableData[index].userId+' Order Id: '+this.tableData[index].orderId, 'Remind', {
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel',
        type: 'warning'
      })
        .then(() => {
          axios.post('/order/endDate/', this.tableData[index]).then(res => {
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
            message: 'Cancel'
          })
        })

      console.log(index)
      console.log(rows)
    },
    searchCategory() {
      axios
        .post(
          '/category/searchCategory/' + this.currentPage + '/' + this.pageSize,
          this.searchForm
        )
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
      this.searchForm = {}
    }
  }
}
</script>

<style scoped>
.el-table .warning-row {
  --el-table-tr-background-color: var(--el-color-warning-lighter);
}
.el-table .success-row {
  --el-table-tr-background-color: var(--el-color-success-lighter);
}
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
}
</style>
