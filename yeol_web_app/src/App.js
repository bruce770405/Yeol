import React from 'react';
import Box from '@material-ui/core/Box';
import { makeStyles } from '@material-ui/core/styles';

import { Route } from 'react-router-dom';
import routes from './tw/com/yeol/common/routes';
import CssBaseline from '@material-ui/core/CssBaseline';
import { HeaderComponent } from './pagecode/Header';


function App() {

  /** 設定css. */
  const classes = useStyles();

  return (
    <div className="App">

      <div className={classes.root}>
        <CssBaseline />
        
        <HeaderComponent/>

        <main className={classes.content}>
          <div className={classes.toolbar} />
          <Box my={1}>
            {routes.map((route, i) => {
              const { path, exact, routes } = route;
              return (
                <Route
                  key={i}
                  path={path}
                  exact={exact}
                  render={(routeProps) => (
                    <route.component routes={routes} {...routeProps} />
                  )}
                />
              );
            })}
          </Box>
        </main>
      </div>
    </div>
  );
}

export default App;


const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      display: 'none',
    },
  },
  toolbar: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  },
}));
