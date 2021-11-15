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
        <el-form-item label="Picture Id">
          <el-input
            v-model="searchForm.pictureId"
            placeholder="Picture Id"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            style="margin-left: 5px"
            @click="searchImg"
            >Search</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
      <el-table-column prop="productId" label="product Id" width="180" sortable>
      </el-table-column>
      <el-table-column prop="pictureId" label="picture Id" sortable>
      </el-table-column>
      <el-table-column prop="url" label="url"> </el-table-column>
      <el-table-column prop="isMain" label="isMain"> </el-table-column>
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
      <el-dialog title="Product Img Information" v-model="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="product id" :label-width="120">
            <el-input
              v-model="form.productId"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="picture id" :label-width="120">
            <el-input
              v-model="form.pictureId"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="product status" :label-width="120">
            <el-radio v-model="form.isMain" label="1">Is_Main</el-radio>
            <el-radio v-model="form.isMain" label="2">Not Main</el-radio>
          </el-form-item>
          <el-form-item label="url" :label-width="120">
            <el-upload
              action="http://localhost:9000/images/upload"
              :on-success="fileUploadSuccess"
            >
              <el-button size="small" type="primary">upload</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="save">Confirm</el-button>
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
          </span>
        </template>
      </el-dialog>
      <el-dialog title="Product Img Information" v-model="editFormVisible">
        <el-form :model="editForm">
          <el-form-item label="product id" :label-width="120">
            <el-input
              v-model="editForm.productId"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="picture id" :label-width="120">
            <el-input
              v-model="editForm.pictureId"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="product status" :label-width="120">
            <el-radio v-model="editForm.isMain" label="1">Is_Main</el-radio>
            <el-radio v-model="editForm.isMain" label="2">Not Main</el-radio>
          </el-form-item>
          <el-form-item label="url" :label-width="120">
            <el-upload
              action="http://localhost:9000/images/upload"
              :on-success="fileUploadSuccessEdit"
            >
              <el-button size="small" type="primary">upload</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="editSave">Confirm</el-button>
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
      pageSize: 10,
      total: 400,
      dialogFormVisible: false,
      form: {},
      editForm: {},
      editFormVisible: false
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios
        .get('/goodPic/getAllGoodPic/' + this.currentPage + '/' + this.pageSize)
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
        .get('/goodPic/getAllGoodPic/' + this.currentPage + '/' + this.pageSize)
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
      this.tableData.productImg = this.imageUrl
      axios.post('/goodPic/addGoodPic', this.form).then(res => {
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
        this.form = {}
      })
    },
    fileUploadSuccess(res) {
      console.log(res)
      this.form.url = res.data
    },
    handleRemove(index, rows) {
      axios
        .delete('/goodPic/deleteGoodPic/' + this.tableData[index].pictureId)
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
      console.log(this.tableData[index])
      this.editFormVisible = true
      this.editForm = this.tableData[index]
    },
    fileUploadSuccessEdit(res) {
      this.editForm.url = res.data
    },
    editSave() {
      axios.post('/goodPic/editGoodPic', this.editForm).then(res => {
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
    searchImg() {
      axios
        .post(
          '/goodPic/searchGoodPic/' + this.currentPage + '/' + this.pageSize,
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
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
}
</style>
