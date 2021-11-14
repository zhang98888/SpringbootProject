<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 80%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="productid" label="product Id" width="200">
        </el-table-column>
        <el-table-column prop="productName" label="product Name" width="200">
        </el-table-column>
        <el-table-column prop="cartNum" label="Quantity" width="200">
        </el-table-column>
        <el-table-column prop="cartTime" label="Date" show-overflow-tooltip>
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
      <el-dialog title="Borrow Information" v-model="borrowFormVisible" width="70%">
        <el-form :model="borrowForm">
          <el-table :data="formData" style="width: 100%">
            <el-table-column prop="productid" label="product Id" width="150">
            </el-table-column>
            <el-table-column
              prop="productName"
              label="product Name"
              width="150"
            >
            </el-table-column>
            <el-table-column prop="cartNum" label="Quantity" width="150">
            </el-table-column>
            <el-table-column prop="cartTime" label="Date" show-overflow-tooltip>
            </el-table-column>
          </el-table>
          <el-form-item label="Category id" :label-width="120">
            <el-input v-model="formData" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="borrowFormSave"
              >Confirm</el-button
            >
            <el-button @click="borrowFormVisible = false">Cancel</el-button>
          </span>
        </template>
      </el-dialog>
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
      multipleSelection: [],
      currentPage: 1,
      pageSize: 10,
      total: 400,
      borrowFormVisible: false,
      borrowForm: [],
      formData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    toggleSelection() {
      this.$refs.multipleTable.clearSelection()
      console.log(this.$refs.multipleTable)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    load() {
      axios
        .get('/cart/' + this.currentPage + '/' + this.pageSize, {
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
    borrow() {
        this.formData = this.multipleSelection
        this.borrowFormVisible = true
      },
    borrowFormSave() {
        
        this.formData ={}
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
