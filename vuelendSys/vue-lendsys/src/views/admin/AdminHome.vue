<template>
  <el-row class="home" :getter="20">
    <el-col :span="6" class="user">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="user">
          <img :src="userImg" alt="" />
          <div class="login-info">
            <div class="name">
              <p>
                User name: <span>{{ username }}</span>
              </p>
            </div>
            <div class="access">
              <p>
                User Level: <span>{{ userlevel }}</span>
              </p>
            </div>
            <div class="access">
              <p>
                Date: <span>{{ new Date() }}</span>
              </p>
            </div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="18">
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
            >
            </el-table-column>
            <el-table-column align="center" prop="cartNum" label="Quantity">
            </el-table-column>
            <el-table-column align="center" prop="endDate" label="Return Date">
            </el-table-column>
            <el-table-column align="center" label="Return">
              <template #default="scope">
                <el-button
                  @click.prevent="deleteRow(scope.$index, tableData)"
                  type="text"
                  size="small"
                  @click="returnOrder"
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
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      userImg: require('../../assets/logo2.png'),
      username: 'username',
      userlevel: 'userlevel',
      tableData: []
    }
  },
  created() {
    this.load()
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
    }
  }
}
</script>

<style>
@import '../../assets/css/adminhome.css';
</style>
