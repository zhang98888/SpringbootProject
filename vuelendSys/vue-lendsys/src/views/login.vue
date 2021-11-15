<template>
  <div class="login-container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/logo2.png" alt="" />
      </div>
      <!-- Form Name: loginForm  Rules: loginFormRules -->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <!--User Id-->
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            prefix-icon="iconfont el-icon-user-solid"
          ></el-input>
        </el-form-item>
        <!--Password-->
        <el-form-item prop="pwd">
          <el-input
            v-model="loginForm.pwd"
            prefix-icon="iconfont el-icon-s-cooperation"
            type="password"
          ></el-input>
        </el-form-item>
        <!--Button-->
        <el-form-item class="btns">
          <el-button type="primary" @click="login">Log In</el-button>
          <el-button type="info" @click="resetLoginForm">Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

export default {
  data() {
    return {
      // It is used for login form data
      loginForm: {
        username: 'user Name',
        pwd: '123456'
      },
      loginFormRules: {
        username: [
          {
            required: true,
            message: 'Please enter user Id',
            trigger: 'blur'
          },
          {
            min: 2,
            max: 10,
            message: 'The length is between 2 to 10',
            trigger: 'blur'
          }
        ],
        password: [
          {
            required: true,
            message: 'Please Enter password',
            trigger: 'blur'
          },
          {
            min: 6,
            max: 15,
            message: 'The length is between 4 to 10',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    // reset method
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields()
    },
    // log in method
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return
      })

      axios.post('login', this.loginForm).then(function(resp) {
        // alert(resp);
        if (resp.data.status === 1000) {
          ElMessage({
            showClose: true,
            message: "success",
            type: 'success'
          })
          window.sessionStorage.setItem('token', resp.data.msg)
          router.push('/')
        } else {
          ElMessage({
            showClose: true,
            message: resp.data.msg,
            type: 'error'
          })
        }

        console.log(resp)
      })
    }
  }
}
</script>

<style lang="less">
.login-container {
  background-color: #dddddd;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #bdc3c7;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid lightsteelblue;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px lightsteelblue;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }
}

.btns {
  display: flex;
  justify-content: flex-end;
  margin-left: 232px;
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
</style>
