import React, { useState } from 'react';
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
import { memberReducer, memberInitialState } from './pagecode/login/Reducer';
import { ContextStore } from './tw/com/yeol/common/context';

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

  const [state, dispatch] = React.useReducer(memberReducer, memberInitialState);

  return (
    <ContextStore.Provider
      value={{
        member: state.member,
        datas: state.datas,
        memberDispatch: dispatch
      }}
    >
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
              const { path, exact } = route;
              return (
                <Route
                  key={i}
                  path={path}
                  exact={exact}
                  render={(routeProps) => (
                    <route.component {...routeProps} />
                  )}
                />
              );
            })}
          </Container>
          <FooterComponent />
        </div>

      </ThemeProvider >
    </ContextStore.Provider>
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
    marginTop: theme.spacing(8),
    marginBottom: theme.spacing(2),
  },
  shiftContent: {
    paddingLeft: 240
  }
}));
