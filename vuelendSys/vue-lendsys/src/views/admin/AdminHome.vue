<template>
  <el-row class="home" :getter="20" style=" height: 255px;margin-top: 20px;">
    <el-col :span="8" class="user">
      <el-card
        shadow="hover"
        :body-style="{ padding: '0px' }"
        style="display: flex;justify-content: center;align-items: center;"
      >
        <div class="user">
          <img :src="userImg" alt="" />
          <div class="login-info">
            <div class="name">
              <p>
                User name: <span>{{ username }}</span>
              </p>
            </div>
            <div class="name">
              <p>
                User Level: <span>{{ userlevel }}</span>
              </p>
            </div>
            <div class="name">
              <p>
                Date: <span>{{ dateTime }}</span>
              </p>
            </div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="16">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="user">
          <el-table
            :data="tableData"
            style="width: 100%"
            max-height="240"
            fit="true"
          >
            <el-table-column
              align="center"
              prop="productName"
              label="product Name"
              width="300"
            >
            </el-table-column>
            <el-table-column align="center" prop="cartNum" label="Quantity">
            </el-table-column>
            <el-table-column
              align="center"
              prop="endDate"
              label="Return Date"
              width="250"
            >
              <template v-slot="scope">
                {{ endDateCal(scope.row.endDate) }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="Return">
              <template #default="scope">
                <el-button
                  @click.prevent="returnOrder(scope.$index, tableData)"
                  type="text"
                  size="small"
                >
                  Return
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-card>
    </el-col>
  </el-row>
  <el-card
    shadow="hover"
    :body-style="{ padding: '0px' }"
    style="margin-bottom:0px"
  >
    <div class="user">
      <el-table
        :data="approvalTable"
        style="width: 100%"
        max-height="240"
        fit="true"
      >
        <el-table-column
          align="center"
          prop="productName"
          label="product Name"
          width="300"
        >
        </el-table-column>
        <el-table-column align="center" prop="cartNum" label="Quantity">
        </el-table-column>
        <el-table-column align="center" prop="userId" label="User Id">
        </el-table-column>
        <el-table-column
          align="center"
          prop="startDate"
          label="Start Date"
          width="250"
        >
          <template v-slot="scope">
            {{ endDateCal(scope.row.startDate) }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="Aprove">
          <template #default="scope">
            <el-button
              @click.prevent="approveOrder(scope.$index, approvalTable)"
              type="text"
              size="small"
            >
              Approve
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</template>

<script>
import axios from 'axios'
import { dateFormat } from '../../utils/dateFormate'
import { ElMessage } from 'element-plus'

export default {
  data() {
    return {
      userImg: require('../../assets/logo2.png'),
      username: window.sessionStorage.getItem('token'),
      userlevel: null,
      tableData: [],
      date: new Date(),
      approvalTable: []
    }
  },
  created() {
    this.load(), this.loadApprove(), this.getUser()
  },
  computed: {
    dateTime() {
      return dateFormat(this.date, 'yyyy-MM-dd hh:mm:ss')
    }
  },
  methods: {
    load() {
      axios
        .get('/order/getReturn', {
          params: { username: window.sessionStorage.getItem('token') }
        })
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    loadApprove() {
      axios
        .get('/order/approveRentOrder/', {
          params: { username: window.sessionStorage.getItem('token') }
        })
        .then(res => {
          console.log(res)
          this.approvalTable = res.data.data
        })
    },
    endDateCal(endDate) {
      return dateFormat(endDate, 'yyyy-MM-dd hh:mm:ss')
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
            message: '已取消删除'
          })
        })

      console.log(index)
      console.log(rows)
    },
    approveOrder(index, rows) {
      this.$confirm(
        'Please Confirm Order Id: ' + this.approvalTable[index].orderId,
        'Remind',
        {
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
          type: 'warning'
        }
      )
        .then(() => {
          axios.post('/order/approve/', this.approvalTable[index]).then(res => {
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
            message: '已取消删除'
          })
        })

      console.log(index)
      console.log(rows)
    },
    getUser() {
      let data = new FormData()
      const tokenStr = window.sessionStorage.getItem('token')
      data.append('username', tokenStr)
      axios
        .post('/getUserInfo', data)
        .then(res => {
          console.log(res.data, data)
          this.userlevel = res.data.data.userLevel
        })
    }
  }
}
</script>

<style>
@import '../../assets/css/adminhome.css';
</style>
