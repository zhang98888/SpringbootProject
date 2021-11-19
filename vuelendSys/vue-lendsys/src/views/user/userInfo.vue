<template>
  <el-descriptions
    class="margin-top"
    title="User Info"
    :column="3"
    :size="size"
    border
    style="margin-top:20px"
  >
    <template #extra>
      <el-button type="primary" size="small" @click="editUserInfo"
        >Edit Information</el-button
      >
      <el-button type="primary" size="small" @click="addAddress"
        >Add Address</el-button
      >
    </template>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-user"></i>
        User Name
      </template>
      {{ this.userTable.username }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-mobile-phone"></i>
        Mobile
      </template>
      {{ this.userTable.userMobie }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-office-building"></i>
        Department
      </template>
      {{ this.userTable.departmentid }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-message"></i>
        Email
      </template>
      {{ this.userTable.userEmail }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-location"></i>
        Status
      </template>
      {{ this.userTable.workingStatus }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-postcard"></i>
        Level
      </template>
      {{ this.userTable.userLevel }}
    </el-descriptions-item>
  </el-descriptions>
  <div class="addressStyle">
    <h1>Address</h1>
    <el-table :data="addressTable" style="width: 100%; margin-top:20px">
      <el-table-column prop="addrid" label="Id" width="80"> </el-table-column>
      <el-table-column prop="receiverName" label="Receiver" width="160">
      </el-table-column>
      <el-table-column prop="receiverMobile" label="Mobile"> </el-table-column>
      <el-table-column prop="address" label="Address"> </el-table-column>
      <el-table-column prop="city" label="City"> </el-table-column>
      <el-table-column prop="postcode" label="Postcode"> </el-table-column>
      <el-table-column prop="defaultAddress" label="default">
        <template v-slot="scope">
          <span v-if="scope.row.defaultAddress === '1'">Yes</span>
          <span v-if="scope.row.defaultAddress === '2'">No</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="operation">
        <template #default="scope">
          <el-button
            @click.prevent="edit(scope.$index, addressTable)"
            size="mini"
            >Edit</el-button
          >
          <el-button
            @click.prevent="remove(scope.$index, addressTable)"
            size="mini"
            >remove</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog title="User Address" v-model="dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="user id" :label-width="120">
        <el-input
          v-model="form.userid"
          autocomplete="on"
          style="width: 80%"
          :disabled="true"
        ></el-input>
      </el-form-item>
      <el-form-item label="receiver name" :label-width="120">
        <el-input
          v-model="form.receiverName"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Mobile" :label-width="120">
        <el-input
          v-model="form.receiverMobile"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Address" :label-width="120">
        <el-input
          v-model="form.address"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="City" :label-width="120">
        <el-input
          v-model="form.city"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Postcode" :label-width="120">
        <el-input
          v-model="form.postcode"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="status" :label-width="120">
        <el-radio v-model="form.defaultAddress" label="1">default</el-radio>
        <el-radio v-model="form.defaultAddress" label="2">no default</el-radio>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="saveForm">Confirm</el-button>
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog title="User Address" v-model="dialogEdidFormVisible">
    <el-form :model="editForm">
      <el-form-item label="receiver name" :label-width="120">
        <el-input
          v-model="editForm.receiverName"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Mobile" :label-width="120">
        <el-input
          v-model="editForm.receiverMobile"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Address" :label-width="120">
        <el-input
          v-model="editForm.address"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="City" :label-width="120">
        <el-input
          v-model="editForm.city"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="Postcode" :label-width="120">
        <el-input
          v-model="editForm.postcode"
          autocomplete="off"
          style="width: 80%"
        ></el-input>
      </el-form-item>
      <el-form-item label="status" :label-width="120">
        <el-radio v-model="editForm.defaultAddress" label="1">default</el-radio>
        <el-radio v-model="editForm.defaultAddress" label="2"
          >no default</el-radio
        >
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="saveEdirForm">Confirm</el-button>
        <el-button @click="dialogEdidFormVisible = false">Cancel</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'

export default {
  data() {
    return {
      userTable: [],
      addressTable: [],
      dialogFormVisible: false,
      form: {},
      dialogEdidFormVisible: false,
      editForm: {}
    }
  },
  created() {
    this.load(), this.loadAddre()
  },
  methods: {
    load() {
      let data = new FormData()
      const tokenStr = window.sessionStorage.getItem('token')
      data.append('username', tokenStr)
      axios.post('/getUserInfo', data).then(res => {
        console.log(res.data, data)
        this.userTable = res.data.data
      })
    },
    loadAddre() {
      axios
        .get('/address/', {
          params: { username: window.sessionStorage.getItem('token') }
        })
        .then(res => {
          console.log(res.data.data)
          this.addressTable = res.data.data
        })
    },
    addAddress() {
      this.dialogFormVisible = true
      this.form.userid = this.userTable.userid
    },
    saveForm() {
      axios.post('/address/setAddress', this.form).then(res => {
        if (res.data.code == 1001) {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'error'
          })
        } else {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          this.dialogFormVisible = false
          this.form = {}
          this.loadAddre()
        }
      })
    },
    edit(index, row) {
      this.dialogEdidFormVisible = true
      this.editForm = this.addressTable[index]
    },
    saveEdirForm() {
      axios.post('/address/editAddress', this.editForm).then(res => {
        if (res.data.code == 1001) {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'error'
          })
        } else {
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          this.dialogEdidFormVisible = false
          this.editForm = {}
          this.loadAddre()
        }
      })
    },
    remove(index, rows) {
    console.log(this.addressTable[index])
      this.$confirm('Do you want to delete?', 'Remind', {
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel',
        type: 'warning'
      })
        .then(() => {
          axios
            .delete('/address/deleteAddress/' + this.addressTable[index].addrid)
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
    }
  }
}
</script>

<style scoped>
.addressStyle {
  margin-top: 20px;
}
</style>
