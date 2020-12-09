import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { DropdownItem } from '../../component/modal/DropdownItem';
import { SubList } from './component/SubList';
import Pagination from "material-ui-flat-pagination";
import { HttpService } from '../../tw/com/yeol/common/http/HttpService';
import { ArticleList } from './component/ArticleList';
import WhatshotIcon from '@material-ui/icons/Whatshot';
import { StyleTabbeds, StyleTab } from '../../component/tabbed/StyleTabbed';
import SubscriptionsIcon from '@material-ui/icons/Subscriptions';
import { TabPanel } from '../../component/tabbed/TabPannel';


/**
 * Home component.
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
      tabbedValue: 0,
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
    let succFunction = response => {
      console.log('succ ' + response)
      // do something data
      this.setState({
        data: response.data,
        total: response.totalPages
      })
    }
    // fail
    let failFunction = error => {
      console.log('fail ' + error)
      // do something error handle
    }
    console.log('componentDidMount post');
    //獲取第1頁內容
    HttpService.httpGet(succFunction, failFunction, '/api/messages/page/0')
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
  changePage = (e, offset) => {
    console.log(e, offset);
  }

  render() {

    function a11yProps(index) {
      return {
        id: `full-width-tab-${index}`,
        'aria-controls': `full-width-tabpanel-${index}`,
      };
    }

    // const sorted = this.genSortedItems()

    const { tabbedValue } = this.state;
    return (
      <div style={{ 'marginTop': 32 }}>
        <Grid container spacing={6}>

          <Grid item xs={12} md={8}>

            <StyleTabbeds aria-label="styled" value={tabbedValue} onChange={(event, newValue) => {
              this.setState({
                tabbedValue: newValue
              })
            }}>
              <StyleTab label="熱門主題"  {...a11yProps(0)} icon={<WhatshotIcon />} />
              <StyleTab label="關注討論"   {...a11yProps(1)} icon={<SubscriptionsIcon />} />
            </StyleTabbeds>
            <TabPanel value={tabbedValue} index={0}>
              <ArticleList data={this.state.data} />
              <Grid container direction="row" justify="center" alignItems="center">
                <Pagination limit={10}
                  offset={this.state.current}
                  total={this.state.total}
                  onClick={(e, offset) => this.changePage(e, offset)}
                />
              </Grid>
            </TabPanel>
          </Grid>

          <Grid item xs={12} md={4}>
            <SubList />
          </Grid>
        </Grid >
      </div>
    )
  };
}

export default Home;
