<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" round @click="addData"
        >Add Data
        <i class="el-icon-edit el-icon--right"></i>
      </el-button>
    </div>
    <div style="margin: 10px 0">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="Product Id">
          <el-input
            v-model="searchForm.productId"
            placeholder="Product Id"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="Product Name">
          <el-input
            v-model="searchForm.productName"
            placeholder="Product name"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            style="margin-left: 5px"
            @click="searchProduct"
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
      <el-table-column prop="productId" label="product id" width="180">
      </el-table-column>
      <el-table-column prop="productName" label="product Name" width="180">
      </el-table-column>
      <el-table-column prop="categoryId" label="category Id"> </el-table-column>
      <el-table-column prop="rentNum" label="stock"> </el-table-column>
      <el-table-column prop="productStatus" label="status"> </el-table-column>
      <el-table-column prop="productDes" label="description"> </el-table-column>
      <el-table-column label="Operation" fixed="right">
        <template #default="scope">
          <el-button
            size="mini"
            @click.prevent="handleEdit(scope.$index, tableData)"
          >
            Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click.prevent="handleRemove(scope.$index, tableData)"
          >
            Remove
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
    <div>
      <el-dialog title="Product Information" v-model="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="product id" :label-width="120">
            <el-input
              v-model="form.productId"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="category id" :label-width="120">
            <el-input
              v-model="form.categoryId"
              autocomplete="off"
              style="width: 80%"
              sortable
            ></el-input>
          </el-form-item>
          <el-form-item label="product Name" :label-width="120">
            <el-input
              v-model="form.productName"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="stock" :label-width="120">
            <el-input
              v-model="form.rentNum"
              autocomplete="off"
              style="width: 80%"
              sortable
            ></el-input>
          </el-form-item>
          <el-form-item label="product status" :label-width="120">
            <el-radio v-model="form.productStatus" label="1"
              >Available</el-radio
            >
            <el-radio v-model="form.productStatus" label="2"
              >Not Available</el-radio
            >
          </el-form-item>
          <el-form-item label="product des" :label-width="120">
            <el-input
              v-model="form.productDes"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="save">Confirm</el-button>
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="Product Information" v-model="editFormVisible">
        <el-form :model="editForm">
          <el-form-item label="product id" :label-width="120">
            <el-input
              v-model="editForm.productId"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="category id" :label-width="120">
            <el-input
              v-model="editForm.categoryId"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="product Name" :label-width="120">
            <el-input
              v-model="editForm.productName"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="stock" :label-width="120">
            <el-input v-model="editForm.rentNum" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="product status" :label-width="120">
            <el-radio v-model="editForm.productStatus" label="1"
              >Available</el-radio
            >
            <el-radio v-model="editForm.productStatus" label="2"
              >Not Available</el-radio
            >
          </el-form-item>
          <el-form-item label="product des" :label-width="120">
            <el-input
              v-model="editForm.productDes"
              style="width: 80%"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="editFormsave">Confirm</el-button>
            <el-button @click="editFormVisible = false">Cancel</el-button>
          </span>
        </template>
      </el-dialog>
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
      total: 400,
      dialogFormVisible: false,
      form: {},
      editFormVisible: false,
      editForm: {}
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
        .get('/product/pageProduct/' + this.currentPage + '/' + this.pageSize)
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
        .get('/product/pageProduct/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    addData() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      axios.post('/product/addProductInfo', this.form).then(res => {
        if (res.data.status === 1000) {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          this.tableData.push(res.data.data)
        } else {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'error'
          })
        }
        console.log(res)
        this.dialogFormVisible = false
      })
    },
    handleRemove(index, rows) {
      axios
        .delete('/product/delete/' + this.tableData[index].productId)
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
      console.log(index)
      console.log(rows)
    },
    handleEdit(index, rows) {
      this.editFormVisible = true
      this.editForm = this.tableData[index]
    },
    editFormsave() {
      axios.post('/product/editProductInfo', this.editForm).then(res => {
        if (res.data.status === 1000) {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          this.tableData[index] = res.data.data
          this.editForm = {}
          this.editFormVisible = false
        } else {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'error'
          })
        }

        console.log(res)
      })
    },
    searchProduct() {
      axios
        .post(
          '/product/searchProduct/' + this.currentPage + '/' + this.pageSize,
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
