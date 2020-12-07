import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

/**
 * 畫面卡片式元件
 * @author BruceHsu
 * @version
 * @since
 */
export const SubList = (props) => {

  const classes = useStyles();

  return (
    <Paper elevation={0} className={classes.sidebarAboutBox}>
      <Typography variant="h6" gutterBottom>
        {'title'}
      </Typography>
      <Typography>{'description description description description description'}</Typography>
    </Paper>
  );
}

const useStyles = makeStyles((theme) => ({
  sidebarAboutBox: {
    padding: theme.spacing(2),
    backgroundColor: theme.palette.grey[200],
  }
}));