import React, { useState } from 'react';
import Box from '@material-ui/core/Box';
import { makeStyles } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';
import { useMediaQuery } from '@material-ui/core';
import { Route } from 'react-router-dom';
import routes from './tw/com/yeol/common/routes';
import theme from './theme/index';
import { HeaderComponent } from './pagecode/Header';
import FooterComponent from './pagecode/Footer';
import Sidebar from './component/siderbar/Siderbar';
import clsx from 'clsx';

function App() {

  /** 設定css. */
  const classes = useStyles();

  const [openSidebar, setOpenSidebar] = useState(false);

  const isDesktop = useMediaQuery(theme.breakpoints.up('lg'), {
    defaultMatches: true
  });

  const shouldOpenSidebar = isDesktop ? true : openSidebar;

  const handleSidebarOpen = () => {
    setOpenSidebar(true);
  };

  const handleSidebarClose = () => {
    setOpenSidebar(false);
  };

  return (
    <ThemeProvider theme={theme}>

      <div className={clsx({
        [classes.root]: true,
        [classes.shiftContent]: isDesktop
      })}>

        <HeaderComponent onSidebarOpen={handleSidebarOpen} />
        <Sidebar
          onClose={handleSidebarClose}
          open={shouldOpenSidebar}
          variant={isDesktop ? 'persistent' : 'temporary'}
        />
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
          <FooterComponent />
        </main>

      </div>

    </ThemeProvider>
  );
}

export default App;


const useStyles = makeStyles(theme => ({
  root: {
    height: '100%',
  },
  shiftContent: {
    paddingLeft: 240
  },
  toolbar: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  },
}));
