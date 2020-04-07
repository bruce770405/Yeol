import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/styles';
import { Typography, Link } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(4)
  }
}));

const FooterComponent = props => {
  const { className, ...rest } = props;

  const classes = useStyles();

  return (
    <div
      {...rest}
      className={clsx(classes.root, className)}
    >
      <Typography variant="body1">
        &copy;{' '}
        <Link
          component="a"
          href="https://devias.io/"
          target="_blank"
        >
          Yeol.com
        </Link>
        Copyright © 2020 Yeol com. All rights reserved
      </Typography>
      <Typography variant="caption">
        Created with love for the environment. By designers and developers who
        love to work together in offices!
      </Typography>
    </div>
  );
};

FooterComponent.propTypes = {
  className: PropTypes.string
};

export default FooterComponent;
