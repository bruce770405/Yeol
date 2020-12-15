import React from 'react';
import { fade, makeStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import InputBase from '@material-ui/core/InputBase';
import SearchIcon from '@material-ui/icons/Search';
import { NavLink } from 'react-router-dom';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import { withRouter } from 'react-router-dom';
import HeaderLogo from '../component/headerLogo/index';
import AppBar from '@material-ui/core/AppBar';
import Avatar from '@material-ui/core/Avatar';
import { useAuthorizedState } from '../tw/com/yeol/context/Context';

/**
 * app Header.
 * @author BruceHsu
 * @version
 * @since
 * @see
 */
const Header = (props) => {
  /** 設定css. */
  const classes = useStyles();
  const { user, token } = useAuthorizedState();
  const { onSidebarOpen } = props;
  console.log(user)
  return (
    <>
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={onSidebarOpen}
            className={classes.menuButton}
          >
            <MenuIcon />
          </IconButton>


          <Typography
            variant="h4"
            className={classes.brandText}
            display="inline"
            color="primary"
          >

          </Typography>
          <Typography className={classes.title} variant="h6" noWrap>
            <HeaderLogo />
          </Typography>

          <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div>


          {
            (user && token) ?
              <>
                <NavLink to="/member" style={{ textDecorationLine: "none", color: "white" }} activeStyle={{ textDecorationLine: "none", color: "white" }}>
                  <IconButton color="inherit">
                    <Avatar src="/static/images/avatar/1.jpg" alt={user} />
                  </IconButton>
                </NavLink>
                <Button color="inherit" >
                  登出
                </Button>
              </>
              :

              <>
                <NavLink to="/signup" style={{ textDecorationLine: "none", color: "white" }} activeStyle={{ textDecorationLine: "none", color: "white" }}>
                  <Button color="inherit" >
                    註冊
                  </Button>
                </NavLink>
                <NavLink to="/login" style={{ textDecorationLine: "none", color: "white" }} activeStyle={{ textDecorationLine: "none", color: "white" }}>
                  <Button color="inherit" >
                    登入
                   </Button>
                </NavLink>
              </>
          }

        </Toolbar>
      </AppBar>
    </>
  );
}

export const HeaderComponent = withRouter(props => <Header {...props} />);

const drawerWidth = 240;

const useStyles = makeStyles(theme => ({
  drawer: {
    [theme.breakpoints.up('sm')]: {
      width: drawerWidth,
      flexShrink: 0,
    },
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      display: 'none',
    },
  },
  toolbar: theme.mixins.toolbar,
  drawerPaper: {
    width: drawerWidth
  },
  title: {
    fontSize: '2rem',
    marginLeft: '1rem',
    position: 'relative',
    zIndex: '2',
    flexGrow: 1,
    paddingLeft: 30,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
      textDecorationLine: 'none',
    },
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    width: theme.spacing(7),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 7),
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: 120,
      '&:focus': {
        width: 200,
      },
    },
  },
}));
