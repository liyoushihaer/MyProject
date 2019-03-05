﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MeshTool {
    public class MeshManager {
        private static MeshManager _instance = null;

        public struct MeshData {
            public Mesh mesh;
            public Material material;
        }

        public MeshManager () {

        }

        public static MeshManager GetInstance () {
            if (_instance == null) {
                _instance = new MeshManager ();
            }

            return _instance;
        }

        public void CombineNormalMesh (string key, GameObject[] gameObjects, GameObject gameObject) {
            List<MeshFilter> meshFilters = new List<MeshFilter> ();
            List<MeshRenderer> meshRenderers = new List<MeshRenderer> ();
            for (int i = 0; i < gameObjects.Length; i++) {
                meshFilters.Add (gameObjects[i].GetComponent<MeshFilter> ());
                meshRenderers.Add (gameObjects[i].GetComponent<MeshRenderer> ());
            }
            MeshData meshData = CombineNormalMesh (key, meshFilters.ToArray (), meshRenderers.ToArray ());
            if (meshData.mesh != null) {
                MeshFilter meshFilter = gameObject.GetComponent<MeshFilter> ();
                if (meshFilter == null) {
                    meshFilter = gameObject.AddComponent<MeshFilter> ();
                }
                meshFilter.sharedMesh = meshData.mesh;
            }
            if (meshData.material != null) {
                MeshRenderer meshRenderer = gameObject.GetComponent<MeshRenderer> ();
                if (meshRenderer == null) {
                    meshRenderer = gameObject.AddComponent<MeshRenderer> ();
                }
                meshRenderer.sharedMaterial = meshData.material;
            }
        }

        //合并网格
        //非SkinnedMeshRenderer
        public MeshData CombineNormalMesh (string key, MeshFilter[] meshFilters, MeshRenderer[] meshRenderers) {
            MeshData meshData = new MeshData ();
            if (meshFilters.Length != meshRenderers.Length) {
                return meshData;
            }
            CombineInstance[] combine = new CombineInstance[meshFilters.Length];
            Mesh newMesh = new Mesh ();
            //先合并图片
            Material newMaterial = MaterialManager.GetInstance ().combinelMaterial (key, meshRenderers);
            if (newMaterial == null) {
                return meshData;
            }
            TextureManager.TextureData textureData = TextureManager.GetInstance ().CombineTexture2D (key, meshRenderers);
            newMaterial.SetTexture ("_MainTex", textureData.texture);
            //合并网格
            for (int i = 0; i < meshFilters.Length; i++) {
                Rect rect = textureData.rects[textureData.indexs[i]];
                Mesh meshCombine = meshFilters[i].sharedMesh;
                Vector2[] uvs = new Vector2[meshCombine.uv.Length];
                //把网格的uv根据贴图的rect刷一遍
                for (int j = 0; j < uvs.Length; j++) {
                    uvs[j].x = rect.x + meshCombine.uv[j].x * rect.width;
                    uvs[j].y = rect.y + meshCombine.uv[j].y * rect.height;
                }
                meshCombine.uv = uvs;
                combine[i].mesh = meshCombine;
                combine[i].transform = meshFilters[i].transform.localToWorldMatrix;
                //meshFilters[i].gameObject.SetActive(false);
            }
            newMesh.CombineMeshes (combine, true, true);
            meshData.mesh = newMesh;
            meshData.material = newMaterial;
            return meshData;
        }
    }
}