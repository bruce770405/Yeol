import { endpoint, isDebug } from '../../../../../config';


/**
 * 共用驗證參數.
 */
const commonParams = {};

/**
 * http post.
 * @param {*} succ 成功回應的function
 * @param {*} fail 失敗回應的function
 * @param {*} methodUri 資源uri
 */
export function httpPost(params, succ, fail, methodUri) {
  let body = convertBody(params);

  fetch(endpoint + methodUri, {
    method: 'POST',
    headers: new Headers({
      'Content-Type': 'application/json; charset=utf-8'
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
export function httpGet(succ, fail, uri) {

  fetch(endpoint + uri, {
    method: 'GET',
    headers: new Headers({
      'Content-Type': 'application/json; charset=utf-8'
    }),
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
 * 將傳入參數轉換成json body.
 * @param {*} params 參數
 */
function convertBody(params) {
  return isDebug ? undefined : JSON.stringify(Object.assign({}, params, commonParams));
}