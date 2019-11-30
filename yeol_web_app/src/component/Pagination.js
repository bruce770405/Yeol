import React, { Component } from "react";
import { NavLink } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';


const Pagination = props => {
  /**
   * 創建導覽頁的頁碼.
   */
  const createPagesItem = (pages, nowPage) => {
    let children = [];
    for (let i = 0; i < pages; i++) {
      children.push(
        <NavLink
          prefetch
          href={{
            pathname: "items",
            query: { page: nowPage - 1 }
          }}
        >
          <a aria-current={nowPage === i + 1}
            class={nowPage === i + 1 ? "active item" : "item"}
            aria-disabled={nowPage <= 1}
            type="pageItem"
          >
            {i + 1}
          </a>
        </NavLink>
      );
    }
    return children;
  };

  const count =  202; //data.itemsConnection.aggregate.count;
  const pages =   202/10; //Math.ceil(count / perPage);
  const nowPage = props.page;

  return (
          <div className="classes.main">
            <div
              aria-label="Pagination Navigation"
              role="navigation"
              className="ui pagination menu"
            >
              {/* <Head>
                <title>page {nowPage}</title>
              </Head> */}
              <NavLink
                prefetch
                href={{
                  pathname: "items",
                  query: { page: nowPage - 1 }
                }}
              >
                <a
                  aria-current="false"
                  className="item"
                  aria-disabled={nowPage <= 1}
                >
                  ⟨
                </a>
              </NavLink>

              {/* 頁數 */}

              {createPagesItem(pages, nowPage)}

              <NavLink
                prefetch
                href={{
                  pathname: "items",
                  query: { page: nowPage + 1 }
                }}
              >
                <a
                  aria-current="false"
                  className="item"
                  // aria-disabled={nowPage >= pages}
                >
                  ⟩
                </a>
              </NavLink>

            </div>
          </div>
  );
};

export default Pagination;


/** css. */
const classes = makeStyles(theme => ({
  main: {
  textAlign: 'center',
  display: 'inline-grid',
  // gridTemplateColumns: repeat(4, auto),
  alignItems: 'stretch',
  justifyContent: 'center',
  alignContent: 'center',
  margin: 10,
  /* border: 1px solid ${props => props.theme.lightgrey}; */
  /* border-radius: 10px; */
  // & > * {
  //   margin: 0;
  //   padding: 0px;
  //   /* border-right: 1px solid ${props => props.theme.lightgrey}; */
  //   &:last-child {
  //     /* border-right: 0; */
  //   }
  // }
  // a[aria-disabled='true'] {
  //   /* color: grey; */
  //   pointer-events: none;
  // }
  }
}));