import React, {Component} from 'react'
import {httpPost,httpGet} from '../../tw/com/yeol/common/http/HttpService'

/**
 * 首頁 component.
 * @author BruceHsu
 * @version
 * @since 
 * @see
 */
class Home extends Component {

  constructor(props){
    super(props);
    this.state = {
      data: ''
    }
  }

  /**
   * life cycle.
   */
  componentDidMount(){
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
    httpPost(succFunction, failFunction, '')
  }

  render() {


   
    return ( 
      <div className = "main" >
      is a home component.. 
      </div>
    )
  };
}

export default Home;
