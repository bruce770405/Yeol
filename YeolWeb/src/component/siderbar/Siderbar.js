import React from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';
import { Drawer } from '@material-ui/core';
import DashboardIcon from '@material-ui/icons/Dashboard';
import PeopleIcon from '@material-ui/icons/People';
import ShoppingBasketIcon from '@material-ui/icons/ShoppingBasket';
import TextFieldsIcon from '@material-ui/icons/TextFields';
import ImageIcon from '@material-ui/icons/Image';
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import SettingsIcon from '@material-ui/icons/Settings';
import LockOpenIcon from '@material-ui/icons/LockOpen';
import { Profile, SidebarNav } from './components';

const useStyles = makeStyles(theme => ({
  drawer: {
    width: 240,
    [theme.breakpoints.up('lg')]: {
      marginTop: 64,
      height: 'calc(100% - 64px)'
    }
  },
  root: {
    backgroundColor: theme.palette.white,
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
    padding: theme.spacing(2)
  },
  divider: {
    margin: theme.spacing(2, 0)
  },
  nav: {
    marginBottom: theme.spacing(2)
  }
}));


const Sidebar = props => {
  const { open, variant, onClose, className, ...rest } = props;

  const classes = useStyles();
  
  const pages = [
    {
      title: '熱門討論區',
      href: '/dashboard',
      expansion: true,
      open:false,
      icon: <DashboardIcon />
    },
    {
      title: '校園',
      href: '/users',
      expansion: true,
      icon: <PeopleIcon />
    },
    {
      title: '生活',
      href: '/products',
      expansion: true,
      icon: <ShoppingBasketIcon />
    },
    {
      title: '遊戲',
      href: '/sign-in',
      expansion: true,
      icon: <LockOpenIcon />
    },
    {
      title: 'Typography',
      href: '/typography',
      expansion: true,
      icon: <TextFieldsIcon />
    },
    {
      title: 'Icons',
      href: '/icons',
      expansion: true,
      icon: <ImageIcon />
    },
    {
      title: 'Account',
      href: '/account',
      expansion: false,
      icon: <AccountBoxIcon />
    },
    {
      title: 'Settings',
      href: '/settings',
      expansion: false,
      icon: <SettingsIcon />
    }
  ];

  return (
    <Drawer
      anchor="left"
      classes={{ paper: classes.drawer }}
      onClose={onClose}
      open={open}
      variant={variant}
    >
      <div
        {...rest}
        className={clsx(classes.root, className)}
      >
        {/* <Profile />
        <Divider className={classes.divider} /> */}
        <SidebarNav
          className={classes.nav}
          pages={pages}
        />
      </div>
     
    </Drawer>
  );
};

Sidebar.propTypes = {
  className: PropTypes.string,
  onClose: PropTypes.func,
  open: PropTypes.bool.isRequired,
  variant: PropTypes.string.isRequired
};

export default Sidebar;
