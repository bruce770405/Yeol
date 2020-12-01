import { createMuiTheme } from '@material-ui/core';

import palette from './palette';
// import typography from './typography';
// import overrides from './overrides';

const theme = createMuiTheme({
  palette,
  // typography,
  // overrides,
  red: "#FF0000",
  black: "#393939",
  grey: "#3A3A3A",
  lightGrey: "#E1E1E1",
  offwhite: "#EDEDED",
  maxWidth: "1000px",
  bs: "0px 12px 24px 0px rgba(0,0,0,0.09)",
  zIndex: {
    appBar: 1200,
    drawer: 1100
  },
});

export default theme;