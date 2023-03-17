<template>
  <div id="container" class="box">
    <div id="cesiumContainer"></div>
  </div>
</template>
<script>
import * as Cesium from "cesium/Cesium";
export default {
  name: "cesiumPage",
  data() {
    return {};
  },
  mounted() {
    let viewer = new Cesium.Viewer("cesiumContainer", {
      terrainProvider: Cesium.createWorldTerrain(),
      animation: false //左下角图案
    });

    viewer.scene.globe.depthTestAgainstTerrain = false;

    let gjson = require("@/assets/lj.json");
    Cesium.Math.setRandomNumberSeed(0); //设置随机数种子
    let promise = Cesium.GeoJsonDataSource.load(gjson, {
      clampToGround: true
    }); //geojson面数据
    promise.then(function(dataSource) {
      viewer.dataSources.add(dataSource);
      let entities = dataSource.entities.values;
      let colorHash = {};
      for (let i = 0; i < entities.length; i++) {
        let entity = entities[i];
        let name = entity.name; //geojson里面必须得有一个name属性，entity.name对应
        let color = colorHash[name]; //可以使两个同名要素使用同一种颜色。。。
        if (!color) {
          color = Cesium.Color.fromRandom({
            alpha: 1.0
          });
          colorHash[name] = color;
        }
        entity.polygon.material = color; //设置要素颜色
        entity.polygon.outline = false;
        // entity.billboard.disableDepthTestDistance = Number.POSITIVE_INFINITY; //去掉地形遮挡
        //entity.polygon.classificationType = Cesium.ClassificationType.TERRAIN; //地形
        //entity.polygon.height = 10000; //要素距离地面的高度
        //entity.polygon.extrudedHeight = 1000;
        // entity.polygon.extrudedHeight = Math.floor(
        //   Math.random() * 40000 + 20000
        // ); //20000~60000的随机数，单位是米
      }
    });
    viewer.zoomTo(promise);
    // let privide = new Cesium.WebMapServiceImageryProvider({
    //   url: "http://localhost:8098/geoserver/LjPipeGIS/wms", //服务地址
    //   layers: "LjPipeGIS:BaseBoundaryTown", //服务图层，需要修改成你自己发布的名称
    //   parameters: {
    //     service: "WMS",
    //     format: "image/png",
    //     transparent: true
    //   }
    // });
    // //wms服务
    // let layer = viewer.imageryLayers.addImageryProvider(privide);
    // viewer.camera.flyTo({
    //   destination: Cesium.Cartesian3.fromDegrees(
    //     100.3283867,
    //     26.8411889,
    //     200000
    //   )
    // });
  },
  methods: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
html,
body,
#cesiumContainer {
  width: 100%;
  height: 100%;
}
#app {
  font-family: "Avenir" ， Helvetica， Arial， sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  height: 100%;
}
.box {
  height: 100%;
}
</style>
