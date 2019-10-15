import React, { Component } from 'react'
import { httpPost } from '../../tw/com/yeol/common/http/HttpService'
import Grid from '@material-ui/core/Grid';
import { CardComponent } from './CardComponent';
import { Dropdown } from '../../component/Dropdown';
import { DropdownItem } from '../../component/modal/DropdownItem';
import Hidden from '@material-ui/core/Hidden';
import Pagination from '../../component/Pagination';

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
      isShowMenu: false
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
    httpPost({}, succFunction, failFunction, '')

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
      <div>

        <Grid container>

          <Grid container item xs={12} md={10} spacing={1}>
            <Grid container item xs={12} direction="row" justify="flex-end" alignItems="center">
              <Dropdown eventFunction={this.handleChange} obj={sorted}></Dropdown>
            </Grid>

            <Grid item xs={12}>
              <CardComponent></CardComponent>
            </Grid>

            <Grid item xs={12}>
              <CardComponent></CardComponent>
            </Grid>

            <Grid item xs={12}>
              <CardComponent></CardComponent>
            </Grid>
            <Grid item xs={12}>
              <CardComponent></CardComponent>
            </Grid>

            <Pagination></Pagination>
          </Grid>


          <Hidden smDown implementation="css">
            <Grid container item xd={2} spacing={1}>
              留...
          </Grid>
          </Hidden>

        </Grid>


      </div>
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