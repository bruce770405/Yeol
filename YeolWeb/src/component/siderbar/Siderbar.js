import React from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';
import { Drawer } from '@material-ui/core';
import DashboardIcon from '@material-ui/icons/Dashboard';
import PeopleIcon from '@material-ui/icons/People';
import VideogameAssetIcon from '@material-ui/icons/VideogameAsset';
import SchoolIcon from '@material-ui/icons/School';
import { SidebarNav } from './components';

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

  let pages = [
    {
      title: '熱門討論',
      href: '/hot',
      expansion: true,
      icon: <DashboardIcon />
    },
    {
      title: '校園',
      href: '/schools',
      expansion: true,
      icon: <PeopleIcon />
    },
    {
      title: '生活',
      href: '/life',
      expansion: true,
      icon: <SchoolIcon />
    },
    {
      title: '遊戲',
      href: '/games',
      expansion: false,
      icon: <VideogameAssetIcon />
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
      <div  {...rest} className={clsx(classes.root, className)}  >
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
