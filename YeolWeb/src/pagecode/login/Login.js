import React from 'react';
import { withRouter } from 'react-router'
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import { NavLink } from 'react-router-dom';
import { HttpService } from '../../tw/com/yeol/common/http/HttpService';
import { useAuthorizedDispatch, useAuthorizedState } from '../../tw/com/yeol/context/Context';
import Chip from '@material-ui/core/Chip';
import FaceIcon from '@material-ui/icons/Face';
const Login = (props) => {
  const classes = useStyles();
  const [values, setValues] = React.useState({ name: '', password: '' });
  const { loading, errorMessage } = useAuthorizedState();
  const dispatch = useAuthorizedDispatch();

  const change = (e) => {
    const { name, value } = e.target
    setValues({ ...values, [name]: value });
  }

  const doLogin = () => {
    dispatch({ type: 'PRE_LOGIN' });

    const body = {
      'name': values['name'],
      'password': values['password']
    }

    const succ = (response) => {
      dispatch({ type: 'LOGIN', payload: response.data });
      localStorage.setItem('currentUser', JSON.stringify(response.data));
      props.history.push('/')
    }

    const fail = (ex) => {
      dispatch({ type: 'PRE_LOGIN' });
    }

    HttpService.httpPost(body, succ, fail, '/api/account/username/login');
  }

  return (
    <Grid container component="main" className={classes.root}>
      <CssBaseline />
      <Grid item xs={false} sm={4} md={7} className={classes.image} />
      <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            帳號登入
          </Typography>
          <form className={classes.form} noValidate>
            <TextField
              onChange={change}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="name"
              autoComplete="email"
              autoFocus
            />
            <TextField
              onChange={change}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />

            <Button
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              onClick={doLogin}
              disabled={loading}
            >
              登入
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <NavLink to="/signup" variant="body2">
                  {"Don't have an account? Sign Up"}
                </NavLink>
              </Grid>
            </Grid>
            <Box mt={5}>
              <Chip
                icon={<FaceIcon />}
                label="Deletable secondary"
                color="secondary"
                variant="outlined"
              />
              {/* <Copyright /> */}
            </Box>
          </form>
        </div>
      </Grid>
    </Grid >
  )
};

export default withRouter(Login);

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100vh',
  },
  image: {
    backgroundImage: 'url(https://source.unsplash.com/random)',
    backgroundRepeat: 'no-repeat',
    backgroundColor:
      theme.palette.type === 'light' ? theme.palette.grey[50] : theme.palette.grey[900],
    backgroundSize: 'cover',
    backgroundPosition: 'center',
  },
  paper: {
    margin: theme.spacing(8, 4),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));
