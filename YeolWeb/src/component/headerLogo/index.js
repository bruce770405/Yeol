import React from 'react';
import { Box } from '@material-ui/core';
import { Fragment } from 'react';
import { NavLink } from 'react-router-dom';

const HeaderLogo = () => {
  return (
    <Fragment>
      <div className='app-header-logo'>
        <Box className="header-logo-wrapper">
          <NavLink to="/" activeStyle={{ textDecorationLine: "none", fontWeight: "bold", color: "white" }}>
            {/* <IconButton
              color="primary"
              size="medium"
              className="header-logo-wrapper-btn">
              <img
                className="app-header-logo-img"
                alt="Carolina React Admin Dashboard with Material-UI Free"
              // src={projectLogo}
              />
            </IconButton> */}
          </NavLink>
          <Box className="header-logo-text">Yeol</Box>
        </Box>
      </div>
    </Fragment>
  );
};

export default HeaderLogo;
