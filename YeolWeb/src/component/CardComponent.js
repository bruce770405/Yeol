import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import Container from '@material-ui/core/Container';

/**
 * 畫面卡片式元件
 * TODO
 * @author BruceHsu
 * @version
 * @since
 * @see
 */
export const CardComponent = (props) => {

  const classes = useStyles();

  return (
    <Card className={classes.card}>
      <Container fixed>
        {props.children}
      </Container>
    </Card >
  );
}




const useStyles = makeStyles(theme => ({
  card: {
    // minWidth: 200,
    padding: 5,
    minHeight: 50,
  }
}));