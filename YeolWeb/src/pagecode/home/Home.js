import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid';
import { DropdownItem } from '../../component/modal/DropdownItem';
import { SubList } from './component/SubList';
import Pagination from '@material-ui/lab/Pagination';
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
export default class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {
      data: null,
      tabbedValue: 0,
      isShowMenu: false,
      current: 0,
      total: 0,
      tab1page: 0
    }
  }

  /**
   * life cycle.
   */
  componentDidMount() {
    // succ
    let succFunction = response => {
      console.log('succ ' + JSON.stringify(response))
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
   * 
   * @param {*} event 
   * @param {*} value 
   */
  handleChange = (event, value) => {
    console.log(value)
    this.setState({
      tab1page: value
    })
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

    function a11yProps(index) {
      return {
        id: `full-width-tab-${index}`,
        'aria-controls': `full-width-tabpanel-${index}`,
      };
    }

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
                <Pagination limit={10} shape="rounded"
                  offset={this.state.current}
                  total={this.state.total}
                  onChange={this.handleChange}
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