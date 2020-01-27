# API

### 取得全部貼文
#### GET {contextpath}/api/messages/all
取得全部包含已刪除的貼文。

- Response
  ```javascript
  {
    "code": "S0000",
    "msg": "Success",
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```

### 取得熱門貼文
#### GET {contextpath}/api/messages/top-view/{recordNumber}
`{recordNumber}`為要取得的貼文筆數。
查詢前一日到現在依瀏覽數降冪排序的貼文筆數。

- Response
  ```javascript
  {
    "code": "S0000",
    "msg": "Success",
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```

### 取得分頁貼文
#### GET {contextpath}/api/messages/page/{page}
`{page}`為頁次，第一頁為0，第二頁為1，依此類推，預設返回20筆。
查詢依時間降冪排列的貼文

- Response
  ```javascript
  {
    "code": string, //回應代碼
    "msg": "Success", //回應訊息
    "totalRecords", //總筆數
    "totalPages", //總頁數
    "pageSize", //每頁筆數
    "pageNumber", //目前頁次
    "pageRecords", //目前頁次筆數
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```
### 新增貼文
#### POST {contextpath}/api/messages/add
- Request
  ```javascript
  {
    "title": string, //貼文標題/主旨
    "content": string //貼文內容
  }
  ```

- Response 新增後的貼文
  ```javascript
  {
    "code": string, //回應代碼
    "msg": "Success", //回應訊息
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```
### 修改貼文
#### PATCH {contextpath}/api/messages/update
- Request
  ```javascript
  {
    "id": string, //貼文ID
    "title": string, //貼文標題
    "content": string, //貼文內容
    "view": int, //貼文瀏覽數
    "up": int, //貼文推數
    "down": int, //貼文噓數
  }
  ```
- Response 修改後的貼文
  ```javascript
  {
    "code": string, //回應代碼
    "msg": "Success", //回應訊息
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```

### 刪除貼文
#### DELETE {contextpath}/api/messages/delete
- Request
  ```javascript
  {
    "id": string //貼文ID
  }
  ```

- Response 刪除後的貼文
  ```javascript
  {
    "code": string, //回應代碼
    "msg": "Success", //回應訊息
    "data": [
      {
        "id": string, //貼文ID
        "memberId": string, //會員ID，貼文者的ID
        "memberName": string, //會員名稱，貼文者的名稱
        "title": string, //貼文標題
        "content": string, //貼文內容
        "view": int, //瀏覽次數
        "up": int, //推
        "down": int, //噓
        "createMs": long, //貼文建立時間
        "updateMs": long, //貼文最後被修改時間
        "deleteFlag": boolean //是否刪除
      },
      {...}
    ]
  }
  ```
