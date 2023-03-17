import requests from './../utils/request'

export function searchDataset (data) {
  return requests({
    url: '/yls/area/searchDataset', // 请求地址
    method: 'post', // 请求方法
    data
  })
}

export function downloadDatasetZip (data) {
  return requests({
    url: '/yls/dataset/download/zip', // 请求地址
    responseType: 'blob',
    method: 'post', // 请求方法
    data
  })
}
