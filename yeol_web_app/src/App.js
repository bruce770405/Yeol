import React from 'react';
import Home from './pagecode/home/HomeComponent';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import { ScrollTop } from './component/Scroll';
import { Header } from './pagecode/Header';


function App() {

  return (
    <div className="App">

      <Header />

      <Container>
        <Box my={2}>
          < Home></Home>
        </Box>
      </Container>

      <ScrollTop />
      
    </div>
  );
}

export default App;
