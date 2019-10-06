
 /**
   * http post.
   * @param {*} succ 成功回應的function
   * @param {*} fail 失敗回應的function
   * @param {*} uri 資源uri
   */
  export function httpPost(succ, fail, uri) {

    fetch('http://localhost:8080/' + uri, {
        method: 'POST',
        headers: new Headers({
          'Content-Type': 'text/json'
        })
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

    fetch('http://localhost:8080/' + uri, {
        method: 'GET'
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