<template>
  <el-container>
    <el-aside width="200px">
        <common-aside></common-aside>
    </el-aside>
    <el-container>
      <el-header>
        <div
          style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc;display: flex"
        >
          <div
            style="width: 200px; padding-left: 0px; font-weight: bold; color: cadetblue"
          >
            Admin Management
          </div>
        </div>
      </el-header>
      <el-main>
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
            <el-input
              v-model="search"
              placeholder="Please Search"
              style="width: 20%"
            ></el-input>
            <el-button type="primary" round style="margin-left: 5px">
              <i class="el-icon-search"></i>
            </el-button>
          </div>
          <el-table :data="tableData" style="width: 100%" stripe>
            <el-table-column type="expand">
              <template #default="users">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="user name">
                    <span>{{ users.row.username }}</span>
                  </el-form-item>
                  <el-form-item label="user Id">
                    <span>{{ users.row.userid }}</span>
                  </el-form-item>
                  <el-form-item label="user Sex">
                    <span>{{ users.row.userSex }}</span>
                  </el-form-item>
                  <el-form-item label="Department ID">
                    <span>{{ users.row.departmentid }}</span>
                  </el-form-item>
                  <el-form-item label="User Email">
                    <span>{{ users.row.userEmail }}</span>
                  </el-form-item>
                  <el-form-item label="Level">
                    <span>{{ users.row.userLevel }}</span>
                  </el-form-item>
                  <el-form-item label="Working Status">
                    <span>{{ users.row.workingStatus }}</span>
                  </el-form-item>
                  <el-form-item label="modify time">
                    <span>{{ users.row.userModtime }}</span>
                  </el-form-item>
                  <el-form-item label="password">
                    <span>{{ users.row.userpwd }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>

            <el-table-column label="Employee ID" prop="userid" sortable>
            </el-table-column>
            <el-table-column label="Name" prop="username"> </el-table-column>
            <el-table-column label="Department ID" prop="departmentid">
            </el-table-column>

            <el-table-column label="Operation">
              <template #default="scope">
                <el-button
                  size="mini"
                  @click.prevent="handleEdit(scope.$index, tableData)"
                >
                  Edit
                </el-button>
                <el-popconfirm title="Do you want to delete?"></el-popconfirm
                >-->
                <el-button
                  size="mini"
                  type="danger"
                  @click.prevent="handleRemove(scope.$index, scope.row)"
                >
                  Remove
                </el-button>
              </template>
            </el-table-column>
          </el-table>
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
                  <el-radio v-model="form.workingStatus" label="Home"
                    >Home</el-radio
                  >
                  <el-radio v-model="form.workingStatus" label="Office"
                    >Office</el-radio
                  >
                  <el-radio v-model="form.workingStatus" label="Unknown"
                    >Unknown</el-radio
                  >
                </el-form-item>
                <el-form-item label="userSex" :label-width="120">
                  <el-radio v-model="form.userSex" label="Male">Male</el-radio>
                  <el-radio v-model="form.userSex" label="Female"
                    >Female</el-radio
                  >
                </el-form-item>
              </el-form>
              <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogFormVisible = false"
                    >Cancel</el-button
                  >
                  <el-button type="primary" @click="save">Confirm</el-button>
                </span>
              </template>
            </el-dialog>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'
import requst from '../utils/requst'
import CommonAside from '../components/commonAside.vue'


export default {
  name: 'AdminApp',
  components: {CommonAside},

  data() {
    return {
      form: {},
      tableData: [],
      dialogFormVisible: false,
      search: '',
      listLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleEdit() {},
    handleRemove() {},
    addData() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      axios.POST('admin/addUserInfo', this.form.target).then(res => {
        console.log(res)
      })
    },
    getList(page = 1) {
      this.page = page
      teacher
        .getPageTeacherCondition(this.page, this.limit, this.teacherQuery)
        .then(response => {
          // console.log(response);
          this.list = response.data.rows
          this.total = response.data.total
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style>
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
.el-header,
.el-footer {
  text-align: center;
  line-height: 60px;
}

.el-aside {
  line-height: 200px;
}

.el-main,
body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
