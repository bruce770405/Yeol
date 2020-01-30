import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { Dropdown } from '../../component/Dropdown';
import { DropdownItem } from '../../component/modal/DropdownItem';
import { SubList } from './component/SubList';
import Pagination from "material-ui-flat-pagination";
import { HttpService } from '../../tw/com/yeol/common/http/HttpService';
import { ArticleList } from './component/HomeCard';

/**
 * 首頁 component.
 * @author BruceHsu
 * @version
 * @since 
 * @see
 */
class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {
      data: null,
      sortMethod: '',
      isShowMenu: false,
      current: 0,
      total: 0
    }
  }

  /**
   * life cycle.
   */
  componentDidMount() {
    // succ
    let succFunction = data => {
      console.log('succ ' + data)
      // do something data
      this.setState({
        data
      })
    }
    // fail
    let failFunction = error => {
      console.log('fail ' + error)
      // do something error handle
    }
    console.log('componentDidMount post');
    //獲取第1頁內容
    HttpService.httpPost({}, succFunction, failFunction, '/page/0')
  }

  /**
   * 異動排序方法.
   */
  handleChange = value => {
    this.setState(oldValues => ({
      ...oldValues,
      [value]: value,
    }));
  };

  /**
   * 創建sorted下拉選單
   */
  genSortedItems = () => {
    let item1 = new DropdownItem();
    item1.val = 1
    item1.name = '按日期排序'
    let item2 = new DropdownItem();
    item2.val = 2
    item2.name = '按文章標題排序'
    return { item1, item2 }
  }

  /**
   * page click.
   */
  handleClick = (e, offset) => {
    console.log(e, offset);
  }

  render() {

    const sorted = this.genSortedItems()

    return (

      <Grid container spacing={4}>
        <Grid
          item
          lg={8}
          md={12}
          xl={9}
          xs={12}
          spacing={2}
        >
          <Grid container direction="row" justify="flex-end" alignItems="center">
            <Dropdown eventFunction={this.handleChange} obj={sorted}></Dropdown>
          </Grid>

          <ArticleList data={this.state.data} />

          <Grid container direction="row" justify="center" alignItems="flex-end">
            <Pagination
              limit={10}
              offset={this.state.current}
              total={100}
              onClick={(e, offset) => this.handleClick(e, offset)}
            />
          </Grid>

        </Grid>


        <Grid
          item
          lg={4}
          md={6}
          xl={3}
          xs={12}
        >
          {/* <Hidden smDown implementation="css"> */}
          <SubList />
          {/* </Hidden> */}
        </Grid>
      </Grid>


    )
  };
}

export default Home;


// {({ data, error, loading }) => {
//   if (loading) return <p>Loading...</p>;
//   if (error) return <p>Error : {error.errorMessage} </p>;
//   console.log(data);
//   return (
//     <ItemList>
//       {data.items.map(item => (
//         <Item item={item} />
//       ))}
//     </ItemList>
//   );
// }}