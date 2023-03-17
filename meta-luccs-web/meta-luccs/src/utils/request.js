import axios from 'axios'

// 创建axios实例
const requests = axios.create({
  baseURL: 'http://localhost:3003',
  timeout: '8000'
})

// 添加请求拦截器
requests.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  config.headers.token = '123'
  return config
})

// 添加响应拦截器
requests.interceptors.response.use(function (response) {
  console.log(response)
  if (response.status === 200) {
    console.log(123)
  }
  return response
}, function (error) {
  console.log(error)
})

// 对外暴露
export default requests
