import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { Dropdown } from '../../component/Dropdown';
import { DropdownItem } from '../../component/modal/DropdownItem';
import Hidden from '@material-ui/core/Hidden';
import { HomeCard } from './component/HomeCard';
import { SubList } from './component/SubList';
import Pagination from "material-ui-flat-pagination";
import { HttpService } from '../../tw/com/yeol/common/http/HttpService';

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
      data: '',
      sortMethod: '',
      isShowMenu: false,
      offset: 0
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
    }
    // fail
    let failFunction = error => {
      console.log('fail ' + error)
      // do something error handle
    }
    console.log('componentDidMount post');
    HttpService.httpPost({}, succFunction, failFunction, '')

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

  render() {

    const sorted = this.genSortedItems()

    return (
     
        <Grid container spacing={4}>

          <Grid container item xs={12} md={8} spacing={1}>
            <Grid container item direction="row" justify="flex-end" alignItems="center">
              <Dropdown eventFunction={this.handleChange} obj={sorted}></Dropdown>
            </Grid>

            <Grid item xs={12}>
              <HomeCard />
            </Grid>

            <Grid item xs={12}>
              <HomeCard />
            </Grid>

            <Grid item xs={12}>
              <HomeCard />
            </Grid>
            <Grid item xs={12}>
              <HomeCard />
            </Grid>

            <Grid item xs={12} >
              <Pagination
                limit={10}
                offset={this.state.offset}
                total={100}
                onClick={(e, offset) => this.handleClick(offset)}
              />
            </Grid>

          </Grid>


          <Hidden smDown implementation="css">
            <Grid >
              <SubList></SubList>
            </Grid>
          </Hidden>

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