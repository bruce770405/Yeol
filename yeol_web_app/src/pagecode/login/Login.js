import React, { Component } from 'react'
import { httpPost } from '../../tw/com/yeol/common/http/HttpService'
import Container from '@material-ui/core/Container';
import CommandButton from '../../component/CommandButton';

/**
 * 首頁 component.
 * @author BruceHsu
 * @version
 * @since 
 * @see
 */
class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {

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
    console.log('login componentDidMount post');
    httpPost({}, succFunction, failFunction, 'login')

  }


  render() {


    return (
      <React.Fragment>
        <Container fixed>
          <div>...</div>
          <div>...login...</div>
          <div>...</div>
        <div><CommandButton>按我登入</CommandButton></div>
        </Container>
      </React.Fragment>
    )
  };
}

export default Login;


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