import React from 'react';
import { endpoint, isDebug } from '../../../../../config';

export class HttpService {


  /**
   * http post.
   * @param {*} succ 成功回應的function
   * @param {*} fail 失敗回應的function
   * @param {*} methodUri 資源uri
   */
  static httpPost(params, succ, fail, methodUri) {

    const token = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')).token : '';

    const body = convertBody(params);

    fetch(endpoint + methodUri, {
      method: 'POST',
      headers: new Headers({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST',
        'Authorization': 'Bearer ' + token
      }),
      body: body
    })
      .then((response) => {
        //ok 代表狀態碼在範圍 200-299
        if (!response.ok) throw new Error(response.statusText)
        return response.json()
      })
      .then((json) => {
        succ(json)
      })
      .catch((error) => {
        //這裡可以顯示一些訊息
        console.error(error)
        fail(error)
      })
  }
  /**
   * http get.
   * @param {*} succ 
   * @param {*} fail 
   * @param {*} uri 
   */
  static httpGet(succ, fail, uri) {
    const token = localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')).token : '';

    fetch(endpoint + uri, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': `Bearer ${token}`
      }
    })
      .then((response) => {
        //ok 代表狀態碼在範圍 200-299
        if (!response.ok) throw new Error(response.statusText)
        return response.json()
      })
      .then((json) => {
        if (json.code && json.code === 'S0000') {
          succ(json)
        } else {
          throw new Error(json.msg)
        }
      })
      .catch((error) => {
        //這裡可以顯示一些訊息
        console.error(error)
        fail(error)
      })

  }

}
/**
 * 共用驗證參數.
 */
const commonParams = {};



/**
 * 將傳入參數轉換成json body.
 * @param {*} params 參數
 */
function convertBody(params) {
  return JSON.stringify(Object.assign({}, params, commonParams));
}