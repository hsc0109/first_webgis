<template>
  <div id="tdMap"></div>
</template>

<script>
import XYZ from 'ol/source/XYZ'
import {Map, View} from 'ol'

import TileLayer from 'ol/layer/Tile'
import OlExtLayerSwitcher from 'ol-ext/control/LayerSwitcher'
import TileWMS from 'ol/source/TileWMS'
import {GeoJSON} from 'ol/format'
import VectorSource from 'ol/source/Vector'
import VectorLayer from 'ol/layer/Vector'
import {Fill, Stroke, Style} from 'ol/style'
import CircleStyle from 'ol/style/Circle'
import {searchDataset} from '../../api'

export default {
  name: 'map',
  data () {
    return {
      map: null,
      singleSelectClick: null,
      vectorLayer: null,
      tileLayerArea1: null,
      tileLayerArea2: null,
      tileLayerArea3: null,
      tileLayerArea4: null,
      tileLayerArea5: null,
      tileLayerArea6: null,
      tileLayerArea7: null,
      dataSetList: null,
      dateSet: null,
      ctrl: null,
      selLayerName: null
    }
  },
  mounted () {
    // proj4.defs(
    //   'EPSG:4326',
    //   'GEOGCS["WGS 84",DATUM["WGS_1984",SPHEROID["WGS 84",6378137,298.257223563,AUTHORITY["EPSG","7030"]],AUTHORITY["EPSG","6326"]],PRIMEM["Greenwich",0,AUTHORITY["EPSG","8901"]],UNIT["degree",0.0174532925199433,AUTHORITY["EPSG","9122"]],AUTHORITY["EPSG","4326"]]'
    // )
    // register(proj4)
    this.initMap()
    this.$EventBus.$on('vecSelect', () => {
      this.selectByCorr()
    })
    this.$EventBus.$on('delVectorLayer', () => {
      this.map.removeLayer(this.vectorLayer)
    })
  },
  methods: {
    initMap () {
      var tiandiMapImg = new TileLayer({
        // source: new OSM(),
        name: '天地图矢量底图',
        source: new XYZ({
          corssOrigin: 'anonymous',
          url: 'https://t0.tianditu.gov.cn/DataServer?T=vec_w&x={x}&y={y}&l={z}&tk=079eac0dc9c60ab03a5a92f8b0dbaab6' // 矢量底图
        })
      })
      var tiandiMapCia = new TileLayer({
        name: '天地图矢量注记',
        source: new XYZ({
          crossOrigin: 'anonymous',
          url: 'https://t0.tianditu.gov.cn/DataServer?T=cva_w&x={x}&y={y}&l={z}&tk=079eac0dc9c60ab03a5a92f8b0dbaab6' // 矢量注记
        })
      })
      this.tileLayerArea1 = new TileLayer({
        name: '选取图层1',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs1',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.tileLayerArea2 = new TileLayer({
        name: '选取图层2',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs2',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.tileLayerArea3 = new TileLayer({
        name: '选取图层3',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs3',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.tileLayerArea4 = new TileLayer({
        name: '选取图层4',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs4',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.tileLayerArea5 = new TileLayer({
        name: '选取图层5',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs5',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.tileLayerArea6 = new TileLayer({
        name: '选取图层6',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs6',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true

      })
      this.tileLayerArea7 = new TileLayer({
        name: '选取图层7',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:dggs7',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      this.dateSet = new TileLayer({
        name: 'dateSet',
        source: new TileWMS({
          url: 'http://localhost:3003/geoserver/GOODLUCCs/wms',
          params: {
            'FORMAT': 'image/png',
            'VERSION': '1.1.1',
            tiled: true,
            'STYLES': '',
            'LAYERS': 'GOODLUCCs:data_set',
            'exceptions': 'application/vnd.ogc.se_inimage',
            tilesOrigin: 116.61346435546875 + ',' + 39.822837829589844
          }
        }),
        visible: false,
        baseLayer: true
      })
      // 实例化Map对象加载地图
      this.map = new Map({
        // 地图容器div的ID
        target: 'tdMap',
        // 地图容器中加载的图层
        layers: [tiandiMapImg, tiandiMapCia, this.tileLayerArea1, this.tileLayerArea2, this.tileLayerArea3, this.tileLayerArea4, this.tileLayerArea5, this.tileLayerArea6, this.tileLayerArea7, this.dateSet],
        // 地图视图设置
        view: new View({
          'projection': 'EPSG:4326',
          // 地图初始中心点
          'center': [116.703369, 39.892311],
          // 地图初始显示级别
          'zoom': 1,
          'maxZoom': 22
        })
      })
      this.ctrl = new OlExtLayerSwitcher({
        show_progress: true,
        extent: true,
        collapsed: true,
        trash: true,
        onchangeCheck: function (l) {
          debugger
          // this.map.removeLayer(this.vectorLayer)
          let name = l.get('name')
          sessionStorage.setItem('layerName', name)
          // switch (name) {
          //   case '选取图层1':
          //     this.selLayer = this.tileLayerArea1
          //     break
          //   case '选取图层2':
          //     this.selLayer = this.tileLayerArea2
          //     break
          //   case '选取图层3':
          //     this.selLayer = this.tileLayerArea3
          //     break
          //   case '选取图层4':
          //     this.selLayer = this.tileLayerArea4
          //     break
          //   case '选取图层5':
          //     this.selLayer = this.tileLayerArea5
          //     break
          //   case '选取图层6':
          //     this.selLayer = this.tileLayerArea6
          //     break
          //   default:
          //     this.selLayer = this.tileLayerArea7
          // }
          debugger
        }
      })

      this.map.addControl(this.ctrl)
      // eslint-disable-next-line no-unused-vars
    },
    selectByCorr () {
      let that = this
      that.singleSelectClick = that.map.on('click', function (ev) {
        debugger
        let view = that.map.getView()
        let viewResolution = view.getResolution()
        let wellUrl = ''
        let layerName = sessionStorage.getItem('layerName')
        if (layerName === '选取图层1') {
          wellUrl = that.tileLayerArea1.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层2') {
          wellUrl = that.tileLayerArea2.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层3') {
          wellUrl = that.tileLayerArea3.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层4') {
          wellUrl = that.tileLayerArea4.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层5') {
          wellUrl = that.tileLayerArea5.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层6') {
          wellUrl = that.tileLayerArea6.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }
        if (layerName === '选取图层7') {
          wellUrl = that.tileLayerArea7.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }

        if (layerName === '选取图层7') {
          wellUrl = that.tileLayerArea7.getSource().getFeatureInfoUrl(ev.coordinate, viewResolution, view.getProjection(), {
            'INFO_FORMAT': 'application/json',
            'FEATURE_COUNT': 1
          })
        }

        debugger
        fetch(wellUrl)
          .then(response => response.json())
          .then(data => {
            console.log(data)
            let geojsonFormat = new GeoJSON({defaultDataProjection: 'EPSG:4326'})
            let features = geojsonFormat.readFeatures(data)
            console.log('-->' + features[0].getProperties().geometry)
            if (features != null && features.length > 0) {
              that.map.removeLayer(that.vectorLayer)
              let vectorSource = new VectorSource({
                features: features
              })
              that.vectorLayer = new VectorLayer({
                name: '选取',
                source: vectorSource,
                opacity: 0.6,
                style: new Style({
                  image: new CircleStyle({
                    radius: 7,
                    stroke: new Stroke({
                      width: 3,
                      color: '#3166ea'
                    }),
                    fill: new Fill({
                      color: '#3166ea'
                    })
                  }),
                  fill: new Fill({// 填充样式
                    color: '#3166ea'
                  }),
                  stroke: new Stroke({
                    color: '#3166ea',
                    width: 8
                  })
                })
              })
              that.map.addLayer(that.vectorLayer)
            }
            let arr = data.features[0].geometry.coordinates[0]
            that.searchDatasetByCorrd({'corrdinates': arr[0]})
            console.log(arr[0])
          })
      })
    },
    async searchDatasetByCorrd (param) {
      let result = await searchDataset(param)
      if (result.data.code === 0) {
        this.dataSetList = result.data.data
        this.$EventBus.$emit('dataSetList', this.dataSetList)
        this.$EventBus.$emit('corrd', param)
      }
    }
  }
}
</script>

<style>
#tdMap {
  position: absolute;
  height: 100%;
  width: 100%;
}
</style>
