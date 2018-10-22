var SceneLightManager = require('SceneLightManager');
cc.Class({
    extends: cc.Component,

    properties: {
        //z深度
        z: 0,
        //光照颜色
        lightColor: new cc.Color(0.5, 0.5, 0.5),
        //光照范围
        lightWidth: 500,
        //漫反射加强系数
        diffNum: 4,
    },

    // use this for initialization
    onLoad: function () {
        SceneLightManager.addLight(this.node.x, this.node.y, this.z, this.lightColor, this.lightWidth, this.node, this.diffNum);
    },

    // called every frame
    update: function (dt) {

    },
});