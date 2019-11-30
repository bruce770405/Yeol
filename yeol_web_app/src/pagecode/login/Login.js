import React, { Component } from 'react'
import Container from '@material-ui/core/Container';
import CommandButton from '../../component/CommandButton';
import { HttpService } from '../../tw/com/yeol/common/http/HttpService';
import Button  from '@material-ui/core/Button';

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
    // 將左選單隱藏
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
    HttpService.httpPost({}, succFunction, failFunction, 'login')

  }


  render() {

const loading = false;
    return (
      <React.Fragment>
        <Container>
        <fieldset disabled={loading} aria-busy={loading}>
              <h2>Sign into your account</h2>
              {/* <Error error={error} /> */}
              <label htmlFor="email">
                Email
                <input
                  type="email"
                  name="email"
                  placeholder="email"
                  value={this.state.email}
                  onChange={this.saveToState}
                />
              </label>
              <label htmlFor="password">
                Password
                <input
                  type="password"
                  name="password"
                  placeholder="password"
                  value={this.state.password}
                  onChange={this.saveToState}
                />
              </label>

              <Button variant="outlined">重設</Button>
              <Button variant="contained" color="primary">登入</Button>
            </fieldset>
          
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