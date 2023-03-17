<template>

  <div>
    <div class="sidebar-logo-container">
      <img class="sidebar-logo" src="@/assets/images/logo_1.png" />
      <h1 class="sidebar-title">全球LUCCs数据集再分析与共享系统</h1>
    </div >

      <div class="sidebar-tool">
        <div class="sidebar-tool-area">
          <el-tabs type="border-card"  style="height: 80px" >
<!--            <el-tab-pane label="行政区域">-->
<!--              <el-select v-model="value" placeholder="请选择国家"  >-->
<!--                <el-option-->
<!--                  v-for="item in options"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value"-->
<!--                  :disabled="item.disabled">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--              <el-select v-model="value" placeholder="请选择省">-->
<!--                <el-option-->
<!--                  v-for="item in options"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value"-->
<!--                  :disabled="item.disabled">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--              <el-select v-model="value" placeholder="请选择市">-->
<!--                <el-option-->
<!--                  v-for="item in options"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value"-->
<!--                  :disabled="item.disabled">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </el-tab-pane>-->
            <el-tab-pane label="地图选择">
              <el-button type="primary" @click="mapVecSelect" size="mini">地图选择</el-button>
              <el-button @click="download" type="info" plain size="mini" class="download">下 载</el-button>
            </el-tab-pane>
<!--            <el-tab-pane label="行列号">行列号</el-tab-pane>-->

          </el-tabs>

        </div>
        <el-scrollbar class="scrollbar">
          <div class="box">
            <ul class="sidebar-layer-item" v-for="(item,i) in this.dataSetList" :key="i">
              <li class="item-title"> 名称：{{item.dataSetName}}</li>
              <li class="item-info"> 位置：{{item.filePath}}</li>
            </ul>
<!--            <div class="sidebar-layer-item" v-for="(item,i) in this.dataSetList" :key="i">-->
<!--              <div class="item-title">-->
<!--                名称：{{item.dataSetName}}-->
<!--              </div>-->
<!--              <div class="item-info">-->
<!--                位置：{{item.filePath}}-->

<!--              </div>-->
<!--            </div>-->
          </div>
        </el-scrollbar>

      </div>

  </div>
</template>

<script>
import {downloadDatasetZip} from '../../api'

export default {
  name: 'Aside',
  data () {
    return {
      value1: null,
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶',
        disabled: true
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: '',
      checked: false,
      dataSetList: null,
      corrdinate: null
    }
  },
  mounted () {
    this.$EventBus.$on('dataSetList', (event) => {
      this.dataSetList = event
    })
    this.$EventBus.$on('corrd', (event) => {
      this.corrdinate = event.corrdinates
    })
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(keyPath)
    },
    mapVecSelect () {
      this.$EventBus.$emit('vecSelect')
    },
    download () {
      debugger
      if (this.corrdinate == null) {
        this.$message({
          message: '暂无数据下载！',
          type: 'warning'
        })
      } else {
        this.downloadDatasetZip({'corrdinates': this.corrdinate})
      }
    },
    downloadDatasetZip (param) {
      downloadDatasetZip(param).then(res => {
        console.log('zip', res)
        var name = 'data.zip'
        var blob = new Blob([res.data])
        var url = window.URL.createObjectURL(blob)
        var aLink = document.createElement('a')
        aLink.style.display = 'none'
        aLink.href = url
        aLink.setAttribute('download', name)
        document.body.appendChild(aLink)
        aLink.click()
        document.body.removeChild(aLink) // 下载完成移除元素
        window.URL.revokeObjectURL(url) // 释放掉blob对象
      })
    }
  }
}
</script>

<style scoped>
.box {
  max-height: 600px;
}
.download {
  float: right;
  margin-right: 10px;
}
.sidebar-logo-container{
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  text-align: center;
  overflow: hidden;
}
.sidebar-logo {
  width: 32px;
  height: 32px;
  vertical-align: middle;
  margin-right: 6px;
}
.sidebar-title {
  display: inline-block;
  margin: 0;
  color: #fff;
  font-weight: 600;
  line-height: 50px;
  font-size: 14px;
  font-family: Avenir,Helvetica Neue,Arial,Helvetica,sans-serif;
  vertical-align: middle;
}

.sidebar-tool-area{
  height: 80px;
}
::v-deep {
  padding: 5px;
}
/deep/ .el-tabs__content{
  height: 28px;
  line-height: 28px;
  padding: 5px 1px 1px 1px;
}
/deep/ .el-tabs__content .el-select{
  height: 28px;
}
/deep/ .el-tabs__content .el-select .el-input{
  height: 28px;
}
/deep/ .el-tabs__content .el-select .el-input .el-input__inner{
  height: 28px;
  font-size: 12px;
  padding-left: 8px;
}
/deep/ .el-tabs__content .el-select .el-input .el-input__suffix{
  margin-top: 5px;
}
.el-scrollbar {
  font-size: 12px;
}
.sidebar-layer-item {
  display: flex;
  flex-direction: column;
  color: #FFFFFF;
  float: initial;
  border-bottom:1px solid #FFFFFF;
  padding-left: 20px;
}
.item-img {
  position: absolute;
  margin: 10px;
  height: 80px;
  width: 80px;
  background-color: #2c3e50;
}
.item-title {
  height: 30px;
  line-height: 30px;
  font-size: 14px;
}
.item-info {
  font-size: 14px;
}
.item-slider {
  position: absolute;
  float: right;
  width: 70%;
  margin-top: 66px;
  margin-left: 28px;
}
.el-slider {
  margin-left: 80px;
  width: 260px;
}
/deep/ .el-slider__runway {
  height: 2px;
}
/deep/ .el-slider__button{
  width: 8px;
  height: 8px;
}
/deep/ .el-slider__button-wrapper {
  width: 16px;
  height: 16px;
  top:-8px;
}
/deep/ .el-checkbox__inner {
  width: 12px;
  height: 12px;
}

</style>
