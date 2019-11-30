import React from 'react';
import Box from '@material-ui/core/Box';
import { makeStyles } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';

import { Route } from 'react-router-dom';
import routes from './tw/com/yeol/common/routes';
import { HeaderComponent } from './pagecode/Header';
import theme from './theme/index';


function App() {

  /** 設定css. */
  const classes = useStyles();

  return (
    <ThemeProvider theme={theme}>

      <div className={classes.root}>

        <HeaderComponent />

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
    </ThemeProvider>
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
