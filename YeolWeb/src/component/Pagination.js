// import React, { Component } from 'react';
// import { perPage } from '../../config';
// import PaginationStyle from '../theme/PaginationStyles';


// const Pagination = props => {
//   /**
//    * 創建導覽頁的頁碼.
//    */
//   const createPagesItem = (pages, nowPage) => {
//     let children = [];
//     for (let i = 0; i < pages; i++) {
//       children.push(
//         <Link
//           prefetch
//           href={{
//             pathname: "items",
//             query: { page: nowPage - 1 }
//           }}
//         >
//           <a
//             aria-current={nowPage === i + 1}
//             class={nowPage === i + 1 ? "active item" : "item"}
//             aria-disabled={nowPage <= 1}
//             type="pageItem"
//           >
//             {i + 1}
//           </a>
//         </Link>
//       );
//     }
//     return children;
//   };

//   return (

//     // {
//     //   if(loading) return<p>loading...</p>;
//     // }
//   // const count = data.itemsConnection.aggregate.count;
//   // const pages = Math.ceil(count / perPage);
//   // const nowPage = props.page;

//   // return (
//     <PaginationStyle>
//       <div
//         aria-label="Pagination Navigation"
//         role="navigation"
//         className="ui pagination menu"
//       >
//         {/* <PaginationStyle> */}
//         <Head>
//           <title>page {nowPage}</title>
//         </Head>
//         <Link
//           prefetch
//           href={{
//             pathname: "items",
//             query: { page: nowPage - 1 }
//           }}
//         >
//           <a aria-current="false" className="item" aria-disabled={nowPage <= 1} >
//             ⟨
//                 </a>
//         </Link>

//         {/* 頁數 */}

//         {createPagesItem(pages, nowPage)}

//         <Link
//           prefetch
//           href={{
//             pathname: "items",
//             query: { page: nowPage + 1 }
//           }}
//         >
//           <a
//             aria-current="false"
//             className="item"
//             aria-disabled={nowPage >= pages}
//           >
//             ⟩
//                 </a>
//         </Link>

//         {/* </PaginationStyle> */}
//       </div>
//     </PaginationStyle>
//   // );


//   );
// };

// export default Pagination;
