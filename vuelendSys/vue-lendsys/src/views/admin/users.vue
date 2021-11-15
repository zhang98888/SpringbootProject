<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" round @click="addData"
        >Add Data
        <i class="el-icon-edit el-icon--right"></i>
      </el-button>
      <el-button type="primary" round
        >Import<i class="el-icon-upload el-icon--right"></i
      ></el-button>

      <el-button type="primary" round
        >DownLoad
        <i class="el-icon-download el-icon--right"></i>
      </el-button>
    </div>
    <div style="margin: 10px 0">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="User Id">
          <el-input
            v-model="searchForm.userid"
            placeholder="User Id"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="User level">
          <el-input
            v-model="searchForm.userlevel"
            placeholder="User level"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="User Name">
          <el-input
            v-model="searchForm.username"
            placeholder="User Name"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            style="margin-left: 5px"
            @click="searchInfo"
            >Search</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="tableData" style="width: 100%" stripe>
      <el-table-column type="expand">
        <template #default="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="user Sex">
              <span>{{ props.row.userSex }}</span>
            </el-form-item>
            <el-form-item label="User Email">
              <span>{{ props.row.userEmail }}</span>
            </el-form-item>
            <el-form-item label="modify time">
              <span>{{ props.row.userModtime }}</span>
            </el-form-item>
            <el-form-item label="password">
              <span>{{ props.row.userpwd }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="Employee ID"
        prop="userid"
        sortable
      ></el-table-column>
      <el-table-column label="Name" prop="username"> </el-table-column>
      <el-table-column
        label="Department ID"
        prop="departmentid"
      ></el-table-column>
      <el-table-column label="Level" prop="userLevel"></el-table-column>
      <el-table-column
        label="Working Status"
        prop="workingStatus"
      ></el-table-column>

      <el-table-column label="Operation" fixed="right">
        <template #default="scope">
          <el-button
            size="mini"
            @click.prevent="handleEdit(scope.$index, tableData)"
          >
            Edit
          </el-button>
          <!-- <el-popconfirm title="Do you want to delete?"></el-popconfirm> -->
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
      <el-dialog title="User Information" v-model="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="user name" :label-width="120">
            <el-input
              v-model="form.username"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="user id" :label-width="120">
            <el-input
              v-model="form.userid"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="department Id" :label-width="120">
            <el-input
              v-model="form.departmentid"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="user Email" :label-width="120">
            <el-input
              v-model="form.userEmail"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="user Level" :label-width="120">
            <el-input
              v-model="form.userLevel"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="password" :label-width="120">
            <el-input
              v-model="form.userpwd"
              autocomplete="off"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="status" :label-width="120">
            <el-radio v-model="form.workingStatus" label="Home">Home</el-radio>
            <el-radio v-model="form.workingStatus" label="Office"
              >Office</el-radio
            >
            <el-radio v-model="form.workingStatus" label="Unknown"
              >Unknown</el-radio
            >
          </el-form-item>
          <el-form-item label="userSex" :label-width="120">
            <el-radio v-model="form.userSex" label="Male">Male</el-radio>
            <el-radio v-model="form.userSex" label="Female">Female</el-radio>
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
      <el-dialog title="User Information" v-model="editFormVisible">
        <el-form :model="editForm">
          <el-form-item label="user name" :label-width="120">
            <el-input v-model="editForm.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="user id" :label-width="120">
            <el-input v-model="editForm.userid" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="department Id" :label-width="120">
            <el-input
              v-model="editForm.departmentid"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="user Email" :label-width="120">
            <el-input
              v-model="editForm.userEmail"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="user Level" :label-width="120">
            <el-input
              v-model="editForm.userLevel"
              style="width: 80%"
            ></el-input>
          </el-form-item>
          <el-form-item label="password" :label-width="120">
            <el-input v-model="editForm.userpwd" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="status" :label-width="120">
            <el-radio v-model="editForm.workingStatus" label="Home"
              >Home</el-radio
            >
            <el-radio v-model="editForm.workingStatus" label="Office"
              >Office</el-radio
            >
            <el-radio v-model="editForm.workingStatus" label="Unknown"
              >Unknown</el-radio
            >
          </el-form-item>
          <el-form-item label="userSex" :label-width="120">
            <el-radio v-model="editForm.userSex" label="Male">Male</el-radio>
            <el-radio v-model="editForm.userSex" label="Female"
              >Female</el-radio
            >
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
import request from '../../utils/requst'
import { resolveDirective } from '@vue/runtime-core'

export default {
  name: 'users',
  data() {
    return {
      form: {},
      searchForm: {},
      tableData: [],
      dialogFormVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 400,
      editFormVisible: false,
      editForm: {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    handleEdit(index, rows) {
      this.editFormVisible = true
      this.editForm = this.tableData[index]
    },
    editFormsave() {
      axios.post('/admin/editUserInfo', this.editForm).then(res => {
        if (res.data.status === 1000) {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          this.tableData[index] = res.data.data
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
    handleRemove(index, rows) {
      axios
        .delete('/admin/delete/' + this.tableData[index].userid)
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
          router.push('/admin/users')
        })
      console.log(index)
      console.log(rows)
    },
    addData() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      axios.post('/admin/addUserInfo', this.form).then(res => {
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
    load() {
      axios
        .get('/admin/pageUser/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    },
    searchInfo() {
      axios
        .post(
          '/admin/pageSearchUser/' + this.currentPage + '/' + this.pageSize,
          this.searchForm
        )
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
      this.searchForm = {}
    },
    handleSizeChange(val) {
      console.log(` ${val} per page `)
    },
    handleCurrentChange(val) {
      console.log(`now page: ${val}`)
      axios
        .get('/admin/pageUser/' + this.currentPage + '/' + this.pageSize)
        .then(res => {
          console.log(res)
          this.tableData = res.data.data
        })
    }
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 100px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.pagination {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
}
</style>
