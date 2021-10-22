<template>
  <div>
    <h1>{{ msg }}</h1>
    <table>
      <tr>
        <td>id</td>
        <td>name</td>
        <td>addressid</td>
      </tr>
      <tr v-for="item in userList">
        <td>{{ item.userid }}</td>
        <td>{{ item.username }}</td>
        <td>{{ item.departmentid }}</td>
      </tr>
    </table>
  </div>
</template>



<script>

import axios from "axios";

export default {
  name: "AdminHome",
  data() {
    return {
      msg: "Hello Vue",
      userList: [],
    };
  },
  mounted() {
    this.getAllUsers();
  },
  methods: {
    getAllUsers() {
      axios.get("admin/home").then(function(resp) {
        console.log(resp);
        this.userList = resp.data.result;
      });
    },
  },
};
</script>

<style scoped></style>
