import React from 'react';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import { ScrollTop } from './component/Scroll';
import { Header } from './pagecode/Header';

import { Route } from 'react-router-dom';
import routes from './tw/com/yeol/common/routes';

function App() {

  return (
    <div className="App">

      <Header />

      <Container>

        <Box my={2}>
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

      </Container>

      <ScrollTop />

    </div>
  );
}

export default App;
