import React from 'react';
import './App.css';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  ul: {

  }
}));

function App() {
  const classes = useStyles();
  return (
    <div className="App">
      <header className="App-header">
        <AppBar>
          <Toolbar>
            <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              YOEL
          </Typography>
            <Button color="inherit">Login</Button>
            <Button color="inherit">Logout</Button>
          </Toolbar>
        </AppBar>
      </header>

      <div className="main">
        main...
      </div>
    </div>
  );
}

export default App;
