import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
    baseURL: process.env.BASE_API,
    timeout: 5000
})

const service = axios.create({
    baseURL: process.env.BASE_API, // api 的 base_url
    timeout: 20000 // 请求超时时间
  })
  
  // request拦截器
  service.interceptors.request.use(
    config => {
      return config
    },
    error => {
      // Do something with request error
      console.log(error) // for debug
      Promise.reject(error)
    }
  )
  
  // response 拦截器
  service.interceptors.response.use(
    response => {
      const res = response.data
      // debugger
      if (res.code !== 1000) {
        ElMessage({
          message: res.message,
          type: 'error',
          duration: 5 * 1000
        })
        return Promise.reject('error')
      } else {
        return response.data
      }
    },
    error => {
      console.log('err' + error) // for debug
      ElMessage({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error)
    }
  )
  
  export default service



