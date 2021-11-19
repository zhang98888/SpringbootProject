<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="Need Approval" name="1">
          <el-table
            :data="approvalData"
            tooltip-effect="dark"
            style="width: 100%"
            fit="true"
          >
            <el-table-column align="center" prop="orderId" label="order Id">
            </el-table-column>
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
              </template>
            </el-table-column>
            <el-table-column align="center" prop="cartNum" label="Quantity">
            </el-table-column>
            <el-table-column align="center" prop="startDate" label="Start Date">
              <template v-slot="scope">
                {{ dateCal(scope.row.startDate) }}
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
        </el-tab-pane>
        <el-tab-pane label="Collect/Delivery" name="2">
          <el-table
            :data="collectData"
            tooltip-effect="dark"
            style="width: 100%"
            fit="true"
          >
            <el-table-column align="center" prop="orderId" label="order Id">
            </el-table-column>
            <el-table-column align="center" prop="productId" label="product Id">
            </el-table-column>
            <el-table-column
              align="center"
              prop="orderRemark"
              label="Rent Status"
              sortable="true"
            >
              <template v-slot="scope">
                <span v-if="scope.row.orderRemark === 1">Delivery</span>
                <span v-if="scope.row.orderRemark === 2">Collect</span>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="cartNum" label="Quantity">
            </el-table-column>
            <el-table-column align="center" prop="startDate" label="Start Date">
              <template v-slot="scope">
                {{ dateCal(scope.row.startDate) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="Return">
              <template v-slot="scope">
                <el-button
                  v-if="scope.row.orderRemark === 1"
                  @click.prevent="receive(scope.$index, collectData)"
                  >Receive</el-button
                >
                <el-button
                  v-if="scope.row.orderRemark === 2"
                  @click.prevent="receive(scope.$index, collectData)"
                  disabled
                  >Collect</el-button
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
        </el-tab-pane>
        <el-tab-pane label="Renting" name="3">
          <el-table
            :data="rentingData"
            tooltip-effect="dark"
            style="width: 100%"
            fit="true"
          >
            <el-table-column align="center" prop="orderId" label="order Id">
            </el-table-column>
            <el-table-column align="center" prop="productId" label="product Id">
            </el-table-column>
            <el-table-column
              align="center"
              prop="orderStatus"
              label="Rent Status"
              sortable="true"
            >
              <template v-slot="scope">
                <span v-if="scope.row.orderStatus === 3">Renting</span>
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
                  v-if="scope.row.orderStatus === 3"
                  @click.prevent="returnOrder(scope.$index, rentingData)"
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
        </el-tab-pane>
        <el-tab-pane label="Return" name="4">
          <el-table
            :data="returnData"
            tooltip-effect="dark"
            style="width: 100%"
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
        </el-tab-pane>
      </el-tabs>
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
      approvalData: [],
      collectData: [],
      rentingData: [],
      returnData: [],
      currentPage: 1,
      pageSize: 10,
      total: 400,
      activeName: '1'
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios
        .get(
          '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
          {
            params: {
              username: window.sessionStorage.getItem('token'),
              status: '1'
            }
          }
        )
        .then(res => {
          console.log(res)
          this.approvalData = res.data.data
        }),
        axios
          .get(
            '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
            {
              params: {
                username: window.sessionStorage.getItem('token'),
                status: '2'
              }
            }
          )
          .then(res => {
            console.log(res)
            this.collectData = res.data.data
          }),
        axios
          .get(
            '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
            {
              params: {
                username: window.sessionStorage.getItem('token'),
                status: '3'
              }
            }
          )
          .then(res => {
            console.log(res)
            this.rentingData = res.data.data
          }),
        axios
          .get(
            '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
            {
              params: {
                username: window.sessionStorage.getItem('token'),
                status: '4'
              }
            }
          )
          .then(res => {
            console.log(res)
            this.returnData = res.data.data
          })
    },
    handleCurrentChange(val) {
      console.log(`now page: ${val}`)
      axios
        .get(
          '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
          {
            params: {
              username: window.sessionStorage.getItem('token'),
              status: this.activeName
            }
          }
        )
        .then(res => {
          console.log(res)
          if (this.activeName == '1') {
            this.approvalData = res.data.data
          } else if (this.activeName == '2') {
            this.collectData = res.data.data
          } else if (this.activeName == '3') {
            this.rentingData = res.data.data
          } else if (this.activeName == '4') {
            this.returnData = res.data.data
          }
        })
    },
    returnOrder(index, rows) {
      this.$confirm(
        'Please Confirm Order Id: ' + this.rentingData[index].orderId,
        'Remind',
        {
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
          type: 'warning'
        }
      )
        .then(() => {
          axios
            .post('/order/returnOrder/', this.rentingData[index])
            .then(res => {
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
    },
    handleClick(tab, event) {
      console.log(tab, event)
      axios
        .get(
          '/order/selectByStatus/' + this.currentPage + '/' + this.pageSize,
          {
            params: {
              username: window.sessionStorage.getItem('token'),
              status: this.activeName
            }
          }
        )
        .then(res => {
          console.log(res)
          if (this.activeName == '1') {
            this.approvalData = res.data.data
          } else if (this.activeName == '2') {
            this.collectData = res.data.data
          } else if (this.activeName == '3') {
            this.rentingData = res.data.data
          } else if (this.activeName == '4') {
            this.returnData = res.data.data
          }
        })
    },
    receive(index, rows) {
      this.$confirm(
        'Please Confirm you receive the order : ' +
          this.collectData[index].orderId,
        'Remind',
        {
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
          type: 'warning'
        }
      )
        .then(() => {
          axios.post('/order/endDate/', this.collectData[index]).then(res => {
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
    }
  }
}
</script>

<style scoped>
.el-main {
  display: flex;
  flex-direction: column;
  margin-left: 100px;
  margin-right: 100px;
}
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 80%;
}
</style>
