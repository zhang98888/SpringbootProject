<template>
  <el-container>
    <el-header>
      <div
        style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc;display: flex"
      >
        <div
          style="width: 200px; padding-left: 30px; font-weight: bold; color: cadetblue"
        >
          Admin Management
        </div>
        <div style="flex: 1"></div>
        <div style="width: 150px">
          <el-dropdown>
            <span class="el-dropdown-link">
              Dropdown List<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="a">Action 1</el-dropdown-item>
                <el-dropdown-item command="b">Action 2</el-dropdown-item>
                <el-dropdown-item command="c">Action 3</el-dropdown-item>
                <el-dropdown-item command="d" disabled
                  >Action 4</el-dropdown-item
                >
                <el-dropdown-item command="e" divided
                  >Action 5</el-dropdown-item
                >
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <div>
          <el-menu
            style="width: 200px; min-height: calc(100vh - 50px)"
            text-color="#5F9EA0"
            default-active="2"
            class="el-menu-vertical-demo"
          >
            <el-sub-menu index="1-4">
              <template #title>Admin</template>
              <el-menu-item index="1-4-1">Admin Information</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <template #title>User Management</template>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <template #title>Item Management</template>
            </el-menu-item>
            <el-menu-item index="4" disabled>
              <i class="el-icon-setting"></i>
              <template #title>function </template>
            </el-menu-item>
          </el-menu>
        </div>
      </el-aside>
      <el-container>
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
            <div>
                <h1>{{message}}</h1>
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
                <template #default="props">
                  <el-form
                    label-position="left"
                    inline
                    class="demo-table-expand"
                  >
                    <el-form-item label="user name">
                      <span>{{ props.row.username }}</span>
                    </el-form-item>
                    <el-form-item label="user Id">
                      <span>{{ props.row.userid }}</span>
                    </el-form-item>
                    <el-form-item label="Department ID">
                      <span>{{ props.row.departmentId }}</span>
                    </el-form-item>
                    <el-form-item label="User Email">
                      <span>{{ props.row.userEmail }}</span>
                    </el-form-item>
                    <el-form-item label="Level">
                      <span>{{ props.row.userLevel }}</span>
                    </el-form-item>
                    <el-form-item label="Working Status">
                      <span>{{ props.row.status }}</span>
                    </el-form-item>
                    <el-form-item label="user modtime">
                      <span>{{ props.row.userModtime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>

              <el-table-column label="Employee ID" prop="userid" sortable>
              </el-table-column>
              <el-table-column label="Name" prop="username"> </el-table-column>
              <el-table-column label="Department ID" prop="departmentId">
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
                  <el-form-item label="name" :label-width="120">
                    <el-input
                      v-model="form.userName"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="department" :label-width="120">
                    <el-input
                      v-model="form.department"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="id" :label-width="120">
                    <el-input
                      v-model="form.ID"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="departmentId" :label-width="120">
                    <el-input
                      v-model="form.departmentId"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="Level" :label-width="120">
                    <el-input
                      v-model="form.userLevel"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="address" :label-width="120">
                    <el-input
                      type="textarea"
                      v-model="form.address"
                      autocomplete="off"
                      style="width: 80%"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="status" :label-width="120">
                    <el-radio v-model="form.status" label="Home">Home</el-radio>
                    <el-radio v-model="form.status" label="Office"
                      >Office</el-radio
                    >
                    <el-radio v-model="form.status" label="Unknown"
                      >Unknown</el-radio
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
          <el-footer>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="10"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination
          ></el-footer>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>

import requst from "@/utils/requst";
import axios from "axios";

export default {
  name: "Home",
  components: {},
  methods: {
    handleEdit() {},
    handleRemove() {},
    addData() {
      this.dialogFormVisible = true;
      this.form = {};
    },
    save() {
      axios.get("admin/regist", this.form).then((requst) => {
        console.log(requst);
      });
    },
  },
  data() {
    return {
      form: {},
      dialogFormVisible: false,
      search: "",
      currentPage: 1,
      total: 10,
      tableData: [],
    };
  },
  mounted() {
    this.create();
  },
  create() {
    axios
      .get("admin/home")
      .then((requst) => {
        console.log(res.data);
        this.tableData = requst.data;
      })
      .catch(function(error) {
        console.log(error);
      });
  },
};
</script>
<style >
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
