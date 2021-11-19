<template>
  <el-container>
    <el-header style="height: 80px;">
      <commonHeader />
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="12"
          ><div class="grid-content">
            <el-carousel indicator-position="outside" height="500px" v-if="product.length > 0">
              <el-carousel-item
                v-for="item in product"
                :key="item"
                class="medium"
              >
                <img :src="item.url" style="height:500px; width:500px" />
              </el-carousel-item>
            </el-carousel></div
        ></el-col>
        <el-col :span="12"
          ><div class="grid-content">
            <el-descriptions
              v-if="product.length > 0"
              :title="title"
              :column="1"
              border
              style="padding-top: 20px;padding-right: 100px;padding-bottom:20px"
            >
              <el-descriptions-item
                label="Rent Number"
                label-align="left"
                align="left"
                label-class-name="my-label"
                class-name="my-content"
                width="20px"
                >{{ this.product[0].rentNum }}</el-descriptions-item
              >
              <el-descriptions-item
                label="Rent Level"
                label-align="left"
                align="left"
                >{{ this.product[0].productLevel }}</el-descriptions-item
              >
              <el-descriptions-item
                label="Rent Per Employee"
                label-align="left"
                align="left"
                >{{ this.product[0].productStatus }}</el-descriptions-item
              >
              <el-descriptions-item
                label="Description"
                label-align="left"
                align="left"
                >{{ this.product[0].productDes }}</el-descriptions-item
              >
            </el-descriptions>
            <div class="button" style="padding-right:100px">
              <el-input-number
                v-model="num"
                @change="handleChange"
                :min="1"
                :max="max"
                label="描述文字"
              ></el-input-number>
            </div>
            <div class="button" style="padding-right:100px">
              <el-button
                type="primary"
                plain
                style="margin-top:20px"
                @click="AddToCart"
                >Add to Cart</el-button
              >
            </div>
          </div></el-col
        >
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios'
import commonHeader from '../components/commonHeader.vue'
import { ElMessage } from 'element-plus'

export default {
  components: {
    commonHeader
  },
  data() {
    return {
      product: [],
      title: null,
      num: 1,
      max: null
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios.get('/product/' + this.$route.params.productId).then(res => {
        console.log(res)
        this.product = res.data.data
        this.title = this.product[0].productName
        this.max = this.product[0].productStatus + 1
      })
    },
    handleChange(value) {
      if (this.num > this.product[0].productStatus) {
        ElMessage({
          showClose: true,
          message:
            'Could borrow ' +
            this.product[0].productStatus +
            'pcs. If you want to borrow more, you need further approval',
          type: 'warning'
        })
      }
      console.log(value)
    },
    AddToCart() {
      var map = {
        productid: this.$route.params.productId,
        username: window.sessionStorage.getItem('token'),
        cartNum: this.num
      }
      axios.post('/cart/add', map).then(res => {
        console.log(map)
        this.$confirm(
          'Do you want to jump to Basket?',
          'Remind',
          {
            confirmButtonText: 'confirm',
            cancelButtonText: 'cancel',
            type: 'warning'
          }
        ).then(() => {
             this.$router.push('/basket')
          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: 'cancel'
            })
          })
      })
    }
  }
}
</script>

<style scoped lang="less">
.el-carousel {
  width: 500px;
  padding-left: 100px;
  padding-right: 200px;
}

.el-carousel__item h3 {
  font-size: 18px;
  opacity: 0.75;
  margin: 0;
  height: 500px !important;
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.button {
  display: flex;
  justify-content: flex-end;
  padding-right: 100 px;
}
</style>
