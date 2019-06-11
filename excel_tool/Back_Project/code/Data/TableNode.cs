﻿using System.Collections.Generic;

//存储多个Row节点
namespace Back_Project.code.Data
{
    class TableNode
    {
        //工作表的名字
        private string _sheetName;
        //存储key数据的row节点，不能存在空节点
        private RowNode _keyRowNode = null;
        private List<RowNode> _rowNodeList;
        //根据_translate的key和output决定输出的数据
        TranslateFileData.TranslateData _translateData;
        public TableNode(string sheetName, string fileName)
        {
            if (code.GlobalData.translateDic.ContainsKey(fileName))
            {
                 //拿translate中配置的数据表格的新名字
                _translateData = code.GlobalData.translateDic[fileName].GetTranslateData(sheetName);
                sheetName = _translateData.newFileName;
            }
            _sheetName = sheetName;
            _rowNodeList = new List<RowNode>();
        }

        public string getSheetName()
        {
            return _sheetName;
        }

        public TranslateFileData.TranslateData getTranslateData()
        {
            return _translateData;
        }

        //设置key数值行
        //一般是第二行
        public void setKeyRowNode(RowNode keyRowNode)
        {
            _keyRowNode = keyRowNode;
        }

        //获取第index个key对应的字符串
        public string getKeyStrByIndex(int index)
        {
            if (_keyRowNode == null || index >= _keyRowNode.getCellNodeList().Count || _keyRowNode.getCellNodeList()[index] == null)
            {
                return null;
            }
            return _keyRowNode.getCellNodeList()[index].getStr();
        }

        //这个才是数据行
        public void addDataRowNode(RowNode dataRowNode)
        {
            _rowNodeList.Add(dataRowNode);
        }

        public List<RowNode> getRowNodeList()
        {
            return _rowNodeList;
        }
    }
}
