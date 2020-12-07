import React, { useState } from 'react';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
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
        <Container main className={classes.main}>
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
        </Container>
        <FooterComponent />
      </div>

    </ThemeProvider >
  );
}

export default App;

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    minHeight: '100vh',
  },
  main: {
    flexGrow: 1,
    marginTop: theme.spacing(12),
    marginBottom: theme.spacing(2),
  },
  shiftContent: {
    paddingLeft: 240
  }
}));
