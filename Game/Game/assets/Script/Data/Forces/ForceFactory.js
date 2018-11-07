/**
 * 割据势力数据工厂
 */
var outModule = {};
var local = {};
var CityFactory = require('CityFactory');

/**
 * @param force 为割据数据绑定相应的函数
 */
local.buildFunc = function (force) {

};

/**
 * @param saveData 存储的数据
 */
local.createOneForceBySaveData = function (saveData) {

    local.buildFunc(this);
};

/**
 * @param forceId 割据势力id
 * 新建一个割据势力数据
 */
local.createOneForce = function (forceId) {

    this._id = parseInt(forceId);
    //配置数据
    var jsonData = g_JsonDataTool.getDataById('_table_force_force', forceId);
    //势力名字
    this._name = jsonData.name;

    local.buildFunc(this);

    //割据势力所属的城市
    this._cityArr = ('' + jsonData.city).split(',').map((cityId) => {
        return CityFactory.createOneCity(cityId, undefined);
    });
};

/**
 * @param cityId
 * @param saveData 
 */
outModule.createOneForce = (forceId, saveData) => {
    if (saveData) {
        return new local.createOneForceBySaveData(saveData);
    }
    return new local.createOneForce(forceId);
};

module.exports = outModule;